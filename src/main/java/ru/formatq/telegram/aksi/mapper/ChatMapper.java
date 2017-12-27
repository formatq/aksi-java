package ru.formatq.telegram.aksi.mapper;

import org.apache.ibatis.annotations.*;
import ru.formatq.telegram.aksi.model.Chat;

@Mapper
public interface ChatMapper {

    @Select("SELECT * FROM Chat WHERE id = #{id}")
    Chat selectChatById(@Param("id") Long id);

    @Insert("INSERT INTO chat (chat_id, title, date_add) VALUES (#{chatId}, #{title}, current_timestamp) ON CONFLICT (chat_id) " +
            "DO UPDATE SET date_update = current_timestamp, title = #{title}")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Long insert(Chat chat);

}