package ru.formatq.telegram.aksi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.formatq.telegram.aksi.model.Chat;

@Mapper
public interface ChatMapper {

    @Select("select * from Chat where id = #{id}")
    Chat selectChatById(@Param("id") Long id);



}