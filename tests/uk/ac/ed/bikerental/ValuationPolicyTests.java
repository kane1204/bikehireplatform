package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    private Bike testbike;
    private BikeType testBiketype;
    private LinearDepreciation ld;
    private DoubleDecline dd ;
    @BeforeEach
    void setUp() throws Exception {
       // System.out.println("Next test:");
        testBiketype = new BikeType();
        testbike = new Bike();
        testbike.type = testBiketype;
        ld = new LinearDepreciation();
        dd = new DoubleDecline();
        
        
        
    }
    
    @Test
    @DisplayName("Linear Depreciation Test 1")
    void firstTest() {
        LocalDate dateBikeNew = LocalDate.of(2016,1,1);
        ld.depriciationRate = new BigDecimal("0.1");
        testBiketype.setReplacementValue(new BigDecimal("900"));
            
        BigDecimal result = ld.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("630.00");
        
        assertEquals(expectedResult, result);
    }
 
    @Test
    @DisplayName("Linear Depreciation Test 2")
    void secondTest() {
        LocalDate dateBikeNew = LocalDate.of(2012,1,1);
        ld.depriciationRate = new BigDecimal("0.2");
        testBiketype.setReplacementValue(new BigDecimal("1000"));
            
        BigDecimal result = ld.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("-400.00");
        
        assertEquals(expectedResult, result);
    }
    @Test
    @DisplayName("Linear Depreciation Test 3")
    void thirdTest() {
        LocalDate dateBikeNew = LocalDate.of(2018,1,1);
        ld.depriciationRate = new BigDecimal("0.3");
        testBiketype.setReplacementValue(new BigDecimal("3000"));
            
        BigDecimal result = ld.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("2100.00");
        
        assertEquals(expectedResult, result);
    }
    @Test
    @DisplayName("Double Delclining Balance Depreciation Test")
    void fourthTest() {     
        LocalDate dateBikeNew = LocalDate.of(2016,1,1);
        dd.depriciationRate = new BigDecimal("0.1");
        testBiketype.setReplacementValue(new BigDecimal("900"));
            
        BigDecimal result = dd.calculateValue(testbike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("460.80");
        
        assertEquals(expectedResult, result);
    }
    
}
