package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcCompanyDAOImpl;
import com.gmail.fomichov.m.model.Company;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;
import java.util.List;

public class GetCompaniesCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        List<Company> list = new JdbcCompanyDAOImpl().getAll();
        for (Company company : list) {
            System.out.println(company.toString());
        }
    }
}
