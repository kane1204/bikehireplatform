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
    
    public BigDecimal calcTotalPrice(BikeStore store, BikeType type, Bike bike) {
        String valuationPolicy = store.getValuationPolicy();
        LinearDepreciation ld = new LinearDepreciation();
        DoubleDecline dd = new DoubleDecline();
        LocalDate dateBikeNew = bike.getDateNew();
        
        Iterator<Bike> bikesInQuoteIterator = bikes.iterator();

        while(bikesInQuoteIterator.hasNext()){
            Bike nextBike = bikesInQuoteIterator.next();
            
            if(valuationPolicy=="Linear Depreciation") totalPrice = ld.calculateValue(bike,
                    dateBikeNew).add(ld.calculateValue(bike, dateBikeNew));
            else if(valuationPolicy=="Double Declining Balance Depreciation") totalPrice =
                    dd.calculateValue(bike,dateBikeNew).add(dd.calculateValue(bike,dateBikeNew));
        }
        return totalPrice;
    }
    public BigDecimal calcTotalDeposit(BigDecimal depositRate) {
        totalDeposit = totalPrice.multiply(depositRate);
        return totalDeposit;
    }
}
