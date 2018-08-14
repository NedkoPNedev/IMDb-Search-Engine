package bg.uni.sofia.fmi.imdb.commands;

public class Constants {

    public static final String OMDB_API_URL = "http://www.omdbapi.com/?apikey=e1abbdb3&t=";
    public static final String CHARSET = "UTF-8";
    public static final String MOVIES_LOCATION = "C:\\Users\\C5275150\\Desktop\\IMDbSearch\\movieDataBase\\";
    public static final String ALREADY_IN_DATABASE = "The movie is already in the Database.";
    public static final String FOUND_AND_ADDED = "The movie is found and added to the movie Database.";
    public static final String JSON = ".json";
    public static final String ERROR = "Error";
    public static final String GENRES_TYPE = "genres";
    public static final String ACTORS_TYPE = "actors";
    public static final String IMDB_RATING = "imdbRating";
    public static final String TITLE = "Title";
    public static final String YEAR = "Year";
    public static final String EPISODES = "Episodes";
    public static final String SEASON_FORMAT = "%s&season=%s";
    public static final String POSTER = "Poster";
    public static final int START_INDEX = 10;
    public static final int GENRE_LEN = 5;
    public static final int ORDER_LEN = 6;
    public static final String ORDER = "order=";
    public static final double EPSILON = 0.01;
    public static final String ACTORS = "--actors=";
    public static final String MOVIE_POSTER = "get-movie-poster";
    public static final String FIELDS = "--fields=";
    public static final String TV_SERIES = "get-tv-series";
    public static final String SEASON = "--season=";
    public static final String NOT_FOUND_IN_SYSTEM = "The movie is not found in the system. Please, try again.";
    public static final String NO_MOVIE_WITH_GIVEN_PARAMS = "There aren't any movies with given parameters.";
    public static final String IMAGE_SUCCESSFULLY_ADDED = "The image has been successfully added to your directory!";
    public static final String TV_SERIES_NOT_FOUND = "TV series are not found in the system. Please, try again.";
}