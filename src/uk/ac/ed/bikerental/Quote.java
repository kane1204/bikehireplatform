package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public class Quote {
    public String providerName;
    public BikeStore bikeStore;
    public DateRange dates;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    public Collection<Bike> bikes;
    
    public Quote(String providerName, BikeStore bikeStore, DateRange dates, Collection<Bike> bikes) {
        super();
        this.providerName = providerName;
        this.bikeStore = bikeStore;
        this.dates = dates;
        this.bikes = bikes;
    }
    public BigDecimal calcTotalPrice(BigDecimal dailyPrice, BikeStore store, BikeType type, Bike bike) {
        //dailyprice, store and type will be set in the bikestore method with a getter
        
        //for each bike
        //  totalPrice+=calcBikeCost
        //  totalDeposit+=calcBikeDeposit
        //end for
        String valuationPolicy = store.getValuationPolicy();
        int bikeCost = 0;
        int bikeDeposit = 0;
        LinearDepreciation ld = new LinearDepreciation();
        DoubleDecline dd = new DoubleDecline();
        LocalDate dateBikeNew = bike.getDateNew();
        
        //for next bike in quote
        //      if(valuationPolicy=="Linear Depreciation") totalPrice += ld.calculateValue(type, dateBikeNew);
        //      else if(valuationPolicy=="Double Declining Balance Depreciation"  totalPrice += dd.calculateValue(type, dateBikeNew);
        //next
        return null;
    }
    public BigDecimal calcTotalDeposit(BigDecimal depositRate) {
        //totalDeposit += totalprice * depositRate;
        return null;
    }
}
