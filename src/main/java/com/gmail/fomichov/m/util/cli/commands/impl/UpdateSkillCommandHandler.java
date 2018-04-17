package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcSkillDAOImpl;
import com.gmail.fomichov.m.model.Skill;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class UpdateSkillCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Skill skill = new JdbcSkillDAOImpl().getById(Long.valueOf(args[0]));
        skill.setName(args[1]);
        skill.setYear(Integer.parseInt(args[2]));
        skill.setAuthor(args[3]);
        new JdbcSkillDAOImpl().update(skill);
    }
}
