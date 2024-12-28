package org.example;

import java.util.Objects;

public class RentalTransaction {
    private final Customer customer;
    private final Vehicle vehicle;
    private final int rentalDays;
    private final double cost;

    // Constructor
    public RentalTransaction(Customer customer, Vehicle vehicle, int rentalDays, double cost) {
        if (customer == null || vehicle == null || rentalDays < 1 || cost < 0)  {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.cost = cost;
    }

    // Get the cost of the transaction
    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RentalTransaction that)) return false;
        return rentalDays == that.rentalDays && Double.compare(getCost(), that.getCost()) == 0 && Objects.equals(customer, that.customer) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, vehicle, rentalDays, getCost());
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() +
                ", Vehicle: " + vehicle.getModel() +
                ", Days: " + rentalDays +
                ", Cost: $" + cost;
    }
}
