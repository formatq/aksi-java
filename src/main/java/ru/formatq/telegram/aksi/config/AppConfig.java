package ru.formatq.telegram.aksi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import ru.formatq.telegram.aksi.handler.AksiHandlers;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {

    @Bean
    TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }


    @PostConstruct
    public void init() throws Exception {
        ApiContextInitializer.init();
    }
}