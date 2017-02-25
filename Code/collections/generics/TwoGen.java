package be.leerstad.collections.generics;

public class TwoGen<T, V> {
    private T ob1;

    private V ob2;

    public TwoGen(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }

    public void showTypes() {
        System.out.println("Type of T is " + ob1.getClass().getName());
        System.out.println("Type of V is " + ob2.getClass().getName());
    }

    public T getob1() {
        return ob1;
    }

    public V getob2() {
        return ob2;
    }
}
