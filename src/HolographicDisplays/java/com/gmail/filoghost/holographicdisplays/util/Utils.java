package com.gmail.filoghost.holographicdisplays.util;

import org.bukkit.ChatColor;

import java.util.*;

public class Utils extends Object {

    /**
     * Converts a generic array to an array of Strings using the method toString().
     *
     * @param array the array to convert
     * @return the new generated array of Strings
     */
    public static String[] arrayToStrings(Object... array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] != null ? array[i].toString() : null;
        }

        return result;
    }


    /**
     * Convenience method to add colors to a string.
     *
     * @param text the text to colorize
     * @return the colorized text, or null if text was null
     */
    public static String addColors(String text) {
        if (text == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }


    public static boolean containsIgnoreCase(String toCheck, String content) {
        return toCheck.toLowerCase().contains(content.toLowerCase());
    }

    public static boolean containsIgnoreCase(String toCheck, String content, String... others) {
        if (toCheck.toLowerCase().contains(content.toLowerCase())) {
            return true;
        }

        for (String other : others) {
            if (toCheck.toLowerCase().contains(other.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public static <T, V> Map<T, V> newMap() {
        return new HashMap<>();
    }

    public static <T> List<T> newList() {
        return new ArrayList<>();
    }


    public static <T> Set<T> newSet() {
        return new HashSet<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] listToArray(List<T> list) {
        return (T[]) list.toArray();
    }

    public static int floor(double num) {
        int floor = (int) num;
        return floor == num ? floor : floor - (int) (Double.doubleToRawLongBits(num) >>> 63);
    }


    public static double square(double num) {
        return num * num;
    }


    public static String join(String[] elements, String separator, int startIndex, int endIndex) {
        Validator.isTrue(startIndex >= 0 && startIndex < elements.length, "startIndex out of bounds");
        Validator.isTrue(endIndex >= 0 && endIndex <= elements.length, "endIndex out of bounds");
        Validator.isTrue(startIndex <= endIndex, "startIndex lower than endIndex");

        StringBuilder result = new StringBuilder();

        while (startIndex < endIndex) {
            if (result.length() != 0) {
                result.append(separator);
            }

            if (elements[startIndex] != null) {
                result.append(elements[startIndex]);
            }
            startIndex++;
        }

        return result.toString();
    }

    public static String join(String[] elements, String separator) {
        return join(elements, separator, 0, elements.length);
    }

    public static String join(List<String> elements, String separator, int startIndex, int size) {
        return join(elements.toArray(new String[elements.size()]), separator, startIndex, size);
    }

    public static String join(List<String> elements, String separator) {
        return join(elements, separator, 0, elements.size());
    }

    public static String sanitize(String s) {
        return s != null ? s : "null";
    }

    public static boolean isThereNonNull(Object... objects) {
        if (objects == null) {
            return false;
        }

        for (Object object : objects) {
            if (object != null) {
                return true;
            }
        }

        return false;
    }
}
