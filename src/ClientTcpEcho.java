import java.io.*;
import java.net.Socket;

public class ClientTcpEcho {

    private String hostname;
    private int hostport;

    public ClientTcpEcho(String hostname, int hostport) {
        this.hostname = hostname;
        this.hostport = hostport;
    }

    public void run () {
        Socket maChaussette;
        {
            try {
                maChaussette = new Socket(this.hostname, this.hostport);
                String monMsg;
                while (true) {
                    System.out.println("entrer un chaîne de caractères: ");
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    BufferedWriter monEcriveurBufferise = new BufferedWriter(new OutputStreamWriter(maChaussette.getOutputStream()));
                    monMsg = in.readLine();
                    monEcriveurBufferise.write(monMsg);
                    monEcriveurBufferise.newLine();
                    monEcriveurBufferise.flush();
                    BufferedReader out = new BufferedReader(new InputStreamReader(maChaussette.getInputStream()));
                    System.out.println(out.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
