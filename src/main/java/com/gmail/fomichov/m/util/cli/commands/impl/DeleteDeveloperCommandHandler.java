package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcDeveloperDAOImpl;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class DeleteDeveloperCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Developer developer = new JdbcDeveloperDAOImpl().getById(Long.valueOf(args[0]));
        new JdbcDeveloperDAOImpl().delete(developer);
    }
}
