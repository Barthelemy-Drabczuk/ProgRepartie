import java.io.*;
import java.net.Socket;

public class ClientFTP {
    private String hostanme;
    private int port;
    private Socket maChaussette;

    public ClientFTP(String hostanme, int port) {
        this.hostanme = hostanme;
        this.port = port;
        try {
            this.maChaussette = new Socket(this.hostanme, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFileByName (String InfileName, String OutFileName) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(maChaussette.getOutputStream()));
            InputStream in = maChaussette.getInputStream();

            out.write(InfileName);
            out.newLine();
            out.flush();

            byte buff [] = new byte[512];

            FileOutputStream file = new FileOutputStream(OutFileName);

            int nbOctets;

            while ((nbOctets = in.read(buff)) != -1) {
                file.write(buff, 0, nbOctets);
            }

            out.close();
            in.close();
            file.close();
            maChaussette.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
