package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class DoubleDecline implements ValuationPolicy {
    public BigDecimal depreciationRate; // Store will set their own depreciation rate
    
    // Use the method defined in the ValuationPolicy interface
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BikeType bikeType = bike.getType(); // Get the bike object's bike type
        
        /* 
         * Calculate the age of the bike by finding the range between passed in and today's date.
         * BigDecimal's can only be modified by another BigDecimal, so some numbers have been
         * converted to do so.
        */
        
        LocalDate dateNow = LocalDate.now();
        
        // Create the date range using the date passed (day bike was new) in and today's date
        DateRange range = new DateRange(date, dateNow);
        // Find the range in years and format as an integer
        int yearsOld = (int) range.toYears();
        
        /*
         * Calculate the value of the bike.
         * Formula: Replacement Value * (1 - (2 * Depreciation Value))^(Bike's Age)
         */
        
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("2");
        BigDecimal repVal = bikeType.getReplacementValue();
        BigDecimal doubleDepRate = depreciationRate.multiply(two);
        BigDecimal depreciationPt1 = one.subtract(doubleDepRate);
        BigDecimal depreciationPt2 = depreciationPt1.pow(yearsOld);
        BigDecimal value = repVal.multiply(depreciationPt2);
        value = value.setScale(2, RoundingMode.CEILING); // Round to 2 decimal places as it's money
        
        // Return the new cost of the bike
        return value;
    }

}
