import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo extends HttpServlet{
    private int count = 0;
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("Hello, World!" + count);
        } catch (IOException e) {
            e.printStackTrace();
        }

        count++;
    }
}
