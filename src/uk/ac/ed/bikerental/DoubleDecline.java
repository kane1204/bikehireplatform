package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class DoubleDecline implements ValuationPolicy {

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        System.out.println("Double declining balance deprication:");
        
        BikeType bikeType = bike.getType();
        BigDecimal depriciationRate = new BigDecimal("0.1");
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("2");
        LocalDate dateNow = LocalDate.now();
        
        DateRange range = new DateRange(date, dateNow);
        int yearsOld = (int) range.toYears();
        
        BigDecimal repVal = bikeType.getReplacementValue();
        BigDecimal doubleDepRate = depriciationRate.multiply(two);
        BigDecimal depriationPt1 = one.subtract(doubleDepRate);
        BigDecimal depriationPt2 = depriationPt1.pow(yearsOld);
        BigDecimal value = repVal.multiply(depriationPt2);
        value = value.setScale(2, RoundingMode.CEILING);
        
        return value;
    }

}
