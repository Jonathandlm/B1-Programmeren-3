package be.leerstad.collections.list;

import java.util.LinkedList;

/**
 * @author Peter Hardeel
 */
public class LinkedListDemo {


    public LinkedListDemo() {
    }

    public static void main(String[] args) {

        // Create LinkedList object and add 4 Integer objects to it.
        LinkedList<Object> list = new LinkedList<>();
        list.add(1);
        list.add(new Integer(2));
        list.add(new Integer(3));
        list.add(new Integer(1));
        //System.out.println(list + ", size = " + list.size());

        // Add Integer objects to the beginning and end of the LinkedList object.
        list.addFirst(new Integer(0));
        list.addLast(new Integer(4));
       // System.out.println(list);
       // System.out.println(list.getFirst() + ", " + list.getLast());
       // System.out.println(list.get(2) + ", " + list.get(3));

        // Remove the first and the last objects from the LinkedList object.
        list.removeFirst();
        list.removeLast();
        //System.out.println(list);

        // Remove the first instance of Integer(1) object
        list.remove(new Integer(1));
        //System.out.println(list);

        // Add a String and Long objects to the LinkedList
        String s = "Boston";
        list.add(s);
        list.add(2, 45L);
       // System.out.println(list);

        // Get the index of the "Boston" String object
       // System.out.println("Index of Boston String = " + list.indexOf(s));

        // Remove the 3rd object in the Linked List
        list.remove(2);
       // System.out.println(list);

        // Set the value of the second item to "one"
        list.set(1, "one");
       // System.out.println(list);

        // Clone the LinkedList object
        LinkedList<Object> clonedLinkedList = (LinkedList<Object>) list.clone();
        clonedLinkedList.add(0, "Cloned LinkedList");
        list.add(0, "Original LinkedList");
        System.out.println(list);
        System.out.println(clonedLinkedList);

        list.remove("Original LinkedList");

        System.out.println(list);


    }

}

