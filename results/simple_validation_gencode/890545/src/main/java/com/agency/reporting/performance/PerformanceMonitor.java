package com.agency.reporting.performance;

import java.util.HashMap;
import java.util.Map;

/**
 * Monitors performance and tracks timeouts.
 */
public class PerformanceMonitor {
    private final Map<String, TimerInfo> timers = new HashMap<>();

    private static class TimerInfo {
        long startTime;
        long timeoutMs;
        boolean stopped;
    }

    public void startTimer(String timerId, long timeoutMs) {
        TimerInfo timer = new TimerInfo();
        timer.startTime = System.currentTimeMillis();
        timer.timeoutMs = timeoutMs;
        timer.stopped = false;
        timers.put(timerId, timer);
    }

    public long checkTimer(String timerId) {
        TimerInfo timer = timers.get(timerId);
        if (timer == null) return -1;
        if (timer.stopped) return 0;
        long elapsed = System.currentTimeMillis() - timer.startTime;
        return Math.max(0, timer.timeoutMs - elapsed);
    }

    public void stopTimer(String timerId) {
        TimerInfo timer = timers.get(timerId);
        if (timer != null) {
            timer.stopped = true;
        }
    }

    public boolean isTimeout(String timerId) {
        TimerInfo timer = timers.get(timerId);
        if (timer == null || timer.stopped) return false;
        long elapsed = System.currentTimeMillis() - timer.startTime;
        return elapsed > timer.timeoutMs;
    }
}