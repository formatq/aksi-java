package ru.formatq.telegram.aksi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.formatq.telegram.aksi.model.User;

@Mapper
public interface UserMapper {

    @Select("select id, username, firstname, lastname, lang " +
            "from Users " +
            "where id = #{id}")
    User selectUserById(@Param("id") Long id);

    @Select("select id, username, firstname, lastname, lang " +
            "from Users " +
            "where username = #{username}")
    User selectUserByUsername(@Param("username") String username);

    @Insert("insert into Users (id, username, firstname, lastname, lang) " +
            "values (#{id}, #{username}, #{firstname}, #{lastname}, #{lang})")
    Long insertUser(@Param("id") Long id, @Param("username") String username, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("lang") String lang);
}