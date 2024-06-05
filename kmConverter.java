import ecs100.*;

public class kmConverter {
    public static void main(String[] args) {
        kmConvert();
    }
    public static void kmConvert() {  
        double miles = UI.askDouble("Enter miles: ");
        double km = miles * 1.609;
        UI.println(miles + " miles is " + km + " km");
        kmConvert();
    }
}
