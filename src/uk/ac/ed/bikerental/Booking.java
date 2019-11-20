package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Booking {
    public Customer customer;
    public Location location;
    public Collection<Bike> bikes;
    public String ref;
    
    public void payment(BigDecimal totalPrice, BigDecimal totalDeposit) {
        //TODO:payment should just return i think
        return;
    }
    public String orderSummary(BigDecimal totalPrice,BigDecimal totalDeposit) {
        //TODO: Not sure why we are passing in the pricing stuff shouldnt all those details be passed in when the booking was created
        return null;
    }
    
}
