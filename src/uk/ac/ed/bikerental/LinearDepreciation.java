package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {
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
        LocalDate dateNow = LocalDate.now(); // Get today's date
        // Create the date range using the date passed (day bike was new) in and today's date
        DateRange range = new DateRange(date, dateNow);
        // Find the range in years, then format as a BigDecimal
        BigDecimal yearsOld = new BigDecimal(range.toYears());
        
        /*
         * Calculate the value of the bike.
         * Formula: Replacement Value - (Depreciation Rate * Bike's Age * Replacement Value)
         */
        BigDecimal repVal = bikeType.getReplacementValue();
        BigDecimal depreciationPt1 = depreciationRate.multiply(yearsOld);
        BigDecimal depreciationPt2 = depreciationPt1.multiply(repVal);
        BigDecimal value = repVal.subtract(depreciationPt2);
        value = value.setScale(2, RoundingMode.CEILING); // Round to 2 decimal places as it's money
        
        // Return the new cost of the bike
        return value;
    }

}
