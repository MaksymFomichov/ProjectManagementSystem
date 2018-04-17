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

        // пример ввода "createDeveloper name age sex salary"
        commandHandlers.put("createDeveloper", new CreateDeveloperCommandHandler());
        commandHandlers.put("createCompany", new CreateCompanyCommandHandler());
        commandHandlers.put("createProject", new CreateProjectCommandHandler());
        commandHandlers.put("createSkill", new CreateSkillCommandHandler());
        commandHandlers.put("createCustomer", new CreateCustomerCommandHandler());

        // пример ввода "updateDeveloper id name age sex salary" - гле id - это id обновляемого разарботчика
        commandHandlers.put("updateDeveloper", new UpdateDeveloperCommandHandler());
        commandHandlers.put("updateCompany", new UpdateCompanyCommandHandler());
        commandHandlers.put("updateProject", new UpdateProjectCommandHandler());
        commandHandlers.put("updateSkill", new UpdateSkillCommandHandler());
        commandHandlers.put("updateCustomer", new UpdateCustomerCommandHandler());

        // пример ввода "getDevelopers" - выводит в консоль список всех сущностей данной таблицы
        commandHandlers.put("getDevelopers", new GetDevelopersCommandHandler());
        commandHandlers.put("getCompanies", new GetCompaniesCommandHandler());
        commandHandlers.put("getProjects", new GetProjectsCommandHandler());
        commandHandlers.put("getSkills", new GetSkillsCommandHandler());
        commandHandlers.put("getCustomers", new GetCustomersCommandHandler());

        // пример ввода "deleteDeveloper id" где id это id разработчика и т.п. по всем остальным сущностям
        commandHandlers.put("deleteDeveloper", new DeleteDeveloperCommandHandler());
        commandHandlers.put("deleteCompany", new DeleteCompanyCommandHandler());
        commandHandlers.put("deleteProject", new DeleteProjectCommandHandler());
        commandHandlers.put("deleteSkill", new DeleteSkillCommandHandler());
        commandHandlers.put("deleteCustomer", new DeleteCustomerCommandHandler());

        // список разработчиков отдельного проекта
        // пример ввода "getDevelopersFromProject id" где id это id проекта
        commandHandlers.put("getDevelopersFromProject", new GetDevelopersFromProjectCommandHandler());
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
