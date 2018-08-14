package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static bg.uni.sofia.fmi.imdb.commands.Constants.*;

public class TVSeriesCommand extends Command{

    private String seriesName;
    private String seasonValue;

    public TVSeriesCommand(String inputString) {
        seriesName = inputString.substring(inputString.indexOf(TV_SERIES) + TV_SERIES.length(),
                inputString.indexOf(SEASON) - 1);
        seasonValue = inputString.substring(inputString.indexOf(SEASON) + SEASON.length());
    }

    public String processData() throws IOException, MovieSearchException {
        return getTVSeries();
    }

    private String getTVSeries() throws IOException, MovieSearchException {
        String newMovieName = "";
        char currentChar;
        int movieNameLength = seriesName.length();
        for (int i = 0; i < movieNameLength; i++) {
            currentChar = seriesName.charAt(i);
            newMovieName += (currentChar == ' ') ? "+" : currentChar;
        }
        String query = String.format
                (OMDB_API_URL + SEASON_FORMAT,
                        URLEncoder.encode(newMovieName, CHARSET),
                        URLEncoder.encode(seasonValue, CHARSET));
        URLConnection connection = new URL(query).openConnection();
        InputStream movieDescriptionStream = connection.getInputStream();
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) jsonParser.parse
                    (new InputStreamReader(movieDescriptionStream, CHARSET));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        movieDescriptionStream.close();
        return getEpisodesString(jsonObject);
    }

    private String getEpisodesString(JSONObject jsonObject) throws MovieSearchException {
        char currentChar;
        if (jsonObject.containsKey(ERROR)) {
            throw new MovieSearchException(TV_SERIES_NOT_FOUND);
        } else {
            String episodes = jsonObject.get(EPISODES).toString();
            String episodesList = "";
            int episodesLength = episodes.length();
            for (int i = 0; i < episodesLength; i++) {
                currentChar = episodes.charAt(i);
                episodesList += currentChar;
                if (currentChar == ',' && episodes.charAt(i - 1) == '}') {
                    episodesList += " \n";
                }
            }
            return episodesList;
        }
    }
}
