package mike.java8features;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
