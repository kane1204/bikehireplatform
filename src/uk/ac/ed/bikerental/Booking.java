package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Booking {
    public static int BOOKINGS = 0; // global
    public static Collection<Booking> ALLBOOKINGS = new ArrayList<Booking>();
    
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
        this.depositStatus = depositStatuses.COLLECT;
        
        
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
    
    //Setters
    public void setDeliveryService(DeliveryService ds) {
        this.deliveryService = ds;
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
                "\n Total Price: "+ totalPrice + "\n Total Deposit: "+ totalDeposit +"\n";
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
        
        if(this.deliveryService != null) {
            this.depositInDelivery(); //deposit going back to original provider
        }
        else {
            this.depositCollected(); //deposit collected
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bikeDelivery == null) ? 0 : bikeDelivery.hashCode());
        result = prime * result + ((bikes == null) ? 0 : bikes.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((depositStatus == null) ? 0 : depositStatus.hashCode());
        result = prime * result + ((paid == null) ? 0 : paid.hashCode());
        result = prime * result + ((range == null) ? 0 : range.hashCode());
        result = prime * result + ref;
        result = prime * result + ((store == null) ? 0 : store.hashCode());
        result = prime * result + ((totalDeposit == null) ? 0 : totalDeposit.hashCode());
        result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
        Booking other = (Booking) obj;
        if (bikes == null) {
            if (other.bikes != null)
                return false;
        } else if (!bikes.equals(other.bikes))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (range == null) {
            if (other.range != null)
                return false;
        } else if (!range.equals(other.range))
            return false;
        if (ref != other.ref)
            return false;
        if (store == null) {
            if (other.store != null)
                return false;
        } else if (!store.equals(other.store))
            return false;
        if (totalDeposit == null) {
            if (other.totalDeposit != null)
                return false;
        } else if (!totalDeposit.equals(other.totalDeposit))
            return false;
        if (totalPrice == null) {
            if (other.totalPrice != null)
                return false;
        } else if (!totalPrice.equals(other.totalPrice))
            return false;
        return true;
    }
}
