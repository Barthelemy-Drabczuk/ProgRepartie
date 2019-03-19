import java.io.*;
import java.net.Socket;

public class ThreadServerEcho implements Runnable {
    private Socket client;

    public ThreadServerEcho (Socket maSock) {
        this.client = maSock;
    }

    public void run () {
        BufferedWriter out;
        BufferedReader in;

        try {
            in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()));

            while (true) {
                String msg = in.readLine();
                if (msg == null) break;
                out.write(msg.toUpperCase());
                out.newLine();
                out.flush();
            }

            out.close();
            in.close();
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
