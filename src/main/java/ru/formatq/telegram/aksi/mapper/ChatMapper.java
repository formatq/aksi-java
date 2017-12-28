package ru.formatq.telegram.aksi.mapper;

import org.apache.ibatis.annotations.*;
import ru.formatq.telegram.aksi.model.Chat;

@Mapper
public interface ChatMapper {

    @Select("SELECT * FROM Chat WHERE id = #{id}")
    Chat selectChatById(@Param("id") Long id);

    @Insert("INSERT INTO chat (chat_id, title, username, date_add)" +
            " VALUES (#{chatId}, #{title}, #{username}, current_timestamp)" +
            " ON CONFLICT (chat_id)" +
            " DO UPDATE SET date_update = current_timestamp, title = #{title}, username = #{username}")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Long insert(Chat chat);

    @Update("UPDATE chat SET date_update=current_timestamp, is_presented = #{isPresented} WHERE chat_id = #{chatId} AND is_presented = FALSE")
    Long setPresented(@Param("chatId") Long chatId, @Param("isPresented") Boolean isPresented);


}