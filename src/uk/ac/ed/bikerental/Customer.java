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
    private Location accommodation;
    
    //More functional info
    private Collection<Booking> bookings;
    
    //Define constructor
    public Customer(String firstName, String lastName,Location locationInfo, String email,
            String contactNo, Location accommodation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.accommodation = accommodation;
    }
    
    //Getters
    public Collection<Booking> getBookings() {
        return bookings;
    }
    
    //Methods
    //...
    public Collection<Quote> getAllQuotes(Collection<BikeStore> allStores, Collection<BikeType> bikeTypes, DateRange dateRange, 
            Location locationOfHire){
        //TODO: getAllQuotes
        // first check locations in area by bike store
        
        Collection<BikeStore> nearByStores = new ArrayList<BikeStore>();
        Collection<Quote> availableQuotes = new ArrayList<Quote>();     
        Iterator<BikeStore> allStoresIterator = allStores.iterator();
        
        while(allStoresIterator.hasNext()){// AllBikeStores is a global collection
            BikeStore tempStore = allStoresIterator.next();
            Location tempStoreLocation = tempStore.locationOfStore;
            if (tempStoreLocation.isNearTo(locationOfHire)) nearByStores.add(tempStore);
        }

        Iterator<BikeStore> nearByStoreIterator = nearByStores.iterator();

        while(nearByStoreIterator.hasNext()){
            BikeStore tempStore = nearByStoreIterator.next(); 
            Collection<Bike> quoteBikes = tempStore.checkBikeAvailability(dateRange, bikeTypes);
            
            // Check bike availability will need to return a list of bikes
            if(quoteBikes != null) {
                Quote newQuote = new Quote(tempStore.storeName, tempStore, dateRange, 
                        quoteBikes);         
                newQuote.calcTotalPrice(tempStore, quoteBikes);
                newQuote.calcTotalDeposit(tempStore.getDepositRate());
                availableQuotes.add(newQuote);
            }
        }
        
        return availableQuotes;
    }
    
    // Adds the booking created to the collection of booking for each customer
    public Quote bookQuote(Quote quote) {       
        MockDeliveryService dpd = new MockDeliveryService();
        String ref = "";
        Boolean delivery = false;
        Booking newBooking =  new Booking(this,quote.bikeStore, quote.bikeStore.locationOfStore,
                quote.dates, quote.bikes, ref, delivery, quote.totalPrice, quote.totalDeposit);
//        if(newBooking.bikeDelivery) {
//             Deliverable delivery = new Deliverable();
//            dpd.scheduleDelivery(deliverable, newBooking.location, this.accommodation, newBooking.range.getStart());
//        }
        bookings.add(newBooking);
        
        //Return the booking objects
        return null;
    }  
}
