package mike.java8features.default_methods;

public interface Animal {
    // concrete method in interface
    default String speak() {
        return "Hello";
    }

    int getLegCount();
}
