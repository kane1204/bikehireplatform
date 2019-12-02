package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

public class Booking {
    public static int BOOKINGS = 0; // global
    public static Collection<Booking> ALLBOOKINGS;
    
    //Booking info
    public Customer customer;
    public Collection<Bike> bikes;
    public int ref;
    public Boolean bikeDelivery;
    public DateRange range;
    public BikeStore store;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    public Boolean paid;
    private DeliveryService deliveryService;
    private enum depositStatuses {
        COLLECT,
        COLLECTED,
        BEING_DELIVERED,
        PARTNER_RETURNED,
        ORIGINAL_RETURNED
    }
    private depositStatuses depositStatus;
    
    //Define constructor
    public Booking(Customer customer, BikeStore store, DateRange dateRange,
            Collection<Bike> bikes, int ref, Boolean bikeDelivery,  BigDecimal totalPrice,
            BigDecimal totalDeposit) {
        // reference should be calculated in bookQuote
        super();
        this.customer = customer;
        this.bikes = bikes;
        this.ref = ref; // Reference should be generated in bookQuote
        this.bikeDelivery = bikeDelivery;
        this.range = dateRange;
        this.store = store;
        this.totalPrice = totalPrice;
        this.totalDeposit = totalDeposit;    
        this.paid = false;
        
        
        if(this.bikeDelivery) {
            this.deliveryService = DeliveryServiceFactory.getDeliveryService();
            Iterator<Bike> bikeIterator = bikes.iterator();
            while(bikeIterator.hasNext()){
                Bike tempBike = bikeIterator.next();
                this.deliveryService.scheduleDelivery(tempBike, store.locationOfStore,
                        customer.getAccommodation(), range.getStart());
            }
        } else {
            this.deliveryService = null;
        }
    }
    
    //Getters
    public DeliveryService getDeliveryService() {
        return deliveryService;
    }
    
    public String getDepositStatus() {
        return depositStatus.toString();
    }
    
    //Methods
    //Simulate payment system
    public Boolean payment() {
        paid = true;
        return paid;
    }
    
    //String to return to customer with all the booking details
    public String orderSummary() {
        return  "Customer: "+ customer.getFirstName()+"\n Stores Name: " + store.storeName + 
                "\n Total Price: "+ totalPrice + "\n Total Deposit: "+ totalDeposit ;
    }
    
    //Deposit statuses
    public void depositCollect() {
        this.depositStatus = depositStatuses.COLLECT;
    }
    
    public void depositCollected() {
        this.depositStatus = depositStatuses.COLLECTED;
    }
    
    public void depositInDelivery() {
        this.depositStatus = depositStatuses.BEING_DELIVERED;
    }
    
    public void depositReturnedToPartner() {
        this.depositStatus = depositStatuses.PARTNER_RETURNED;
    }
    
    public void depositReturnedToProvider() {
        this.depositStatus = depositStatuses.ORIGINAL_RETURNED;
    }
   
    public void bikesBeingDelivered() {
        Iterator<Bike> bikeIterator = bikes.iterator();
        
        while(bikeIterator.hasNext()){
            Bike tempBike = bikeIterator.next();
            tempBike.onPickup(); //being delivered
        }
    }
    
    public void bikesReturned() {
        Iterator<Bike> bikeIterator = bikes.iterator();
        
        while(bikeIterator.hasNext()){
            Bike tempBike = bikeIterator.next();
            if(this.deliveryService != null) {
                tempBike.onDropoff(); //available
            }
            else {
                tempBike.bikeAvailable(); //available
            }
        }
        
        if(this.deliveryService != null) {
            this.depositInDelivery(); //deposit going back to original provider
        }
        else {
            this.depositReturnedToProvider(); //deposit returned to original provider
            
        }
        
    }
    
    public void bikesToBeDeliveredToProvider() {
        Iterator<Bike> bikeIterator = bikes.iterator();
        
        while(bikeIterator.hasNext()){
            Bike tempBike = bikeIterator.next();
            if(this.deliveryService != null) {
                tempBike.onPickup(); //being delivered
            }
            else {
                tempBike.bikeUnavailable(); //unavailable
            }
        }
        
        this.depositCollected(); //deposit is confirmed as collected
        this.depositReturnedToPartner(); //deposit is confirmed as collected to partner
        
        if(this.deliveryService != null) {
            this.depositInDelivery(); //deposit going back to original provider
        }
        else {
            this.depositCollected(); //deposit collected
        }
    }
}
