package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcCustomerDAOImpl;
import com.gmail.fomichov.m.model.Customer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class CreateCustomerCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Customer customer = new Customer();
        customer.setName(args[0]);
        customer.setCapitalization(Integer.parseInt(args[1]));
        customer.setFounder(args[2]);
        new JdbcCustomerDAOImpl().create(customer);
    }
}
