package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here
    private Customer testCustomer;
    private Bike testbike;
    private BikeType testBikeType;
    private String testPartnerShips[];
    private Location testCustomersAddress;
    private Location testCustomersAccom;
    private BikeStore testBikeStore1,testBikeStore2,testBikeStore3;
    private Bike testBike1, testBike2;
    private BikeType testType1, testType2;
    private Collection<BikeStore> allBikeStores;
    

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        Collection<BikeStore> allBikeStores = new ArrayList<BikeStore>();
        testCustomersAddress = new Location("KY118LJ","123 Steeve Street");
        testCustomersAccom = new Location("EH18LB","123 Life Street");
        testCustomer = new Customer("Joe", "Mama", testCustomersAddress, "joemama@yahoo.com", 
                "01245262525", testCustomersAccom);
        testBikeStore1 = new BikeStore("Terrance Store", new Location("EH115LC","123 Hi St"), new String[]{"NeverBike"} ) ;
        testBikeStore2 = new BikeStore("Jeffs Shop", new Location("EH13LC","123 Hola St"), new String[]{""});
        testBikeStore3 = new BikeStore("NeverBike", new Location("KY15LC","123 Bye St"), new String[]{"Terrance Store"} );
        
        allBikeStores.add(testBikeStore1);
        allBikeStores.add(testBikeStore2);
        allBikeStores.add(testBikeStore3);
        
        testType1 = new BikeType("Road", new BigDecimal("1000"), new BigDecimal("300"));
        testType2 = new BikeType("Mountain", new BigDecimal("1200"), new BigDecimal("300"));
        testBike1 = new Bike(testBikeType);
        testBike2 = new Bike(testBikeType);
        testBikeStore1.bikeStock.add(testBike1);
        testBikeStore1.bikeStock.add(testBike2);
        testBikeStore2.bikeStock.add(testBike1);
        testBikeStore2.bikeStock.add(testBike2);
        testBikeStore3.bikeStock.add(testBike1);
        testBikeStore3.bikeStock.add(testBike2);
        
        
        
    }
    
    // TODO: Write system tests covering the three main use cases

    @Test
    @DisplayName("System Test on Getting Quotes")
    void myFirstTest() {
        // JUnit tests look like this
        
        //testCustomer.getAllQuotes(allBikeStores, bikeTypes, dateRange, locationOfHire);
        
        assertEquals("The moon", "cheese"); // Should fail
    }
    @Test
    @DisplayName("System Test on Getting Quotes 2")
    void mySecondTest() {
        // JUnit tests look like this
        
        
        
        assertEquals("The moon", "cheese"); // Should fail
    }
}
