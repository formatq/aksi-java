package ru.formatq.telegram.aksi.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.repository.ChatDao;

@Repository
public class ChatDaoImpl implements ChatDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChatDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final Logger logger = LoggerFactory.getLogger(ChatDaoImpl.class);

    public Chat selectChatById(long id) {
        logger.info(jdbcTemplate.getDataSource().toString());

        return new Chat();
    }
}
