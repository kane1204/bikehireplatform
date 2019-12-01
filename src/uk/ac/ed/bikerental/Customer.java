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
    
    
    public Collection<Quote> getAllQuotes(Collection<BikeType> bikeTypes, DateRange dateRange, 
            Location locationOfHire){
        //TODO: getAllQuotes
        // first check locations in area by bike store
        
        Collection<BikeStore> nearByStores = new ArrayList<BikeStore>();

        Collection<Quote> availableQuotes = new ArrayList<Quote>();
        
        while(AllBikeStores.hasNext()){// AllBikeStores is a global collection
            BikeStore tempStore = AllBikeStores.next();
            Location tempStoreLocation = tempStore.locationOfStore;
            if (tempStoreLocation.isNearTo(locationOfHire)==true) {
                nearByStores.add(tempStore);
           } 
        }

        Iterator<BikeStore> nearByStoreIterator = nearByStores.iterator();

        while(nearByStoreIterator.hasNext()){
            BikeStore tempStore = nearByStoreIterator.next();
            // check bike availability will need to return a list of bikes
            Collection<Bike> quoteBikes = tempStore.checkBikeAvailability(dateRange, bikeTypes);
            
            if(quoteBikes != null) {
                Quote newQuote = new Quote(tempStore.storeName, tempStore, dateRange, 
                        quoteBikes);

                //iterate through the collection of bikes and set dailyprice, store and type will
                //with a getter
                
                newQuote.calcTotalPrice(tempStore, quoteBikes);// not sure what value and from where?
                newQuote.calcTotalDeposit(tempStore.getDepositRate());// not sure what value and from where?
                availableQuotes.add(newQuote);
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
