from .base_agent import BaseAgent
import json
import re
from src.utils import call_LLM, load_config


class Validator(BaseAgent):
    def __init__(self, logging=None):
        super().__init__(
            "Validator",
            "The Validator checks semantic alignment between requirements, design, and code without relying on graph-based traceability.",
        )
        self.logging = logging
        self.memory = {}

    def act(self, **kwargs):
        mode = kwargs.get("mode")
        if mode == "design":
            return self.validate_design(
                requirements=kwargs.get("requirements", {}),
                design_model=kwargs.get("design_model", ""),
            )
        if mode == "code":
            return self.validate_code(
                design_model=kwargs.get("design_model", ""),
                implementation_code=kwargs.get("implementation_code", []),
            )

        if "requirements" in kwargs and "design_model" in kwargs and "implementation_code" not in kwargs:
            return self.validate_design(
                requirements=kwargs.get("requirements", {}),
                design_model=kwargs.get("design_model", ""),
            )
        if "design_model" in kwargs and "implementation_code" in kwargs:
            return self.validate_code(
                design_model=kwargs.get("design_model", ""),
                implementation_code=kwargs.get("implementation_code", []),
            )

        raise ValueError(
            "Validator.act requires either mode='design' with requirements+design_model, "
            "or mode='code' with design_model+implementation_code."
        )

    def _safe_parse_json(self, text: str, fallback: dict) -> dict:
        if not isinstance(text, str):
            return fallback
        candidate = text.strip()
        if candidate.startswith("```json"):
            candidate = candidate[7:]
        if candidate.startswith("```"):
            candidate = candidate[3:]
        if candidate.endswith("```"):
            candidate = candidate[:-3]
        candidate = candidate.strip()
        try:
            return json.loads(candidate)
        except Exception:
            pass
        match = re.search(r"\{[\s\S]*\}", candidate)
        if match:
            try:
                return json.loads(match.group(0))
            except Exception:
                return fallback
        return fallback

    def _normalize_result(self, result: dict) -> dict:
        normalized = {
            "class_diagram": result.get("class_diagram", []),
            "sequence_diagram": result.get("sequence_diagram", []),
            "summary": result.get("summary", ""),
            "repair_suggestions": result.get("repair_suggestions", []),
        }
        if not isinstance(normalized["class_diagram"], list):
            normalized["class_diagram"] = []
        if not isinstance(normalized["sequence_diagram"], list):
            normalized["sequence_diagram"] = []
        if not isinstance(normalized["repair_suggestions"], list):
            normalized["repair_suggestions"] = []
        return normalized

    def _serialize_code(self, implementation_code) -> str:
        if not isinstance(implementation_code, list):
            return str(implementation_code)
        blocks = []
        for file_item in implementation_code:
            filename = file_item.get("filename", "unknown_file")
            content = file_item.get("content", "")
            blocks.append(f"=== FILE: {filename} ===\n{content}")
        return "\n\n".join(blocks)

    def validate_design(self, requirements, design_model: str) -> dict:
        prompt = f"""
You are a software design validator.
You must semantically validate whether the design matches the requirements.
Do not use graph-based traceability.

Input Requirements (JSON-like):
{json.dumps(requirements, ensure_ascii=False)}

Input Design (PlantUML text):
{design_model}

Return ONLY one JSON object in this exact schema:
{{
  "class_diagram": [
    {{
      "requirement": "<requirement id or short requirement text>",
      "issue": "<missing/inconsistent class-level design issue>",
      "evidence": "<brief evidence from design>",
      "suggestion": "<how to fix>"
    }}
  ],
  "sequence_diagram": [
    {{
      "requirement": "<requirement id or short requirement text>",
      "issue": "<missing/inconsistent sequence-level issue>",
      "evidence": "<brief evidence from design>",
      "suggestion": "<how to fix>"
    }}
  ],
  "summary": "<overall semantic alignment summary>",
  "repair_suggestions": [
    "<actionable suggestion 1>",
    "<actionable suggestion 2>"
  ]
}}

Rules:
1) If no issue exists, use empty arrays.
2) Be strict about requirement coverage and behavior consistency.
3) Output JSON only.
"""
        api_key, api_url, model = load_config()
        output = call_LLM(prompt, model, api_key, api_url)
        parsed = self._safe_parse_json(
            output,
            {
                "class_diagram": [],
                "sequence_diagram": [],
                "summary": "Validator returned non-JSON output.",
                "repair_suggestions": [],
            },
        )
        normalized = self._normalize_result(parsed)
        self.memory["design_validation"] = normalized
        if self.logging:
            self.logging.info("Design Validation Output:\n%s", json.dumps(normalized, ensure_ascii=False, indent=2))
        return normalized

    def validate_code(self, design_model: str, implementation_code) -> dict:
        code_text = self._serialize_code(implementation_code)
        prompt = f"""
You are a software implementation validator.
You must semantically validate whether the generated code implements the design intent.
Do not use graph-based traceability.

Input Design (PlantUML text):
{design_model}

Input Generated Code:
{code_text}

Return ONLY one JSON object in this exact schema:
{{
  "class_diagram": [
    {{
      "requirement": "<design element>",
      "issue": "<missing/incorrect class-level implementation>",
      "evidence": "<brief evidence from code>",
      "suggestion": "<how to fix in code>"
    }}
  ],
  "sequence_diagram": [
    {{
      "requirement": "<design interaction>",
      "issue": "<missing/incorrect interaction implementation>",
      "evidence": "<brief evidence from code>",
      "suggestion": "<how to fix in code>"
    }}
  ],
  "summary": "<overall implementation alignment summary>",
  "repair_suggestions": [
    "<actionable suggestion 1>",
    "<actionable suggestion 2>"
  ]
}}

Rules:
1) If no issue exists, use empty arrays.
2) Focus on behavior and interaction semantics, not formatting.
3) Output JSON only.
"""
        api_key, api_url, model = load_config()
        output = call_LLM(prompt, model, api_key, api_url)
        parsed = self._safe_parse_json(
            output,
            {
                "class_diagram": [],
                "sequence_diagram": [],
                "summary": "Validator returned non-JSON output.",
                "repair_suggestions": [],
            },
        )
        normalized = self._normalize_result(parsed)
        self.memory["code_validation"] = normalized
        if self.logging:
            self.logging.info("Code Validation Output:\n%s", json.dumps(normalized, ensure_ascii=False, indent=2))
        return normalized

    def format_report(self, validation_result: dict, mode: str) -> str:
        title = f"[{mode}] LLM Semantic Validation (No Graph)"
        payload = json.dumps(validation_result, ensure_ascii=False, indent=2)
        return f"{title}\n\n{payload}"

