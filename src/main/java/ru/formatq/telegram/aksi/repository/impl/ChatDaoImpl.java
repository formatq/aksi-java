package ru.formatq.telegram.aksi.repository.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.repository.ChatDao;

public class ChatDaoImpl extends SqlSessionDaoSupport implements ChatDao {

    public Chat selectChatById(long id) {
        return getSqlSession().selectOne("selectChatById", id);
    }
}
