package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Customer {
    //Personal Details
    private String firstName;
    private String lastName;
    private Location locationInfo;
    private String email;
    private String contactNo;
    
    private Collection<Booking> bookings;
    
    
    
    
    public Collection<Quote> getAllQuotes(Collection<BikeType> bikeTypes, DateRange dateRange,Location locationOfHire){
        //TODO: getAllQuotes
        // first check locations in area by bike store
        
        Collection<BikeStore> nearByStores = new ArrayList<BikeStore>();

        Iterator<BikeStore> nearByStoreIterator = nearByStores.iterator();

        Collection<Quote> availableQuotes = new ArrayList<Quote>();
        
        while(AllBikeStores.hasNext()){// AllBikeStores is a global collection
            BikeStore tempStore = AllBikeStores.next();
            Location tempStoreLocation = tempStore.locationOfStore;
            if (tempStoreLocation.isNearTo(locationOfHire)==true) {
                nearByStores.add(tempStore);
           } 
        }

        Iterator<BikeStore> nearByStoreiterator = nearByStores.iterator();

        while(nearByStoreIterator.hasNext()){
            BikeStore tempStore = nearByStoreIterator.next();
            // check bike availability will need to return a list of bikes
            Collection<Bike> availibleBikes = tempStore.checkBikeAvailability(dateRange, bikeTypes);
            
            if(availibleBikes != null) {
                Quote newQuote = new Quote(tempStore.storeName,tempStore,dateRange,availibleBikes);
                newQuote.calcTotalDeposit(null);// not sure what value and from where?
                newQuote.calcTotalPrice(null);// not sure what value and from where?
                availableQuotes.add(newQuote);
                }

        }
        
        
        if (availableQuotes.size() == 0) {// date extension by 3 days with the same duration
            // reinitialise the iterator for a the nearby stores
            Iterator<BikeStore> nearByStoreiterator1 = nearByStores.iterator();
            
            while(nearByStoreiterator1.hasNext()){
                BikeStore tempStore = nearByStoreiterator1.next();
                // check bike availability will need to return a list of bikes
              
                DateRange newDateRange = new DateRange(dateRange.getStart().plusDays(3),
                        dateRange.getStart().plusDays(3).plusDays(dateRange.toDays()));
                
                Collection<Bike> availibleBikes = tempStore.checkBikeAvailability(newDateRange, bikeTypes);
                
                if(availibleBikes != null) {
                    Quote newQuote = new Quote(tempStore.storeName,tempStore,dateRange,availibleBikes);
                    newQuote.calcTotalDeposit(null);// not sure what value and from where?
                    newQuote.calcTotalPrice(null);// not sure what value and from where?
                    availableQuotes.add(newQuote);
                    }
            }
            if (availableQuotes.size() == 0) {// another date extension
             // reinitialise the iterator for a the nearby stores
                Iterator<BikeStore> nearByStoreiterator2 = nearByStores.iterator();
                while(nearByStoreiterator2.hasNext()){
                    BikeStore tempStore = nearByStoreiterator2.next();
                    // check bike availability will need to return a list of bikes
                    
                    DateRange newDateRange = new DateRange(dateRange.getStart().plusDays(3),
                            dateRange.getStart().plusDays(3).plusDays(dateRange.toDays()));
                    
                    Collection<Bike> availibleBikes = tempStore.checkBikeAvailability(dateRange, bikeTypes);
                    
                    if(availibleBikes != null) {
                        Quote newQuote = new Quote(tempStore.storeName,tempStore,dateRange,availibleBikes);
                        newQuote.calcTotalDeposit(null);// not sure what value and from where?
                        newQuote.calcTotalPrice(null);// not sure what value and from where?
                        availableQuotes.add(newQuote);
                        }
                }
            }
        }
        
        
        
        return availableQuotes;
    }
    
    public String bookQuote(Quote quote) {
        //TODO: bookQuote 
        // Adds that booking created to the collection of booking for each customer
        return null;
    }
    //getter for bookings
    public Collection<Booking> getBookings() {
        return bookings;
    }
    
    
}
