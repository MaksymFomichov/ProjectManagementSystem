package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcProjectDAOImpl;
import com.gmail.fomichov.m.model.Project;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class DeleteProjectCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Project project = new JdbcProjectDAOImpl().getById(Long.valueOf(args[0]));
        new JdbcProjectDAOImpl().delete(project);
    }
}
