import java.io.*;
import java.util.*;
public class Polynomial{
    double [] nonzerocoefficents;
    int [] exponents;

    //No arguement constructor
    public Polynomial(){
        //Changed due to piazza post listing
        this.nonzerocoefficents = null;
        this.exponents = null;

    }

    //Arguement constuctor
    public Polynomial(double [] nonzerocoefficents, int [] exponents){
        this.nonzerocoefficents = nonzerocoefficents;
        this.exponents = exponents;
    }

    //File constructor
    public Polynomial(File f){
        try {
            //Polynomial will be formatted like this: 5x-3x2+2x3
            Scanner sc = new Scanner(f);
            String s = sc.nextLine();
            //Consider the fact that the polynomial may have a negative coefficient
            s = s.replaceAll("-", "+-");
            String [] split = s.split("\\+");
            double [] nonzerocoeffs = new double[split.length];
            int [] expon = new int[split.length];
            for(int i = 0; i < split.length; i++){
                if(split[i].contains("x")){
                    String [] split2 = split[i].split("x");
                    nonzerocoeffs[i] = Double.parseDouble(split2[0]);
                    expon[i] = Integer.parseInt(split2[1]);
                }
                else{
                    nonzerocoeffs[i] = Double.parseDouble(split[i]);
                    expon[i] = 0;
                }
                
            }
            sc.close();
            this.nonzerocoefficents = nonzerocoeffs;
            this.exponents = expon; 
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //add method, recheck
    //Recall redundant exponents are allowed
    //We must remove all 0 entries
    public Polynomial add(Polynomial p){
        //We are allowed to have redundant exponents, so we can just add the arrays
        //Check if either array is null or empty
        double [] zeroCoeffs = {0.0};
        if(this.nonzerocoefficents == null || p.nonzerocoefficents == null){
            return null;
        }
        if(this.nonzerocoefficents.length == 0){
            //Empty array means 0 polynomial
            this.nonzerocoefficents = zeroCoeffs;
        }
        if(p.nonzerocoefficents.length == 0){
            p.nonzerocoefficents = zeroCoeffs;
        }
        for(int i = 0; i < this.nonzerocoefficents.length; i++){
            //i = 0, 5, 0 non-zero and expon respectively
            //We want to traverse p's non-zero exponent's to see if there's a 0
            //If there is a 0, we want to add the coefficent to the coefficent of the same exponent
            //If there is no 0, we want to add the coefficent and exponent to the end of the array, but do this after we search through app of our exponents
            for(int j = 0; j < p.nonzerocoefficents.length; j++){
                if(this.exponents[i] == p.exponents[j]){
                    this.nonzerocoefficents[i] += p.nonzerocoefficents[j];
                }
            }
        }
        //Deal with removing zeros
        int numzero = 0;
        for(int i = 0; i < this.nonzerocoefficents.length; i++){
            if(this.nonzerocoefficents[i] == 0){
                numzero++;
            }
        }
        double [] newcoeffs = new double[this.nonzerocoefficents.length - numzero];
        int count = 0;
        for(int i = 0; i < this.nonzerocoefficents.length; i++){
            if(this.nonzerocoefficents[i] != 0){
                newcoeffs[count] = this.nonzerocoefficents[i];
                count++;
            }
        }
        this.nonzerocoefficents = newcoeffs;
        return this;
    }

    //evaluate method
    public double evaluate(double x){
        if(nonzerocoefficents == null || nonzerocoefficents.length == 0){
            return 0;
        }
        double sum = 0;
        for(int i = 0; i < nonzerocoefficents.length; i++){
            sum += nonzerocoefficents[i] * Math.pow(x, exponents[i]);
        }
        return sum;
    }

    //hasRoot method
    public boolean hasRoot(double x){
        if(nonzerocoefficents == null || nonzerocoefficents.length == 0){
            return false;
        }
        if(evaluate(x) == 0){
            return true;
        }
        return false;
    }

    public Polynomial multiply(Polynomial p){
        //For each coefficent in this, we want to multiply it by each coefficent in p, and then add the exponents
        //New array for coefficents and exponents
        int size1 = nonzerocoefficents.length;
        int size2 = p.nonzerocoefficents.length;
        double [] zeroCoeffs = {0.0};
        double [] empty = {};
        if(size1==0){
            this.nonzerocoefficents = zeroCoeffs;
        }
        if(size2==0){
            p.nonzerocoefficents = zeroCoeffs;
        }
        if(size1==0 && size2 == 0){
            int [] zeroExponents = {0};
            return new Polynomial(empty, zeroExponents);
        }
        double[] resultCoefficients = new double[size1 + size2 - 1];
        int[] resultExponents = new int[size1 + size2 - 1];

        //Exception of 0 polynomial and 1 polynomial
        if (size1 == 0 || size2 == 0) {
            return new Polynomial(resultCoefficients, resultExponents);
        }
        if (size1 == 1 && size2 == 1) {
            resultCoefficients[0] = nonzerocoefficents[0] * p.nonzerocoefficents[0];
            resultExponents[0] = exponents[0] + p.exponents[0];
            //Remove 0 coefficents
            if (resultCoefficients[0] == 0) {
                return new Polynomial(resultCoefficients, resultExponents);
            }
            return new Polynomial(resultCoefficients, resultExponents);
        }
        //If just one polynomial is of size 1
        if (size1 == 1) {
            for (int i = 0; i < size2; i++) {
                resultCoefficients[i] = nonzerocoefficents[0] * p.nonzerocoefficents[i];
                resultExponents[i] = exponents[0] + p.exponents[i];
            }
            int numzero = 0;
            for(int i = 0; i < resultCoefficients.length; i++){
                if(resultCoefficients[i] == 0){
                    numzero++;
                }
            }
            double [] newcoeffs = new double[resultCoefficients.length - numzero];
            int count = 0;
            for(int i = 0; i < resultCoefficients.length; i++){
                if(resultCoefficients[i] != 0){
                    newcoeffs[count] = resultCoefficients[i];
                    count++;
                }
            }
            return new Polynomial(newcoeffs, resultExponents);
        }
        else if(size2 == 1){
            for (int i = 0; i < size1; i++) {
                resultCoefficients[i] = nonzerocoefficents[i] * p.nonzerocoefficents[0];
                resultExponents[i] = exponents[i] + p.exponents[0];
            }
            //Remove 0 coefficents from resultCoefficents
            int numzero = 0;
            for(int i = 0; i < resultCoefficients.length; i++){
                if(resultCoefficients[i] == 0){
                    numzero++;
                }
            }
            double [] newcoeffs = new double[resultCoefficients.length - numzero];
            int count = 0;
            for(int i = 0; i < resultCoefficients.length; i++){
                if(resultCoefficients[i] != 0){
                    newcoeffs[count] = resultCoefficients[i];
                    count++;
                }
            }
            return new Polynomial(newcoeffs, resultExponents);
        }
        //If both polynomials are of size greater than 1
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++){
                resultCoefficients[i + j] += nonzerocoefficents[i] * p.nonzerocoefficents[j];
                resultExponents[i + j] = exponents[i] + p.exponents[j];
            }
        }

        //Remove zero coefficents
        int numzero = 0;
        for(int i = 0; i < resultCoefficients.length; i++){
            if(resultCoefficients[i] == 0){
                numzero++;
            }
        }
        double [] newcoeffs = new double[resultCoefficients.length - numzero];
        int [] newexpon = new int[resultExponents.length - numzero];
        int count = 0;
        for(int i = 0; i < resultCoefficients.length; i++){
            if(resultCoefficients[i] != 0){
                newcoeffs[count] = resultCoefficients[i];
                newexpon[count] = resultExponents[i];
                count++;
            }
        }
        return new Polynomial(newcoeffs, newexpon);
    }

    public void saveToFile(String fileName){
        try{
            PrintWriter pw = new PrintWriter(fileName);
            for(int i = 0; i < nonzerocoefficents.length; i++){
                if(exponents[i] == 0){
                    pw.print(nonzerocoefficents[i] + "+");
                }
                else if(i == nonzerocoefficents.length - 1){
                    pw.print(nonzerocoefficents[i] + "x" + exponents[i]);
                }
                else{
                    pw.print(nonzerocoefficents[i] + "x" + exponents[i] + "+");
                }
            }
            pw.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

}