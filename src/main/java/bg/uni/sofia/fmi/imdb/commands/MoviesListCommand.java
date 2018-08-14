package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static bg.uni.sofia.fmi.imdb.commands.Constants.*;

public class MoviesListCommand extends Command{

    private List<String> genres = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private String inputString;

    public MoviesListCommand(String inputString) {
        this.inputString = inputString;
    }

    public String processData() throws IOException, MovieSearchException {
        return getMoviesList();
    }

    private String getMoviesList() throws IOException, MovieSearchException {
        int length = inputString.length();
        File dir = new File(MOVIES_LOCATION);
        File[] files = dir.listFiles();
        List<Map<String, Object>> moviesList = new ArrayList<>(files.length);
        for (File file : files) {
            Map<String, Object> movie = getMovieInfoMap(file);
            moviesList.add(movie);
        }
        if (inputString.contains(GENRES_TYPE)) {
            fillList(genres, GENRES_TYPE, length);
        }
        if (inputString.contains(ACTORS_TYPE)) {
            fillList(actors, ACTORS_TYPE, length);
        }
        return filteredAndSortedString(moviesList);
    }

    private void fillList(List<String> list, String fieldType, int length) {
        String currentString = "";
        char currentChar;
        int index = inputString.indexOf(fieldType) + 7;
        if (inputString.contains(ACTORS) && fieldType.equals(GENRES_TYPE)) {
            length = inputString.indexOf(ACTORS) - 1;
        }
        for (int i = index; i < length; i++) {
            currentChar = inputString.charAt(i);
            if (currentChar == ',') {
                list.add(currentString);
                currentString = "";
            } else if (currentChar == '+') {
                break;
            } else {
                currentString += currentChar;
            }
        }
        if (currentString.length() > 0) {
            list.add(currentString);
        }
    }

    private String filteredAndSortedString(List<Map<String, Object>> moviesList)
            throws MovieSearchException {
        String outputStringCode = "";
        List<Map<String, Object>> sortedMovieList = moviesList.stream()
                .filter(e -> {
                    String genresList = e.get(GENRES_TYPE.replace('g','G')
                            .substring(0,GENRE_LEN)).toString();
                    String actorsList = e.get(ACTORS_TYPE.replace('a','A')).toString();
                    if (genres.size() > 0) {
                        for (String genre : genres) {
                            if (!genresList.contains(genre)) {
                                return false;
                            }
                        }
                    }
                    if (actors.size() > 0) {
                        for (String actor : actors) {
                            if (!actorsList.contains(actor)) {
                                return false;
                            }
                        }
                    }
                    return true;
                })
                .sorted((o1, o2) -> {
                    double rating_1 = Double.parseDouble(o1.get(IMDB_RATING).toString());
                    double rating_2 = Double.parseDouble(o2.get(IMDB_RATING).toString());

                    return (inputString.charAt(inputString.indexOf(ORDER) + ORDER_LEN) == 'a') ? compare(rating_1, rating_2, 1) :
                            compare(rating_1, rating_2, -1);
                })
                .collect(Collectors.toList());

        for (Map<String, Object> map : sortedMovieList) {
            outputStringCode += TITLE + " - " + map.get(TITLE) + "   " +
                    YEAR + " - " + map.get(YEAR) + "   " +
                    IMDB_RATING + " - " + map.get(IMDB_RATING) + " \n";
        }
        if (outputStringCode.length() == 0) {
            throw new MovieSearchException(NO_MOVIE_WITH_GIVEN_PARAMS);
        }
        return outputStringCode;
    }

    private static int compare(double rating_1, double rating_2, int val) {
        if (rating_1 - rating_2 >= EPSILON) return val;
        else if (rating_1 - rating_2 <= EPSILON && rating_1 - rating_2 >= -EPSILON) return 0;
        else return -val;
    }
}
