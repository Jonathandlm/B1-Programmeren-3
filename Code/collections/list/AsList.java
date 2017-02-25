package be.leerstad.collections.list;

import java.util.Arrays;
import java.util.List;

public class AsList {

    public static void main(String[] args) {

        List<String> list = Arrays.asList(new String[3]);
		System.out.println(list);
		try {
			list.add("een");
			list.add("twee");
			list.add("drie");
		} catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException");
		}

       /* String[] strings = new String[3];
        list = Arrays.asList(strings);
        System.out.println(list);
        strings[0] = "een";
        strings[1] = "twee";
        strings[2] = "drie";
        System.out.println(list);*/
    }
}
