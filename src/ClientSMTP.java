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

            System.out.println("avant la boucle");

            while (' ' != in.readLine().charAt(3)) {}

            System.out.println("après la boucle");

            out.write(new StringBuilder("MAIL FROM: ").append(from).toString());
            out.newLine();
            out.flush();

            String retour = in.readLine();
            if (Pattern.matches("^[4-5]", retour)) {
                throw new Exception("SMTP error");
            }
            else {
                System.out.println(retour);
            }


            out.write(new StringBuilder("RCPT TO: ").append(to).toString());
            out.newLine();
            out.flush();

            retour = in.readLine();
            if (Pattern.matches("^[4-5]", retour)) {
                throw new Exception("SMTP error");
            }
            else {
                System.out.println(retour);
            }

            out.write("DATA");
            out.newLine();
            out.flush();

            retour = in.readLine();
            if (Pattern.matches("^[4-5]", retour)) {
                throw new Exception("SMTP error");
            }
            else {
                System.out.println(retour);
            }

            out.write(new StringBuilder("FROM: ").append(from).toString());
            out.newLine();
            out.flush();

            out.write(new StringBuilder("TO: ").append(to).toString());
            out.newLine();
            out.flush();

            out.write(new StringBuilder("SUBJECT: ").append(subject).toString());
            out.newLine();
            out.flush();

            out.write(body);
            out.newLine();
            out.flush();

            out.write(".");
            out.newLine();
            out.flush();

            retour = in.readLine();
            if (Pattern.matches("^[4-5]", retour)) {
                throw new Exception("SMTP error");
            }
            else {
                System.out.println(retour);
            }

            out.write("QUIT");
            out.newLine();
            out.flush();

            retour = in.readLine();
            if (Pattern.matches("^[4-5]", retour)) {
                throw new Exception("SMTP error");
            }
            else {
                System.out.println(retour);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }//sendMail

    public String getHostname() {
        return hostname;
    }
}
