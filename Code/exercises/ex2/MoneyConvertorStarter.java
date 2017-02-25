package be.leerstad.javabasics.exercises.ex2;

public class MoneyConvertorStarter {

    public static void main(String[] args) {
        MoneyConverter convertor = new MoneyConverter(40.3399f);
        float result = convertor.convertToNewMonetaryUnit(100);
        System.out.println("100 Bef must be 2.48 Euro:" + result + " Euro");
        System.out.println(result + " Euro must be 100.0 Bef: " + convertor.convertToBaseMonetaryUnit(result) + "Bef");
    }
}
