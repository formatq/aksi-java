package ru.formatq.telegram.aksi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.formatq.telegram.aksi.repository.ChatDao;

@Service
public class ChatService {

    @Autowired
    ChatDao chatDao;
}
