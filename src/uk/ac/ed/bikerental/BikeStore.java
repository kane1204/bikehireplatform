package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
        Collection<Bike> allAvailableBikes = new ArrayList<Bike>();
        Collection<Bike> returnBikes = new ArrayList<Bike>();
        Iterator<Bike> bikeStockIterator = bikeStock.iterator();
        while(bikeStockIterator.hasNext()){
            Bike tempBike = bikeStockIterator.next();
            Iterator<BikeType> bikeTypeIterator = bikeTypes.iterator();
            
            while(bikeTypeIterator.hasNext()){
                BikeType tempBikeType = bikeTypeIterator.next();
                if(tempBikeType == tempBike.getType() && tempBike.checkDates(dateRange)) {
                    allAvailableBikes.add(tempBike);
                }
            }   
        }
        Iterator<BikeType> bikeTypeIterator = bikeTypes.iterator();
        while(bikeTypeIterator.hasNext()){
            int i = 0;
            BikeType tempBikeType = bikeTypeIterator.next();
            Iterator<Bike> allAvailableBikesIterator = allAvailableBikes.iterator();
            while (allAvailableBikesIterator.hasNext()) {
                Bike tempBike = allAvailableBikesIterator.next();
                if(tempBikeType == tempBike.getType() && i!=1) {
                    i= 1;
                    returnBikes.add(tempBike);
                }
            }
        }
        
        
        return returnBikes;
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
