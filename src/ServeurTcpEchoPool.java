import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurTcpEchoPool {
    private int nbClient;
    private int myHostPort;
    private ServerSocket chaussetteServer;
    private int taillePool;

    public ServeurTcpEchoPool(int nbClient, int myHostPort, int taillePool) {
        this.nbClient = nbClient;
        this.myHostPort = myHostPort;

        try {
            this.chaussetteServer = new ServerSocket(this.myHostPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.taillePool = taillePool;
    }

    public void run () {
        ExecutorService pool = Executors.newFixedThreadPool(this.taillePool);

        for (int i = 0; i < this.nbClient; ++i) {
            try {
                Thread monFil = new Thread(new ThreadServerEcho(chaussetteServer.accept()));
                pool.execute(monFil);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
