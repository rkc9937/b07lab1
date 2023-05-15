public class Polynomial{
    double [] coefficents;

    //No arguement constructor
    public Polynomial(){
        double [] zero = {0.0};
        coefficents = zero;
    }

    //Arguement constuctor
    public Polynomial(double [] poly){
        coefficents = poly;
    }

    //add method
    public Polynomial add(Polynomial p){
        for(int i = 0; i < coefficents.length; i++){
            p.coefficents[i] += coefficents[i];
        }
        return p;
    }

    //evaluate method
    public double evaluate(double x){
        double y = 0;
        for(int i = 0; i < coefficents.length; i++){
            y +=(Math.pow(x,i) * coefficents[i]);
        }
        return y;
    }

    //hasRoot method

    public boolean hasRoot(double x){
        if(evaluate(x) == 0){
            return true;
        }
        return false;
    }
}