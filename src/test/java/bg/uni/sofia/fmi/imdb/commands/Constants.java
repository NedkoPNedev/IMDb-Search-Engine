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
/*
COMMMAND 1
get-movie Django Unchained --fields=Year,Actors,imdbRating,Director
Django Unchained
Year - 2012
Actors - Jamie Foxx, Christoph Waltz, Leonardo DiCaprio, Kerry Washington
imdbRating - 8.4
Director - Quentin Tarantino

get-movie Seven
Seven

COMMAND 2
get-movies --order=asc --genres=Action --actors=Ryan Reynolds
Title - Green Lantern   Year - 2011   imdbRating - 5.6
Title - The Hitman's Bodyguard   Year - 2017   imdbRating - 6.9
Title - Deadpool 2   Year - 2018   imdbRating - 8.0
Title - Deadpool   Year - 2016   imdbRating - 8.0

COMMAND 3
get-tv-series Game of Thrones --season=5
[{"Episode":"1","Released":"2015-04-12","imdbID":"tt3658012","Title":"The Wars to Come","imdbRating":"8.6"},
{"Episode":"2","Released":"2015-04-19","imdbID":"tt3846626","Title":"The House of Black and White","imdbRating":"8.6"},
{"Episode":"3","Released":"2015-04-26","imdbID":"tt3866836","Title":"High Sparrow","imdbRating":"8.6"},
{"Episode":"4","Released":"2015-05-03","imdbID":"tt3866838","Title":"Sons of the Harpy","imdbRating":"8.8"},
{"Episode":"5","Released":"2015-05-10","imdbID":"tt3866840","Title":"Kill the Boy","imdbRating":"8.7"},
{"Episode":"6","Released":"2015-05-17","imdbID":"tt3866842","Title":"Unbowed, Unbent, Unbroken","imdbRating":"8.1"},
{"Episode":"7","Released":"2015-05-24","imdbID":"tt3866846","Title":"The Gift","imdbRating":"9.1"},
{"Episode":"8","Released":"2015-05-31","imdbID":"tt3866850","Title":"Hardhome","imdbRating":"9.9"},
{"Episode":"9","Released":"2015-06-07","imdbID":"tt3866826","Title":"The Dance of Dragons","imdbRating":"9.5"},
{"Episode":"10","Released":"2015-06-14","imdbID":"tt3866862","Title":"Mother's Mercy","imdbRating":"9.0"}]

COMMMAND 4
get-movie-poster The good, the bad and the ugly
The image has been successfully added to your directory!+Thegood,thebadandtheugly+https://m.media-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1_SX300.jpg

get-movie-poster The Dark Knight
The image has been successfully added to your directory!+TheDarkKnight+https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg

 */