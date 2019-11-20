package uk.ac.ed.bikerental;

import java.time.LocalDate;

//Version1
public class Bike {

    public Boolean status;
    public String typeOfBike;
    public BikeStore store;
    //public Booking booking;
    private BikeType type;
    
    
//    private LocalDate dateNew;
//    public Bike(LocalDate dateNew) {
//        this.dateNew = dateNew;
//    }
//    public LocalDate getNewDate() {
//        return this.dateNew;
//    }
    
    public BikeType getType() {
        //assert false;
        return type;
    }
    
    public Boolean checkDates(DateRange dates) {
        // TODO: Implement checkDates Function
        return false;
    }
    
}