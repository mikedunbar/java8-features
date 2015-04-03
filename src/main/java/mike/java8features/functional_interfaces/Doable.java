package mike.java8features.functional_interfaces;

// Functional interfaces simply have a single abstract method
// You can annotate them to force a compile error if that intent is violated
@FunctionalInterface
public interface Doable {
    void doIt();

    // Functional interfaces can have any number of default methods
    default String explainIt(){return "It's too complicated";}

    default int anotherDefaulMethod() { return -1;}

}
