package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static void main(String[] args) {
        try {

            ServerSocket ss = new ServerSocket(10221);
            Socket s = ss.accept();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream())
            );
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Полученная сообщенька:");
                System.out.println(br.readLine());

                //отправка
                System.out.println("Напишите сообщеньку");
                String message = bufferedConsoleReader.readLine();
                bw.write(message, 0, message.length());
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
