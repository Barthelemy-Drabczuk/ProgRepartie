import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurEchoThreade {

    private ServerSocket maChaussetteDeServeur;

    public ServeurEchoThreade (int myHostPort) {
        try {
            this.maChaussetteDeServeur = new ServerSocket (myHostPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run (int nbClient) {
        try {

            for (int i = 0; i < nbClient; ++i) {
                Thread sylvain = new Thread(new ThreadServerEcho(this.maChaussetteDeServeur.accept()));
                sylvain.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
