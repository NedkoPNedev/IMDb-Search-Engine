package bg.uni.sofia.fmi.imdb.validation;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private static final int FIELDS = 8;
    private static final int SEASON = FIELDS;
    private static final int UPPER_BOUND_2 = 11;
    private static final int LOWER_BOUND = UPPER_BOUND_2;
    private static final int VAL_SIZE = 15;
    private static final int ORDER = 22;

    private static final String FIELDS_STRING = "fields";
    private static final String ORDER_ASC = "--order=asc";
    private static final String ORDER_DESC = "--order=desc";
    private static final String ACTORS = "--actors=";
    private static final String GENRES = "--genres=";
    private static final String SEASONS = "season=";

    private static List<String> validKeyData = new ArrayList<>();

    static {
        validKeyData.add("Title");
        validKeyData.add("Year");
        validKeyData.add("Rated");
        validKeyData.add("Released");
        validKeyData.add("Runtime");
        validKeyData.add("Genre");
        validKeyData.add("Director");
        validKeyData.add("Writer");
        validKeyData.add("Actors");
        validKeyData.add("Plot");
        validKeyData.add("Language");
        validKeyData.add("Awards");
        validKeyData.add("Poster");
        validKeyData.add("Ratings");
        validKeyData.add("Metascore");
        validKeyData.add("imdbRating");
        validKeyData.add("imdbVotes");
        validKeyData.add("imdbID");
        validKeyData.add("Type");
        validKeyData.add("DVD");
        validKeyData.add("BoxOffice");
        validKeyData.add("Production");
        validKeyData.add("Website");
    }

    public static boolean movieInfoValidation(String inputString) {
        int length = inputString.length();
        int index = inputString.indexOf("--");
        if (index != -1) {
            index++;
            if (inputString.substring(index + 1, index + FIELDS).equals(FIELDS_STRING + "=")) {
                return fieldsValidation(inputString, index, length);
            }
            return false;
        }
        if (inputString.lastIndexOf("-") > 3) {
            return false;
        }
        if (inputString.contains(FIELDS_STRING)) {
            return false;
        }
        return true;
    }

    private static boolean fieldsValidation(String inputString, int index, int length) {
        String key = "";
        char currentChar;
        for (int i = index + FIELDS; i < length; i++) {
            currentChar = inputString.charAt(i);
            if (currentChar == ',') {
                if (!validKeyData.contains(key)) {
                    return false;
                }
                key = "";
            } else {
                key += currentChar;
            }
        }
        if (validKeyData.contains(key)) {
            return true;
        }
        return false;
    }

    public static boolean MoviesListValidation(String inputString) {
        if (inputString.length() >= ORDER && inputString.substring(LOWER_BOUND, ORDER).equals(ORDER_ASC)) {
            return genreAndActorsValidation(inputString, ORDER);
        } else if (inputString.length() >= ORDER + 1 && inputString.substring(LOWER_BOUND, ORDER + 1).equals(ORDER_DESC)) {
            return genreAndActorsValidation(inputString,ORDER + 1);
        }
        return false;
    }

    private static boolean genreAndActorsValidation(String inputString, int position) {
        if (inputString.length() <= position) {
            return true;
        }
        String afterOrderString = inputString.substring(position + 1);
        return (afterOrderString.contains(GENRES) || afterOrderString.contains(ACTORS))? true : false;
    }

    public static boolean tvSeriesValidation(String inputString) {
        int index = inputString.indexOf("--");
        if (index != -1) {
            index++;
            if (inputString.substring(index + 1, index + SEASON).equals(SEASONS)) {
                int value = Integer.parseInt(inputString.substring(index + SEASON));
                if (value >= 1 && value <= VAL_SIZE) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
