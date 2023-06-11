import java.io.*;
import java.util.*;
public class Driver {
    public static void main(String [] args) {
        //Test no argument constructor
        Polynomial p = new Polynomial();
        System.out.println("No argument constructor: " + Arrays.toString(p.nonzerocoefficents) + " " + Arrays.toString(p.exponents));
        //Test argument constructor
        double [] nonzerocoeffs = {1.0, 2.0, 3.0};
        int [] expon = {1, 2, 3};
        Polynomial p2 = new Polynomial(nonzerocoeffs, expon);
        System.out.println("Argument constructor: " + Arrays.toString(p2.nonzerocoefficents) + " " + Arrays.toString(p2.exponents));
        //Test file constructor
        File f = new File("poly.txt");
        Polynomial p3 = new Polynomial(f);
        System.out.println("File constructor: " + Arrays.toString(p3.nonzerocoefficents) + " " + Arrays.toString(p3.exponents));

        //Test add method
        Polynomial p4 = p2.add(p3);
        System.out.println("Add method: " + Arrays.toString(p4.nonzerocoefficents) + " " + Arrays.toString(p4.exponents));

        //Test evaluate method
        System.out.println("Evaluate method: " + p4.evaluate(2.0));

        //Test hasRoot method
        System.out.println("Has root method: " + p4.hasRoot(2.0));

        //Test multiply method
        Polynomial p5 = p2.multiply(p3);
        System.out.println("Multiply method: " + Arrays.toString(p5.nonzerocoefficents) + " " + Arrays.toString(p5.exponents));

        //Test saveToFile method
        p5.saveToFile("test.txt");

        System.out.println("Testing zero polynomaial");
        double [] zeroCoeffs = {0.0};
        int [] zeroExponents = {0};
        Polynomial p6 = new Polynomial(zeroCoeffs, zeroExponents);
        System.out.println("Zero polynomial: " + Arrays.toString(p6.nonzerocoefficents) + " " + Arrays.toString(p6.exponents));
        Polynomial p7 = p6.add(p6);
        System.out.println("Zero polynomial add: " + Arrays.toString(p7.nonzerocoefficents) + " " + Arrays.toString(p7.exponents));
        Polynomial p8 = p6.multiply(p6);
        System.out.println("Zero polynomial multiply: " + Arrays.toString(p8.nonzerocoefficents) + " " + Arrays.toString(p8.exponents));
        System.out.println("Zero polynomial evaluate: " + p6.evaluate(2.0));
        System.out.println("Zero polynomial hasRoot: " + p6.hasRoot(2.0));
        //Test multiply zero polynomial
        Polynomial p9 = p2.multiply(p6);
        System.out.println("Multiply zero polynomial: " + Arrays.toString(p9.nonzerocoefficents) + " " + Arrays.toString(p9.exponents));
        

        //Print out the polynomial's non-zero coefficients and exponents
        System.out.println("Polynomial: " + Arrays.toString(p.nonzerocoefficents) + " " + Arrays.toString(p.exponents));

        double [] x  = {3.0,2.0,7.0};
        int [] x_expon = {0,1,2};

        double [] q = {4,9,4,8};
        int [] q_expon = {0,1,3,2};
        Polynomial poly_for_multiply1 = new Polynomial(x, x_expon);
        Polynomial poly_for_multiply2 = new Polynomial(q, q_expon);

        Polynomial multiplied = poly_for_multiply1.multiply(poly_for_multiply2);

        System.out.println("Multiply: " + Arrays.toString(poly_for_multiply1.multiply(poly_for_multiply2).nonzerocoefficents) + " " + Arrays.toString(poly_for_multiply1.multiply(poly_for_multiply2).exponents));

        //Save to file
        multiplied.saveToFile("test.txt");

        double [] h = {1.0};
        int [] h_expon = {1};
        double [] h1 = {1.0, 2};
        int [] h1_expon = {2, 0};
        Polynomial multiply_test_2 = new Polynomial(h ,h_expon);
        Polynomial multiply_test_3 = new Polynomial(h1, h1_expon);

        Polynomial multiplied2 = multiply_test_2.multiply(multiply_test_3);
        System.out.println("Multiply: " + Arrays.toString(multiplied2.nonzerocoefficents) + " " + Arrays.toString(multiplied2.exponents));

        //Testing x^2+2x * 2x+1

        double [] x2 = {1.0,2.0};
        int [] x2_expon = {2,1};
        double [] x3 = {2.0, 1.0};
        int [] x3_expon = {1, 0};

        Polynomial multiply_test_4 = new Polynomial(x2, x2_expon);
        Polynomial multiply_test_5 = new Polynomial(x3, x3_expon);

        Polynomial multiplied3 = multiply_test_4.multiply(multiply_test_5);
        System.out.println("Multiply: " + Arrays.toString(multiplied3.nonzerocoefficents) + " " + Arrays.toString(multiplied3.exponents));

   
        Polynomial zerPolynomial = new Polynomial(zeroCoeffs, zeroExponents);
        //Zero polynomial multiplied by another polynomial
        Polynomial zeroMultiplied = multiplied3.multiply(zerPolynomial);
        System.out.println("Zero multiplied: " + Arrays.toString(zeroMultiplied.nonzerocoefficents) + " " + Arrays.toString(zeroMultiplied.exponents));

        //Zero polynomial added to another polynomial
        Polynomial zeroAdded = multiplied3.add(zerPolynomial);
        System.out.println("Zero added: " + Arrays.toString(zeroAdded.nonzerocoefficents) + " " + Arrays.toString(zeroAdded.exponents));



    }
}