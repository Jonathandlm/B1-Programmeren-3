package be.leerstad.javabasics.exercises.ex1;

public class PrimeNumberCheck {

    public static void main(String[] args) {
        PrimeNumberCheck numberCheck = new PrimeNumberCheck();
        System.out.println("13 is a primenumber : " + numberCheck.isPrimeNumber(13));
        System.out.println("54 is a primenumber : " + numberCheck.isPrimeNumber(54));
        System.out.println("27 is a primenumber : " + numberCheck.isPrimeNumber(27));

    }

    public boolean isPrimeNumber(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}