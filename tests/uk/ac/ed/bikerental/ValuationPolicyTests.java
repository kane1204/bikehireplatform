package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    private Bike bike;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Next test:");
        bike = new Bike();
        
    }
    
    // TODO: Write tests for valuation policies
    @Test
    @DisplayName("Linear Depreciation Test")
    void firstTest(Bike bike) {
        System.out.println("Linear Depreciation Test");
        
        
        
        LocalDate dateBikeNew = LocalDate.of(2016,1,1);
        LinearDepreciation ld = new LinearDepreciation();
            
        BigDecimal result = ld.calculateValue(bike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("630.00");
        
        assertEquals(expectedResult, result);
    }
 
    @Test
    @DisplayName("Second test")
    void secondTest() {
        System.out.println("Second test method");
    }
}
