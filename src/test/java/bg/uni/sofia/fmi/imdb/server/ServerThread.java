package bg.uni.sofia.fmi.imdb.server;

import java.io.IOException;

public class ServerThread implements Runnable {

    @Override
    public void run() {
        try {
            Server.main(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
