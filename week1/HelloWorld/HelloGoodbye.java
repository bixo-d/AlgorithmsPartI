/******************************************************************************
 * Compilation: javac HelloGoodbye.java
 * 
 * Execution: java HelloGoodbye name1 name2
 *
 * Prints "Hello, World". By tradition, this is everyone's first program.
 *
 * % java HelloGoodbye John Jane Hello John and Jane. Goodbye Jane and John.
 *
 *
 ******************************************************************************/

public class HelloGoodbye {

    public static void main(String[] args) {
        // Prints "Hello, World" in the terminal window.
        if (args.length == 2) {
            System.out.println("Hello " + args[0] + " and " + args[1] + ".");
            System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
        } else {
            System.out.println("Require two arguments (names).");
        }
    }
}
