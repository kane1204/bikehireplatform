package uk.ac.ed.bikerental;

import java.time.LocalDate;

//Version1
public class Bike {

    public Boolean status;
    public String typeOfBike;
    public BikeStore store;
    //public Booking booking;
    public BikeType type;
    private LocalDate dateNew;
    
    public LocalDate getDateNew() {
        return this.dateNew;
    }
    
    public void setDateNew(LocalDate dateNew) { //set in test
        this.dateNew = dateNew;
    }
    
    
//    private LocalDate dateNew;
//    public Bike(LocalDate dateNew) {
//        this.dateNew = dateNew;
//    }
//    public LocalDate getNewDate() {
//        return this.dateNew;
//    }
    
    public BikeType getType() {
        return type;
    }
    
    public Boolean checkDates(DateRange dates) {
        // TODO: Implement checkDates Function
        return false;
    }
    
}