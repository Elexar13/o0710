package query_pages;

import model.Table;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/delete-servlet")
public class DeleteServlet extends HttpServlet {

    @Inject
    Table table;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8;");
        int id = Integer.parseInt(request.getParameter("idListToDelete"));
        int i = table.getId().indexOf(id);
        table.getEditions().remove(i);
        table.getDeliveries().remove(i);
        table.getRecipients().remove(i);
        table.getId().remove(i);
        Table.maxId--;

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-servlet?idCheckBox=on&editionCheckBox=on&decipientCheckBox=on&deliveryCheckBox=on");
        dispatcher.forward(request, response);
    }
}
