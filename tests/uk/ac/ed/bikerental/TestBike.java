package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestBike {
    private Bike testBike1, testBike2, testBike3;
    private BikeType bmx, standard, offroad;
    private LocalDate testDate1, testDate2, testDate3, testDate4;
    private DateRange testRange1, testRange2, testRange3, testRange4;
    
    @BeforeEach
    void setUp() throws Exception {
        bmx = new BikeType("BMX", new BigDecimal(100), new BigDecimal(80));
        standard = new BikeType("BMX", new BigDecimal(100), new BigDecimal(80));
        offroad = new BikeType("BMX", new BigDecimal(100), new BigDecimal(80));
        
        testBike1 = new Bike(bmx);
        testBike2 = new Bike(standard);
        testBike3 = new Bike(offroad);
        
        testDate1 = LocalDate.of(2000, 1, 1);
        testDate2 = LocalDate.of(2000, 1, 10);
        testDate3 = LocalDate.of(2000, 1, 5);
        testDate4 = LocalDate.of(2001, 1, 10);
        
        testRange1 = new DateRange(testDate1, testDate2);
        testRange2 = new DateRange(testDate2, testDate3);
        testRange3 = new DateRange(testDate2, testDate4);
        testRange4 = new DateRange(testDate1, testDate4);
    }

    @Test
    @DisplayName("Check getting names")
    void testBikeTypes() {
        assertEquals(testBike1.getType().getType(), bmx.getType());
        assertEquals(testBike2.getType().getType(), standard.getType());
        assertEquals(testBike3.getType().getType(), offroad.getType());
    }
    
    @Test
    @DisplayName("Testing Dates Booked for a Bike") 
    void reserveDates() {      
        testBike1.addBooking(testRange3);
        testBike1.addBooking(testRange2);
        System.out.println("test");
        
        Collection<DateRange> unavailableDates = testBike1.getUnavailableDates();
        Iterator<DateRange> unavailableDatesIterator = unavailableDates.iterator();
        boolean found = false;
        while(unavailableDatesIterator.hasNext()){
            DateRange tempDR = unavailableDatesIterator.next();
            if(tempDR == testRange3) {
                found = true;
            }
        }
        
        assert(found);
    }
    
//    @Test
//    @DisplayName("Testing equality and hash code.")
//    void testEqualsAndHashCode() {
//        assert(bike1.equals(bikeDupe));
//        assertEquals(bike1.hashCode(), bikeDupe.hashCode());
//        
//        assert!(bike1.equals(bikeNotDupe));
//        assertNotEquals(bike1.hashCode(), bikeNotDupe.hashCode());
//    }
}
