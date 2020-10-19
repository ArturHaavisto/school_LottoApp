package fi.tuni.tamk.tiko.haavistoartur.util;

/**
 * The class Arrays contains methods that do different things to arrays.
 * 
 * @author Artur Haavisto
 */
public class Arrays {

    /**
     * The method takes a String type array, converts it to an int type array and returns it.
     * The given array has to have integers represented as strings.
     * 
     * @param array An array that contains integers that are represented as strings.
     * @return An array that contains integers which were taken from the String-type array.
     */
    public static int [] toIntArray(String [] array) {
        int [] intArray = new int [array.length];
        for (int i=0; i<array.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
        }
        return intArray;
    }

    /**
     * The method searches a given value from the given array and returns true or false depending if the value is found.
     * If the value is found from the array, true is returned and if it is not found, false is returned.
     * 
     * @param value A value.
     * @param array An int-type array.
     * @return True if value found from the array, false if not found.
     */
    public static boolean contains(int value, int [] array) {
        for (int i=0; i<array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method goes through both given arrays and returns the amount of same integers found in both of them.
     * 
     * @param array1 The first array.
     * @param array2 The second array.
     * @return The amount of same integers found in both arrays.
     */
    public static int containsSameValues(int [] array1, int [] array2) {
        int numOfSameValues = 0;
        for (int i=0; i<array1.length; i++) {
            for (int j=0; j<array2.length; j++) {
                if (array1[i] == array2[j]) {
                    numOfSameValues++;
                }
            }
        }
        return numOfSameValues;
    }

    /**
     * The method takes an array and sorts it into ascending order.
     * 
     * @param array An array which will be sorted.
     * @return A sorted array in ascending order.
     */
    public static int [] sort(int [] array) {
        int [] sorted = new int [array.length];
        for (int i=0; i<array.length; i++) {
            int min = array[i];
            for (int j=i; j<array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                }
            }
            sorted[i] = min;
        }
        return sorted;
    }

    /**
     * The method converts an int-type array into a String-type arrray, adds a possible prefix to values and returns it.
     * If the value has less characters than the length parameter, it will be prefixed by the given character
     * for so many times that the new value has the length parameter amount of characters.
     * 
     * @param array An int-type array.
     * @param length A number of characters in one value.
     * @param character A character to get prefixed to values.
     * @return A String-type array which values has been 
     */
    public static String [] prefixFormat(int [] array, int length, char character) {
        String [] newArray = new String [array.length];
        for (int value=0; value<array.length; value++) {
            String oneValue = "";
            oneValue += array[value];
            String newValue = "";
            for (int i=oneValue.length(); i<length; i++) {
                newValue += character;
            }
            newValue += oneValue;
            newArray[value] = newValue;
        }
        return newArray;
    }
}
