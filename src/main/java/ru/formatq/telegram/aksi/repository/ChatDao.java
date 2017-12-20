package ru.formatq.telegram.aksi.repository;

import ru.formatq.telegram.db.aksi.postgres.tables.records.ChatRecord;


public interface ChatDao {

    ChatRecord selectChatById(long id);
}
