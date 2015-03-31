package mike.java8features.default_methods;

public class Driver {

    public static void main (String[] args) {
        Dog d = new Dog();
        System.out.println("Dogs have " + d.getLegCount() + " legs and they say " + d.speak());

        Emu e = new Emu();
        System.out.println("Emus have " + e.getLegCount() + " legs and they say " + e.speak());
    }
}
