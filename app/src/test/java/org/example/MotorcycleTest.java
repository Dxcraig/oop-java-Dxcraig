package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
}