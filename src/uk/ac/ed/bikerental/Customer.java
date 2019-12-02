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
    //Find all possible quote that match the user's query
    public Collection<Quote> getAllQuotes(Collection<BikeStore> allStores,
            Collection<BikeType> bikeTypes, DateRange dateRange, Location locationOfHire){       
        Collection<BikeStore> nearByStores = new ArrayList<BikeStore>();
        Collection<Quote> availableQuotes = new ArrayList<Quote>();     
        Iterator<BikeStore> allStoresIterator = allStores.iterator();
        
        //First check locations in area by bike store
        while(allStoresIterator.hasNext()){
            BikeStore tempStore = allStoresIterator.next();
            Location tempStoreLocation = tempStore.locationOfStore;
            if (tempStoreLocation.isNearTo(locationOfHire)) nearByStores.add(tempStore);
        }

        Iterator<BikeStore> nearByStoreIterator = nearByStores.iterator();

        //Next check if a store that's near enough can fulfil the quote
        while(nearByStoreIterator.hasNext()){
            BikeStore tempStore = nearByStoreIterator.next(); 
            Collection<Bike> quoteBikes = tempStore.checkBikeAvailability(dateRange, bikeTypes);
            
            //Add all available quotes to a list with the total cost and deposit calculated
            if(quoteBikes != null) {
                Quote newQuote = new Quote(tempStore.storeName, tempStore, dateRange, 
                        quoteBikes);         
                newQuote.calcTotalPrice(tempStore, quoteBikes);
                newQuote.calcTotalDeposit(tempStore.getDepositRate());
                availableQuotes.add(newQuote);
            }
        }
        
        //Return all available quotes
        return availableQuotes;
    }
    
    // Adds the booking created to the collection of booking for each customer
    public Booking bookQuote(Quote quote, Boolean delivery) {       
        int ref = Booking.BOOKINGS;

        Booking newBooking =  new Booking(this,quote.bikeStore, quote.bikeStore.locationOfStore,
                quote.dates, quote.bikes, ref, delivery, quote.totalPrice, quote.totalDeposit);
        
        Iterator<Bike> bikeIterator = newBooking.bikes.iterator();
        while(bikeIterator.hasNext()){
            Bike tempBike = bikeIterator.next();
            tempBike.onPickup();
        }
        
        if(newBooking.bikeDelivery) {
            DeliveryServiceFactory dpd = new DeliveryServiceFactory(); 
            dpd.getDeliveryService();
        }
       
        Booking.BOOKINGS +=1;
        Booking.ALLBOOKINGS.add(newBooking);
        //Return the booking objects
        return newBooking;
    }  
}
