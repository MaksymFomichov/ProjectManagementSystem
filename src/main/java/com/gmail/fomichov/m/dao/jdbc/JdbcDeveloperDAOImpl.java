package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.DeveloperDAO;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeveloperDAOImpl implements DeveloperDAO {

    public Developer getById(Long id) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM developers WHERE id = " + id);
        Developer developer = new Developer();
        while (resultSet.next()) {
            developer.setId(resultSet.getLong("id"));
            developer.setName(resultSet.getString("name"));
            developer.setAge(resultSet.getInt("age"));
            developer.setSex(resultSet.getBoolean("sex"));
            developer.setSalary(resultSet.getBigDecimal("salary"));
        }
        resultSet.close();
        statement.close();
        return developer;
    }

    public List<Developer> getAll() throws SQLException {
        List<Developer> list = new ArrayList<Developer>();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name, age, sex, salary FROM developers");
        while (resultSet.next()) {
            Developer developer = new Developer();
            developer.setId(resultSet.getLong("id"));
            developer.setName(resultSet.getString("name"));
            developer.setAge(resultSet.getInt("age"));
            developer.setSex(resultSet.getBoolean("sex"));
            developer.setSalary(resultSet.getBigDecimal("salary"));
            list.add(developer);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public void update(Developer developer) throws SQLException {
        PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("UPDATE developers SET name = ?, age = ?, sex = ?, salary = ? WHERE id = ?");
        statement.setString(1, developer.getName());
        statement.setInt(2, developer.getAge());
        statement.setBoolean(3, developer.getSex());
        statement.setBigDecimal(4, developer.getSalary());
        statement.setLong(5, developer.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(Developer developer) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM developers WHERE id=" + developer.getId());
        statement.close();
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
        String sql = "SELECT id FROM developers ORDER BY id DESC LIMIT 1";
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
