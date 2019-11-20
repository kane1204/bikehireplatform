//*TEST STUFF PROBS NOT NEEDED AT LL*
package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        System.out.println("Linear Depreciation Test");
        
        LinearDepreciation test1 = new LinearDepreciation();
        
        Bike bike = new Bike();
        LocalDate dateBikeNew = LocalDate.of(2016,1,1);
        
        BigDecimal result = test1.calculateValue(bike, dateBikeNew);
        BigDecimal expectedResult = new BigDecimal("630.00");
        
        System.out.println("Result: " + result + "\nExpected Result: " + expectedResult);  
        
    }

}
