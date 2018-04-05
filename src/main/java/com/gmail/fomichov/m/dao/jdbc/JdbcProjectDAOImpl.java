package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.ProjectDAO;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcProjectDAOImpl implements ProjectDAO {
    public Project getById(Long aLong) throws SQLException {
        return null;
    }

    public List<Project> getAll() throws SQLException {
        return null;
    }

    public void update(Project project) throws SQLException {

    }

    public void delete(Project project) throws SQLException {

    }

    public void create(Project project) throws SQLException {
        PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("INSERT INTO projects (name, pr_level, cost) VALUES(?, ?, ?)");
        statement.setString(1, project.getName());
        statement.setInt(2, project.getLevel());
        statement.setBigDecimal(3, project.getCost());
        statement.executeUpdate();
        project.setId(getLastID());
        statement.close();
    }

    public long getLastID() throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        String sql = "SELECT id FROM projects ORDER BY id DESK LIMIT 1";
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
