package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.util.Scanner;

public class CalcBikeValue {

    public static void main(String[] args) {
        LinearDeprication linearDep = new LinearDeprication();
        DoubleDecline ddDep = new DoubleDecline();
        Bike bike1 = new Bike();
        LocalDate date1 = LocalDate.now();
        
        Scanner scan = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("Choose an option:\n"
                    + "1. Linear deprication\n"
                    + "2. Double decline balance deprication");
            option = scan.nextInt();
            if(option==1) {
                linearDep.calculateValue(bike1, date1);
            } else if(option==2) {
                ddDep.calculateValue(bike1, date1);
            } else {
                System.out.println("-\nPlease choose a valid option: 1 or 2\n-");
            }
        } while(option!=1 && option!=2);
        
    }

}
