package be.leerstad.collections;

import java.util.*;

/**
 * @author Peter hardeel
 */
public class ConvenienceImplementations {

    public static void main(String[] args) {
        ConvenienceImplementations con = new ConvenienceImplementations();
//		String[] array = new String[3];
//		List<String> list = con.listViewOfArray(array);
//		try {
//			list.add("A");
//		} catch (UnsupportedOperationException e) {
//			System.out.println("UnsupportedOperationException");
//		}
//		System.out.println(list);
//		array[0] = "A";
//		array[1] = "B";
//		array[2] = "C";
//		System.out.println(list);


        //System.out.println(con.copyList(10));

        //System.out.println(con.singletonSet("Singleton"));

        System.out.println(con.emptyList());
//		System.out.println(con.emptySet());
//		System.out.println(con.emptyMap());
    }

    public List<String> listViewOfArray(String[] array) {
        return Arrays.asList(array);
    }

    public List<String> copyList(int copies) {
        List<String> list = Collections.nCopies(copies, "default");
        return new ArrayList<String>(list);
    }

    public Set<Object> singletonSet(Object object) {
        return Collections.singleton(object);
    }

    public List<?> emptyList() {
        return Collections.EMPTY_LIST;
    }

    public Set<?> emptySet() {
        return Collections.EMPTY_SET;
    }

    public Map<?, ?> emptyMap() {
        return Collections.EMPTY_MAP;
    }


}
