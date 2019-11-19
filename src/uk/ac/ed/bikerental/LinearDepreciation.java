package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {   
        System.out.println("Linear deprication:");
        
        BikeType bikeType = bike.getType();
        BigDecimal depriciationRate = new BigDecimal("0.1");
        LocalDate dateNow = LocalDate.now();
        
        DateRange range = new DateRange(date, dateNow);
        BigDecimal yearsOld = new BigDecimal(range.toYears());
        
        BigDecimal repVal = bikeType.getReplacementValue();
        BigDecimal depriationPt1 = depriciationRate.multiply(yearsOld);
        BigDecimal depriationPt2 = depriationPt1.multiply(repVal);
        BigDecimal value = depriationPt2.subtract(depriciationRate);
        value = value.setScale(2, RoundingMode.CEILING);
        
        /*
         * TODO: how to get depreciation rate? user input?
         */
        
        return null;
    }

}
