package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcSkillDAOImpl;
import com.gmail.fomichov.m.model.Skill;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class CreateSkillCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Skill skill = new Skill();
        skill.setName(args[0]);
        skill.setYear(Integer.parseInt(args[1]));
        skill.setAuthor(args[2]);
        new JdbcSkillDAOImpl().create(skill);
    }
}
