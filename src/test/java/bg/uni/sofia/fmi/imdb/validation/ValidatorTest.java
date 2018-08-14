package bg.uni.sofia.fmi.imdb.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    private static final String TEST1 = "get-movie The Dark Knight --fields=Released,Metascore";
    private static final String TEST2 = "get-movie The Dark Knight -fieldsYear";
    private static final String TEST3 = "get-movies --order=asc --genres=Comedy --actors=Leonardo DiCaprio";
    private static final String TEST4 = "get-movies order=desc";
    private static final String TEST5 = "get-tv-series Friends --season=4";
    private static final String TEST6 = "get-tv-series Friends season3";


    @Test
    public void TestMovieInfoValidation() {
        assertEquals(true, Validator.movieInfoValidation(TEST1));
        assertEquals(false, Validator.movieInfoValidation(TEST2));
    }

    @Test
    public void TestMoviesListValidation() {
        assertEquals(true, Validator.MoviesListValidation(TEST3));
        assertEquals(false, Validator.MoviesListValidation(TEST4));
    }

    @Test
    public void TestTVSeriesValidation() {
        assertEquals(true, Validator.tvSeriesValidation(TEST5));
        assertEquals(false, Validator.tvSeriesValidation(TEST6));
    }
}