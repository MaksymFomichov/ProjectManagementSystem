package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.CompanyDAO;
import com.gmail.fomichov.m.model.Company;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcCompanyDAOImpl implements CompanyDAO {
    public Company getById(Long aLong) throws SQLException {
        return null;
    }

    public List<Company> getAll() throws SQLException {
        return null;
    }

    public void update(Company company) throws SQLException {

    }

    public void delete(Company company) throws SQLException {

    }

    public void create(Company company) throws SQLException {
        PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("INSERT INTO companies (name, capitalization, founder) VALUES(?, ?, ?)");
        statement.setString(1, company.getName());
        statement.setInt(2, company.getCapitalization());
        statement.setString(3, company.getFounder());
        statement.executeUpdate();
        company.setId(getLastID());
        statement.close();
    }

    public long getLastID() throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        String sql = "SELECT id FROM companies ORDER BY id DESK LIMIT 1";
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
