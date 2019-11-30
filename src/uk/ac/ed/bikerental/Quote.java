package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Quote {
    public String providerName;
    public BikeStore bikeStore;
    public DateRange dates;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    public Collection<Bike> bikes;
    
    public Quote(String providerName, BikeStore bikeStore, DateRange dates, Collection<Bike> bikes) {
        super();
        this.providerName = providerName;
        this.bikeStore = bikeStore;
        this.dates = dates;
        this.bikes = bikes;
    }
    public BigDecimal calcTotalPrice(BigDecimal dailyPrice) {
        return null;
    }
    public BigDecimal calcTotalDeposit(BigDecimal deposit) {
        return null;
    }
}
