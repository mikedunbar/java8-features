package mike.java8features.lambda_scopes;

import mike.java8features.Converter;

public class Driver {
    static int staticNum = 5;
    int fieldNum = 10;

    // Same rules that applied to anonymous functions apply here, but access to outer local vars
    // only requires the var to be 'effectively' final, not declared final
    public static void main (String[] args) {
        showLocalOuterVariableAccess();
        showStaticVariableAccess();
        new Driver().showInstanceVariableAccess();
        showDefaultInterfaceAccessProhibited();

    }

    private static void showLocalOuterVariableAccess() {
        //Access outer final variable from outer scope of a lambda expression
        final int num1 = 1;
        Converter<Integer, String> converter1 = from -> String.valueOf(from + num1);
        System.out.println("Conversion of 5: " + converter1.convert(5));

        //Access outer non-final variable from outer scope of a lambda expression
        int num2 = 2;
        Converter<Integer, String> converter2 = from -> String.valueOf(from + num2);
        System.out.println("Conversion of 3: " + converter2.convert(3));

        //But variables accessed by the lambda must be implicitly final
        //The code below causes a compile error
        //num2 = 7;

        //Lambdas also cannot modify an outer variable. Below causes compile error
        Converter<Integer, String> converter = from -> {
            //num2 += 6;  // Compile error!!!
            return String.valueOf(from + num2);
        };
    }

    // Lambdas can access & modify non-final static outer vars
    private static void showStaticVariableAccess() {
        Converter<Integer, String> converter = from -> {
            staticNum = 10;
            return String.valueOf(from + staticNum);
        };
        System.out.println("convertering 7 & modifying static outer variable: " + converter.convert(3));
    }

    // Lambdas can acess & modify non-final instance vars
    private void showInstanceVariableAccess() {
        Converter<Integer, String> converter = from -> {
            fieldNum = 20;
            return String.valueOf(from + fieldNum);
        };
        System.out.println("converting 8 & modifying outer instance variable: " + converter.convert(8));
    }

    private static void showDefaultInterfaceAccessProhibited() {
    }


}
