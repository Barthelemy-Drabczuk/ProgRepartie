import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTcpEcho {
    private ServerSocket maChaussetteDeServeur;

    public ServeurTcpEcho(int myHostPort) {
        try {
            this.maChaussetteDeServeur = new ServerSocket (myHostPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run (int nbClient) {
        Socket client;
        BufferedWriter out;
        BufferedReader in;

        try {

            for (int i = 0; i < nbClient; ++i) {

                client = this.maChaussetteDeServeur.accept();
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                out.write(in.readLine().toUpperCase());
                out.newLine();
                out.flush();

                out.close();
                in.close();
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.maChaussetteDeServeur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
