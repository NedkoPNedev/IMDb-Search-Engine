package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MoviePosterCommandTest {

    private static final String TEST_COMMAND = "get-movie-poster The good, the bad and the ugly";
    private static final String EXPECTED_OUTPUT =
            "The image has been successfully added to your directory!+Thegood,thebadandtheugly+https://m.media-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1_SX300.jpg";

    private static final String TEST_COMMAND1 = "get-movie-poster The Dark Knight";
    private static final String EXPECTED_OUTPUT1 =
            "The image has been successfully added to your directory!+TheDarkKnight+https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg";

    @Test
    public void TestGetMoviePoster() throws IOException, MovieSearchException {
        Command moviePosterCommand = new MoviePosterCommand(TEST_COMMAND);
        assertEquals(EXPECTED_OUTPUT, moviePosterCommand.processData());

        Command moviePosterCommand1 = new MoviePosterCommand(TEST_COMMAND1);
        assertEquals(EXPECTED_OUTPUT1, moviePosterCommand1.processData());
    }
}