package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ValuationPolicy {
    //date passed in is purchase date by store
    public BigDecimal calculateValue(Bike bike, LocalDate date);
}
