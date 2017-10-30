package ru.formatq.telegram.aksi.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import ru.formatq.telegram.aksi.model.Chat;

@Component
public class ChatDao {

    private final SqlSession sqlSession;

    public ChatDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Chat selectChatById(long id) {
        return this.sqlSession.selectOne("selectChatById", id);
    }
}
