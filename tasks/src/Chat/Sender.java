package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sender {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 10221);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream())
            );
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Напишите сообщеньку");
                String message = bufferedConsoleReader.readLine();
                bw.write(message, 0, message.length());
                bw.newLine();
                bw.flush();

                System.out.println("Полученная сообщенька:");
                System.out.println(br.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
