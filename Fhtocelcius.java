import ecs100.*;

public class Fhtocelcius
{
    public static void main(String[] args) {
        printEquation();
        Calculation();
    }
 
    static void printEquation() {
        UI.println("Celcius = (Farenheit - 32) *5/9");
    } 
    static void Calculation() { 
        double farenheit = UI.askDouble("Farenheit:");
        double celcius = (farenheit - 32.0) * 5.0 / 9.0;
        UI.println(farenheit + " F -> " + celcius + " C");
    }
}