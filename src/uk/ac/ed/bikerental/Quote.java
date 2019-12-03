package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Quote {
    //Quote info
    public String providerName;
    public BikeStore bikeStore;
    public DateRange dates;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    public Collection<Bike> bikes;
    
    //Define constructor
    public Quote(String providerName, BikeStore bikeStore, DateRange dates,
            Collection<Bike> bikes) {
        super();
        this.providerName = providerName;
        this.bikeStore = bikeStore;
        this.dates = dates;
        this.bikes = bikes;
    }
    
    //Methods
    
    /*
     * For each available bike a store has for the query, calculate it's cost with the specified
     * depreciation rate, then add it to the total cost.
     */
    public BigDecimal calcTotalPrice(BikeStore store, Collection<Bike> bikes) {
        String valuationPolicy = store.getValuationPolicy();
        LinearDepreciation ld = new LinearDepreciation();
        DoubleDecline dd = new DoubleDecline();
        
        Iterator<Bike> bikesInQuoteIterator = bikes.iterator();

        while(bikesInQuoteIterator.hasNext()){
            Bike nextBike = bikesInQuoteIterator.next();
            LocalDate dateBikeNew = nextBike.getDateNew();
            
            if(valuationPolicy=="Linear Depreciation") totalPrice = 
                totalPrice.add(ld.calculateValue(nextBike, dateBikeNew));
            else if(valuationPolicy=="Double Declining Balance Depreciation") totalPrice =
                totalPrice.add(dd.calculateValue(nextBike,dateBikeNew));
            else {
                BikeType type = nextBike.getType();
                totalPrice = type.getReplacementValue();
            }
        }
        return totalPrice;
    }
    
    //Calculate the total deposit using the total cost global variable and passed in deposit rate. 
    public BigDecimal calcTotalDeposit(BigDecimal depositRate) {
        totalDeposit = totalPrice.multiply(depositRate);
        return totalDeposit;
    }
    
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quote other = (Quote) obj;
        return Objects.equals(totalDeposit, other.totalDeposit) && 
               Objects.equals(providerName, other.providerName) && 
               Objects.equals(dates, other.dates) &&
               bikes.equals(other.bikes);
    }
}
