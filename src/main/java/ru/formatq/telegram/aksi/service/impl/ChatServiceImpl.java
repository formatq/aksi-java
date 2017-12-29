package ru.formatq.telegram.aksi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.formatq.telegram.aksi.mapper.ChatMapper;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.repository.impl.ChatDaoImpl;
import ru.formatq.telegram.aksi.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    private final Logger logger = LoggerFactory.getLogger(ChatDaoImpl.class);

    @Override
    public Chat getByChatId(Long chatId) {
        return chatMapper.getByChatId(chatId);
    }

    @Override
    public Long insert(Chat chat) {
        return chatMapper.insert(chat);
    }

    public Long setPresented(Long chatId, Boolean isPresented) {
        return chatMapper.setPresented(chatId, isPresented);
    }

    @Override
    public Long migrateChatId(Long oldChatId, Long newChatId) {
        Long aLong = chatMapper.migrateChatId(oldChatId, newChatId);
        if (aLong == 0L) {
            logger.warn("Migration was NOT successful. From {} to {}", oldChatId, newChatId);
        }
        return chatMapper.migrateChatId(oldChatId, newChatId);
    }

    @Override
    public Long updateTitle(Long chatId, String title) {
        return chatMapper.updateTitle(chatId, title);
    }
}
