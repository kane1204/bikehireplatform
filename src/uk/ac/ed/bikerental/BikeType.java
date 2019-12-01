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
    
    public BikeType(BigDecimal replacementCost, BigDecimal dailyPrice) {
        this.replacementCost = replacementCost;
        this.dailyPrice = dailyPrice;
    }
    
    public void setReplacementValue(BigDecimal x) {
        this.replacementCost = x;
    }
    
    public BigDecimal getReplacementValue() {
        return replacementCost;
    }
    
    public void setDailyprice(BigDecimal x) {
        this.dailyPrice = x;
    }
    
    public BigDecimal getDailyPrice() {
        return dailyPrice;
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