package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcSkillDAOImpl;
import com.gmail.fomichov.m.model.Skill;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;
import java.util.List;

public class GetSkillsCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        List<Skill> list = new JdbcSkillDAOImpl().getAll();
        for (Skill skill : list) {
            System.out.println(skill.toString());
        }
    }
}
