package org.example;

import java.util.Objects;

public class Truck extends Vehicle implements Rentable {
    private double loadCapacity;

    public double getLoadCapacity() {
        return loadCapacity;
    }
    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
    public String toString() {
        return super.toString() + ", loadCapacity=" + loadCapacity + " tons}";
    }

    public void rent(Customer customer) {
        if (!isAvailable()) {
            System.out.println("Truck is not available for rental.");
            return;
        }

        setAvailable(false); // Mark the truck as rented
        System.out.println(customer.getName() + " has rented the truck model: " + getModel() );
    }

    @Override
    public void returnVehicle() {
        setAvailable(true); // Mark the truck as available
        System.out.println("Truck model: " + getModel() + " has been returned.");
    }

    // Overloaded rent method for additional cargo details
    public void rent(Customer customer, int days, double cargoWeight) {
        rent(customer); // Reuse the original rent method
        System.out.println("Cargo weight for this rental: " + cargoWeight + " kg.");

    }


    public Truck(String vehicleId, String model, double baseRentalRate, boolean isAvailable, double loadCapacity) {
        super(vehicleId, model, baseRentalRate, isAvailable);
        super.setBaseRentalRate(15.0);
        this.loadCapacity = loadCapacity;
    }

    public double calculateRentalCost(int days) {
        if (days < 1) {
            throw new IllegalArgumentException("A truck must be rented for at least 1 day.");
        }
        if (days > 20) {
            throw new IllegalArgumentException("A truck cannot be rented for more than 20 days.");
        }

        double totalCost = getBaseRentalRate() * days;

        if (days > 5) {
            int extraDays = days - 5;
            totalCost += extraDays * 20.0;
        }

        return totalCost;
    }

    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Truck truck)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getLoadCapacity(), truck.getLoadCapacity()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLoadCapacity());
    }

    public void setRentalDays(int rentalDays) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("A truck must be rented for at least 1 day.");
        }
        if (rentalDays > 100) {
            throw new IllegalArgumentException("A truck cannot be rented for more than 20 days.");
        }
    }
}
