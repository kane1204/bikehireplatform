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
        this.locationInfo = locationInfo;
        this.email = email;
        this.contactNo = contactNo;
        this.accommodation = accommodation;
    }
    
    //Getters
    public Collection<Booking> getBookings() {
        return bookings;
    }
    public String getFirstName() {
        return firstName;
    }
    public Location getAccommodation() {
        return accommodation;
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
        if(availableQuotes.isEmpty()) {
            return null;
        } else {
            return availableQuotes;
        }
    }
    
    // Adds the booking created to the collection of booking for each customer
    public Booking bookQuote(Quote quote, Boolean delivery) {       
        int ref = Booking.BOOKINGS;

        Booking newBooking =  new Booking(this,quote.bikeStore, quote.dates, quote.bikes, ref,
                delivery, quote.totalPrice, quote.totalDeposit);
        
        Boolean paid = newBooking.payment();
        if (paid) {      
            System.out.print(newBooking.orderSummary());
            Booking.BOOKINGS +=1;
            Booking.ALLBOOKINGS.add(newBooking);
            //Return the booking objects
            return newBooking;
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accommodation == null) ? 0 : accommodation.hashCode());
        result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
        result = prime * result + ((contactNo == null) ? 0 : contactNo.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((locationInfo == null) ? 0 : locationInfo.hashCode());
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
        Customer other = (Customer) obj;
        if (accommodation == null) {
            if (other.accommodation != null)
                return false;
        } else if (!accommodation.equals(other.accommodation))
            return false;
        if (bookings == null) {
            if (other.bookings != null)
                return false;
        } else if (!bookings.equals(other.bookings))
            return false;
        if (contactNo == null) {
            if (other.contactNo != null)
                return false;
        } else if (!contactNo.equals(other.contactNo))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (locationInfo == null) {
            if (other.locationInfo != null)
                return false;
        } else if (!locationInfo.equals(other.locationInfo))
            return false;
        return true;
    }  
}