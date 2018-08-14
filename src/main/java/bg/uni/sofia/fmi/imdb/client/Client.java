package bg.uni.sofia.fmi.imdb.client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    private SocketChannel clientSocketChannel;

    private static final int PORT = 4444;
    private static final int BUFFER_SIZE = 10000;
    private static final int MIN_COMMAND_LENGTH = 9;

    private static final String FILE_LOCATION = "ClientDir_";
    private static final String SOCKET_CHANNEL_OPENING_PROBLEM = "Problem with opening the Socket Channel!";
    private static final String SERVICE_EXITED = "You have exited the service";
    private static final String QUIT = "quit";
    private static final String IO_BUFFER_PROBLEM = "Problem with reading from / writing to the buffer!";
    private static final String ENTER_COMMAND = "Please, enter a command :";
    private static final String SOCKET_CHANNEL_CLOSING_PROBLEM = "Problem with closing the Socket Channel!";
    private static final String JPG = "jpg";
    private static final String URL_PROBLEM = "Problem with getting the URL!";
    private static final String INVALID_INPUT = "Invalid Input!";

    private static final String MOVIE_INFO_COMMAND = "*   get-movie <movie_name> --fields=[field_1, field_2]";
    private static final String MOVIES_LIST_COMMAND = "*   get-movies --order=[asc|desc] -genres=[genre_1, genre_2] --actors=[actor_1, actor_2]";
    private static final String TV_SERIES_COMMAND = "*   get-tv-series <name> --season=<value>";
    private static final String MOVIE_POSTER_COMMAND = "*   get-movie-poster <name>";
    private static final String QUIT_COMMAND = "*   quit";

    private static final String WELCOME_MESSAGE = "Welcome to the IMDb Search Engine!";
    private static final String LIST_OF_COMMANDS = "Here is the List of Commands : ";
    private static final String FIELD_OPTION = "Option 'fields' is arbitrary.";
    private static final String GENRES_AND_ACTORS_OPTIONS = "Options 'genres' and 'actors' are arbitrary.";
    private static final String IMAGE_SUCCESSFULLY_ADDED = "The image has been successfully added to your directory!";

    private Client() {
        printIMDbSearchInfo();

        try {
            clientSocketChannel = SocketChannel.open(new InetSocketAddress(PORT));
        } catch (IOException e) {
            System.out.println(SOCKET_CHANNEL_OPENING_PROBLEM);
        }
        Scanner scanner = new Scanner(System.in);
        String inputString;
        File clientDir = new File(FILE_LOCATION + String.valueOf(System.currentTimeMillis()));
        clientDir.mkdir();

        while (scanner.hasNext()) {
            inputString = scanner.nextLine();
            if (inputString.equals(QUIT)) {
                System.out.println(SERVICE_EXITED);
                break;
            }

            if (inputString.length() >= MIN_COMMAND_LENGTH) {
                ByteBuffer buffer = ByteBuffer.wrap(inputString.getBytes());
                try {
                    clientSocketChannel.write(buffer);
                    buffer.flip();
                    ByteBuffer buff = ByteBuffer.allocate(BUFFER_SIZE);
                    clientSocketChannel.read(buff);
                    String responseString = new String(buff.array()).trim();
                    System.out.println(response(responseString, clientDir));
                } catch (IOException e) {
                    System.out.println(IO_BUFFER_PROBLEM);
                }
            } else {
                System.out.println(INVALID_INPUT);
            }
            System.out.println(ENTER_COMMAND);
        }
        try {
            clientSocketChannel.close();
        } catch (IOException e) {
            System.out.println(SOCKET_CHANNEL_CLOSING_PROBLEM);
        }
    }

    private String response(String responseString, File clientDir) {
        if (responseString.contains(IMAGE_SUCCESSFULLY_ADDED)) {
            try {
                URL url = new URL(responseString.substring(responseString.lastIndexOf('+') + 1));
                BufferedImage img = ImageIO.read(url);
                File imgFile = new File(clientDir.getPath() + "\\" +
                        responseString.substring(responseString.indexOf('+') + 1,
                                responseString.lastIndexOf('+')) + "." + JPG);
                ImageIO.write(img, JPG, imgFile);
            } catch (IOException e) {
                System.out.println(URL_PROBLEM);
            }
            return responseString.substring(0, responseString.indexOf('+'));
        }
        return responseString;
    }

    private void printIMDbSearchInfo() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(LIST_OF_COMMANDS);
        System.out.println(MOVIE_INFO_COMMAND);
        System.out.println(FIELD_OPTION);
        System.out.println(MOVIES_LIST_COMMAND);
        System.out.println(GENRES_AND_ACTORS_OPTIONS);
        System.out.println(TV_SERIES_COMMAND);
        System.out.println(MOVIE_POSTER_COMMAND);
        System.out.println(QUIT_COMMAND);
        System.out.println(ENTER_COMMAND);
    }

    public static void main(String[] args) {
        Client movieClient = new Client();
    }
}
