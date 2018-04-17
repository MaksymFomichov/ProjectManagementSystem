package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcProjectDAOImpl;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;
import java.util.List;

public class GetDevelopersFromProjectCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Project project = new JdbcProjectDAOImpl().getById(Long.valueOf(args[0]));
        List<Developer> list = new JdbcProjectDAOImpl().getListDevelopers(project);
        for (Developer developer : list) {
            System.out.println(developer.toString());
        }
    }
}
