package org.example;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    /**
     * The main method serves as the entry point for the rental agency application.
     * It provides a flow for users to interact with the system, including registering,
     * selecting a vehicle category, and finalizing the vehicle rental process.
     *
     * @param args the command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Create a rental agency
        RentalAgency rentalAgency = new RentalAgency();

        // Populate the rental agency with vehicles
        rentalAgency.addVehicle(new Car("C001", "Toyota Corolla", 30.0, true));
        rentalAgency.addVehicle(new Car("C002", "Honda Civic", 35.0, true));
        rentalAgency.addVehicle(new Truck("T001", "Ford F-150", 50.0, true, 10.0));
        rentalAgency.addVehicle(new Truck("T002", "Ford F-250", 60.0, true, 15.0));
        rentalAgency.addVehicle(new Motorcycle("M001", "Yamaha R15", 20.0, true, true));
        rentalAgency.addVehicle(new Motorcycle("M002", "Harley Davidson", 40.0, true, false));

        // Display all available vehicles
        System.out.println("Welcome to the rental agency!");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        // Step 1: Collect customer details
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your driver's license number:");
        String licenseNumber = scanner.nextLine();

        System.out.println("Is your license valid? (true/false):");
        boolean licenseValid = scanner.nextBoolean();

        try {
            // Create a new customer
            Customer customer = new Customer(name, licenseNumber, licenseValid);

            // Validate customer eligibility
            if (customer.isEligibleForRental()) {
                System.out.println("You are not eligible for a rental due to outstanding balance, invalid license, or too many active rentals.");
                scanner.close();
                return;
            }
            // Display rented vehicles
            System.out.println("Vehicles currently rented out:");
            List<Vehicle> rentedVehicles = rentalAgency.getRentedVehicles();
            if (rentedVehicles.isEmpty()) {
                System.out.println("No vehicles are currently rented.");
            } else {
                for (Vehicle vehicle : rentedVehicles) {
                    System.out.println(vehicle.getModel() + " (ID: " + vehicle.getVehicleId() + ")");
                }
            }

            // Allow user to proceed with selecting and renting a vehicle
            System.out.println("\nWould you like to proceed with renting a vehicle? (yes/no)");
            scanner.nextLine();


            // Step 2: Allow the user to choose a vehicle category
            System.out.println("Choose a category of vehicle:");
            System.out.println("1: Car");
            System.out.println("2: Truck");
            System.out.println("3: Motorcycle");
            int categoryChoice = scanner.nextInt();

            // Filter vehicles by category
            List<Vehicle> filteredVehicles;
            switch (categoryChoice) {
                case 1 -> filteredVehicles = rentalAgency.getAvailableVehicles()
                        .stream()
                        .filter(v -> v instanceof Car)
                        .collect(Collectors.toList());
                case 2 -> filteredVehicles = rentalAgency.getAvailableVehicles()
                        .stream()
                        .filter(v -> v instanceof Truck)
                        .collect(Collectors.toList());
                case 3 -> filteredVehicles = rentalAgency.getAvailableVehicles()
                        .stream()
                        .filter(v -> v instanceof Motorcycle)
                        .collect(Collectors.toList());
                default -> {
                    System.out.println("Invalid category.");
                    scanner.close();
                    return; // Exit the program if the category is invalid
                }
            }

            // Step 3: Allow the user to select a vehicle
            if (filteredVehicles.isEmpty()) {
                System.out.println("No available vehicles in the selected category.");
            } else {
                System.out.println("Available Vehicles in the selected category:");
                for (int i = 0; i < filteredVehicles.size(); i++) {
                    System.out.println(i + ": " + filteredVehicles.get(i));
                }

                System.out.println("Enter the index of the vehicle you'd like to rent:");
                int selectedIndex = scanner.nextInt();

                if (selectedIndex < 0 || selectedIndex >= filteredVehicles.size()) {
                    System.out.println("Invalid index. Please restart the program and try again.");
                } else {
                    // Retrieve the selected vehicle
                    Vehicle selectedVehicle = filteredVehicles.get(selectedIndex);

                    // Step 4: Ask the user for the number of rental days
                    System.out.println("Enter the number of days you'd like to rent the vehicle:");
                    int rentalDays = scanner.nextInt();

                    if (rentalDays <= 0) {
                        System.out.println("Invalid number of days. Please restart the program and try again.");
                    } else {
                        // Set rental duration and calculate cost
                        if (selectedVehicle instanceof Car) {
                            ((Car) selectedVehicle).setRentalDays(rentalDays);
                        } else if (selectedVehicle instanceof Truck) {
                            ((Truck) selectedVehicle).setRentalDays(rentalDays);
                        } else if (selectedVehicle instanceof Motorcycle) {
                            ((Motorcycle) selectedVehicle).setRentalDays(rentalDays);
                        }

                        double rentalCost = selectedVehicle.calculateRentalCost(rentalDays);

                        // Finalize the rental
                        selectedVehicle.setAvailable(false); // Mark the vehicle as rented
                        customer.addToCurrentRentals(selectedVehicle.getModel()); // Add to customer's active rentals

                        System.out.println("Rental Confirmed!");
                        System.out.println("Customer: " + customer.getName());
                        System.out.println("Rented Vehicle: " + selectedVehicle.getModel());
                        System.out.println("Rental Days: " + rentalDays);
                        System.out.println("Total Rental Cost: $" + rentalCost);

                        // Add loyalty points to the customer
                        customer.addPoints((int) rentalCost / 10); // Example: 1 point per $10 spent
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close(); // Close the scanner
    }
}