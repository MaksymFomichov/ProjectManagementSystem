package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.DeveloperDAO;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcDeveloperDAOImpl implements DeveloperDAO {

    public Developer getById(Long aLong) throws SQLException {
        return null;
    }

    public List<Developer> getAll() throws SQLException {
        return null;
    }

    public void update(Developer developer) throws SQLException {

    }

    public void delete(Developer developer) throws SQLException {

    }

    public void create(Developer developer) throws SQLException {
        PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("INSERT INTO developers (name, age, sex, salary) VALUES(?, ?, ?, ?)");
        statement.setString(1, developer.getName());
        statement.setInt(2, developer.getAge());
        statement.setBoolean(3, developer.getSex());
        statement.setBigDecimal(4, developer.getSalary());
        statement.executeUpdate();
        developer.setId(getLastID());
        statement.close();
    }

    public long getLastID() throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        String sql = "SELECT id FROM developers ORDER BY id DESK LIMIT 1";
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
