package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.SkillDAO;
import com.gmail.fomichov.m.model.Skill;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillDAOImpl implements SkillDAO {
    public Skill getById(Long id) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM skills WHERE id = " + id);
        Skill skill = new Skill();
        while (resultSet.next()) {
            skill.setId(resultSet.getLong("id"));
            skill.setName(resultSet.getString("name"));
            skill.setYear(resultSet.getInt("year_foundation"));
            skill.setAuthor(resultSet.getString("author"));
        }
        resultSet.close();
        statement.close();
        return skill;
    }

    public List<Skill> getAll() throws SQLException {
        List<Skill> list = new ArrayList<Skill>();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name, year_foundation, author FROM skills");
        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getLong("id"));
            skill.setName(resultSet.getString("name"));
            skill.setYear(resultSet.getInt("year_foundation"));
            skill.setAuthor(resultSet.getString("author"));
            list.add(skill);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public void update(Skill skill) throws SQLException {

    }

    public void delete(Skill skill) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM skills WHERE id=" + skill.getId());
        statement.close();
    }

    public void create(Skill skill) throws SQLException {
        PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("INSERT INTO skills (name, year_foundation, author) VALUES(?, ?, ?)");
        statement.setString(1, skill.getName());
        statement.setInt(2, skill.getYear());
        statement.setString(3, skill.getAuthor());
        statement.executeUpdate();
        skill.setId(getLastID());
        statement.close();
    }

    public long getLastID() throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        String sql = "SELECT id FROM skills ORDER BY id DESC LIMIT 1";
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
