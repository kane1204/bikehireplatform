package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Booking {
    public static int BOOKINGS =0; // global
    public static Collection<Booking> ALLBOOKINGS;
    
    
    //Booking info
    public Customer customer;
    public Location location;
    public Collection<Bike> bikes;
    public int ref;
    public Boolean bikeDelivery;
    public DateRange range;
    public BikeStore store;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    public Boolean paid;
    
    //Define constructor
    public Booking(Customer customer,BikeStore store, Location location, DateRange dateRange, Collection<Bike> bikes,
        int ref,Boolean bikeDelivery,BigDecimal totalPrice, BigDecimal totalDeposit) {
        // reference should be calculated in bookQuote
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
        this.paid = false;
    }
    
    //Methods
    //...
    public Boolean payment() {
        //to simulate payment system
        paid = true;
        return paid;
    }
    
    //...
    public String orderSummary() {
        return  "Customer: "+ customer.getFirstName()+"\n Stores Name: " + store.storeName + 
                "\n Total Price: "+ totalPrice + "\n Total Deposit: "+ totalDeposit ;
    }
    
}
