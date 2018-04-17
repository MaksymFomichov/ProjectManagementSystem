package com.gmail.fomichov.m.dao.jdbc;

import com.gmail.fomichov.m.dao.interfaces.CompanyDAO;
import com.gmail.fomichov.m.model.Company;
import com.gmail.fomichov.m.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcCompanyDAOImpl implements CompanyDAO {
    public Company getById(Long id) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM companies WHERE id = " + id);
        Company company = new Company();
        while (resultSet.next()) {
            company.setId(resultSet.getLong("id"));
            company.setName(resultSet.getString("name"));
            company.setCapitalization(resultSet.getInt("capitalization"));
            company.setFounder(resultSet.getString("founder"));
        }
        resultSet.close();
        statement.close();
        return company;
    }

    public List<Company> getAll() throws SQLException {
        List<Company> list = new ArrayList<Company>();
        Statement statement = ConnectionUtil.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name, capitalization, founder FROM companies");
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getLong("id"));
            company.setName(resultSet.getString("name"));
            company.setCapitalization(resultSet.getInt("capitalization"));
            company.setFounder(resultSet.getString("founder"));
            list.add(company);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public void update(Company company) throws SQLException {

    }

    public void delete(Company company) throws SQLException {
        Statement statement = ConnectionUtil.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM companies WHERE id=" + company.getId());
        statement.close();
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
        String sql = "SELECT id FROM companies ORDER BY id DESC LIMIT 1";
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
