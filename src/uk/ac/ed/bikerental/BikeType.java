package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class BikeType {
    public String nameOfType;
    private BigDecimal replacementCost;
    private BigDecimal depositRate;
    private Bike bike; //not sure tbh
    private BigDecimal deposit;
    private BigDecimal dailyPrice;
    
    public BikeType(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }
    
    public void setReplacementValue(BigDecimal x) {
        replacementCost = x;
    }
    
    public BigDecimal getReplacementValue() {
        //assert false;
        return replacementCost;
    }
    
    public BigDecimal calcDeposit(){
        //Not sure if this is getReplacement Value
        return null;
    }
    public BigDecimal calcDailyPrice() {
        //TODO: calc Daily Price
        return null;
    }
    
    
}