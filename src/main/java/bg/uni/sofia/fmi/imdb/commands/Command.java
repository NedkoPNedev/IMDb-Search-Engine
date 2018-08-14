package bg.uni.sofia.fmi.imdb.commands;

import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import static bg.uni.sofia.fmi.imdb.commands.Constants.CHARSET;

public abstract class Command {

    public abstract String processData() throws IOException, MovieSearchException;

    public Map<String, Object> getMovieInfoMap(File file) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(file, Map.class);
    }

    public JSONObject getMovieJSONObject(String url) throws IOException{
        URLConnection connection = new URL(url).openConnection();
        InputStream movieDescriptionStream = connection.getInputStream();
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        JSONObject jsonObject = new JSONObject();
        try(InputStreamReader inputStreamReader = new InputStreamReader(movieDescriptionStream, CHARSET)) {
            jsonObject = (JSONObject) jsonParser.parse
                    (inputStreamReader);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        movieDescriptionStream.close();
        return jsonObject;
    }
}
