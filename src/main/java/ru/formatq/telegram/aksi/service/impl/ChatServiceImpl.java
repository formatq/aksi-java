package ru.formatq.telegram.aksi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.formatq.telegram.aksi.repository.ChatDao;
import ru.formatq.telegram.aksi.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao dao;

}
