package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcProjectDAOImpl;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.math.BigDecimal;
import java.sql.SQLException;

public class CreateProjectCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Project project = new Project();
        project.setName(args[0]);
        project.setLevel(Integer.parseInt(args[1]));
        project.setCost(new BigDecimal(args[2]));
        new JdbcProjectDAOImpl().create(project);
    }
}
