package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class BikeStore {
    //Info about the store
    public String storeName;
    public Location locationOfStore;
    
    //More functional info
    public Collection<Bike> bikeStock;
    public BikeStore store;
    private String[] partnerships;
    private String valuationPolicy;
    private BigDecimal depositRate;
    
    public void addPartnership(String partnerName) {
      //TODO: addPartnerShip
    }
    public String[] listBookings() {
        //TODO: list Bookings using their own name
        return null;
    }
    public void updateBooking(String message) {
        //TODO: Honestly No idea what was the idea behind this
    }
    public Collection<Bike> checkBikeAvailability(DateRange dateRange,Collection<BikeType> bikeTypes) {
        //TODO: uses the list of types of bikes to see if they are available 
        return null;
    }
    public void setDailyPrice(BikeType typeOfBike, BigDecimal dailyPrice) {
        //TODO: set Daily price on a specified bike type
    }
    
    public String getValuationPolicy() {
        return this.valuationPolicy;
    }
    
    public void setValuationPolicy(String policy) { //set in test
        this.valuationPolicy = policy;
    }
    
    public BigDecimal getDepositRate() {
        return this.depositRate;
    }
    
    public void setDepositRate(BigDecimal rate) { //set in test
        this.depositRate = rate;
    }
}
