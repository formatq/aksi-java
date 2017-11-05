package ru.formatq.telegram.aksi.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import ru.formatq.telegram.aksi.model.Chat;


public interface ChatDao {

    Chat selectChatById(long id);
}
