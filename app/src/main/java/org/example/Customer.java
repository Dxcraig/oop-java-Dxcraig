package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer implements LoyaltyProgram {
    private final String name;
    private final String licenseNumber;
    private boolean licenseValid;
    private final List<String> rentalHistory; // History of rented vehicles
    private final List<String> currentRentals; // Active rentals
    private boolean outstandingBalance;
    private int loyaltyPoints; // Loyalty points for the customer

    // Constructor
    public Customer(String name, String licenseNumber, boolean licenseValid) {
        if ((name == null) || (licenseNumber == null) || !licenseValid || licenseNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid customer data.");
        }
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.licenseValid = true;
        this.rentalHistory = new ArrayList<>();
        this.currentRentals = new ArrayList<>();
        this.outstandingBalance = false;
        this.loyaltyPoints = 0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public boolean isLicenseValid() {
        return licenseValid;
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public List<String> getCurrentRentals() {
        return currentRentals;
    }


    public boolean hasOutstandingBalance() {
        return outstandingBalance;
    }

    public void setLicenseValid(boolean licenseValid) {
        this.licenseValid = licenseValid;
    }

    public void setOutstandingBalance(boolean outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    // Rental management methods
    public void addToCurrentRentals(String vehicleModel) {
        currentRentals.add(vehicleModel);
    }

    public void completeRental(String vehicleModel) {
        currentRentals.remove(vehicleModel);
        rentalHistory.add(vehicleModel);
    }

    public boolean isEligibleForRental() {
        if (!licenseValid) return true;
        if (outstandingBalance) return true;
        return currentRentals.size() >= 2; // Max 2 active rentals
    }

    // Loyalty program methods
    @Override
    public void addPoints(int points) {
        loyaltyPoints += points;
        System.out.println(name + " earned " + points + " loyalty points. Total: " + loyaltyPoints + " points.");
    }

    @Override
    public boolean usePoints(int points) {
        if (loyaltyPoints >= points) {
            loyaltyPoints -= points;
            System.out.println(name + " redeemed " + points + " points. Remaining: " + loyaltyPoints + " points.");
            return true;
        } else {
            System.out.println(name + " does not have enough points to redeem. Available: " + loyaltyPoints + " points.");
            return false;
        }
    }

    @Override
    public int getPoints() {
        return loyaltyPoints;
    }
}