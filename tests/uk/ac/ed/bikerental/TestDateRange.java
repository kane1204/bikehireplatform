package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    //Attributes to set up
    private DateRange dateRange1, dateRange2, dateRange3, dateRange4;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        this.dateRange4 = new DateRange(LocalDate.of(2019, 1, 11),
                LocalDate.of(2019, 1, 27));
    }

    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }

    //Check a few different overlaps of dates returning true or false, for different test data
    @Test
    void testOverlapsTrue() {     
        assertEquals(true, dateRange1.overlaps(dateRange2));
    }

    @Test
    void testOverlapsFalse() {
        assertEquals(false, dateRange1.overlaps(dateRange3));
    }
    @Test
    void testOverlapsFalse2() {
        assertEquals(false, dateRange1.overlaps(dateRange4));
    }
}
