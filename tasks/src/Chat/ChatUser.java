package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class ChatUser {
    private static Socket socketUp;
    private static Socket socketDown;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
           // Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Y4V6s7xd");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.214.180/nosova", "webadmin", "FSBrid52282");
            final Statement statement = connection.createStatement();
            final ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
            final String myName = "User" + Integer.parseInt(args[0]);
            System.out.println(myName);
            if (args.length == 2) {
                socketUp = new Socket("localhost", Integer.parseInt(args[1]));
            }
            boolean execute = statement.execute("SELECT * FROM messages");
            if (execute) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)  + " | " + resultSet.getString(3));
                }
            }
            Thread down = new Thread() {
                public void run() {
                try {
                    while (true){
                        if (socketUp != null) {
                            String message = getMessage(socketUp);
                            if (socketDown != null) {
                                sendMessage(socketDown, message);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            };

            Thread up = new Thread() {
                public void run() {
                    try {
                        socketDown = ss.accept();
                        System.out.println("connect");
                        while (true){
                            if (socketDown != null) {
                                String message = getMessage(socketDown);
                                if (socketUp != null) {
                                    sendMessage(socketUp, message);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread upDown = new Thread() {
                public void run() {
                    try {
                        BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));
                        while (true) {
                            String message = bufferedConsoleReader.readLine();
                            statement.executeUpdate("INSERT INTO messages (name, text) VALUES ('" + myName + "', '" + message + "')");
                            if (socketDown != null) {
                                sendMessage(socketDown, message);
                            }
                            if (socketUp != null) {
                                sendMessage(socketUp, message);
                            }
                        }
                    }
                    catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            };

            down.start();
            up.start();
            upDown.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(Socket socket) throws IOException {
        BufferedReader brFromUp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = brFromUp.readLine();
        System.out.println("Полученная сообщенька:");
        System.out.println(message);
        return message;
    }

    public static synchronized void sendMessage(Socket socket, String message) throws IOException {
        BufferedWriter bwToDown = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bwToDown.write(message, 0, message.length());
        bwToDown.newLine();
        bwToDown.flush();
    }
}

