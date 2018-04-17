package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcDeveloperDAOImpl;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;
import java.util.List;

public class GetDevelopersCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        List<Developer> list = new JdbcDeveloperDAOImpl().getAll();
        for (Developer developer : list) {
            System.out.println(developer.toString());
        }
    }
}
