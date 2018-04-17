package com.gmail.fomichov.m;

import com.gmail.fomichov.m.util.cli.JdbcCli;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Доcтупны такие комманды CRUD:" +
                "\nсоздание: ncreateCompany, createCustomer, createDeveloper, createProject, createSkill" +
                "\nотображение данных: getCompanies, getCustomers, getDevelopers, getProjects, getSkills" +
                "\nредактирование: updateCompany, updateCustomer, updateDeveloper, updateProject, updateSkill" +
                "\nудаление: deleteCompany, deleteCustomer, deleteDeveloper, deleteProject, deleteSkill" +
                "\n\ngetDevelopersFromProject - список разработчиков отдельного проекта");
        new JdbcCli();
    }
}
