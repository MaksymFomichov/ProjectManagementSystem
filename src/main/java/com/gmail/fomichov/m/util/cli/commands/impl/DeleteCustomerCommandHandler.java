package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcCustomerDAOImpl;
import com.gmail.fomichov.m.model.Customer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class DeleteCustomerCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Customer customer = new JdbcCustomerDAOImpl().getById(Long.valueOf(args[0]));
        new JdbcCustomerDAOImpl().delete(customer);
    }
}
