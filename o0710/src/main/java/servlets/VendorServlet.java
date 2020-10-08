package servlets;

import beans.VendorBean;
import dao.CountryDAO;
import dao.VendorDAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "VendorServlet", urlPatterns = "/vendor-servlet")
public class VendorServlet extends HttpServlet {

    DataSource ds;
    private VendorDAO vendorDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/watches");
            Connection connection = ds.getConnection();
            vendorDAO = new VendorDAO(connection);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VendorBean vendorBean = new VendorBean();
        vendorBean.setVendors(vendorDAO.findVendors(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("vendorBean", vendorBean);
        request.getRequestDispatcher("/to-vendor-page.jsp").forward(request, response);
    }
}
