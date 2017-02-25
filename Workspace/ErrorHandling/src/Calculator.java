public class Calculator {

    public double divide(int first, int second) throws IllegalArgumentException {
        if (second != 0) return first / second;
        else throw new IllegalArgumentException("Argument 'divisor' is 0");
    }
}
