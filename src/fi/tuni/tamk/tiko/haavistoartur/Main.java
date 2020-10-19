package fi.tuni.tamk.tiko.haavistoartur;

import fi.tuni.tamk.tiko.haavistoartur.util.*;

/**
 * The lotto program.
 * 
 * The program calculates that how many years it would take to get a jackpot from the lottery. 
 * 
 * @author Artur Haavisto
 */
public class Main {

    static final int minNumber = 1;
    static final int maxNumber = 40;
    static final String askNumbers = "Please give unique number between [" + minNumber + ", " + maxNumber + "]";
    static final String errorMessageNonNumeric = "Please give number.";
    static final String errorMessageNotUnique = "Not unique numbers!";
    static final String showLottoNumbersMessage = "Do you want to see every lotto rows from every week? Write y for yes and n for no answer?";
    static final int amountOfLottoNumbers = 7;
    static final String errorMessageAnswer = "Give either y or n";
    static final char yesCharacter = 'y';
    static final char noCharacter = 'n';
    static int [] lottoUser;
    static int [] lotto;
    static int [][] rightNumbers;

    /**
     * This is the main method which runs the program.
     * A user can give lotto numbers as command line arguments or when asked.
     * A user is asked if they want to see weekly results.
     * The program runs until the jackpot is won within 120 years.
     * The results are shown every time the jackpot has been won.
     * 
     * @param args Lotto numbers.
     */
    public static void main(String [] args) {
        if (args.length != 0) {
            lottoUser = Arrays.toIntArray(args);
        } else {
            lottoUser = askLottoNumbers();
        }
        rightNumbers = createRightNumbers();
        boolean showLottoNumbers = MyConsole.askIf(showLottoNumbersMessage, errorMessageAnswer, yesCharacter, noCharacter);
        int bestResult = 0;
        long weekCounter = 0;
        int yearCounter = 0;
        while (!(yearCounter <= 120 && bestResult == amountOfLottoNumbers)) {
            bestResult = 0;
            weekCounter = 0;
            yearCounter = 0;
            while (bestResult < amountOfLottoNumbers) {
                lotto = calculateLotto();
                int numOfRight = Arrays.containsSameValues(lottoUser, lotto);
                if (showLottoNumbers) {
                    printWeeklyResults(numOfRight, yearCounter);
                }
                for (int i=bestResult; i<numOfRight; i++) {
                    rightNumbers[i][0] = yearCounter;
                    bestResult = numOfRight;
                }
                weekCounter++;
                yearCounter = fi.tuni.tamk.tiko.haavistoartur.util.Math.weeksToYears(weekCounter);
            }
            printResults();
            System.out.println("You won!");
            if (!(yearCounter <= 120 && bestResult == amountOfLottoNumbers)) {
                System.out.println("Althought it took more than a lifetime, let's try it again.");
            }
        }
       
    }

    /**
     * The method asks the user for lotto numbers and returns them as an array. 
     * An error message is shown if given number already exists in the newlottoUser. 
     * If the given number passes the qualifications, it is put to the newlottoUser.
     * When all of the numbers have been asked, it returns the created array.
     * 
     * @return The created lotto array.
     */
    public static int [] askLottoNumbers() {
        int [] newlottoUser = new int [amountOfLottoNumbers];

        for (int i=0; i<amountOfLottoNumbers; i++) {
            boolean notUnique = true;
            System.out.println(askNumbers);

            while (notUnique) {
                int lottoNumber = MyConsole.readInt(minNumber, maxNumber, errorMessageNonNumeric, askNumbers);
                if (!(Arrays.contains(lottoNumber, newlottoUser))) {
                    newlottoUser[i] = lottoNumber;
                    notUnique = false;
                } else {
                    System.out.println(errorMessageNotUnique);
                }
            }
        }
        return newlottoUser;
    }

    /**
     * The method creates a random lotto row and returns it as an array.
     * 
     * @return The created lotto row.
     */
    public static int [] calculateLotto() {
        int [] numbers = new int [maxNumber];
        int [] lottoArray = new int [amountOfLottoNumbers];
        for (int i=0; i<numbers.length; i++) {
            numbers[i] = i + 1;
        }
        for (int j=0; j<amountOfLottoNumbers; j++) {
            int index = fi.tuni.tamk.tiko.haavistoartur.util.Math.getRandom(0, numbers.length - 1);
            lottoArray[j] = numbers[index];
            numbers = fi.tuni.tamk.tiko.haavistoartur.util.Math.removeIndex(numbers, index);
        }
        return lottoArray;
    }

    /**
     * The method creates a 2D array which has amountOfLottoNumbers rows and one column. The rows are numbered from 1 to
     * amountOfLottoNumbers. It returns the created array.
     * 
     * @return The array which has amountOfLottoNumbers amount of rows numbered 1 to amountOfLottoNumbers and the column size of 1.
     */
    public static int [][] createRightNumbers() {
        int [][] newRightNumbers = new int [amountOfLottoNumbers][1];
        for (int i=0; i<amountOfLottoNumbers; i++) {
            newRightNumbers[i][0] = i + 1;
        }
        return newRightNumbers;
    }

    /**
     * The method prints the results which shows how many years it took to get 1 to amountOfLottoNumbers right lotto numbers.
     */
    public static void printResults() {
        for (int i=0; i<amountOfLottoNumbers; i++) {
            System.out.println(createOneResult(i + 1, rightNumbers[i][0]));
        }
    }

    /**
     * The method prints the weekly results.
     * 
     * @param amount An amount of numbers right on the lotto row.
     * @param time A time in years.
     */
    public static void printWeeklyResults(int amount, int time) {
        String [] lottoUserString = Arrays.prefixFormat(lottoUser, 2, '0');
        String [] lottoString = Arrays.prefixFormat(lotto, 2, '0');
        System.out.println("User lotto:   " + arrayToString(lottoUserString));
        System.out.println("Random lotto: " + arrayToString(lottoString));
        System.out.println(createOneResult(amount, time));
    }

    /**
     * The method creates and returns a string which tells how many lotto numbers was right and how many years is passed.
     * 
     * @param amount An amount of lotto numbers right.
     * @param time A number of years passed.
     * @return A string that tells how many lotto numbers was right and how many years passed.
     */
    public static String createOneResult(int amount, int time) {
        return "Got " + amount + " right! Took " + time + " years";
    }

    /**
     * The method converts an array to specific format of a string and then returns it.
     * The specific format looks for example like "[value1, value2, value3]".
     * 
     * @param array An array.
     * @return A string that was converted from an array to specific format.
     */
    public static String arrayToString(String [] array) {
        String arrayString = "[";
        for (int i=0; i<array.length; i++) {
            arrayString += array[i];
            if (i < array.length - 1) {
                arrayString += ", ";
            }
        }
        arrayString += "]";
        return arrayString;
    }
}
