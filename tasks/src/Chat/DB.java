package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;

public class DB {
    public static void main(String[] args) {
        try {
            //Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.214.180/nosova", "webadmin", "FSBrid52282");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nosova", "postgres", "Y4V6s7xd");
            Statement statement = connection.createStatement();
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            //statement.executeQuery("CREATE TABLE messages (id serial primary key, name text, text text)");
//            statement.executeQuery("INSERT INTO messages (name, text) VALUES ('User1', 'hello?')");
            //statement.executeQuery("INSERT INTO messages (name, text) VALUES ('User2', 'I am here!')");
            while (true) {
                createQuery(statement, consoleReader);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void createQuery(Statement statement, BufferedReader consoleReader) throws IOException, SQLException {
        System.out.println("Введите id или name:");
        String reply = consoleReader.readLine();
        String[] message = reply.split("=");
        getReply(statement, message);
    }

    private static void executeMyQuery(Statement statement, String query) throws SQLException {
        boolean execute = statement.execute(query);
        if (execute){
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " -" + resultSet.getString(3));
            }
        }
    }

    private static void getReply(Statement statement, String[] message) throws SQLException {
        boolean execute2 = statement.execute("SELECT * FROM messages WHERE " + message[0] + " = '" + message[1] + "'");
        if (execute2){
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)  + " | " + resultSet.getString(3));
            }
        }
    }
}
