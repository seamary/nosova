package com.nosova;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.StringWriter;

/**
 * Created by st030578 on 22.04.2016.
 */
public class DocumentBuilderDemo {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element chatMessage = document.createElement("chat-message");
        document.appendChild(chatMessage);
        chatMessage.setAttribute("sent", "today");

        Element sender = document.createElement("sender");
        chatMessage.appendChild(sender);
        Text senderText = document.createTextNode("Chat_User1");
        sender.appendChild(senderText);

        Element message = document.createElement("message");
        chatMessage.appendChild(message);
        Text messageText = document.createTextNode("Hello!");
        message.appendChild(messageText);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
        System.out.println(writer);
    }
}
