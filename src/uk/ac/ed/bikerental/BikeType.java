package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class BikeType {
    //Info on type
    public String nameOfType;
    private Bike bike;
    private BigDecimal replacementCost;
    
    //More functional info  
    private BigDecimal depositRate;  
    private BigDecimal deposit;
    private BigDecimal dailyPrice;
    
    //Define constructor    
    public BikeType(String nameofType, BigDecimal replacementCost, BigDecimal dailyPrice) {
        super();
        this.nameOfType = nameofType;
        this.replacementCost = replacementCost;
        this.dailyPrice = dailyPrice;
    }
    
    //Getters
    public BigDecimal getReplacementValue() {
        return replacementCost;
    }
    
    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }
    
    //Setters
    public void setReplacementValue(BigDecimal x) {
        this.replacementCost = x;
    }
    
    public void setDailyprice(BigDecimal x) {
        this.dailyPrice = x;
    }
}
