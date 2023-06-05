import java.io.*;
import java.util.*;
public class Driver {
    public static void main(String [] args) {
        double [] coefficents = {1.0, 2.0, 3.0};
        int [] exponents = {1, 2, 3};
        Polynomial p = new Polynomial(coefficents, exponents);
        System.out.println(p.evaluate(2.0));
        //Try add method
        double [] coefficents2 = {1.0, 2.0, 3.0};
        int [] exponents2 = {1, 2, 3};
        Polynomial p2 = new Polynomial(coefficents2, exponents2);
        p2.add(p);
        System.out.println(p2.evaluate(2.0));
        //Try multiply method
        double [] coefficents3 = {1.0, 2.0, 3.0};
        int [] exponents3 = {1, 2, 3};
        Polynomial p3 = new Polynomial(coefficents3, exponents3);
        p3.multiply(p);
        for (double i: p3.nonzerocoefficents){
            System.out.println("Coefficents of p3 after multiplication " + i);
        }
        for(int i: p3.exponents){
            System.out.println("Exponents of p3 after multiplication " + i);
        }
        //Try saveToFile method
        p3.saveToFile("test.txt");
    }
}