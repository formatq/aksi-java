package ru.formatq.telegram.aksi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

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
        return new EmbeddedDatabaseBuilder().addScript("import.sql").build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }


    @PostConstruct
    public void init() throws Exception {
        ApiContextInitializer.init();
    }
}