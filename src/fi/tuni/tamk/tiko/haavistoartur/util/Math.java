package fi.tuni.tamk.tiko.haavistoartur.util;

/**
 * The class Math contains methods for performing basic numeric operations.
 * 
 * @author Artur Haavisto
 */
public class Math {

    /**
     * Returns a random integer between min and max parameters.
     * 
     * @param min A minimum value.
     * @param max A maximum value.
     * @return A random integer between min and max parameters.
     */
    public static int getRandom(int min, int max) {
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }

    /**
     * The method takes an array and removes one value from it. The index of the value is given. 
     * It then returns the new array, which has one value less in it than the original had.
     * 
     * @param original An array.
     * @param index An index.
     * @return The original array otherwise but the original[index] has been removed and the length of the array is one shorter.
     */
    public static int [] removeIndex(int [] original, int index) {
        int [] newArray = new int [original.length - 1];
        int indexOriginal = 0;
        int indexNew = 0;
        while (indexOriginal < original.length && indexNew < newArray.length) {
            if (indexOriginal != index) {
                newArray[indexNew] = original[indexOriginal];
                indexNew++;
            }
            indexOriginal++;
        }
        return newArray;
    }

    /**
     * The method takes a number of weeks and returns the amount of years it is.
     * The method uses 52 weeks to represent one year.
     * 
     * @param weeks A number of weeks.
     * @return A number of years.
     */
    public static int weeksToYears(long weeks) {
        return (int) (weeks / 52);
    }
}
