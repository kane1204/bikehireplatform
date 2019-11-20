package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {
    public BigDecimal depriciationRate;
    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {   
        BikeType bikeType = bike.getType();
         
        LocalDate dateNow = LocalDate.now();
        
        DateRange range = new DateRange(date, dateNow);
        BigDecimal yearsOld = new BigDecimal(range.toYears());
        
        BigDecimal repVal = bikeType.getReplacementValue();
        BigDecimal depriationPt1 = depriciationRate.multiply(yearsOld);
        BigDecimal depriationPt2 = depriationPt1.multiply(repVal);
        BigDecimal value = repVal.subtract(depriationPt2);
        value = value.setScale(2, RoundingMode.CEILING);
        
        return value;
    }

}
