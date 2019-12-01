package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

public class Quote {
    public String providerName;
    public BikeStore bikeStore;
    public DateRange dates;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    public Collection<Bike> bikes;
    
    public Quote(String providerName, BikeStore bikeStore, DateRange dates,
            Collection<Bike> bikes) {
        super();
        this.providerName = providerName;
        this.bikeStore = bikeStore;
        this.dates = dates;
        this.bikes = bikes;
    }
    
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
        }
        return totalPrice;
    }
    public BigDecimal calcTotalDeposit(BigDecimal depositRate) {
        totalDeposit = totalPrice.multiply(depositRate);
        return totalDeposit;
    }
}
