import java.math.*;

public class Pi implements Task {

    private int digits;

    public Pi(int digits) {  //constructeur
        this.digits = digits;
    }

    public Object execute() {
        return computePi(digits);
    }

    public static String computePi(int digits) {
        
        /* ... calcul compliqué ...*/
        return ("3.14");
    }
}
