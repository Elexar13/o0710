package dao;

import entities.Country;
import entities.Vendor;
import exceptions.CantFindCountryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {
    private Connection connection;

    public VendorDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Vendor> findVendors(int countryId){

            List<Vendor> result = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select v.id, v.name from vendor v " +
                                                                    "inner join country c " +
                                                                    "on v.country_id = c.id " +
                                                                    "where c.id = ?");

        ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                result.add(new Vendor(id, name, countryId));
                System.out.println(name);
            }
            return result;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        return null;
    }
}
