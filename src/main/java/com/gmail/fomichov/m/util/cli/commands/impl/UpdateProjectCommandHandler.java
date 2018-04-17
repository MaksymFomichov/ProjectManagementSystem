package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcProjectDAOImpl;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.math.BigDecimal;
import java.sql.SQLException;

public class UpdateProjectCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Project project = new JdbcProjectDAOImpl().getById(Long.valueOf(args[0]));
        project.setName(args[1]);
        project.setLevel(Integer.parseInt(args[2]));
        project.setCost(new BigDecimal(args[3]));
        new JdbcProjectDAOImpl().update(project);
    }
}
