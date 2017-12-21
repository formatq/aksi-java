package ru.formatq.telegram.aksi.repository;

import ru.formatq.telegram.aksi.db.tables.records.ChatRecord;


public interface ChatDao {

    ChatRecord selectChatById(long id);
}
