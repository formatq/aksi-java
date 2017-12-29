package ru.formatq.telegram.aksi.mapper;

import org.apache.ibatis.annotations.*;
import ru.formatq.telegram.aksi.model.User;

@Mapper
@CacheNamespace(flushInterval = 300000, size = 2048)
public interface UserMapper {

    @Select("select id, username, first_name, last_name, lang, date_added, last_updated from users where id = #{id}")
    @Options(useCache = true)
    @ResultType(User.class)
    User getById(@Param("id") Long id);

    @Select("select id, username, first_name, last_name, lang, date_added, last_updated from users where username = #{username}")
    @Options(useCache = true)
    @ResultType(User.class)
    User getByUsername(@Param("username") String username);

    @Insert("INSERT INTO users (id, username, first_name, last_name, date_added)" +
            " VALUES (#{id}, #{username}, #{firstName}, #{lastName}, current_timestamp)" +
            " ON CONFLICT (id)" +
            " DO UPDATE SET last_updated = current_timestamp, first_name = #{firstName}, last_name = #{lastName},  title = #{title}, username = #{username}")
    Long insert(User user);

}