package com.nosova;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.*;
import java.util.ArrayList;

public class Tester2 {
    private Statement statement;
    private Document mainDoc;
    private ArrayList<String[]> messageList = new ArrayList<String[]>();

    public static void main(String[] args) {

    }
//    public DB () {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nosova", "postgres", "Y4V6s7xd");
//            statement = connection.createStatement();
//            ArrayList<String[]> messageList = getMessageList();
//            mainDoc = buildDoc(messageList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private ArrayList<String[]> getMessageList(){ /*получить список всех сообщений из базы данных*/
        try {
            boolean execute = statement.execute("SELECT * FROM messages");
            if (execute){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String[] strs = {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)};
                    messageList.add(strs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return messageList;
        }
    }

    private Document buildDoc(ArrayList<String[]> messageList) { /*построить документ на основе списка*/
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();
        Element messages = document.createElement("messages");
        document.appendChild(messages);
        for (String[] str : messageList) {
            Element chatMessage = document.createElement("chat-message");
            messages.appendChild(chatMessage);
            chatMessage.setAttribute("id", str[0]);

            Element sender = document.createElement("sender");
            chatMessage.appendChild(sender);
            Text senderText = document.createTextNode(str[1]);
            sender.appendChild(senderText);

            Element message = document.createElement("message");
            chatMessage.appendChild(message);
            Text messageText = document.createTextNode(str[2]);
            message.appendChild(messageText);
        }
        return document;
    }

    public void addMessageToDB(String message)  {
        try {
            statement.executeQuery("INSERT INTO messages (name, text) VALUES ('User1', '" + message + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        messageList = getMessageList();
        mainDoc = buildDoc(messageList);
    }

    public Document getMainDoc() {
        return mainDoc;
    }
}
