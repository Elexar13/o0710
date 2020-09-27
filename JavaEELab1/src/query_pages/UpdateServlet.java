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

@WebServlet("/update-servlet")
public class UpdateServlet extends HttpServlet {
    @Inject
    Table table;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8;");
        String str = request.getParameter("idList");
        System.out.println(str);
        int id = Integer.parseInt(request.getParameter("idList"));

            if (table.getEditions().get(id-1).getName().equals(getStringUTFParameter(request.getParameter("updateEditionOld"))))
                table.getEditions().get(id-1).setName(getStringUTFParameter(request.getParameter("updateEditionNew")));
            if (table.getRecipients().get(id-1).getName().equals(getStringUTFParameter(request.getParameter("updateDecipientOld"))))
                table.getRecipients().get(id-1).setName(getStringUTFParameter(request.getParameter("updateDecipientNew")));
            if (table.getDeliveries().get(id-1).getName().equals(getStringUTFParameter(request.getParameter("updateDeliveryOld"))))
                table.getDeliveries().get(id-1).setName(getStringUTFParameter(request.getParameter("updateDeliveryNew")));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-servlet?idCheckBox=on&editionCheckBox=on&decipientCheckBox=on&deliveryCheckBox=on");
        dispatcher.forward(request, response);
    }

    private String getStringUTFParameter(String parameter){
        return new String(parameter.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
