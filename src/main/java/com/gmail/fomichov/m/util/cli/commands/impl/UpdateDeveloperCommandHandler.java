package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcDeveloperDAOImpl;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.math.BigDecimal;
import java.sql.SQLException;

public class UpdateDeveloperCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Developer developer = new JdbcDeveloperDAOImpl().getById(Long.valueOf(args[0]));
        developer.setName(args[1]);
        developer.setAge(Integer.parseInt(args[2]));
        developer.setSex(args[3].equalsIgnoreCase("man"));
        developer.setSalary(new BigDecimal(args[4]));
        new JdbcDeveloperDAOImpl().update(developer);
    }
}
