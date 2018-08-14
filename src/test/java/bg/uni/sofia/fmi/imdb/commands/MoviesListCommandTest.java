package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MoviesListCommandTest {

    private static final String TEST_COMMAND = "get-movies --order=asc --genres=Action --actors=Ryan Reynolds";
    private static final String EXPECTED_OUTPUT = "Title - Green Lantern   Year - 2011   imdbRating - 5.6 \n" +
            "Title - The Hitman's Bodyguard   Year - 2017   imdbRating - 6.9 \n" +
            "Title - Deadpool 2   Year - 2018   imdbRating - 8.0 \n" +
            "Title - Deadpool   Year - 2016   imdbRating - 8.0 \n";

    @Test
    public void TestGetMoviesList() throws IOException, MovieSearchException {
        Command moviesListCommand = new MoviesListCommand(TEST_COMMAND);

        assertEquals(EXPECTED_OUTPUT, moviesListCommand.processData());
    }
}