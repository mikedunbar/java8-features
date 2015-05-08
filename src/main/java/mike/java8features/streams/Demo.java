package mike.java8features.streams;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) {
        //showBasicStreamStuff();
        //showParallelStreamStuff();
        showMapCollectionStuff();

    }

    private static void showBasicStreamStuff() {
        // A steam is a sequence of elements on which one or more operations can be performed.
        // Terminal operations return a result of a certain type
        // Intermediate operations return the stream itself, so you can chain method calls

        // Streams are created on a source, like a Collection
        // Stream operations can either be sequential (single thread) or parallel (multiple threads)
        List<String> stringList = Arrays.asList(
                "ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc3", "bbb2", "ddd1");

        // filter() takes a predicate to filter the stream
        stringList
                .stream()
                .filter(s -> s.startsWith("a")) // intermediate operation
                .forEach(System.out::println); // terminal operation

        // sorted() returns a sorted view of stream
        stringList
                .stream()
                .sorted() // could pass a comparator
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);

        // Only sorts the stream, not the underlying collection
        System.out.println(stringList);

        // map() converts each object into another via the given function
        stringList
                .stream()
                .map(s -> Integer.parseInt(s.substring(s.length() - 1))) // Can change the type of the stream
                .forEach(i -> System.out.println(i.getClass() + ": " + i));

        // various matching operations
        boolean anyStartsWithA =
        stringList
                .stream()
                .anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        boolean allStartWithA =
        stringList
                .stream()
                .allMatch(s -> s.startsWith("a"));
        System.out.println(allStartWithA);

        boolean noneStartWithZ =
        stringList
                .stream()
                .noneMatch(s -> s.startsWith("a"));
        System.out.println(noneStartWithZ);

        long startsWithB =
                stringList
                .stream()
                .filter(s -> s.startsWith("b"))
                .count();
        System.out.println(startsWithB + " startsWithB");

        Optional<String> reduced =
                stringList
                .stream()
                .reduce((s1,s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }

    private static void showParallelStreamStuff() {
        // Big list
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        long t1 = System.nanoTime();

        long millisElapsed = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Sequential sort took %d ms", millisElapsed));

        // reshuffle
        values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        t0 = System.nanoTime();
        count = values.parallelStream().sorted().count();
        t1 = System.nanoTime();

        millisElapsed = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Parallel sort took %d ms", millisElapsed));

    }

    private static void showMapCollectionStuff() {
        //Maps don't support streams, but have new methods for common tasks
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (num, val) -> num + val);
        System.out.println("new computed value for key 3: " + map.get(3));

        System.out.println("containsKey(9)?: " + map.containsKey(9));
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println("containsKey(9)?: " + map.containsKey(9));

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println("containsKey(23)?: " + map.containsKey(23) + " - " + map.get(23));

        System.out.println("containsKey(3)?: " + map.containsKey(3) + " - " + map.get(3));
        map.computeIfAbsent(3, val -> val + "updated");
        System.out.println("containsKey(3)?: " + map.containsKey(3) + " - " + map.get(3));
        map.computeIfPresent(3, (num, val) -> val + "updated");
        System.out.println("containsKey(3)?: " + map.containsKey(3) + " - " + map.get(3));

        // Remove a given key, only if currently mapped to certain value
        map.remove(3, "cookie");
        System.out.println("map.get(3): " + map.get(3));
        map.remove(3, "3val3updated");
        System.out.println("map.get(3): " + map.get(3));

        System.out.println(map.getOrDefault(666, "default val"));

        System.out.println("Pre-merge, map.get(9): " + map.get(9));
        map.merge(9, "merged stuf", (value, newValue) -> value.concat(newValue));
        System.out.println("Post-merge, map.get(9): " + map.get(9));
        map.merge(9, "pre-pended", (val, newVal) -> newVal.concat(val));
        System.out.println("Post-merge2, map.get(9): " + map.get(9));
    }


}
