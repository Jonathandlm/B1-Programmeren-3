package be.leerstad.collections.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Peter Hardeel
 */


public class IteratorDemo {


    public static void main(String[] args) {
        IteratorDemo id = new IteratorDemo();
        id.giveAnIterator();

    }

    private void giveAnIterator() {
        Collection<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(new Integer(27));
        l.add(new Integer(9));
        l.add(new Integer(9));
        l.add(new Integer(98));
        l.add(new Integer(25));
        l.add(new Integer(3));
        l.add(new Integer(16));
        l.add(new Integer(99));
        l.add(new Integer(123));

        Iterator<Integer> it = l.iterator();


//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//
//        for (Integer element : l
//                ) {
//            System.out.println(element);
//        }


        for (Integer integer:l
             ) {

            System.out.print(integer);

        }

        System.out.print(l);

        for (; it.hasNext(); ) {
            if (!cond(it.next())) {
                it.remove();
            }
        }
        System.out.print(l);
    }

    private boolean cond(Integer next) {
        int i = next % 2;
        if (i == 0) {
            return true;
        }
        return false;
    }

}
