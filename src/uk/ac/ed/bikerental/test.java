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
        DateRange other = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        
        LocalDate rentalEndDate = end;
        LocalDate otherStartDate = other.getStart();
        DateRange rentToQuery = new DateRange(rentalEndDate, otherStartDate);
        int daysBetween = (int) rentToQuery.toDays();
        int yearsBetween = (int) ChronoUnit.DAYS.between(start,other.getEnd());
        System.out.println(daysBetween);
        
    }

}
