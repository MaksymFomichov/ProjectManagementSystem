package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcCompanyDAOImpl;
import com.gmail.fomichov.m.model.Company;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class CreateCompanyCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Company company = new Company();
        company.setName(args[0]);
        company.setCapitalization(Integer.parseInt(args[1]));
        company.setFounder(args[2]);
        new JdbcCompanyDAOImpl().create(company);
    }
}
