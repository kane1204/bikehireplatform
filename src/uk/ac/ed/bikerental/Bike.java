package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

public class Bike implements Deliverable{
    //Info on bike
    private enum statuses {
        AVAILABLE,
        UNAVAILABLE,
        BEING_DELIVERED
    }
    private statuses status;
    public String typeOfBike;
    public BikeType type;
    
    //More functional info
    private Collection<DateRange> unavailableDates;
    public BikeStore store;
    
    //Define constructor
    public Bike(BikeStore store,  BikeType type, LocalDate dateNew) {
        super();
        this.type = type;
        this.typeOfBike = type.nameOfType;
        this.status = statuses.AVAILABLE;
    }
    
    //Getters    
    public BikeType getType() {
        return type;
    }
    
    public String getStatus() {
        return status.toString();
    }

    
    //Methods
    //Add all unavailable dates to a collection
    public void addBooking(DateRange dates) {
        unavailableDates.add(dates);
    }
    
    //Return the truth value of a date within the unavailableDates collection and the queried date
    public Boolean checkDates(DateRange dates) {
        Iterator<DateRange> unavailableDatesIterator = unavailableDates.iterator();
        
        while(unavailableDatesIterator.hasNext()){
            DateRange tempDateRange = unavailableDatesIterator.next();
            if(tempDateRange.overlaps(dates)) {
                return false;
            }        
        }
        
        return true;
    }

    //Implement methods
    @Override
    public void onPickup() {
        this.status = statuses.BEING_DELIVERED; //Currently in delivery stage          
    }

    @Override
    public void onDropoff() {
        this.status = statuses.AVAILABLE; //Currently in delivery stage
    }

    public void bikeAvailable() {
        this.status = statuses.AVAILABLE;     
    }

    public void bikeUnavailable() {
        this.status = statuses.UNAVAILABLE;       
    }
    
}