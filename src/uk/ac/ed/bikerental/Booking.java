package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Booking {
    //Booking info
    public Customer customer;
    public Location location;
    public Collection<Bike> bikes;
    public String ref;
    public Boolean bikeDelivery;
    public DateRange range;
    public BikeStore store;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    
    //Define constructor
    public Booking(Customer customer,BikeStore store, Location location, DateRange dateRange, Collection<Bike> bikes,
            String ref,Boolean bikeDelivery,BigDecimal totalPrice, BigDecimal totalDeposit) {
        super();
        this.customer = customer;
        this.location = location;
        this.bikes = bikes;
        this.ref = ref; // Reference should be generated in bookQuote
        this.bikeDelivery = bikeDelivery;
        this.range = dateRange;
        this.store = store;
        this.totalPrice = totalPrice;
        this.totalDeposit = totalDeposit;       
    }
    
    //Methods
    //...
    public void payment(BigDecimal totalPrice, BigDecimal totalDeposit) {
        //TODO:payment should just return i think
        return;
    }
    
    //...
    public String orderSummary(BigDecimal totalPrice,BigDecimal totalDeposit) {
        //TODO: Not sure why we are passing in the pricing stuff shouldnt all those details be passed in when the booking was created
        return null;
    }
    
}
