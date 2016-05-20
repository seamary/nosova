package com.nosova;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        ArrayList<String[]> messageList = new ArrayList<String[]>();
        messageList.add(new String[]{"1", "User1", "hello"});
        messageList.add(new String[]{"2", "User2", "hi"});
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

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println(writer);
    }
}
