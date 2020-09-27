package authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/undefined-servlet")
public class UndefinedServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write("<html>" +
                "<head><title>Undefined page</title><head>" +
                "<body>" +
                "<h1 align='center'>Немає такого користувача</h1>" +
                "<form  method='get' action='logout-servlet'>" +
                "<input type='submit' value='LOG OUT'>" +
                "</form>" +
                "</body>" +
                "</html>");
    }
}
