package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Recevier2 {
    public static void main(String[] args) {
        try {
            //Открываем сокет на прослущивание Sender2
            ServerSocket ss = new ServerSocket(10221);
            Socket s = ss.accept();

            final BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream())
            );
            final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            final BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));

            Thread tReader = new Thread() {
                public void run() {
                    while (true){
                        String message = null;
                        try {
                            message = br.readLine();
                            System.out.println("Полученная сообщенька:");
                            System.out.println(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            Thread tWriter = new Thread() {
                public void run() {
                    while (true) {
                        String message = null;
                        try {
                            message = bufferedConsoleReader.readLine();
                            bw.write(message, 0, message.length());
                            bw.newLine();
                            bw.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            tReader.start();
            tWriter.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
