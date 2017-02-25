public class Main {

    public static void main(String[] args) {
        System.out.println("TEST GCD:");
        System.out.format("GCD(10,5): %d%n",GCD(10,5));
        System.out.format("GCD(1,5): %d%n",GCD(1,5));
        System.out.format("GCD(13,7): %d%n",GCD(13,7));
        System.out.println("------");
        System.out.println("TEST isPrime:");
        System.out.format("isPrime(2): %s%n",isPrime(2));
        System.out.format("isPrime(7): %s%n",isPrime(7));
        System.out.format("isPrime(10): %s%n",isPrime(10));

    }

    public static boolean isPrime(int n){
        int i = 2;
        while (i < Math.sqrt(n)){
            if (GCD(i,n) > 1) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static int GCD(int a, int b){
        while (a != b){
            if (a > b) a = a - b;
            else b = b - a;
        }
        return a;
    }

}
