package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    private Bike testbike;
    private BikeType testBikeType;
    private LinearDepreciation ld;
    private DoubleDecline dd;
    private String testPartnerShips[];
    private Location testStoresLocation;
    private BikeStore testStore;
    /*
     * Set Up:
     * Create objects for a bike, a bike type and the 2 types of depreciation.
     * Set the bike's bike type
     */
    
    @BeforeEach
    void setUp() throws Exception {
        // Create objects for each test
        //testPartnerShips[0] =  {"Test Store 2"};
        testStoresLocation = new Location("KY12 5EB", "123 bobs Street");
        testStore = new BikeStore("Test Store",testStoresLocation,testPartnerShips);
        testBikeType = new BikeType("Race", new BigDecimal("0"), new BigDecimal("0"));
        testbike = new Bike(testBikeType);
        testbike.type = testBikeType;
        ld = new LinearDepreciation();
        dd = new DoubleDecline();
    }
    
    /*
     * Linear Depreciation Tests:
     * A LinearDepreciation object has been created globally.
     * We must set a date at which the bike was band new at.
     * Set a depreciation rate and replacement value.
     * Compare the result from the method implemented from class ValuationPolicy with the correct value
     */
    
    @Test
    @DisplayName("Linear Depreciation Test 1")
    void firstTest() {
        LocalDate dateBikeNew = LocalDate.of(2016,1,1);
        ld.depriciationRate = new BigDecimal("0.1");
        testBikeType.setReplacementValue(new BigDecimal("900"));
            
        BigDecimal result = ld.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("630");
        
        assertEquals(expectedResult.stripTrailingZeros(), result.stripTrailingZeros());
    }
 
    @Test
    @DisplayName("Linear Depreciation Test 2")
    void secondTest() {
        LocalDate dateBikeNew = LocalDate.of(2012,1,1);
        ld.depriciationRate = new BigDecimal("0.2");
        testBikeType.setReplacementValue(new BigDecimal("1000"));
            
        BigDecimal result = ld.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("-400");
        
        assertEquals(expectedResult.stripTrailingZeros(), result.stripTrailingZeros());
    }
    
    @Test
    @DisplayName("Linear Depreciation Test 3")
    void thirdTest() {
        LocalDate dateBikeNew = LocalDate.of(2018,1,1);
        ld.depriciationRate = new BigDecimal("0.3");
        testBikeType.setReplacementValue(new BigDecimal("3000"));
            
        BigDecimal result = ld.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("2100");
        
        assertEquals(expectedResult.stripTrailingZeros(), result.stripTrailingZeros());
    }
    
    /*
     * Double Declining Balance Depreciation Tests:
     * A DoubleDelining object has been created globally.
     * We must set a date at which the bike was band new at.
     * Set a depreciation rate and replacement value.
     * Compare the result from the method implemented from class ValuationPolicy with the correct
     * value
     */
    
    @Test
    @DisplayName("Double Declining Balance Depreciation Test 1")
    void fourthTest() {     
        LocalDate dateBikeNew = LocalDate.of(2016,1,1);
        dd.depriciationRate = new BigDecimal("0.1");
        testBikeType.setReplacementValue(new BigDecimal("900"));
        
        BigDecimal result = dd.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("460.8");
        
        assertEquals(expectedResult.stripTrailingZeros(), result.stripTrailingZeros());
    }
    
    @Test
    @DisplayName("Double Declining Balance Depreciation Test 2")
    void fifthTest() {
        LocalDate dateBikeNew = LocalDate.of(2012,1,1);
        dd.depriciationRate = new BigDecimal("0.2");
        testBikeType.setReplacementValue(new BigDecimal("1000"));
            
        BigDecimal result = dd.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("28");
        
        assertEquals(expectedResult.stripTrailingZeros(), result.stripTrailingZeros());
    }
    
    @Test
    @DisplayName("Double Declining Balance Depreciation Test 3")
    void sixthTest() {
        LocalDate dateBikeNew = LocalDate.of(2018,1,1);
        dd.depriciationRate = new BigDecimal("0.3");
        testBikeType.setReplacementValue(new BigDecimal("3000"));
            
        BigDecimal result = dd.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("1200");
        
        assertEquals(expectedResult.stripTrailingZeros(), result.stripTrailingZeros());
    }
    
}
