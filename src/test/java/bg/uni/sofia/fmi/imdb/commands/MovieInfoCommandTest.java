package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MovieInfoCommandTest {

    private static final String TEST_COMMAND = "get-movie Django Unchained --fields=Year,imdbRating";
    private static final String EXPECTED_OUTPUT = "Django Unchained \n" +
            "Year - 2012 \n" +
            "imdbRating - 8.4 \n";
    private static final String TEST_COMMAND1 = "get-movie Seven";
    private static final String EXPECTED_OUTPUT1 = "Seven\n";

    private static final String TEST_COMMAND2 = "get-movie Forrest Gump --fields=Metascore";
    private static final String EXPECTED_OUTPUT2 = "Forrest Gump \n" + "Metascore - 82 \n";

    @Test
    public void TestGetMovieInfo() throws IOException, MovieSearchException {
        Command movieInfoCommand =
                new MovieInfoCommand(TEST_COMMAND);
        Command movieInfoCommand1 = new MovieInfoCommand(TEST_COMMAND1);
        Command movieInfoCommand2 = new MovieInfoCommand(TEST_COMMAND2);
        assertEquals(EXPECTED_OUTPUT, movieInfoCommand.processData());
        assertEquals(EXPECTED_OUTPUT1,movieInfoCommand1.processData());
        assertEquals(EXPECTED_OUTPUT2, movieInfoCommand2.processData());
    }
}