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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dailyPrice == null) ? 0 : dailyPrice.hashCode());
        result = prime * result + ((nameOfType == null) ? 0 : nameOfType.hashCode());
        result = prime * result + ((replacementCost == null) ? 0 : replacementCost.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BikeType other = (BikeType) obj;
        if (dailyPrice == null) {
            if (other.dailyPrice != null)
                return false;
        } else if (!dailyPrice.equals(other.dailyPrice))
            return false;
        if (nameOfType == null) {
            if (other.nameOfType != null)
                return false;
        } else if (!nameOfType.equals(other.nameOfType))
            return false;
        if (replacementCost == null) {
            if (other.replacementCost != null)
                return false;
        } else if (!replacementCost.equals(other.replacementCost))
            return false;
        return true;
    }
}
