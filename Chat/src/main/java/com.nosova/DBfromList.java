package com.nosova;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.*;
import java.util.ArrayList;

public class DBfromList {
    private Statement statement;
    private Document mainDoc;
    private ArrayList<String[]> messageList = new ArrayList<String[]>();

    public DBfromList () {
        messageList.add(new String[]{"User1", "hello!"});
        messageList.add(new String[]{"User2", "Hi!!"});
        mainDoc = buildDoc(messageList);
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
        for (int i = 0; i < messageList.size(); i++) {
            Element chatMessage = document.createElement("chat-message");
            messages.appendChild(chatMessage);
            chatMessage.setAttribute("id",Integer.toString(i));

            Element sender = document.createElement("sender");
            chatMessage.appendChild(sender);
            Text senderText = document.createTextNode(messageList.get(i)[0]);
            sender.appendChild(senderText);

            Element message = document.createElement("message");
            chatMessage.appendChild(message);
            Text messageText = document.createTextNode(messageList.get(i)[1]);
            message.appendChild(messageText);
        }
        return document;
    }

    public void addMessageToDB(String message)  {
        messageList.add(new String[]{"Random User", message});
        mainDoc = buildDoc(messageList);
    }

    public Document getMainDoc() {
        return mainDoc;
    }
}
