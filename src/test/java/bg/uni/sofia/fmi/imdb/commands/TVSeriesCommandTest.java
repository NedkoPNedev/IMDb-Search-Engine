package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TVSeriesCommandTest {

    private static final String TEST_COMMAND = "get-tv-series Game of Thrones --season=5";
    private static final String EXPECTED_OUTPUT = "[{\"Episode\":\"1\",\"Released\":\"2015-04-12\",\"imdbID\":\"tt3658012\",\"Title\":\"The Wars to Come\",\"imdbRating\":\"8.6\"}, \n" +
            "{\"Episode\":\"2\",\"Released\":\"2015-04-19\",\"imdbID\":\"tt3846626\",\"Title\":\"The House of Black and White\",\"imdbRating\":\"8.6\"}, \n" +
            "{\"Episode\":\"3\",\"Released\":\"2015-04-26\",\"imdbID\":\"tt3866836\",\"Title\":\"High Sparrow\",\"imdbRating\":\"8.6\"}, \n" +
            "{\"Episode\":\"4\",\"Released\":\"2015-05-03\",\"imdbID\":\"tt3866838\",\"Title\":\"Sons of the Harpy\",\"imdbRating\":\"8.8\"}, \n" +
            "{\"Episode\":\"5\",\"Released\":\"2015-05-10\",\"imdbID\":\"tt3866840\",\"Title\":\"Kill the Boy\",\"imdbRating\":\"8.7\"}, \n" +
            "{\"Episode\":\"6\",\"Released\":\"2015-05-17\",\"imdbID\":\"tt3866842\",\"Title\":\"Unbowed, Unbent, Unbroken\",\"imdbRating\":\"8.1\"}, \n" +
            "{\"Episode\":\"7\",\"Released\":\"2015-05-24\",\"imdbID\":\"tt3866846\",\"Title\":\"The Gift\",\"imdbRating\":\"9.1\"}, \n" +
            "{\"Episode\":\"8\",\"Released\":\"2015-05-31\",\"imdbID\":\"tt3866850\",\"Title\":\"Hardhome\",\"imdbRating\":\"9.9\"}, \n" +
            "{\"Episode\":\"9\",\"Released\":\"2015-06-07\",\"imdbID\":\"tt3866826\",\"Title\":\"The Dance of Dragons\",\"imdbRating\":\"9.5\"}, \n" +
            "{\"Episode\":\"10\",\"Released\":\"2015-06-14\",\"imdbID\":\"tt3866862\",\"Title\":\"Mother's Mercy\",\"imdbRating\":\"9.0\"}]";


    @Test
    public void TestGetTVSeries() throws IOException, MovieSearchException {
        Command tvSeriesCommand = new TVSeriesCommand(TEST_COMMAND);
        assertEquals(EXPECTED_OUTPUT, tvSeriesCommand.processData());
    }
}