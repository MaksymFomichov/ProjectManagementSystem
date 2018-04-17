package com.gmail.fomichov.m.util.cli.commands.impl;

import com.gmail.fomichov.m.dao.jdbc.JdbcSkillDAOImpl;
import com.gmail.fomichov.m.model.Skill;
import com.gmail.fomichov.m.util.cli.commands.CommandHandler;

import java.sql.SQLException;

public class DeleteSkillCommandHandler implements CommandHandler {
    public void handleCommand(String[] args) throws SQLException {
        Skill skill = new JdbcSkillDAOImpl().getById(Long.valueOf(args[0]));
        new JdbcSkillDAOImpl().delete(skill);
    }
}
