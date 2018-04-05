package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.CustomerDAO;
import com.gmail.fomichov.m.model.Customer;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcCustomerDAOImpl implements CustomerDAO {
    public Customer getById(Long aLong) throws SQLException {
        return null;
    }

    public List<Customer> getAll() throws SQLException {
        return null;
    }

    public void update(Customer customer) throws SQLException {

    }

    public void delete(Customer customer) throws SQLException {

    }

    public void create(Customer customer) throws SQLException {
        PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("INSERT INTO customers (name, capitalization, founder) VALUES(?, ?, ?)");
        statement.setString(1, customer.getName());
        statement.setInt(2, customer.getCapitalization());
        statement.setString(3, customer.getFounder());
        statement.executeUpdate();
        customer.setId(getLastID());
        statement.close();
    }

    public long getLastID() throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        String sql = "SELECT id FROM customers ORDER BY id DESK LIMIT 1";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.first()) {
                return resultSet.getLong("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
