package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcProjectDAOImpl;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;
import java.util.List;

public class GetProjectsCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        List<Project> list = new JdbcProjectDAOImpl().getAll();
        for (Project project : list) {
            System.out.println(project.toString());
        }
    }
}
