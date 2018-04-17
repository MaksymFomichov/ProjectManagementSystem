package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.CustomerDAO;
import com.gmail.fomichov.m.model.Customer;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDAOImpl implements CustomerDAO {
    public Customer getById(Long id) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE id = " + id);
        Customer customer = new Customer();
        while (resultSet.next()) {
            customer.setId(resultSet.getLong("id"));
            customer.setName(resultSet.getString("name"));
            customer.setCapitalization(resultSet.getInt("capitalization"));
            customer.setFounder(resultSet.getString("founder"));
        }
        resultSet.close();
        statement.close();
        return customer;
    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> list = new ArrayList<Customer>();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name, capitalization, founder FROM customers");
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.setName(resultSet.getString("name"));
            customer.setCapitalization(resultSet.getInt("capitalization"));
            customer.setFounder(resultSet.getString("founder"));
            list.add(customer);
        }
        resultSet.close();
        statement.close();
        return list;
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
        String sql = "SELECT id FROM customers ORDER BY id DESC LIMIT 1";
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
