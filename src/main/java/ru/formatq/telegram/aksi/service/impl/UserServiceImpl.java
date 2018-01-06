package ru.formatq.telegram.aksi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.formatq.telegram.aksi.mapper.UserMapper;
import ru.formatq.telegram.aksi.model.User;
import ru.formatq.telegram.aksi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Long insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
