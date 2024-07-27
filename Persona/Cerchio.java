public class Cerchio {
    private double PI = 3.1415926535897932384626433832;

    public double circonferenza(double raggio){
        return (2*PI*raggio)/2;
    }

    public double area(double raggio){
        return PI * Math.sqrt(raggio);
    }
}
