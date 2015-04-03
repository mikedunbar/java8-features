package mike.java8features.default_methods;

public interface Animal {
    // concrete "default" method in interface
    default String speak() {
        return "Hello";
    }

    default int getAge() {return 1;}

    int getLegCount();
}
