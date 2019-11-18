package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DoubleDecline implements ValuationPolicy {

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        System.out.println("You have chosen double declining balance deprication.");
        //get replacementCost
        //calc how old bike is from date passed in
        //value = replacementCost*(1-(2*depricationRate))^yearsOld
        //deposit = value*depositRate
        return null;
    }

}
