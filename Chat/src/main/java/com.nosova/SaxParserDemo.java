package com.nosova;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

public class SaxParserDemo {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("forParse.xml"), new DefaultHandler(){
            public void startDocument(){
                System.out.println("Start d");
            }
            public void characters(char[] ch, int start, int length){
                char[] chars = Arrays.copyOfRange(ch, start, length);
                //System.out.println(Arrays.toString(chars));
                System.out.print("found");
                for (int i = start; i < length; i++) {
                    System.out.println(chars[i]);
                }
                System.out.println();
            }
        });
    }
}
