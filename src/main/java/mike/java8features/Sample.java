package mike.java8features;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sample
{
    public static void main( String[] args ) {
        // Filter a list
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("list: " + list);
        list = list.stream().filter(i -> i > 5 ).collect(Collectors.toList());
        System.out.println("filtered list: " + list);
    }
}
