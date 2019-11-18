package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LinearDeprication implements ValuationPolicy {

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        System.out.println("You have chosen linear deprication.");
        //get replacementCost
        //calc how old bike is from date passed in
        //value = replacementCost * ((yearsOld*depricationRate)*replacementCost)
        //deposit = value*depositRate
        
        return null;
    }

}
