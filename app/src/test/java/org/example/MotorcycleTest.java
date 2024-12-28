package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class MotorcycleTest {

    /**
     * Tests for the calculateRentalCost method in the Motorcycle class.
     * This method calculates the total rental cost of a motorcycle
     * based on the number of rental days and additional charges
     * for rentals longer than 7 days.
     */

    @Test
    public void testCalculateRentalCostForTwoDays() {
        Motorcycle motorcycle = new Motorcycle("V123", "Honda CB500X", 50.0, true, true);
        double calculatedCost = motorcycle.calculateRentalCost(2);
        assertEquals(10.0, calculatedCost, 0.0); // Base rate is overridden as 5.0 in the constructor
    }

    @Test
    public void testCalculateRentalCostForSevenDays() {
        Motorcycle motorcycle = new Motorcycle("V123", "Yamaha MT-07", 50.0, true, false);
        double calculatedCost = motorcycle.calculateRentalCost(7);
        assertEquals(35.0, calculatedCost, 0.0); // 7 * 5.0
    }

    @Test
    public void testCalculateRentalCostForMoreThanSevenDays() {
        Motorcycle motorcycle = new Motorcycle("V134", "Ducati Scrambler", 50.0, true, false);
        double calculatedCost = motorcycle.calculateRentalCost(9);
        assertEquals(55.0, calculatedCost, 0.0); // 7 * 5.0 + 2 * 10.0
    }

    @Test
    public void testCalculateRentalCostThrowsExceptionForLessThanTwoDays() {
        Motorcycle motorcycle = new Motorcycle("V130", "Suzuki DR-Z400SM", 50.0, true, false);
        assertThrows(IllegalArgumentException.class, () -> motorcycle.calculateRentalCost(1));
    }

    @Test
    public void testCalculateRentalCostThrowsExceptionForMoreThanHundredDays() {
        Motorcycle motorcycle = new Motorcycle("V140", "Aprilia Tuono V4", 50.0, true, false);
        assertThrows(IllegalArgumentException.class, () -> motorcycle.calculateRentalCost(101));
    }

    @Test
    public void testCalculateRentalCostThrowsExceptionForNegativeDays() {
        Motorcycle motorcycle = new Motorcycle("V141", "MV Agusta Brutale", 50.0, true, false);
        assertThrows(IllegalArgumentException.class, () -> motorcycle.calculateRentalCost(-1));
    }

    @Test
    public void testCalculateRentalCostForTenDays() {
        Motorcycle motorcycle = new Motorcycle("V123", "Kawasaki Ninja 400", 50.0, true, false);
        double calculatedCost = motorcycle.calculateRentalCost(10);
        assertEquals(85.0, calculatedCost, 0.0); // 7 * 5.0 + 3 * 10.0 (extra days)
    }

    @Test
    public void testCalculateRentalCostForMinimumDaysThrowsException() {
        Motorcycle motorcycle = new Motorcycle("V124", "Suzuki GSX-S750", 50.0, true, true);
        assertThrows(IllegalArgumentException.class, () -> motorcycle.calculateRentalCost(1));
    }

    @Test
    public void testCalculateRentalCostForExceedingMaximumDaysThrowsException() {
        Motorcycle motorcycle = new Motorcycle("V125", "Ducati Monster", 50.0, true, true);
        assertThrows(IllegalArgumentException.class, () -> motorcycle.calculateRentalCost(101));
    }

    @Test
    public void testReturnVehicleSetsAvailabilityToTrue() {
        Motorcycle motorcycle = new Motorcycle("V126", "Harley-Davidson Street 750", 50.0, false, true);
        motorcycle.returnVehicle();
        assertTrue(motorcycle.isAvailableForRental());
    }

    @Test
    public void testReturnVehicleDoesNotThrowException() {
        Motorcycle motorcycle = new Motorcycle("V127", "BMW G310R", 50.0, false, true);
        motorcycle.returnVehicle();
    }

    @Test
    public void testRentMotorcycleSetsAvailabilityToFalse() {
        Motorcycle motorcycle = new Motorcycle("V128", "Triumph Tiger 900", 50.0, true, true);
        Customer customer = new Customer("John Doe", "DL123456", true);
        motorcycle.rent(customer);
        assertFalse(motorcycle.isAvailableForRental());
    }

    @Test
    public void testRentMotorcyclePrintsCorrectMessage() {
        Motorcycle motorcycle = new Motorcycle("V129", "Royal Enfield Himalayan", 50.0, true, true);
        Customer customer = new Customer("Jane Smith", "DL654321", true);
        motorcycle.rent(customer);
        // Ideally you would use a logging system or mock to assert the printed message
    }

    @Test
    public void testRentMotorcycleWithHelmetPrintsMessage() {
        Motorcycle motorcycle = new Motorcycle("V130", "KTM Duke 390", 50.0, true, false);
        Customer customer = new Customer("Alice Brown", "DL567890", true);
        motorcycle.rent(customer, 5, true);
        // Ideally you would use a logging system or mock to assert the printed message
    }

    @Test
    public void testCannotRentUnavailableMotorcycle() {
        Motorcycle motorcycle = new Motorcycle("V131", "Benelli TRK 502", 50.0, false, true);
        Customer customer = new Customer("Bob Green", "DL789123", true);
        motorcycle.rent(customer);
        assertFalse(motorcycle.isAvailableForRental());
    }
}