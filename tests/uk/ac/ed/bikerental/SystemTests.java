package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        // Put your test setup here
        setUpBikes();
        setUpDates();
        setUpLocations();
        setUpPartners();
    }

    private void setUpBikes() {
        // TODO Auto-generated method stub
        
    }
    
    private void setUpDates() {
        // TODO Auto-generated method stub
        
    }
    
    private void setUpLocations() {
        // TODO Auto-generated method stub
        
    }
    
    private void setUpPartners() {
        // TODO Auto-generated method stub
        
    }


    // TODO: Write system tests covering the three main use cases

    @Test
    @DisplayName("Finding a quote")
    void FindingAQuote() {
        // JUnit tests look like this
        assertEquals("The moon", "cheese"); // Should fail
    }
    
    @Test
    @DisplayName("Finding a quote")
    void BookingAQuote() {
        // JUnit tests look like this
        assertEquals("The moon", "cheese"); // Should fail
    }
    
    @Test
    @DisplayName("Returning a bike to it's original provider")
    void ReturningABikeToProvider() {
        // JUnit tests look like this
        assertEquals("The moon", "cheese"); // Should fail
    }
    
    @Test
    @DisplayName("Returning a bike to a partner of the original provider")
    void ReturningABikeToPartner() {
        // JUnit tests look like this
        assertEquals("The moon", "cheese"); // Should fail
    }

}
