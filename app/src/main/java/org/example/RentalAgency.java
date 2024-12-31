package org.example;

import java.util.ArrayList;
import java.util.List;

public class RentalAgency {
    private final List<Vehicle> fleet; // All vehicles in the system
    private final List<RentalTransaction> rentalTransactions; // Track rental transactions

    // Constructor
    public RentalAgency() {
        this.fleet = new ArrayList<>();
        this.rentalTransactions = new ArrayList<>();
    }

    // Fleet management: Add a vehicle to the fleet
    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    // List all available vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : fleet) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    // List all rented vehicles
    public List<Vehicle> getRentedVehicles() {
        if (fleet.isEmpty()) return new ArrayList<>();
        if (rentalTransactions.isEmpty()) return new ArrayList<>();

        List<Vehicle> rentedVehicles = new ArrayList<>();
        for (Vehicle vehicle : fleet) {
            if (!vehicle.isAvailable()) {
                rentedVehicles.add(vehicle);
            }
        }
        return rentedVehicles;
    }

    // Process rental request
    public void processRental(Customer customer, Vehicle vehicle, int days) {
        if (customer.isEligibleForRental()) {
            System.out.println("Customer is not eligible for rental.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Requested vehicle is not available for rental.");
            return;
        }

        // Process the rental
        vehicle.setAvailable(false); // Mark vehicle as rented
        customer.addToCurrentRentals(vehicle.getModel()); // Add rental to customer's records

        // Create a rental transaction
        RentalTransaction transaction = new RentalTransaction(customer, vehicle, days, vehicle.calculateRentalCost(days));
        rentalTransactions.add(transaction);

        System.out.println("Rental processed: " + customer.getName() + " has rented " + vehicle.getModel() + " for " + days + " days.");
    }



    // Generate a revenue report
    public void generateRevenueReport() {
        double totalRevenue = 0.0;
        System.out.println("Rental Transactions Report:");
        for (RentalTransaction transaction : rentalTransactions) {
            System.out.println(transaction);
            totalRevenue += transaction.getCost();
        }
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    // Generate customer rental history report
    public void generateCustomerReport(Customer customer) {
        System.out.println("Rental History for " + customer.getName() + ":");
        for (String vehicleModel : customer.getRentalHistory()) {
            System.out.println("- " + vehicleModel);
        }
        System.out.println("Currently renting: " + customer.getCurrentRentals());
    }
    public void processRental(Customer customer, Vehicle vehicle, int days, boolean useLoyaltyPoints) {
        if (customer.isEligibleForRental()) {
            System.out.println("Customer is not eligible for rental.");
            return;
        }
        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is not available.");
            return;
        }

        // Calculate rental cost
        double cost = vehicle.calculateRentalCost(days);
        if (useLoyaltyPoints) {
            // Allow customers to redeem 100 points for $10 discount
            int pointsToRedeem = (int) (cost / 10) * 100; // Redeemable points
            if (customer.usePoints(pointsToRedeem)) {
                cost -= pointsToRedeem / 100.0 * 10; // Deduct discount
            }
        }

        // Process the rental
        vehicle.setAvailable(false);
        customer.addToCurrentRentals(vehicle.getModel());
        RentalTransaction transaction = new RentalTransaction(customer, vehicle, days, cost);
        rentalTransactions.add(transaction);

        // Add loyalty points (1 point per $1 spent)
        customer.addPoints((int) cost);
        System.out.println("Rental processed: " + customer.getName() + " rented " + vehicle.getModel() + " for $" + cost + ".");
    }
}