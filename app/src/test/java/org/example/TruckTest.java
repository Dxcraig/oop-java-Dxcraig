package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class TruckTest {

    /**
     * Test class for the `calculateRentalCost` method in the `Truck` class.
     * The method calculates the total rental cost of a truck based on the number of rental days.
     * It applies a base rate for the first 5 days and an additional rate for any extra days.
     * It ensures the number of rental days is within the valid range of 1 to 20 days, inclusive.
     */

    @Test
    public void testCalculateRentalCostForOneDay() {
        Truck truck = new Truck("T001", "Model-X", 10.0, true, 5.0);

        double result = truck.calculateRentalCost(1);

        assertEquals(15.0, result, 0.0);
    }

    @Test
    public void testCalculateRentalCostForFiveDays() {
        Truck truck = new Truck("T002", "Model-Y", 10.0, true, 10.0);

        double result = truck.calculateRentalCost(5);

        assertEquals(75.0, result, 0.0);
    }

    @Test
    public void testCalculateRentalCostForSixDays() {
        Truck truck = new Truck("T003", "Model-Z", 10.0, true, 8.0);

        double result = truck.calculateRentalCost(6);

        assertEquals(95.0, result, 0.0); // 75 for first 5 days + 20 for extra day
    }

    @Test
    public void testCalculateRentalCostForTenDays() {
        Truck truck = new Truck("T004", "Model-W", 10.0, true, 7.5);

        double result = truck.calculateRentalCost(10);

        assertEquals(195.0, result, 0.0); // 75 for first 5 days + 120 for 6-10 days (5 * 20)
    }

    @Test
    public void testCalculateRentalCostForMaxAllowedDays() {
        Truck truck = new Truck("T005", "Model-V", 10.0, true, 12.0);

        double result = truck.calculateRentalCost(20);

        assertEquals(475.0, result, 0.0); // 75 for first 5 days + 400 for 6-20 days (15 * 20)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRentalCostForZeroDays() {
        Truck truck = new Truck("T006", "Model-U", 10.0, true, 9.0);

        truck.calculateRentalCost(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRentalCostForNegativeDays() {
        Truck truck = new Truck("T007", "Model-T", 10.0, true, 6.0);

        truck.calculateRentalCost(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRentalCostForExceedingMaxDays() {
        Truck truck = new Truck("T008", "Model-S", 10.0, true, 4.0);

        truck.calculateRentalCost(21);
    }
}