package authentication;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/log-in")
public class LogInServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10 * 60);

        if (session.getAttribute("cookieLogin")!=null & session.getAttribute("cookiePass")!=null){
            Cookie cookieLogin = (Cookie) session.getAttribute("cookieLogin");
            Cookie cookiePass = (Cookie) session.getAttribute("cookiePass");
            RequestDispatcher dispatcher;
            if (cookieLogin.getValue().equals("admin") & cookiePass.getValue().equals("1111")){
                dispatcher = request.getRequestDispatcher("/admin-servlet");
                dispatcher.forward(request, response);
            }else if(cookieLogin.getValue().equals("user") & cookiePass.getValue().equals("1234")){
                dispatcher = request.getRequestDispatcher("/user-servlet");
                dispatcher.forward(request, response);
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(
                    "<html>" +
                        "<head><title>Log in</title></head>" +
                        "<body align='center'>" +
                        "<form action='authentication' method='get'>" +
                        "<input type='text' name='login'> Логин <br>" +
                        "<input type='password' name='password'> Пароль <br>" +
                        "<input type='submit'>" +
                        "</form>" +
                        "</body>" +
                        "</html>"
        );
    }
}
