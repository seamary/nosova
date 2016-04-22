package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatUser2 {
    public static void main(String[] args) {
        try {

            Socket up1 = null;
            Socket down1 = null;

            if (!args[0].equals("0")) {
                ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
                up1 = ss.accept();
            }

            if (!args[1].equals("0")) {
                down1 = new Socket("localhost", Integer.parseInt(args[1]));
            }

            final Socket up = up1;
            final Socket down = down1;



            if (up != null) {
                Thread listenUp = new Thread() {
                    public void run() {
                        try {
                            while (true) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(up.getInputStream()));
                                String strUp = br.readLine();
                                System.out.println(strUp);

                                if (down != null)
                                    sendMessage(down, strUp);
                            }
                        } catch (Exception e) {
                            System.out.println("Oooooops!1 " + e);
                        }
                    }
                };
                listenUp.start();
            }


            if (down != null) {
                Thread listenDown = new Thread() {
                    public void run() {
                        try {
                            while (true) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(down.getInputStream()));
                                String strDown = br.readLine();
                                System.out.println(strDown);

                                if (up != null)
                                    sendMessage(up, strDown);
                            }
                        } catch (Exception e) {
                            System.out.println("Oooooops!2 " + e);
                        }
                    }
                };
                listenDown.start();
            }


            Thread send = new Thread() {
                public void run() {
                    try {
                        while (true) {
                            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                            String str = br2.readLine();

                            if (up != null)
                                sendMessage(up, str);

                            if (down != null)
                                sendMessage(down, str);
                        }
                    } catch (Exception e) {
                        System.out.println("Oooooops!3 " + e);
                    }
                }
            };
            send.start();


        } catch (Exception e) {
            System.out.println("Oooooops!4 " + e);
        }
    }



    public static synchronized void sendMessage(Socket socket, String message)throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(message, 0, message.length());
        bw.newLine();
        bw.flush();
    }
}
