package ru.formatq.telegram.aksi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import ru.formatq.telegram.aksi.repository.ChatDao;
import ru.formatq.telegram.aksi.repository.impl.ChatDaoImpl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("ru.formatq.telegram.aksi.mapper")
public class AppConfig {

    @Bean
    TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }

    @Bean
    public DataSource dataSource() throws IOException {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
//                .addScript("import.sql")
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }

    @Bean
    public ChatDao chatDao() throws Exception {
        ChatDaoImpl chatDao = new ChatDaoImpl();
        chatDao.setSqlSessionFactory(sqlSessionFactory());
        return chatDao;
    }


    @PostConstruct
    public void init() throws Exception {
        ApiContextInitializer.init();
    }
}