package mike.java8features.method_references;

import mike.java8features.Converter;

public class Driver {

    static class Something {
        String startsWith(String s) {
            return s.substring(0,1);
        }
    }

    static class Person {
        String firstName;
        String lastName;

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public static void main(String[] args) {
        Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
        System.out.println("Int value of '123' using converter1: " + converter1.convert("123"));

        // Static method reference
        Converter<String, Integer> converter2 = Integer::valueOf;
        System.out.println("Int value of '123' using converter2: " + converter2.convert("123"));

        // Object method reference
        Something something = new Something();
        Converter<String, String> converter3 = something::startsWith;
        System.out.println("'123' starts with: " + converter3.convert("123"));

        // Constructor method reference
        PersonFactory<Person> personFactory = Person::new;
        System.out.println("Using person factory yields: " + personFactory.create("Mark", "Jones"));


    }
}
