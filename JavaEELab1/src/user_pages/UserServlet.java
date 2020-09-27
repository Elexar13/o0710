package user_pages;

import model.Table;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user-servlet")
public class UserServlet extends HttpServlet {
    @Inject
    Table table;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String editionCheckBoxValue = request.getParameter("editionCheckBox");
        String decipientCheckBoxValue = request.getParameter("decipientCheckBox");
        String deliveryCheckBoxValue = request.getParameter("deliveryCheckBox");
        String idCheckBoxValue = request.getParameter("idCheckBox");
        String checkBoxValue = " ";

        pw.write("<html>" +
                "<head><title>User page</title><head>" +
                "<body>" +
                "<div align='center'>" +
                "<h1> Сторінка юзера </h1>" +
                "<p>З цієї сторінки ви здатні тільки робити вибірку</p>" +
                "</div>" +
                "<div> " +
                "<h2>Блок вибірки</h2>" +
                "<form class='section' method='get' action='user-servlet'>");

        if (idCheckBoxValue == null) checkBoxValue = " ";
        else checkBoxValue = "checked";
        pw.write("<input type='checkbox' name='idCheckBox' " + checkBoxValue + " > ID ");
        if (editionCheckBoxValue == null) checkBoxValue = " ";
        else checkBoxValue = "checked";
        pw.write("<input type='checkbox' name='editionCheckBox' " + checkBoxValue + " > Видання ");
        if (decipientCheckBoxValue == null) checkBoxValue = " ";
        else checkBoxValue = "checked";
        pw.write( "<input type='checkbox' name='decipientCheckBox' " + checkBoxValue + "> Одержувач " );
        if (deliveryCheckBoxValue == null) checkBoxValue = " ";
        else checkBoxValue = "checked";
        pw.write("<input type='checkbox' name='deliveryCheckBox' " + checkBoxValue + "> Доставка <br>" +
                "<input type='submit' value='SELECT'>");

        if (editionCheckBoxValue != null | decipientCheckBoxValue != null | deliveryCheckBoxValue != null | idCheckBoxValue != null){
            pw.write( "<table border='2'>" +
                    "<tr align='center'><td><b>ID</b></td><td><b>Видання</b></td><td><b>Одержувач</b></td><td><b>Доставка</b></td></tr>");
            for(int i = 0; i < table.getEditions().size(); i++){
                pw.write("<tr>");
                if (idCheckBoxValue != null) pw.write("<td>"+table.getId().get(i)+"</td>");
                else  pw.write("<td> </td>");
                if (editionCheckBoxValue != null) pw.write("<td>"+table.getEditions().get(i).getName()+"</td>");
                else  pw.write("<td> </td>");
                if (decipientCheckBoxValue != null) pw.write("<td>"+table.getRecipients().get(i).getName()+"</td>");
                else  pw.write("<td> </td>");
                if (deliveryCheckBoxValue != null) pw.write( "<td>"+table.getDeliveries().get(i).getName()+"</td>");
                else  pw.write("<td> </td>");
                pw.write("</tr>");
            }
            pw.write(    "</table>");}
        pw.write( "</form>" +
                "<hr>" +
                "</div>" +
                "<form  method='get' action='logout-servlet'>" +
                "<input type='submit' value='LOG OUT'>" +
                "</form>" +
                "</body>" +
                "</html>");
    }
}
