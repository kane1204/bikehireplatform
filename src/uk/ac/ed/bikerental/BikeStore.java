package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

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
    private BigDecimal depreciationRate;
    
    //Define constructor
    public BikeStore(String storeName, Location locationOfStore, String[] partnerships) {
        super();
        this.storeName = storeName;
        this.locationOfStore = locationOfStore;
        this.partnerships = partnerships;
        this.bikeStock = new ArrayList<Bike>();
        this.depositRate = new BigDecimal("0");
        this.depreciationRate = new BigDecimal("0");
    }
    
    //Getters
    public String getValuationPolicy() {
        return this.valuationPolicy;
    }
    
    public BigDecimal getDepositRate() {
        return this.depositRate;
    }
    
    public BigDecimal getDepreciationRate() {
        return this.depreciationRate;
    }

    //Setters
    public void setValuationPolicy(String policy) { //set in test
        this.valuationPolicy = policy;
    }
 
    public void setDepositRate(BigDecimal rate) { //set in test
        this.depositRate = rate;
    }
    
    public void setDepreciationRate(BigDecimal rate) { //set in test
        this.depreciationRate = rate;
    }
    
    //Methods
    //Customer returns bikes straight to provider
    public void returnBikeToProvider(int ref) {
        // input validation if the reference exists
        assert ref < Booking.BOOKINGS;
        
        //partner store enters booking ref and finds the oringal store
        //if it isnt found then exit
        Iterator<Booking> allBookingsIterator = Booking.ALLBOOKINGS.iterator();
        while(allBookingsIterator.hasNext()) {
            Booking tempBooking = allBookingsIterator.next();
            
            if(tempBooking.ref == ref) {  
                tempBooking.bikesReturned();
            } else {
                assert false;
            }
        }
    }
    
    //Customer returns bikes to partner of provider
    public void returnBikesAsPartner(int ref) {
        // input validation if the reference exists
        assert ref < Booking.BOOKINGS;
       
        //partner store enters booking ref and finds the original store
        //if it isnt found then exit
        Iterator<Booking> allBookingsIterator = Booking.ALLBOOKINGS.iterator();
        while(allBookingsIterator.hasNext()) {
            Booking tempBooking = allBookingsIterator.next();
            Boolean part = false;
            for (int i = 0; i< partnerships.length; i++) {
                if(partnerships[i] == tempBooking.store.storeName) {
                    part = true;
                }
            }
            if(tempBooking.ref == ref && part) {   
                tempBooking.depositReturnedToPartner();
                tempBooking.bikesToBeDeliveredToProvider();
            } else {
                assert false;
            }
            
        }   
    }
    
    //Partner returns bikes to original provider
    public void returnBikesFromPartner(int ref) {
        // input validation if the reference exists
        assert ref < Booking.BOOKINGS;
       
        //partner store enters booking ref and finds the oringal store
        //if it isnt found then exit
        Iterator<Booking> allBookingsIterator = Booking.ALLBOOKINGS.iterator();
        while(allBookingsIterator.hasNext()) {
            Booking tempBooking = allBookingsIterator.next();
            if(tempBooking.store.equals(this)) {
                if(tempBooking.ref == ref ) {
                    tempBooking.depositInDelivery();
                    tempBooking.bikesReturned();
                } else {
                    assert false;
                }
            }
            
        }   
    }   
    
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
        if(returnBikes.isEmpty()) {
            return null;
        } else {
            return returnBikes;
        }
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bikeStock == null) ? 0 : bikeStock.hashCode());
        result = prime * result + ((depositRate == null) ? 0 : depositRate.hashCode());
        result = prime * result + ((locationOfStore == null) ? 0 : locationOfStore.hashCode());
        result = prime * result + Arrays.hashCode(partnerships);
        result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
        result = prime * result + ((valuationPolicy == null) ? 0 : valuationPolicy.hashCode());
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
        BikeStore other = (BikeStore) obj;
        if (bikeStock == null) {
            if (other.bikeStock != null)
                return false;
        } else if (!bikeStock.equals(other.bikeStock))
            return false;
        if (depositRate == null) {
            if (other.depositRate != null)
                return false;
        } else if (!depositRate.equals(other.depositRate))
            return false;
        if (locationOfStore == null) {
            if (other.locationOfStore != null)
                return false;
        } else if (!locationOfStore.equals(other.locationOfStore))
            return false;
        if (!Arrays.equals(partnerships, other.partnerships))
            return false;
        if (storeName == null) {
            if (other.storeName != null)
                return false;
        } else if (!storeName.equals(other.storeName))
            return false;
        if (valuationPolicy == null) {
            if (other.valuationPolicy != null)
                return false;
        } else if (!valuationPolicy.equals(other.valuationPolicy))
            return false;
        return true;
    }
}