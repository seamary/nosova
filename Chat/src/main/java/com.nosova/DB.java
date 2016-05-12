package com.nosova;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DB {
    private Statement statement;
    private ArrayList<Document> messageList;
    public DB () {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nosova", "postgres", "Y4V6s7xd");
            statement = connection.createStatement();
            messageList = getAllMessages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getAllMessages(){
        ArrayList<Document> messages = new ArrayList<Document>();
        try {
            boolean execute = statement.execute("SELECT * FROM messages");
            if (execute){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String[] str = {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)};
                    Document doc = buildDoc(str);
                    messages.add(doc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return messages;
        }
    } /*получить все сообщения из базы данных, построить на их основе список XML документов*/

    private Document buildDoc(String[] str) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();
        Element chatMessage = document.createElement("chat-message");
        document.appendChild(chatMessage);
        chatMessage.setAttribute("id", str[0]);

        Element sender = document.createElement("sender");
        chatMessage.appendChild(sender);
        Text senderText = document.createTextNode(str[1]);
        sender.appendChild(senderText);

        Element message = document.createElement("message");
        chatMessage.appendChild(message);
        Text messageText = document.createTextNode(str[2]);
        message.appendChild(messageText);

        return document;
    } /*построить отдельный документ*/

    public ArrayList<Document> getMessageList() {
        return messageList;
    }
}
