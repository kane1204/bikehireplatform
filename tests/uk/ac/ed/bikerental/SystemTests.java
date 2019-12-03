package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SystemTests {
    // You can add attributes here
    private Customer testCustomer1;
    private String testPartnerShips[];
    private Location testCustomer1Address;
    private Location testCustomer1Accom;
    private BikeStore testBikeStore1,testBikeStore2, testBikeStore3;
    private Bike testBike1, testBike2, testBike3, testBike4, testBike5, testBike6, testBike7, testBike8, 
                    testBike9;
    private BikeType testType1, testType2, testType3;
    private Collection<BikeStore> allBikeStores;
    private LocalDate testDate1, testDate2, testDate3, testDate4, testDate5, testDate6, testDate7;
    private DateRange testRange1, testRange2, testRange3, testRange4, testRange5, testRange6;
    

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        this.allBikeStores = new ArrayList<BikeStore>();
        
        testCustomer1Address = new Location("KY118LJ","123 Steeve Street");
        testCustomer1Accom = new Location("EH18LB","123 Life Street");
        testCustomer1 = new Customer("Joe", "Mama", testCustomer1Address, "joemama@yahoo.com", 
                "01245262525", testCustomer1Accom);
        
        testBikeStore1 = new BikeStore("Terrance Store", new Location("EH115LC","123 Hi St"), new String[]{"NeverBike"} ) ;
        testBikeStore2 = new BikeStore("Jeffs Shop", new Location("EH13LC","123 Hola St"), new String[]{""});
        testBikeStore3 = new BikeStore("NeverBike", new Location("KY15LC","123 Bye St"), new String[]{"Terrance Store"} );
        
        allBikeStores.add(testBikeStore1);
        allBikeStores.add(testBikeStore2);
        allBikeStores.add(testBikeStore3);
        
        testType1 = new BikeType("Road", new BigDecimal("1000"), new BigDecimal("300"));
        testType2 = new BikeType("Mountain", new BigDecimal("1200"), new BigDecimal("300"));
        testType3 = new BikeType("BMX", new BigDecimal("2000"), new BigDecimal("900"));
        
        testBike1 = new Bike(testType1);
        testBike2 = new Bike(testType2);
        testBike3 = new Bike(testType3);
        testBike4 = new Bike(testType1);
        testBike5 = new Bike(testType2);
        testBike6 = new Bike(testType3);
        testBike7 = new Bike(testType1);
        testBike8 = new Bike(testType2);
        testBike9 = new Bike(testType3);
        
        testBikeStore1.bikeStock.add(testBike1);
        testBikeStore1.bikeStock.add(testBike2);
        testBikeStore2.bikeStock.add(testBike3);
        testBikeStore3.bikeStock.add(testBike4);
        testBikeStore3.bikeStock.add(testBike5);
        testBikeStore3.bikeStock.add(testBike6);
        
        testDate1 = LocalDate.of(2000, 1, 1);
        testDate2 = LocalDate.of(2000, 1, 10);
        testDate3 = LocalDate.of(2000, 1, 5);
        testDate4 = LocalDate.of(2001, 1, 10);
        testDate5 = LocalDate.of(2000, 6, 10);
        testDate6 = LocalDate.of(2000, 7, 16);
        testDate7 = LocalDate.of(2002, 2, 1);
        
        testRange1 = new DateRange(testDate1, testDate2);
        testRange2 = new DateRange(testDate2, testDate3);
        testRange3 = new DateRange(testDate2, testDate4);
        testRange5 = new DateRange(testDate6, testDate7);
        testRange6 = new DateRange(testDate5, testDate4);
        
        

    }
    
    // TODO: Write system tests covering the three main use cases

    @Test
    @DisplayName("System Test on Getting Quotes")
    void myFirstTest() {
        Collection<Bike> queriedBikes = new ArrayList<Bike>();
        queriedBikes.add(testBike1);
        queriedBikes.add(testBike2);
        
        
        Collection<Bike> quoteBikes1 = new ArrayList<Bike>();
        quoteBikes1.add(testBike1);
        quoteBikes1.add(testBike2);
        
        testBike1.addBooking(testRange1); //testtype 1
        //testBike1.addBooking(testRange2); 
        testBike2.addBooking(testRange2); //testtype2
        
        Quote expectedQuote1 = new Quote("Terrance Store", testBikeStore1, testRange1, queriedBikes);
        Quote expectedQuote2 = new Quote("NeverBike", testBikeStore3, testRange1, queriedBikes);
        
        Iterator<Bike> queriedBikesIterator = quoteBikes1.iterator();
        Collection<BikeType> queriedTypes = new ArrayList<BikeType>();
        while(queriedBikesIterator.hasNext()) {
            Bike tempBike = queriedBikesIterator.next();
            queriedTypes.add(tempBike.getType());
        }
        
        Collection<Quote> quotesRecived = testCustomer1.getAllQuotes(allBikeStores, queriedTypes, 
                testRange6, testCustomer1Accom);
        //testCustomer1.getAllQuotes(allStores, bikeTypes, dateRange, locationOfHire)
        Collection<Quote> quotesActual = new ArrayList<Quote>();
        
        quotesActual.add(expectedQuote1);
        quotesActual.add(expectedQuote2);
        
        assert(quotesActual.containsAll(quotesRecived));
        assert(quotesRecived.size() == 2);
    }
    
//    @Test
//    @DisplayName("System Test on Getting Quotes 2")
//    void mySecondTest() {
//        // JUnit tests look like this
//        
//        
//        
//        assertEquals("The moon", "cheese"); // Should fail
//    }
}
