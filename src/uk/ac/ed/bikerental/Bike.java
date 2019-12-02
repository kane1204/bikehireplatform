package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

public class Bike implements Deliverable{
    //Info on bike
    public Boolean status;
    public String typeOfBike;
    public BikeType type;
    private LocalDate dateNew;
    
    //More functional info
    private Collection<DateRange> unavailableDates;
    public BikeStore store;
    
    //Define constructor
    public Bike(BikeStore store,  BikeType type, LocalDate dateNew) {
        super();
        this.type = type;
        this.typeOfBike = type.nameOfType;
        this.dateNew = dateNew;
    }
    
    //Getters
    public LocalDate getDateNew() {
        return this.dateNew;
    }
    
    public BikeType getType() {
        return type;
    }
    
    //Setters
    public void setDateNew(LocalDate dateNew) { //set in test
        this.dateNew = dateNew;
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

    @Override
    public void onPickup() {
        // TODO this is for retrieving the bike either via delivery or from the store
        this.status = true;
        
         
    }

    @Override
    public void onDropoff() {
        // TODO used when returning the bikes ie 3.        
        this.status = false;
    }
    
}