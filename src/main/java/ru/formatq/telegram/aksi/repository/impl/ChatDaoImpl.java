package ru.formatq.telegram.aksi.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.formatq.telegram.aksi.mapper.ChatMapper;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.repository.ChatDao;

@Repository
public class ChatDaoImpl implements ChatDao {

    @Autowired
    private ChatMapper chatMapper;

    private final Logger logger = LoggerFactory.getLogger(ChatDaoImpl.class);

    @Override
    public Chat selectChatById(Long id) {
        return chatMapper.selectChatById(id);
    }

    @Override
    public Long insert(Chat chat) {
        return chatMapper.insert(chat);
    }
}
