package com.nosova;

import org.w3c.dom.Document;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

public class Chat extends HttpServlet {
    private DB myDataBase = new DB();
    private ArrayList<Document> messageList = new ArrayList<Document>();
    private Document mainDoc = myDataBase.getMainDoc();

    @Override
    //просто отправляет клиенту информацию
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        String xml = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?> <messages><message date=\"2016-05-13 12:35:00\">Text of first message</message></messages>";
        try {
            response.getWriter().write(xml, 0, xml.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*responder(response);*/
    }
/*
    @Override
    //принимает сообщение клиента, добавляет его в БД, (+ пока что еще выводит все заново)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request != null) {
            String message = request.getParameter("message");
            myDataBase.addMessageToDB(message);
            mainDoc = myDataBase.getMainDoc();
        }
        responder(response);
    }

    private void responder(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //читаю и вывожу базу сообщений
            out.write(mainDoc);
           // messagesReaderWriter(out, "E:\\Java\\6 sem\\nosova\\Chat\\src\\main\\webapp\\WEB-INF\\db.txt");
            //читаю html из файла и вывожу
            txtReaderWriter(out, "WEB-INF\\form.txt");
        } catch (IOException ignored) {
        }
    } /*метод должен посылать пользователю список сообщенек в виде XML*/
/*
    private void dbWriter(String message, String filePathDB) throws IOException {
        if (message != null) {
            FileWriter fw = new FileWriter(filePathDB, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();

            bw.write(message);
            bw.close();
            fw.close();
        }
    } /*метод должен добавлять в БД новую сообщеньку*/
/*
    private void txtReaderWriter(PrintWriter out, String path) throws IOException {
        String filePath = getServletContext().getRealPath(path);
        InputStream is = new FileInputStream(new File(filePath));
        Reader r = new InputStreamReader(is, "utf-8");
        BufferedReader br = new BufferedReader(r);
        while (true) {
            String s = br.readLine();
            if (s == null)
                break;
            out.println(s);
        }
        is.close();
    } /*этот метод вроде как писал на страницу HTML*/
/*
    private void messagesReaderWriter(PrintWriter out, String path) throws IOException {
        //String filePath = getServletContext().getRealPath(path);
        InputStream is = new FileInputStream(new File(path));
        Reader r = new InputStreamReader(is, "utf-8");
        BufferedReader br = new BufferedReader(r);
        while (true) {
            String s = br.readLine();
            if (s == null)
                break;
            out.println(s + "</br>");
        }
        is.close();
    } /*а этот метод читал файл с БД и писал сообщения оттуда на нашу страничку*/
}

/*Теперь все должно быть так: есть index.html и скрипт к нему.
* Форма на странице появляется из хтмл кода.
* Сообщения на странице появляются с помощью XMLHttpRequest. Он производится по нажатию кнопки(?), которая работает с методом POST
* Метод post добавляет сообщение в мою БД вызывает метод responder, который должен посылать пользователю список всех сообщенек из БД в виде XML.
* Как он это делает? Он обращается к моей БД, она выдает ему список XML документов.
* Как метод post добавляет сообщенние в БД? - С помощью метода addMesToDB. Этот метод должен быть прописан в классе ДБ */