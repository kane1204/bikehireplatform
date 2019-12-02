//*TEST STUFF PROBS NOT NEEDED AT LL*
package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2019, 1, 7);
        LocalDate end = LocalDate.of(2019, 1, 10);
        DateRange other = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
        
        LocalDate rentalEndDate = end;
        LocalDate otherStartDate = other.getStart();
        DateRange rentToQuery = new DateRange(rentalEndDate, otherStartDate);
        int daysBetween = (int) rentToQuery.toDays();
        int yearsBetween = (int) ChronoUnit.YEARS.between(end,other.getStart());
        if(daysBetween <= 0 && yearsBetween == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println(daysBetween);
        System.out.println(yearsBetween);
        
    }

}
