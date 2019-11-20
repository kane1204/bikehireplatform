package uk.ac.ed.bikerental;

import java.util.Collection;

public class Customer {
    //Personal Details
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String email;
    private String contactNo;
    
    private Collection<Booking> bookings;
    
    public Collection<Quote> getAllQuotes(Collection<BikeType> bikeTypes, DateRange dateRange,Location locationOfHire){
        //TODO: getAllQuotes
        return null;
    }
    
    public String bookQuote(Quote quote) {
        //TODO: bookQuote
        return null;
    }
    //getter for bookings
    public Collection<Booking> getBookings() {
        return bookings;
    }
    
    
}
