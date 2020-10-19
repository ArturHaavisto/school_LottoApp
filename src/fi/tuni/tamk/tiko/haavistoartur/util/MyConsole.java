package fi.tuni.tamk.tiko.haavistoartur.util;

import java.io.Console;

/**
 * The class MyConsole contains methods that take user inputs.
 * 
 * @author Artur Haavisto
 */
public class MyConsole {

    /**
     * The method takes an integer from a user and returns it.
     * If a user gives something else than an integer as an input, an error message is shown and a new input is required.
     * 
     * @param errorMessage A message that is given to a user if the input is not an integer.
     * @return A given integer from a user.
     */
    public static int readInt(String errorMessage) {
        Console c = System.console();
        boolean wrongInput = true;
        int input = 0;
        while (wrongInput) {
            try {
                input = Integer.parseInt(c.readLine());
                wrongInput = false;
            } catch(NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    /**
     * The method takes an integer from a user between min and max values and returns it.
     * If the user input is not an integer, the message errorMessageNonNumeric is being printed and a new input is required.
     * If the user input is not an integer between min and max values, the message errorMessageNonMinAndMax is being printed 
     * and a new input is required.
     * 
     * @param min A minimum value.
     * @param max A maximum value.
     * @param errorMessageNonNumeric A message if the input is not an integer.
     * @param errorMessageNonMinAndMax A message if the input is not between min and max values.
     * @return A given integer from a user, between min and max values.
     */
    public static int readInt(int min, int max, String errorMessageNonNumeric, String errorMessageNonMinAndMax) {
        boolean wrongInput = true;
        int input = 0;
        while (wrongInput) {
            input = readInt(errorMessageNonNumeric);
            if (input >= min && input <= max) {
                wrongInput = false;
            } else {
                System.out.println(errorMessageNonMinAndMax);
            }
        }
        return input;
    }

    /**
     * The method asks the user yes or no question and returns either true or false.
     * A given question is being printed and then a user input is being taken. If the input does not equal the given
     * yes or no parameter, an errorMessage is being printed and a new input is required. If the given input, with cases
     * being ignored, equals yes parameter, true is returned and if it equals no parameter, false is returned. 
     * 
     * @param question A question to user.
     * @param errorMessage An error message to user if the given input is wrong.
     * @param yes A character which means yes as an answer.
     * @param no A character which means no as an answer.
     * @return True if the user answer is yes and false if it is no.
     */
    public static boolean askIf(String question, String errorMessage, char yes, char no) {
        Console c = System.console();
        System.out.println(question);
        while (true) {
            String answer = c.readLine();
            if (answer.charAt(0) == yes) {
                return true;
            }
            else if (answer.charAt(0) == no) {
                return false;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
}
