package dao;

import entities.Country;
import exceptions.CantFindCountryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> findAll(){
        try {
            List<Country> result = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("select * from country");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String shortName = rs.getString("short_name");
                result.add(new Country(id, name, shortName));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }

    }
}

