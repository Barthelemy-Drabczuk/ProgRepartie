import java.io.*;
import java.net.Socket;

public class ClientPOP3 {

    private String hostname, user, pass;
    private Socket socket;

    public ClientPOP3(String hostname, String user, String pass) {
        this.hostname = hostname;
        this.user = user;
        this.pass = pass;

        try {
            this.socket = new Socket(this.hostname, 110);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMailList() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 1");
            }

            out.write(new StringBuilder("USER ").append(this.user).toString());
            out.newLine();
            out.flush();

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 2");
            }

            out.write(new StringBuilder("PASS ").append(this.pass).toString());
            out.newLine();
            out.flush();

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 3");
            }

            out.write("LIST");
            out.newLine();
            out.flush();

            int cpt = 0;
            while (true) {
                String ligne = in.readLine();
                if (1 == cpt++ && ligne.equals(".")) {
                    System.out.println("Empty !");
                }
                if (ligne.equals(".")) break;
                System.out.println(ligne);
            }

            out.write("QUIT");
            out.newLine();
            out.flush();

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 2");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readMail(int i) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 1");
            }

            out.write(new StringBuilder("USER ").append(this.user).toString());
            out.newLine();
            out.flush();

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 2");
            }

            out.write(new StringBuilder("PASS ").append(this.pass).toString());
            out.newLine();
            out.flush();

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 3");
            }

            out.write(new StringBuilder("RETR ").append(i).toString());
            out.newLine();
            out.flush();

            while (true) {
                String ligne = in.readLine();
                if (ligne.equals(".")) break;
                System.out.println(ligne);
            }

            out.write("QUIT");
            out.newLine();
            out.flush();

            if ('-' == in.readLine().charAt(0)) {
                throw new Exception("Erreur ! 2");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
