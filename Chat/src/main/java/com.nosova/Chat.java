package com.nosova;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Chat extends HttpServlet {
    private DB myDataBase = new DB();
    private
    @Override
    //метод возвращает список всех обращений
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
            responder(response);
    }

    @Override
    //отправляет строчку, обрабатывая запрос клиента
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request != null) {
            String message = request.getParameter("message");

            String dbPath = "E:\\Java\\6 sem\\nosova\\Chat\\src\\main\\webapp\\WEB-INF\\db.txt";
            dbWriter(message, dbPath);

        }
        responder(response);
    }

    private void dbWriter(String message, String filePathDB) throws IOException {
        if (message != null) {
            FileWriter fw = new FileWriter(filePathDB, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();

            bw.write(message);
            bw.close();
            fw.close();
        }
    }

    private void responder(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //читаю и вывожу базу сообщений
            messagesReaderWriter(out, "E:\\Java\\6 sem\\nosova\\Chat\\src\\main\\webapp\\WEB-INF\\db.txt");
            //читаю html из файла и вывожу
            txtReaderWriter(out, "WEB-INF\\form.txt");
        } catch (IOException ignored) {
        }
    }

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
    }

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
    }
}