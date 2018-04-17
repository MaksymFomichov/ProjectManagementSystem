package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.ProjectDAO;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcProjectDAOImpl implements ProjectDAO {
    public Project getById(Long id) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM projects WHERE id = " + id);
        Project project = new Project();
        while (resultSet.next()) {
            project.setId(resultSet.getLong("id"));
            project.setName(resultSet.getString("name"));
            project.setLevel(resultSet.getInt("pr_level"));
            project.setCost(resultSet.getBigDecimal("cost"));
        }
        resultSet.close();
        statement.close();
        return project;
    }

    public List<Project> getAll() throws SQLException {
        List<Project> list = new ArrayList<Project>();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name, pr_level, cost FROM projects");
        while (resultSet.next()) {
            Project project = new Project();
            project.setId(resultSet.getLong("id"));
            project.setName(resultSet.getString("name"));
            project.setLevel(resultSet.getInt("pr_level"));
            project.setCost(resultSet.getBigDecimal("cost"));
            list.add(project);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public void update(Project project) throws SQLException {

    }

    public void delete(Project project) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM projects WHERE id=" + project.getId());
        statement.close();
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
        String sql = "SELECT id FROM projects ORDER BY id DESKC LIMIT 1";
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

    public List<Developer> getListDevelopers(Project project) throws SQLException {
        List<Developer> developerList = new ArrayList<Developer>();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM developers_projects WHERE project_id = " + project.getId());
        while (resultSet.next()) {
            developerList.add(new JdbcDeveloperDAOImpl().getById(resultSet.getLong("developer_id")));
        }
        resultSet.close();
        statement.close();
        return developerList;
    }
}
