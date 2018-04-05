package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcDeveloperDAOImpl;
import com.gmail.fomichov.m.model.Developer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.math.BigDecimal;
import java.sql.SQLException;

public class CreateDeveloperCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Developer developer = new Developer();
        developer.setName(args[0]);
        developer.setAge(Integer.parseInt(args[1]));
        developer.setSex(args[2].equalsIgnoreCase("man"));
        developer.setSalary(new BigDecimal(args[3]));
        new JdbcDeveloperDAOImpl().create(developer);
    }
}
