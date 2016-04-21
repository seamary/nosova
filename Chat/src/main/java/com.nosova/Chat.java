package com.nosova;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Chat extends HttpServlet {

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
            String filePathDB = getServletContext().getRealPath("WEB-INF\\db.txt");
            FileOutputStream os = new FileOutputStream(filePathDB, true);
            os.write(message.getBytes(), 0, message.length());
//            PrintStream ps = new PrintStream(fos);
//            ps.println(request.getParameter("message"));
            os.close();
        }
        responder(response);
    }

    private void responder(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //читаю базу сообщений
            txtReader(out, "WEB-INF\\db.txt");
            //читаю html из файла
            txtReader(out, "WEB-INF\\form.txt");
        } catch (IOException e) {
            out.print(e.getMessage());
        }
    }

    private void txtReader(PrintWriter out, String path) throws IOException {
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
}