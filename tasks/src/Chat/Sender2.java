package Chat;

import java.io.*;
import java.net.Socket;

public class Sender2 {
    public static void main(String[] args) {
        try {
            //Подсоединяемся к Recevier2
            Socket sOut = new Socket("localhost", 10221);

            final BufferedReader br = new BufferedReader(
                    new InputStreamReader(sOut.getInputStream())
            );
            final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sOut.getOutputStream()));
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
