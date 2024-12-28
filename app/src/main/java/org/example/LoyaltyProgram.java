package org.example;

public interface LoyaltyProgram {

    void addPoints(int points);
    boolean usePoints(int points); // Returns true if points were successfully redeemed
    int getPoints();

}
