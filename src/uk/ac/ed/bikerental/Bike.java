package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

//Version1
public class Bike {

    //public Boolean status;
    public String typeOfBike;
    public BikeStore store;
    //public Booking booking;
    public BikeType type;
    private LocalDate dateNew;
    private Collection<DateRange> unavailableDates;
    
    public Bike(BikeStore store,  BikeType type, LocalDate dateNew) {
        this.type = type;
        this.typeOfBike = type.nameOfType;
        this.dateNew =dateNew;
    }
    
    public void addBooking(DateRange dates) {
        unavailableDates.add(dates);
    }
    
    public LocalDate getDateNew() {
        return this.dateNew;
    }
    
    public void setDateNew(LocalDate dateNew) { //set in test
        this.dateNew = dateNew;
    }
    
    public BikeType getType() {
        return type;
    }
    
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
    
}