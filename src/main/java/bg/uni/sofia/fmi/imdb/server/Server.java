package bg.uni.sofia.fmi.imdb.server;

import bg.uni.sofia.fmi.imdb.commands.*;
import bg.uni.sofia.fmi.imdb.exceptions.MovieSearchException;
import bg.uni.sofia.fmi.imdb.validation.Validator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    private ServerSocketChannel serverSocketChannel;
    private SocketChannel clientSocketChannel;

    private static final int PORT = 4444;
    private static final int BUFFER_SIZE = 128;
    private static final String GET_MOVIE = "get-movie";
    private static final String GET_MOVIES = "get-movies";
    private static final String GET_TV_SERIES = "get-tv-series";
    private static final String GET_MOVIE_POSTER = "get-movie-poster";
    private static final String INVALID_INPUT = "Invalid Input!";

    private Server() throws IOException {
        Selector selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey currentKey = keyIterator.next();
                if (currentKey.isAcceptable()) {
                    accept(selector);
                } else if (currentKey.isReadable()) {
                    clientSocketChannel = (SocketChannel) currentKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    clientSocketChannel.read(buffer);
                    processQuery(buffer);
                }
            }
            keyIterator.remove();
        }
    }

    private void accept(Selector selector) throws IOException {
        clientSocketChannel = serverSocketChannel.accept();
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void processQuery(ByteBuffer buffer) throws IOException {
        String inputString = new String(buffer.array()).trim();
        String outputString = "";
        if (inputString.length() == 0) {
            return;
        }
        Command movieCommand = null;
        if (inputString.startsWith(GET_MOVIE) && Validator.movieInfoValidation(inputString)) {
             movieCommand = new MovieInfoCommand(inputString);
        } else if (inputString.startsWith(GET_MOVIES) && Validator.MoviesListValidation(inputString)) {
             movieCommand = new MoviesListCommand(inputString);
        } else if (inputString.startsWith(GET_TV_SERIES) && Validator.tvSeriesValidation(inputString)) {
            movieCommand = new TVSeriesCommand(inputString);
        } else if (inputString.startsWith(GET_MOVIE_POSTER)) {
            movieCommand = new MoviePosterCommand(inputString);
        } else {
            outputString = INVALID_INPUT;
        }

        if (movieCommand != null) {
            try {
                outputString = movieCommand.processData();
            } catch (MovieSearchException e) {
                outputString = e.getMessage();
            }
        }
        ByteBuffer buff = ByteBuffer.wrap(outputString.getBytes());
        clientSocketChannel.write(buff);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}
