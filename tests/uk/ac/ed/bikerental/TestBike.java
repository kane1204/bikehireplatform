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
    //Attributes to be set up
    private Bike testBike1, testBike2, testBike3, bikeDupe1;
    private BikeType testType1, testType2, testType3;
    private LocalDate testDate1, testDate2, testDate3, testDate4;
    private DateRange testRange1, testRange2, testRange3, testRange4;
    
    @BeforeEach
    void setUp() throws Exception {
        //Create bike types
        testType1 = new BikeType("Road", new BigDecimal("1000"), new BigDecimal("300"));
        testType2 = new BikeType("Mountain", new BigDecimal("1200"), new BigDecimal("300"));
        testType3 = new BikeType("BMX", new BigDecimal("2000"), new BigDecimal("900"));
        
        //Create bikes
        testBike1 = new Bike(testType1, null);
        testBike2 = new Bike(testType2,null);
        testBike3 = new Bike(testType3,null);
        
        bikeDupe1 = testBike1;
        
        //Create dates
        testDate1 = LocalDate.of(2000, 1, 1);
        testDate2 = LocalDate.of(2000, 1, 10);
        testDate3 = LocalDate.of(2000, 1, 5);
        testDate4 = LocalDate.of(2001, 1, 10);
        
        //Create ranges of dates        
        testRange1 = new DateRange(testDate1, testDate2);
        testRange2 = new DateRange(testDate2, testDate3);
        testRange3 = new DateRange(testDate2, testDate4);
        testRange4 = new DateRange(testDate1, testDate4);
    }

    //Testing different aspect of the bike objects
    @Test
    @DisplayName("Check getting names")
    void testBikeTypes() {
        assertEquals(testBike1.getType().getType(), testType1.getType());
        assertEquals(testBike2.getType().getType(), testType2.getType());
        assertEquals(testBike3.getType().getType(), testType3.getType());
    }
    
    @Test
    @DisplayName("Testing Dates Booked for a Bike") 
    void reserveDates() {      
        testBike1.addBooking(testRange3);
        testBike1.addBooking(testRange2);
        
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
    
    @Test
    @DisplayName("Testing equality and hash code.")
    void testEqualsAndHashCode() {
        assert(testBike1.equals(bikeDupe1));
        assertEquals(testBike1.hashCode(), bikeDupe1.hashCode());
    }
}
