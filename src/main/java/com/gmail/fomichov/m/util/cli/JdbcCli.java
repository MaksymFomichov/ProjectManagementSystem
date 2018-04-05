package com.gmail.fomichov.m.util.cli;

import com.gmail.fomichov.m.util.cli.commands.CommandHandler;
import com.gmail.fomichov.m.util.cli.commands.impl.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JdbcCli {
    private Scanner scanner;
    private boolean end;
    private String command;
    private String[] commandArgs;
    private Map<String, CommandHandler> commandHandlers;

    public JdbcCli() throws SQLException {
        scanner = new Scanner(System.in);
        initCommandHandlers();
        while (!end) {
            readCommand();
            handleCommand();
        }
    }

    private void initCommandHandlers() {
        commandHandlers = new HashMap<String, CommandHandler>();
        commandHandlers.put("createDeveloper", new CreateDeveloperCommandHandler());
        commandHandlers.put("createCompany", new CreateCompanyCommandHandler());
        commandHandlers.put("createProject", new CreateProjectCommandHandler());
        commandHandlers.put("createSkill", new CreateSkillCommandHandler());
        commandHandlers.put("createCustomer", new CreateCustomerCommandHandler());
    }

    private void readCommand(){
        String rawCommand = scanner.nextLine();
        String[] rawCommandParts = rawCommand.split(" ");
        command = rawCommandParts[0];
        commandArgs = new String[rawCommandParts.length - 1];
        System.arraycopy(rawCommandParts, 1, commandArgs, 0, commandArgs.length);
    }

    private void handleCommand() throws SQLException {
        if (command.equals("exit")) {
            end = true;
            return;
        }
        if (!commandHandlers.containsKey(command)) {
            System.out.println("Unknown command: " + command);
            return;
        }
        commandHandlers.get(command).handleCommand(commandArgs);
    }
}
