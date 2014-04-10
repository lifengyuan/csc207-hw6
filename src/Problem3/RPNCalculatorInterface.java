package Problem3;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Fengyuan Li
 *
 */

public class RPNCalculatorInterface {

    /**
     * printOptions prints the options available to the user of the RPN
     * calculator
     * 
     * @param PrintWriter
     *            pen
     */
    public static void printOptions(PrintWriter pen) {
        pen.println("Options: ");
        pen.println("p - print the top value on the stack");
        pen.println("s - print the whole stack");
        pen.println("c - clear the stack");
        pen.println("n - find the min of top two values on the stack");
        pen.println("x - find the max of top two values on the stack");
        pen.println("a - find the average of top two values on the stack");
        pen.println("q - quit the calculator program");
    } // printOptions

    public static void main(String[] args) throws Exception {
        // Create a RPNCalc with a size of 30
        RPNCalculator calc = new RPNCalculator(30);
        PrintWriter pen = new PrintWriter(System.out, true);
        InputStreamReader istream = new InputStreamReader(System.in);
        BufferedReader eyes = new BufferedReader(istream);
        // Welcome
        pen.println("Welcome to the RPN calculator!");
        pen.println("Please type your input. Hit enter when you are");
        pen.println("ready to add the input to the stack.");
        // Options to choose from
        printOptions(pen);
        boolean cont = true;
        while (cont) {
            // Read input into a String
            // Perform calculations and decide whether or not to continue the
            // loop (if the input is 'q', evaluate returns false)
            cont = calc.evaluate(eyes.readLine(), pen);
        } // while
        pen.println("Quitting!");
    } // main

} // RPNCalcInterface