package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcCustomerDAOImpl;
import com.gmail.fomichov.m.model.Customer;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;
import java.util.List;

public class GetCustomersCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        List<Customer> list = new JdbcCustomerDAOImpl().getAll();
        for (Customer customer : list) {
            System.out.println(customer.toString());
        }
    }
}
