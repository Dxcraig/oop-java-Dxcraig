package org.example;

import java.util.Objects;

public abstract class Vehicle {
    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, double baseRentalRate, boolean isAvailable) {
        if (vehicleId == null || vehicleId.isEmpty() || model == null || model.isEmpty() || baseRentalRate < 0 || !isAvailable) {
            throw new IllegalArgumentException("Invalid vehicle parameters");
        }
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        if (vehicleId != null && !vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty");

        }
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model != null && !model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate < 0) {
            throw new IllegalArgumentException("Base rental rate cannot be negative");
        }
        this.baseRentalRate = baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract double calculateRentalCost(int days);

    public abstract boolean isAvailableForRental();

    @Override
    public String toString() {
        return "Vehicle{id='" + vehicleId + "', model='" + model + "', baseRentalRate=" + baseRentalRate + ", available=" + isAvailable + "}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Compare by reference
        if (obj == null || getClass() != obj.getClass()) return false; // Check type compatibility
        Vehicle vehicle = (Vehicle) obj; // Typecast for specific comparisons
        return vehicleId.equals(vehicle.vehicleId); // Compare based on unique ID
    }
    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, model, baseRentalRate, isAvailable);
    }

}
