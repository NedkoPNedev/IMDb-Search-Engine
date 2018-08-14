package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static bg.uni.sofia.fmi.imdb.commands.Constants.*;

public class MovieInfoCommand extends Command{

    private String movieName;
    private String movieInfo;
    private List<String> movieFields = new ArrayList<>();

    public MovieInfoCommand(String inputString) {
        movieName = getMovieName(inputString);
        populateFieldsList(inputString);
        movieInfo = "";
        movieInfo += movieName + "\n";
    }

    public String processData() throws IOException, MovieSearchException {
        return getMovieInfo();
    }

    private String getMovieInfo() throws IOException, MovieSearchException {
        String newMovieName = "";
        int movieNameLength = movieName.length();
        char currentChar;
        for (int i = 0; i < movieNameLength; i++) {
            currentChar = movieName.charAt(i);
            newMovieName += (currentChar == ' ') ? "+" : currentChar;
        }
        File file = new File(MOVIES_LOCATION + movieName + JSON);
        if (file.exists()) {
            System.out.println(ALREADY_IN_DATABASE);
            Map<String, Object> movie = getMovieInfoMap(file);
            for (String key : movieFields) {
                movieInfo += key + " - " + movie.get(key) + " \n";
            }
        } else {
            movieInfo += addMovieToDataBase(newMovieName, movieName, movieFields);
        }
        return movieInfo;
    }

    private String getMovieName(String inputString) {
        if (inputString.contains(FIELDS)) {
            return inputString.substring(START_INDEX, inputString.indexOf(FIELDS));
        }
        return inputString.substring(START_INDEX);
    }

    private void populateFieldsList(String inputString) {
        if (inputString.contains(FIELDS)) {
            String fieldsVal = inputString.substring(inputString.indexOf(FIELDS) + FIELDS.length());
            String[] fields = fieldsVal.split(",");
            for (String field : fields) {
                movieFields.add(field);
            }
        }
    }

    private String addMovieToDataBase(String newMovieName, String movieName, List<String> movieFields)
            throws IOException, MovieSearchException {
        String outputStringCode = "";
        JSONObject jsonObject = getMovieJSONObject(OMDB_API_URL + newMovieName);
        if (jsonObject.containsKey(ERROR)) {
            throw new MovieSearchException(NOT_FOUND_IN_SYSTEM);
        } else {
            System.out.println(FOUND_AND_ADDED);
            FileWriter fileWriter =
                    new FileWriter(MOVIES_LOCATION + movieName + JSON);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            for (String key : movieFields) {
                outputStringCode += key + " - " + jsonObject.get(key) + " \n";
            }
            return outputStringCode;
        }
    }
}
