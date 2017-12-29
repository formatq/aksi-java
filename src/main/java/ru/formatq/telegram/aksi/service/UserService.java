package ru.formatq.telegram.aksi.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.formatq.telegram.aksi.model.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    Long insert(User user);
}