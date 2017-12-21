package ru.formatq.telegram.aksi.repository.impl;

import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.formatq.telegram.aksi.repository.ChatDao;
import ru.formatq.telegram.aksi.db.tables.records.ChatRecord;

import java.util.List;

import static ru.formatq.telegram.aksi.db.tables.Chat.CHAT;

@Repository
public class ChatDaoImpl implements ChatDao {

    private final DSLContext create;

    @Autowired
    public ChatDaoImpl(DSLContext dslContext) {
        this.create = dslContext;
    }

    private final Logger logger = LoggerFactory.getLogger(ChatDaoImpl.class);

    public ChatRecord selectChatById(long id) {
        ChatRecord fetch = create.selectFrom(CHAT).fetchAny();
        String title = fetch.getTitle();
        System.out.println(title);
//        for (ChatRecord chatRecord : fetch) {
//            chatRecord.
//        }
//
//        List<Field<?>> select = from.getSelect();
//        for (Field<?> field : select) {
//            System.out.println(field.toString());
//        }

//        System.out.println(select);
        return fetch;
    }
}
