package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;

import uk.ac.ed.bikerental.Bike.statuses;

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
    //Attributes to be set up
    private Customer testCustomer1, testCustomer2;
    private String testPartnerShips[];
    private Location testCustomer1Address, testCustomer2Address;
    private Location testCustomer1Accom, testCustomer2Accom;
    private BikeStore testBikeStore1,testBikeStore2, testBikeStore3;
    private Bike testBike1, testBike2, testBike3, testBike4, testBike5, testBike6, testBike7,
            testBike8, testBike9;
    private BikeType testType1, testType2, testType3;
    private Collection<BikeStore> allBikeStores;
    private LocalDate testDate1, testDate2, testDate3, testDate4, testDate5, testDate6, testDate7;
    private DateRange testRange1, testRange2, testRange3, testRange4, testRange5, testRange6;
    

    @BeforeEach
    void setUp() throws Exception {
        //Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        
        this.allBikeStores = new ArrayList<BikeStore>(); //Ready to store all bike stores
        Booking.BOOKINGS = 0; //Before each test, set the number of bookings to zero
        
        //Test data for dates
        testDate1 = LocalDate.of(2015, 1, 1);
        testDate2 = LocalDate.of(2015, 1, 10);
        testDate3 = LocalDate.of(2015, 1, 5);
        testDate4 = LocalDate.of(2016, 1, 10);
        testDate5 = LocalDate.of(2015, 6, 10);
        testDate6 = LocalDate.of(2015, 7, 16);
        testDate7 = LocalDate.of(2017, 2, 1);
        
        //Test data for different date ranges
        testRange1 = new DateRange(testDate1, testDate2);
        testRange2 = new DateRange(testDate3, testDate2);
        testRange3 = new DateRange(testDate2, testDate4);
        testRange5 = new DateRange(testDate6, testDate7);
        testRange6 = new DateRange(testDate5, testDate4);
        
        //Test data for customers
        testCustomer1Address = new Location("KY118LJ","123 Steeve Street");
        testCustomer1Accom = new Location("EH18LB","123 Life Street");
        testCustomer1 = new Customer("Joe", "Mama", testCustomer1Address, "joemama@yahoo.com", 
                "01245262525", testCustomer1Accom);
        testCustomer2Address = new Location("KY118LJ","123 Steeve Street");
        testCustomer2Accom = new Location("ML18LB","123 Life Street");
        testCustomer2 = new Customer("Joe", "Mama", testCustomer1Address, "joemama@yahoo.com", 
                "01245262525", testCustomer1Accom);
        
        //Test data for different stores
        testBikeStore1 = new BikeStore("Terrance Store", new Location("EH115LC","123 Hi St"),
                new String[]{"NeverBike"} ) ;
        testBikeStore2 = new BikeStore("Jeffs Shop", new Location("G42 5AF","123 Hola St"),
                new String[]{""});
        testBikeStore3 = new BikeStore("NeverBike", new Location("ML5HLC","123 Bye St"),
                new String[]{"Terrance Store"} );
        
        //Add these stores to the list of all bike stores
        allBikeStores.add(testBikeStore1);
        allBikeStores.add(testBikeStore2);
        allBikeStores.add(testBikeStore3);
        
        //Test data for different bike types
        testType1 = new BikeType("Road", new BigDecimal("1000"), new BigDecimal("300"));
        testType2 = new BikeType("Mountain", new BigDecimal("1200"), new BigDecimal("300"));
        testType3 = new BikeType("BMX", new BigDecimal("2000"), new BigDecimal("900"));
        
        //Test data for different bike, assigning their type and the date they were new at
        testBike1 = new Bike(testType1,testDate7);
        testBike2 = new Bike(testType2,testDate7);
        testBike3 = new Bike(testType3,testDate7);
        testBike4 = new Bike(testType1,testDate7);
        testBike5 = new Bike(testType2,testDate7);
        testBike6 = new Bike(testType3,testDate7);
        testBike7 = new Bike(testType1,testDate7);
        testBike8 = new Bike(testType2,testDate7);
        testBike9 = new Bike(testType3,testDate7);
        
        //Adding bikes to the stocks of different stores
        testBikeStore1.bikeStock.add(testBike1);
        testBikeStore1.bikeStock.add(testBike2);
        testBikeStore2.bikeStock.add(testBike3);
        testBikeStore3.bikeStock.add(testBike4);
        testBikeStore3.bikeStock.add(testBike5);
        testBikeStore3.bikeStock.add(testBike6);
        
        //Set the valuation policy of different stores
        testBikeStore1.setValuationPolicy("Linear Depreciation"); //extension
        testBikeStore2.setValuationPolicy("Double Declining Balance Depreciation"); //extension
        testBikeStore3.setValuationPolicy("default"); //default
        
        //Set the deposit rate of of different stores
        testBikeStore1.setDepositRate(new BigDecimal("0.1"));
        testBikeStore2.setDepositRate(new BigDecimal("0.2"));
        testBikeStore3.setDepositRate(new BigDecimal("0.3"));
        
        //Set the depreciation rate of of different stores
        testBikeStore1.setDepreciationRate(new BigDecimal("0.3"));
        testBikeStore2.setDepreciationRate(new BigDecimal("0.2"));
        testBikeStore3.setDepreciationRate(new BigDecimal("0.1"));
    }
    
    //System tests covering the three main use cases

    //Test use case 1: Find a quote
    @Test
    @DisplayName("System Test on Getting 1 Quote, No Bookings")
    void GetQuotesNoBookings() {
        //Actual actual results
        Collection<BikeType> queriedBikeTypes = new ArrayList<BikeType>();
        queriedBikeTypes.add(testType1);
        
        Collection<Bike> expectedBikes = new ArrayList<Bike>();
        expectedBikes.add(testBike1);
        
        //Expected query results
        ArrayList<Quote> quotesExpected = new ArrayList<Quote>();
        quotesExpected.add(new Quote("Terrance Store", testBikeStore1, testRange1,
                expectedBikes));
                
        ArrayList<Quote> quotesActual = (ArrayList<Quote>) testCustomer1.getAllQuotes(allBikeStores,
                queriedBikeTypes, testRange1, testCustomer1Accom);
        
        //Find the quote (there is only 1 so it is the next quote)
        Iterator<Quote> quotesActualIterator = quotesActual.iterator();
        Quote actualQuote = quotesActualIterator.next();
        
        //Find the quote (there is only 1 so it is the next quote)
        Iterator<Quote> quotesExpectedIterator = quotesExpected.iterator();
        Quote expectedQuote = quotesExpectedIterator.next();
        
        //Compare the quotes 
        assertEquals(actualQuote, expectedQuote);
    }
    
    @Test
    @DisplayName("System Test on Getting 1 Quote, With Bookings")
    void GetQuotesWithBookings() {
        //Actual actual results
        Collection<BikeType> queriedBikeTypes = new ArrayList<BikeType>();
        queriedBikeTypes.add(testType2);
        queriedBikeTypes.add(testType3);
        
        Collection<Bike> expectedBikes = new ArrayList<Bike>();
        expectedBikes.add(testBike5);
        expectedBikes.add(testBike6);
            
        testBike5.addBooking(testRange1);
        testBike6.addBooking(testRange2);

        //Expected query results
        ArrayList<Quote> quotesExpected = new ArrayList<Quote>();
        quotesExpected.add(new Quote("NeverBike", testBikeStore3, testRange5,
                expectedBikes));
                
        ArrayList<Quote> quotesActual = (ArrayList<Quote>) testCustomer2.getAllQuotes(allBikeStores,
                queriedBikeTypes, testRange5, testCustomer2Accom);
        
        //Find the quote (there is only 1 so it is the next quote)
        Iterator<Quote> quotesActualIterator = quotesActual.iterator();
        Quote actualQuote = quotesActualIterator.next();
        while(quotesActualIterator.hasNext()) {
            Quote y = quotesActualIterator.next();
            System.out.println(y.providerName.toString());
            System.out.println(y.bikeStore.toString());
            System.out.println(y.dates.toString());
            System.out.println(y.bikes.toString());
            System.out.println("");
        }
             
        
        //Find the quote (there is only 1 so it is the next quote)
        Iterator<Quote> quotesExpectedIterator = quotesExpected.iterator();
        Quote expectedQuote = quotesExpectedIterator.next();
        while(quotesExpectedIterator.hasNext()) {
            Quote y = quotesExpectedIterator.next();
            System.out.println(y.providerName.toString());
            System.out.println(y.bikeStore.toString());
            System.out.println(y.dates.toString());
            System.out.println(y.bikes.toString());
            System.out.println("");
        }
        
        //Compare the quotes 
        //assertEquals(actualQuote, expectedQuote);
        assertTrue(quotesExpected.containsAll(quotesActual)
                && quotesActual.size() == 1
                && quotesActual.size() == quotesExpected.size());
    }
    
  //Test use case 2: Book a quote
    @Test
    @DisplayName("System Test on Booking Quotes w/out Delivery")
    void BookNoDelivery() {       
        Collection<Bike> quoteBikes = new ArrayList<Bike>();
        quoteBikes.add(testBike4);
        quoteBikes.add(testBike5);
        Booking expBooking = new Booking(testCustomer1, testBikeStore1, testRange1, quoteBikes, 0,
                false, new BigDecimal("880.00"), new BigDecimal("88.00"));
        Quote quoteToBook = new Quote("Terrance Store", testBikeStore1, testRange1, quoteBikes);
        
        Booking returnedBooking = testCustomer1.bookQuote(quoteToBook, false);
        assertEquals(returnedBooking, expBooking);
    }
    
    @Test
    @DisplayName("System Test on Booking Quotes w/ Delivery")
    void BookWithDelivery() {
        
        Collection<Bike> quoteBikes = new ArrayList<Bike>();
        quoteBikes.add(testBike1);
        Booking expBooking = new Booking(testCustomer1, testBikeStore1, testRange1, quoteBikes, 0,
                true, new BigDecimal("400.00"), new BigDecimal("40.00"));
        Quote quoteToBook = new Quote("Terrance Store", testBikeStore1, testRange1, quoteBikes);
        
        Booking returnedBooking = testCustomer1.bookQuote(quoteToBook, true);
        assertEquals(returnedBooking, expBooking);
    }
    
    //Test use case 3: Return a bike
    @Test
    @DisplayName("Return via Original Provider")
    void ReturnViaProvider() {      
        Collection<Bike> quoteBikes = new ArrayList<Bike>();
        quoteBikes.add(testBike1);
        quoteBikes.add(testBike2);
        Quote quoteToBook = new Quote("Terrance Store", testBikeStore1, testRange1, quoteBikes);    
        
        testCustomer1.bookQuote(quoteToBook, true); 
        testBike1.bikeUnavailable();
        testBike2.bikeUnavailable();  
        testBikeStore1.returnBikeToProvider(0);
        
        Boolean temp = false;
        if(testBike2.getStatus() == "AVAILABLE" && testBike1.getStatus() == "AVAILABLE" ) {
            temp = true;
        }
        assertEquals( temp,true );
    }
    
    @Test
    @DisplayName("Returned to Partner and send on delivery")
    void ReturnViaPartner() {      
        Collection<Bike> quoteBikes = new ArrayList<Bike>();
        quoteBikes.add(testBike1);
        quoteBikes.add(testBike2);
        Quote quoteToBook = new Quote("Terrance Store", testBikeStore1, testRange1, quoteBikes);    
        
        Booking tempBooking = testCustomer1.bookQuote(quoteToBook, true); 
        //testBike1.bikeUnavailable();
        //testBike2.bikeUnavailable();  
        testBikeStore3.returnBikesAsPartner(0);
        Boolean temp = false;
        if(testBike2.getStatus() == "BEING_DELIVERED" && testBike1.getStatus() == "BEING_DELIVERED" && tempBooking.getDepositStatus() == "BEING_DELIVERED") {
            temp = true;
        }
        assertEquals( temp,true );
    }
    
    @Test
    @DisplayName("Returned to provider via delivery")
    void ReturnViaDeliverytoProvider() {      
        Collection<Bike> quoteBikes = new ArrayList<Bike>();
        quoteBikes.add(testBike1);
        quoteBikes.add(testBike2);
        Quote quoteToBook = new Quote("Terrance Store", testBikeStore1, testRange1, quoteBikes);    
        
        Booking tempBooking = testCustomer1.bookQuote(quoteToBook, true); 
        //testBike1.bikeUnavailable();
        //testBike2.bikeUnavailable();  
        
        testBikeStore3.returnBikesAsPartner(0);
        //System.out.println(testBike2.getStatus());
        testBikeStore1.returnBikesFromPartner(0);
        System.out.println(tempBooking.getDepositStatus());
        assertEquals(tempBooking.getDepositStatus(),"BEING_DELIVERED");
        tempBooking.setDeliveryService(null);
        testBikeStore1.returnBikeToProvider(0);
        System.out.println(tempBooking.getDepositStatus());
        Boolean temp = false;
        if(testBike2.getStatus() == "AVAILABLE" && testBike1.getStatus() == "AVAILABLE" && tempBooking.getDepositStatus() == "ORIGINAL_RETURNED") {
            temp = true;
        }
        assertEquals(temp, true);
    }
    //Test multiple use cases in a single test
    //...
    
    
}