package query_pages;

import model.Delivery;
import model.Edition;
import model.Recipient;
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
import java.util.Comparator;

@WebServlet("/add-servlet")
public class AddServlet extends HttpServlet {

    @Inject
    Table table;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Table.maxId++;
        table.getId().add(Table.maxId);
        table.getEditions().add(new Edition(getStringUTFParameter(request.getParameter("addEdition")), "Some city"));
        table.getDeliveries().add(new Delivery(getStringUTFParameter(request.getParameter("addDelivery")), 0));
        table.getRecipients().add(new Recipient(getStringUTFParameter(request.getParameter("addRecipient")), "Some address"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-servlet?idCheckBox=on&editionCheckBox=on&decipientCheckBox=on&deliveryCheckBox=on");
        dispatcher.forward(request, response);
    }


    private String getStringUTFParameter(String parameter){
        return new String(parameter.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
