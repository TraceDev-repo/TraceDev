package com.agency;

import java.util.Scanner;

/**
 * Confirmation service that uses the console for user input.
 */
public class ConsoleConfirmationService implements IConfirmationService {
    private Scanner scanner;

    public ConsoleConfirmationService() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean requestConfirmation(AgencyOperator operator, String message) {
        System.out.println("Confirmation requested for operator: " + operator.getName());
        System.out.println(message);
        System.out.print("Confirm? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}