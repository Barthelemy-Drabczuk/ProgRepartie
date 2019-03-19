import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;

public class ClientSMTP {

    private String serveurSMTP;
    private int port;
    private String hostname;
    private Socket maChaussette;

    public ClientSMTP(String serveurSMTP, int port, String hostname) {
        this.serveurSMTP = serveurSMTP;
        this.port = port;
        this.hostname = hostname;
        try {
            maChaussette = new Socket(serveurSMTP, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMail(String from, String to, String subject, String body) {
        BufferedReader in;
        BufferedWriter out;

        try {
            out = new BufferedWriter (new OutputStreamWriter(maChaussette.getOutputStream ()));
            in = new BufferedReader(new InputStreamReader(maChaussette.getInputStream()));

            if (Pattern.matches("^[4-5]", in.readLine())) {
                throw new Exception("SMTP error");
            }

            out.write(new StringBuilder("EHLO ").append(this.serveurSMTP).toString());
            out.newLine();
            out.flush();

            while (!Pattern.matches("[1-9]{3} ", in.readLine())) {}

            out.write(new StringBuilder("MAIL FROM: ").append(this.hostname).toString());

            if (Pattern.matches("^[4-5]", in.readLine())) {
                throw new Exception("SMTP error");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
