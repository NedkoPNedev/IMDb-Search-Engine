package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static bg.uni.sofia.fmi.imdb.commands.Constants.*;

public class MoviePosterCommand extends Command{

    private String movieName;

    public MoviePosterCommand(String inputString) {
        movieName = inputString.substring(inputString.indexOf(MOVIE_POSTER) + MOVIE_POSTER.length() + 1);
    }

    public String processData() throws IOException, MovieSearchException{
        return getMoviePoster();
    }

    private String getMoviePoster() throws IOException, MovieSearchException {
        String newMovieName = "";
        String imgName = "";
        char currentChar;
        int movieNameLength = movieName.length();
        for (int i = 0; i < movieNameLength; i++) {
            currentChar = movieName.charAt(i);
            if (currentChar == ' ') {
                newMovieName += "+";
            } else {
                newMovieName += currentChar;
                imgName += currentChar;
            }
        }
        File file = new File(MOVIES_LOCATION + movieName + JSON);
        return  getPosterImage(file, imgName, newMovieName);
    }

    private String getPosterImage(File file, String imgName, String newMovieName)
            throws IOException, MovieSearchException {
        String outputStringCode = "";
        if (file.exists()) {
            System.out.println(ALREADY_IN_DATABASE);
            Map<String, Object> movie = getMovieInfoMap(file);
            String poster = movie.get(POSTER).toString();
            outputStringCode += IMAGE_SUCCESSFULLY_ADDED + "+" + imgName + "+" + poster;
        } else {
            JSONObject jsonObject = getMovieJSONObject(OMDB_API_URL + newMovieName);
            if (jsonObject.containsKey(ERROR)) {
                throw new MovieSearchException(NOT_FOUND_IN_SYSTEM);
            } else {
                String poster = jsonObject.get(POSTER).toString();
                outputStringCode += IMAGE_SUCCESSFULLY_ADDED + "+" + imgName + "+" + poster;
            }
        }
        return outputStringCode;
    }
}
