package servlets;

import beans.CountryBean;
import dao.CountryDAO;
import exceptions.CantFindCountryException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DataBaseServlet", urlPatterns = {"*.html"})
public class DataBaseServlet extends javax.servlet.http.HttpServlet {

    //@Resource(name = "jdbc/watches")
    DataSource ds;
    //Connection connection;
    private CountryDAO countryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
//        try {
//            connection = ds.getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/watches");
            Connection connection = ds.getConnection();
            countryDAO = new CountryDAO(connection);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        try {
            CountryBean countryBean = new CountryBean();
            countryBean.setCountries(countryDAO.findAll());
            request.setAttribute("countryBean", countryBean);
            request.getRequestDispatcher("/showCountries.jsp").forward(request, response);
        }catch (CantFindCountryException e){
            e.printStackTrace();
        }

    }
}
