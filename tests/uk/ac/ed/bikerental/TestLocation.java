package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

class TestLocation {
    private Location testStoresLocation;
    
    
    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
        testStoresLocation = new Location("KY12 5EB", "123 bobs Street");
        
    }
    
    @Test
    @DisplayName("isNearTo Test 1")
    void firstTest() {
        String testPostcode = "KY12 8LJ";
        Location testAnotherLocation = new Location(testPostcode,"");
        Boolean expectedResult = true;
        assertEquals(expectedResult, testStoresLocation.isNearTo(testAnotherLocation));
    }
    
    @Test
    @DisplayName("isNearTo Test 2")
    void secondTest() {
        String testPostcode = "EH92 2GA";
        Location testAnotherLocation = new Location(testPostcode,"");
        Boolean expectedResult = true;
        assertEquals(expectedResult, testStoresLocation.isNearTo(testAnotherLocation));
    }
    @Test
    @DisplayName("isNearTo Test 3")
    void thirdTest() {
        String testPostcode = "KH12 8LJ";
        Location testAnotherLocation = new Location(testPostcode,"");
        Boolean expectedResult = true;
        assertEquals(expectedResult, testStoresLocation.isNearTo(testAnotherLocation));
    }
    
    @Test
    @DisplayName("isNearTo Test 4")
    void fourthTest() {
        String testPostcode = "KY12 3GJ";
        Location testAnotherLocation = new Location(testPostcode,"");
        Boolean expectedResult = true;
        assertEquals(expectedResult, testStoresLocation.isNearTo(testAnotherLocation));
    }
}
