package com.nosova;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Chat extends HttpServlet{

    private ArrayList<String> messages = new ArrayList<String>();

    @Override
    //метод возвращает список всех обращений
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");

            for (String message : messages) {
                response.getWriter().write(message);
            }
            //читаю html из файла
//            InputStream is = new FileInputStream(new File("src\\main\\resources\\form.txt"));
//                Reader r = new InputStreamReader(is);
//                BufferedReader br = new BufferedReader(r);
//                while (true) {
//                    String s = br.readLine();
//                    if (s == null)
//                        break;
//                    response.getWriter().println(s);
//                }

            response.getWriter().println("<form.txt action=Chat.java method=\"post\">\n" +
            "<input type=\"text\" name=\"message\" size=40>\n" +
                    "<input type=\"submit\" value=\"Отпарвить\">\n" +
                    "</form.txt>");
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @Override
    //отправляет строчку, обрабатывая запрос клиента
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
            messages.add(request.getParameter("message"));
    }

}
