package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {
    
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {   
        BikeType bikeType = bike.getType();
        BigDecimal depriciationRate = new BigDecimal("0.1");
        LocalDate dateNow = LocalDate.now();
        
        
        DateRange range = new DateRange(date, dateNow);
        BigDecimal yearsOld = new BigDecimal(range.toYears());
        
        System.out.println("yrs: " + yearsOld);
        
        BigDecimal repVal = bikeType.getReplacementValue();
        //BigDecimal repVal = new BigDecimal("900");  
        System.out.println(repVal);  
        BigDecimal depriationPt1 = depriciationRate.multiply(yearsOld);
        System.out.println(depriationPt1);
        BigDecimal depriationPt2 = depriationPt1.multiply(repVal);
        System.out.println(depriationPt2);
        BigDecimal value = repVal.subtract(depriationPt2);
        value = value.setScale(2, RoundingMode.CEILING);
        System.out.println(value);
        
        /*
         * TODO: how to get depreciation rate? user input?
         *       how to get replacement val?
         */
        
        return value;
    }

}
