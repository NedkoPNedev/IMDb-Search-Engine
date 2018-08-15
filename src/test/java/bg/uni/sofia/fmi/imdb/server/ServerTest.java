package bg.uni.sofia.fmi.imdb.server;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.junit.Assert.*;

public class ServerTest {

    private static final int PORT = 4444;
    private static final int BUFFER_SIZE = 1024;
    private static final String TEST = "get-movie wadadad --fields=Year,imdbRating";
    private static final String EXPECTED_OUTPUT = "The movie is not found in the system. Please, try again.";

    private SocketChannel socketChannel;

    @BeforeClass
    public static void startServer() {
        Thread serverThread = new Thread(new ServerThread());
        serverThread.start();
    }

    @Test
    public void testServerGetMovieCommand() throws IOException{
        socketChannel = SocketChannel.open(new InetSocketAddress(PORT));
        ByteBuffer inputBuffer = ByteBuffer.wrap(TEST.getBytes());
        socketChannel.write(inputBuffer);
        ByteBuffer outputBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        socketChannel.read(outputBuffer);
        String outputString = new String(outputBuffer.array()).trim();

        assertEquals(EXPECTED_OUTPUT, outputString);

    }
}