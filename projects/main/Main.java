package main; // ✅ This line is required

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Merged three projects =====");
            System.out.println("1. Mission Management System");
            System.out.println("2. Land Management System");
            System.out.println("3. Nursery Management System");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    mission.Main.main(null); // Call mission system
                    break;
                case 2:
                    land.Main.main(null);    // Call land system
                    break;
                case 3:
                    nursery.MainMenu.main(null); // Call nursery system
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (choice != 0);
    }
}
