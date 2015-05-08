package mike.java8features.functional_interfaces;

import mike.java8features.default_methods.Dog;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Driver{

    // Functional interfaces simply have a single abstract method
    // You can annotate them to force a compile error if that intent is violated
    @FunctionalInterface
    interface Doable {
        void doIt();
        // Functional interfaces can have any number of default methods
        default String explainIt() {
            return "It's too complicated";
        }

        default int anotherDefaulMethod() {
            return -1;
        }
    }

    public static void main(String[] args) {
        // Java 8 has many built-in functional interfaces
        // Some have been around in earlier versions, e.g. Runnable, Comparator
        showPredicateInterface();
        showFunctionInterface();

        // Suppliers produce a result of a given type
        Supplier<Dog> dogSupplier = Dog::new;
        System.out.println("The supplied dog: " + dogSupplier.get());

        // Consumers represent ops to be performed on a single argument
        Consumer<Dog> dogConsumer = d -> System.out.println("The dog says: " + d.speak());
        dogConsumer.accept(new Dog());


    }

    private static void showFunctionInterface() {
        // functions take a single argument and product a result
        Function<String, Integer> toInteger = Integer::valueOf;

        // Function contains default methods for chaining multiple function together
        // andThen
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        //@TODO
        // compose
    }

    private static void showPredicateInterface() {
        // Predicates are boolean-valued functions of one argument
        Predicate<String> nonEmpty = s -> s != null && s.length() > 0;
        System.out.println("test it: " + nonEmpty.test("cat"));

        // Predicate interface has many default methods for composing predicates, e.g. and, or, negate
        System.out.println("negate and test it: " + nonEmpty.negate().test("cat"));

        Predicate<String> startWithA = s -> s.toLowerCase().startsWith("a");
        Predicate<String> length6 = s -> s.length() == 6;
        Predicate<String> length6AndStartsWithA = length6.and(startWithA);
        System.out.println("test 'animal' for length6AndStartsWithA: " + length6AndStartsWithA.test("animal"));
        System.out.println("test 'asshole' for length6AndStartsWithA: " + length6AndStartsWithA.test("asshole"));
        System.out.println("test 'Antics' for length6AndStartsWithA: " + length6AndStartsWithA.test("Antics"));

        Predicate<String> length6OrStartsWithA = length6.or(startWithA);
        System.out.println("test 'asshole' for lengthOrStartsWithA: " + length6OrStartsWithA.test("asshole"));
        System.out.println("test 'camped' for lengthOrStartsWithA: " + length6OrStartsWithA.test("camped"));


        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> nonEmpty2 = isEmpty.negate();
    }
}
