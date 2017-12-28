package ru.formatq.telegram.aksi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.formatq.telegram.aksi.mapper.ChatMapper;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.repository.ChatDao;
import ru.formatq.telegram.aksi.repository.impl.ChatDaoImpl;
import ru.formatq.telegram.aksi.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

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

    public Long setPresented(Long chatId, Boolean isPresented) {
        return chatMapper.setPresented(chatId, isPresented);
    }
}
