package mike.java8features.lambda_expressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Any functional interface impl can be used as a Lambda, including the built-in JDK ones like Comparator
public class Driver {

    static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tom", "Jerry", "Mike", "Arnold");
        System.out.println("Before sort: " + names);

        // Pre-lambda / Named class style
        Collections.sort(names, new StringComparator());

        // Pre-lambda / Anonymous class style
        Collections.sort(names, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // With a lambda
        Collections.sort(names, (String a, String b) -> {
            return a.compareTo(b);
        });
        System.out.println("After sort: " + names);

        // With a simplified lambda
        //  - Types can be inferred
        //  - Single line methods can omit curly braces + return keyword
        Collections.sort(names, (a, b) -> a.compareTo(b));
    }
}
