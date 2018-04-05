package com.gmail.fomichov.m.util.cli.commands;

import java.sql.SQLException;

public interface CommandHandler {
    void handleCommand(String[] args) throws SQLException;
}

