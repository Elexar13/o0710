package user_pages;

import model.Table;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/admin-servlet")
public class AdminServlet extends HttpServlet {
    @Inject
    Table table;

    private String editionCheckBoxValue ;
    private String decipientCheckBoxValue;
    private String deliveryCheckBoxValue;
    private String idCheckBoxValue;
    private HttpSession session;
    private PrintWriter pw;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8;");
        pw =  response.getWriter();
        session = request.getSession();
        session.setAttribute("table", table);

        idCheckBoxValue = request.getParameter("idCheckBox");
        editionCheckBoxValue = request.getParameter("editionCheckBox");
        decipientCheckBoxValue = request.getParameter("decipientCheckBox");
        deliveryCheckBoxValue = request.getParameter("deliveryCheckBox");

        pw.write(    "<html>" +
                "<head><title>Admin page</title><head>" +
                "<body>" +
                "<div align='center'>" +
                "<h1> Сторінка адміна </h1>" +
                "<p>З цієї сторінки ви здатні робити вибірку, редагування, додоавання та видалення</p>" +
                "</div>" +
                "<hr>");
        select(pw);
        add(pw);
        update(pw);
        delete(pw);
        logOut(pw);
        pw.write("</body>" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        pw =  response.getWriter();
        session = request.getSession();
        session.setAttribute("table", table);
        idCheckBoxValue = request.getParameter("idCheckBox");
        editionCheckBoxValue = request.getParameter("editionCheckBox");
        decipientCheckBoxValue = request.getParameter("decipientCheckBox");
        deliveryCheckBoxValue = request.getParameter("deliveryCheckBox");

        pw.write(    "<html>" +
                        "<head><title>Admin page</title><meta charset='utf-8'><head>" +
                        "<body>" +
                            "<div align='center'>" +
                            "<h1> Сторінка адміна </h1>" +
                            "<p>З цієї сторінки ви здатні робити вибірку, редагування, додоавання та видалення</p>" +
                            "</div>" +
                            "<hr>");
                            select(pw);
                            add(pw);
                            update(pw);
                            delete(pw);
                            logOut(pw);
       pw.write("</body>" +
                "</html>");
    }

    private void add(PrintWriter pw) {
        pw.write("<div>" +
                "<h2>Блок додавання</h2>" +
                "<form method='post' action='add-servlet'>" +
                "<p><h3>Видання: </h3>" +
                "<input type='text' size='15' name='addEdition'>" +
                "<p><h3>Одержувач: </h3>" +
                "<input type='text' size='15' name='addRecipient'>" +
                "<p><h3>Доставка: </h3>"+
                "<input type='text' size='15' name='addDelivery'> <br><br>" +
                "<input type='submit' value='ADD'>" +
                "</form>" +
                "</div>" +
                "<hr>");
    }

    private void select(PrintWriter pw){
        pw.write("<div> " +
                "<h2>Блок вибірки</h2>" +
                "<form class='section' method='get' action='admin-servlet'>");
        String checkBoxValue = " ";

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
                "</div>");
    }

    private void update(PrintWriter pw){
        if (table.getId().size()>0) {
            pw.write("<div>" +
                    "<h2>Блок редагування</h2>" +
                    "<form method='post' action='update-servlet'>" +
                    "<h3>ID</h3><select name='idList'>");
            table.getId().forEach(i -> pw.write("<option value='" + i.toString() + "'>" + i.toString() + "</option>"));
            pw.write("</select>" +
                    "<p><h3>Видання: </h3><div><b>Старе значення</b><b style='margin-left: 50px;'>Нове значення</b><br>" +
                    "<input type='text' size='15' name='updateEditionOld'>" +
                    "<input type='text' size='15' name='updateEditionNew' style='margin-left: 20px;'> </div></p>" +
                    "<p><h3>Одержувач: </h3><div><b>Старе значення</b><b style='margin-left: 50px;'>Нове значення</b><br>" +
                    "<input type='text' size='15' name='updateDecipientOld'>" +
                    "<input type='text' size='15' name='updateDecipientNew' style='margin-left: 20px;'></div></p>" +
                    "<p><h3>Доставка: </h3><div><b>Старе значення</b><b style='margin-left: 50px;'>Нове значення</b><br>" +
                    "<input type='text' size='15' name='updateDeliveryOld'>" +
                    "<input type='text' size='15' name='updateDeliveryNew' style='margin-left: 20px;'></div></p>" +
                    "<input type='submit' value='UPDATE'>" +
                    "</form>" +
                    "</div>" +
                    "<hr>");
        }
    }

    private void delete(PrintWriter pw){
        if (table.getId().size()>0){
            pw.write("<div>" +
                    "<h2>Блок видалення</h2>"+
                    "<form  method='post' action='delete-servlet'>" +
                    "<h3>ID</h3><select name='idListToDelete'>");
            table.getId().forEach(i -> pw.write("<option value='"+i.toString()+"'>" + i.toString() + "</option>"));
            pw.write("</select>" +
                    "<input type='submit' value='DELETE'>" +
                    "</form>" +
                    "</div>");
        }
    }

    private void logOut(PrintWriter pw){
        pw.write("<br><hr><br>" +
                "<form  method='get' action='logout-servlet'>" +
                "<input type='submit' value='LOG OUT'>" +
                "</form>");
    }
}
