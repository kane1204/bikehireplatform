package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        this.totalPrice = new BigDecimal("0");
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
        ld.depreciationRate = store.getDepreciationRate();
        dd.depreciationRate = store.getDepreciationRate();
        
        Iterator<Bike> bikesInQuoteIterator = bikes.iterator();

        while(bikesInQuoteIterator.hasNext()){
            Bike nextBike = bikesInQuoteIterator.next();
            LocalDate dateBikeNew = nextBike.getDateNew();
            
            if(valuationPolicy=="Linear Depreciation") {
                totalPrice = totalPrice.add(ld.calculateValue(nextBike, dateBikeNew));
            }
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
        totalDeposit = totalDeposit.setScale(2, RoundingMode.CEILING); // Round to 2 decimal places as it's money
        return totalDeposit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bikeStore == null) ? 0 : bikeStore.hashCode());
        result = prime * result + ((bikes == null) ? 0 : bikes.hashCode());
        result = prime * result + ((dates == null) ? 0 : dates.hashCode());
        result = prime * result + ((providerName == null) ? 0 : providerName.hashCode());
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
        Quote other = (Quote) obj;
        if (bikeStore == null) {
            if (other.bikeStore != null)
                return false;
        } else if (!bikeStore.equals(other.bikeStore))
            return false;
        if (bikes == null) {
            if (other.bikes != null)
                return false;
        } else if (!bikes.equals(other.bikes))
            return false;
        if (dates == null) {
            if (other.dates != null)
                return false;
        } else if (!dates.equals(other.dates))
            return false;
        if (providerName == null) {
            if (other.providerName != null)
                return false;
        } else if (!providerName.equals(other.providerName))
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
