package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcCompanyDAOImpl;
import com.gmail.fomichov.m.model.Company;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class UpdateCompanyCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Company company = new JdbcCompanyDAOImpl().getById(Long.valueOf(args[0]));
        company.setName(args[1]);
        company.setCapitalization(Integer.parseInt(args[2]));
        company.setFounder(args[3]);
        new JdbcCompanyDAOImpl().update(company);
    }
}
