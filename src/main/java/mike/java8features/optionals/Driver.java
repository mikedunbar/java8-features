package mike.java8features.optionals;

import java.util.Optional;

public class Driver {

    public static void main (String[] args) {
        // Optional is a container for a value which may or may not be null
        Optional<String> owner = Optional.of("Bob");
        demoOptional(owner);
        Optional<String> owner2 = Optional.empty();
        demoOptional(owner2);


    }

    private static void demoOptional(Optional<String> optional) {
        System.out.println(optional.isPresent());
        if (optional.isPresent()) {           // see ifPresent below
            System.out.println(optional.get());
        }
        System.out.println(optional.orElse("Larry"));
        optional.ifPresent(s -> System.out.println(s.charAt(0)));
    }
}
