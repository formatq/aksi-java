package ru.formatq.telegram.aksi.mapper;

import org.apache.ibatis.annotations.*;
import ru.formatq.telegram.aksi.model.Chat;

@Mapper
@CacheNamespace(flushInterval = 300000, size = 2048)
public interface ChatMapper {

    @Select("SELECT id, chat_id, title, username, is_presented, date_add, date_update FROM Chat WHERE chat_id = #{chatId}")
    @Options(useCache = true)
    @ResultType(Chat.class)
    Chat getByChatId(@Param("chatId") Long chatId);

    @Insert("INSERT INTO chat (chat_id, title, username, date_add)" +
            " VALUES (#{chatId}, #{title}, #{username}, current_timestamp)" +
            " ON CONFLICT (chat_id)" +
            " DO UPDATE SET date_update = current_timestamp, title = #{title}, username = #{username}")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Long insert(Chat chat);

    @Update("UPDATE chat SET date_update=current_timestamp, is_presented = #{isPresented} WHERE chat_id = #{chatId} AND is_presented = FALSE")
    Long setPresented(@Param("chatId") Long chatId, @Param("isPresented") Boolean isPresented);

    @Update("UPDATE chat SET date_update=current_timestamp, chat_id = #{newChatId} WHERE chat_id = #{oldChatId}")
    Long migrateChatId(Long oldChatId, Long newChatId);

    @Update("UPDATE chat SET date_update=current_timestamp, title = #{title} WHERE chat_id = #{chatId}")
    Long updateTitle(Long chatId, String title);
}