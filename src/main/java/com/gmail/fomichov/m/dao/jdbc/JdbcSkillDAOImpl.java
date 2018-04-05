package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.SkillDAO;
import com.gmail.fomichov.m.model.Skill;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcSkillDAOImpl implements SkillDAO {
    public Skill getById(Long aLong) throws SQLException {
        return null;
    }

    public List<Skill> getAll() throws SQLException {
        return null;
    }

    public void update(Skill skill) throws SQLException {

    }

    public void delete(Skill skill) throws SQLException {

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
        String sql = "SELECT id FROM skills ORDER BY id DESK LIMIT 1";
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
