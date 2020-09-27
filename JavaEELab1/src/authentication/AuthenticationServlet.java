package authentication;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookieLogin = new Cookie("login", request.getParameter("login"));
        Cookie cookiePass = new Cookie("password", request.getParameter("password"));
        cookiePass.setMaxAge(30 * 60);
        HttpSession session = request.getSession();
        session.setAttribute("cookieLogin", cookieLogin);
        session.setAttribute("cookiePass", cookiePass);
        response.addCookie(cookieLogin);
        response.addCookie(cookiePass);

        RequestDispatcher dispatcher;
        if (cookieLogin.getValue().equals("admin") & cookiePass.getValue().equals("1111")){
            dispatcher = request.getRequestDispatcher("/admin-servlet");
            dispatcher.forward(request, response);
        }else if(cookieLogin.getValue().equals("user") & cookiePass.getValue().equals("1234")){
            dispatcher = request.getRequestDispatcher("/user-servlet");
            dispatcher.forward(request, response);
        }else {
            dispatcher = request.getRequestDispatcher("/undefined-servlet");
            dispatcher.forward(request, response);
        }
    }
}
