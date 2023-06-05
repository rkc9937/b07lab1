import java.io.*;
import java.util.*;
public class Polynomial{
    double [] nonzerocoefficents;
    int [] exponents;

    //No arguement constructor
    public Polynomial(){
        double [] zero = {0.0};
        int [] zero2 = {0};
        this.nonzerocoefficents = zero;
        this.exponents = zero2;

    }

    //Arguement constuctor
    public Polynomial(double [] nonzerocoefficents, int [] exponents){
        this.nonzerocoefficents = nonzerocoefficents;
        this.exponents = exponents;
    }

    //File constructor
    public Polynomial(File f, double [] nonzerocoefficents, int [] exponents){
        try {
            Scanner sc = new Scanner(f);
            String line = sc.nextLine();
            String [] split = line.split("\\+");
            double [] nonzerocoeffs = new double[split.length];
            int [] expon = new int[split.length];
            for(int i = 0; i < split.length; i++){
                String [] split2 = split[i].split("x");
                nonzerocoeffs[i] = Double.parseDouble(split2[0]);
                expon[i] = Integer.parseInt(split2[1].substring(1));
            }
            this.nonzerocoefficents = nonzerocoeffs;
            this.exponents = expon;
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //add method
    public Polynomial add(Polynomial p){
        for(int i = 0; i < nonzerocoefficents.length; i++){
            p.nonzerocoefficents[i] += nonzerocoefficents[i];
        }
        return p;
    }

    //evaluate method
    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < nonzerocoefficents.length; i++){
            sum += nonzerocoefficents[i] * Math.pow(x, exponents[i]);
        }
        return sum;
    }

    //hasRoot method
    public boolean hasRoot(double x){
        if(evaluate(x) == 0){
            return true;
        }
        return false;
    }

    public Polynomial multiply(Polynomial p){
        double [] newCoefficents = new double[nonzerocoefficents.length * p.nonzerocoefficents.length];
        int [] newExponents = new int[exponents.length * p.exponents.length];
        int count = 0;
        for(int i = 0; i < nonzerocoefficents.length; i++){
            for(int j = 0; j < p.nonzerocoefficents.length; j++){
                newCoefficents[count] = nonzerocoefficents[i] * p.nonzerocoefficents[j];
                newExponents[count] = exponents[i] + p.exponents[j];
                count++;
            }
        }
        return new Polynomial(newCoefficents, newExponents);
    }

    public void saveToFile(String fileName){
        try{
            PrintStream ps = new PrintStream(fileName);
            for(int i = 0; i < nonzerocoefficents.length; i++){
                if(i == nonzerocoefficents.length - 1){
                    //Do we need int or double?
                    ps.print(nonzerocoefficents[i] + "x" + exponents[i]);
                }
                else{
                    ps.print(nonzerocoefficents[i] + "x" + exponents[i] + "+");
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

}