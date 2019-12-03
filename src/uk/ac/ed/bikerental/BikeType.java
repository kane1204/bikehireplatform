package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class BikeType {
    //Info on type
    public String nameOfType;
    private BigDecimal replacementCost;
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
    
    public String getType() {
        return nameOfType;
    }
    
    //Setters
    public void setReplacementValue(BigDecimal x) {
        this.replacementCost = x;
    }
    
    public void setDailyprice(BigDecimal x) {
        this.dailyPrice = x;
    }
    
    public void setDailyPrice(BigDecimal x) {
        this.dailyPrice = x;
    }
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BikeType other = (BikeType) obj;
        return Objects.equals(nameOfType, other.nameOfType) && 
               Objects.equals(replacementCost, other.replacementCost)&& 
               Objects.equals(dailyPrice, other.dailyPrice);
    }
}
