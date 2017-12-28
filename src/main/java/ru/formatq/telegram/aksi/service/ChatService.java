package ru.formatq.telegram.aksi.service;

import ru.formatq.telegram.aksi.model.Chat;

public interface ChatService {

    Chat selectChatById(Long id);

    Long insert(Chat chat);

    Long setPresented(Long chatId, Boolean isPresented);
}
