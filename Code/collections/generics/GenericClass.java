package be.leerstad.collections.generics;

public class GenericClass {
    public static void main(String args[]) {

        TwoGen<Integer, String> instance = new TwoGen<>(88,
                "Generics");
        instance.showTypes();
        int v = instance.getob1();
        System.out.println("value: " + v);
        String str = instance.getob2();
        System.out.println("value: " + str);

        TwoGen<Double, String> instance2 = new TwoGen<>(88.0,
                "Generics");
        instance2.showTypes();
        double v1 = instance2.getob1();
        System.out.println("value: " + v);
        String str1 = instance2.getob2();
        System.out.println("value: " + str);

        TwoGen<String, String> instance3 = new TwoGen<>("Demo",
                "Generics");

    }
}
