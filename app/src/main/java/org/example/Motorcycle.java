package org.example;

public class Motorcycle extends Vehicle implements Rentable{
    private boolean helmetIncluded;


    public boolean isHelmetIncluded() {
        return helmetIncluded;
    }
    public void setHelmetIncluded(boolean helmetIncluded) {
        this.helmetIncluded = helmetIncluded;
    }
    public String toString() {
        return super.toString() + ", helmetIncluded=" + helmetIncluded + "}";
    }
    public void rent(Customer customer) {
        if (!isAvailable()) {
            System.out.println("Motorcycle is not available for rental.");
            return;
        }

        setAvailable(false); // Mark the motorcycle as rented
        System.out.println(customer.getName() + " has rented the motorcycle model: " + getModel());
    }

    @Override
    public void returnVehicle() {
        setAvailable(true); // Mark the motorcycle as available
        System.out.println("Motorcycle model: " + getModel() + " has been returned.");
    }

    // Overloaded rent method with additional options
    public void rent(Customer customer, int days, boolean helmetIncluded) {
        rent(customer); // Reuse the original rent method
        if (helmetIncluded) {
            System.out.println("A helmet has been included with the rental.");
        }
    }


    public Motorcycle(String vehicleId, String model, double baseRentalRate, boolean isAvailable, boolean helmetIncluded) {
        super(vehicleId, model, baseRentalRate, isAvailable);
        super.setBaseRentalRate(5.0);
        this.helmetIncluded = helmetIncluded;
    }

    public double calculateRentalCost(int days) {
        if (days < 2) {
            throw new IllegalArgumentException("A car must be rented for at least 2 days.");
        }
        if (days > 100) {
            throw new IllegalArgumentException("A car cannot be rented for more than 30 days.");
        }

        double totalCost = getBaseRentalRate() * days;


        if (days > 7) {
            int extraDays = days - 7;
            totalCost += extraDays * 10.0; // $10 surcharge per extra day
        }

        return totalCost;
    }

    public boolean isAvailableForRental() {
        return isAvailable();
    }

    public void setRentalDays(int rentalDays) {
        if (rentalDays < 2) {
            throw new IllegalArgumentException("A motorcycle must be rented for at least 2 days.");
        }
        if (rentalDays > 100) {
            throw new IllegalArgumentException("A motorcycle cannot be rented for more than 30 days.");
        }
    }
}
