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
    //public BikeStore store;
    private String[] partnerships;
    private String valuationPolicy;
    private BigDecimal depositRate;
    
    //Define constructor
    public BikeStore(String storeName, Location locationOfStore,String[] partnerships ) {
        super();
        this.storeName = storeName;
        this.locationOfStore = locationOfStore;
        this.partnerships = partnerships;
    }
    
    //Getters
    public String getValuationPolicy() {
        return this.valuationPolicy;
    }
    
    public BigDecimal getDepositRate() {
        return this.depositRate;
    }
    
    //Setters
    public void setDailyPrice(BikeType typeOfBike, BigDecimal dailyPrice) {
        //TODO: set Daily price on a specified bike type
    }

    public void setValuationPolicy(String policy) { //set in test
        this.valuationPolicy = policy;
    }
 
    public void setDepositRate(BigDecimal rate) { //set in test
        this.depositRate = rate;
    }
    
    //Methods
    //...
    public void returnBikes(int ref) {
        // input validation if the reference exists
        assert ref >=Booking.BOOKINGS ;
       
        Iterator<Booking> allBookingsIterator = Booking.ALLBOOKINGS.iterator();
        while(allBookingsIterator.hasNext()) {
            Booking tempBooking = allBookingsIterator.next();
            Boolean part = false;
            for (int i = 0; i< partnerships.length; i++) {
                if(partnerships[i]== tempBooking.store.storeName) {
                    part = true;
                }
            }
            
            if(tempBooking.ref == ref) {
                
                if(tempBooking.store.storeName == this.storeName) {
                    tempBooking.bikesReturned();
                    tempBooking.depositCollected();
                }
                
                if (part) {
                    tempBooking.bikesToBeDeliveredToProvider();
                    tempBooking.depositInDelivery();
                }

            }
            
        }   
    }
    
    
    //...
    
    
    //Check if the store has enough of each type of bike queried and for the right dates.
    public Collection<Bike> checkBikeAvailability(DateRange dateRange,
            Collection<BikeType> bikeTypes) { 
        Collection<Bike> allAvailableBikes = new ArrayList<Bike>();
        Collection<Bike> returnBikes = new ArrayList<Bike>();
        Iterator<Bike> bikeStockIterator = bikeStock.iterator();
        
        //Check if the store has enough of each type of bike
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
        
        //Check if each bike that's suitable will be available to rent out on the queried dates.
        while(bikeTypeIterator.hasNext()){
            int i = 0;
            BikeType tempBikeType = bikeTypeIterator.next();
            Iterator<Bike> allAvailableBikesIterator = allAvailableBikes.iterator();
            while (allAvailableBikesIterator.hasNext()) {
                Bike tempBike = allAvailableBikesIterator.next();
                if(tempBikeType == tempBike.getType() && i != 1) {
                    i = 1;
                    returnBikes.add(tempBike);
                }
            }
        }
        
        //Return all available bikes
        return returnBikes;
    }
}
