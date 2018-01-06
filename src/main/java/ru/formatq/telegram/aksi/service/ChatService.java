package ru.formatq.telegram.aksi.service;

import ru.formatq.telegram.aksi.model.Chat;

public interface ChatService {

    Chat getByChatId(Long id);

    Long insert(Chat chat);

    Long setPresented(Long chatId, Boolean isPresented);

    Long migrateChatId(Long oldChatId, Long newChatId);

    Long updateTitle(Long chatId, String title);
}
