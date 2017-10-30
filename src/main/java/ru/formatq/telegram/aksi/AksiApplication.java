package ru.formatq.telegram.aksi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.formatq.telegram.aksi.config.AppConfig;
import ru.formatq.telegram.aksi.handler.AksiHandlers;

@SpringBootApplication
@Import(AppConfig.class)
public class AksiApplication implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(AksiApplication.class);

    @Autowired
    TelegramBotsApi telegramBotsApi;

    @Autowired
    AksiHandlers aksiHandlers;

    public static void main(String[] args) {
        SpringApplication.run(AksiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("Start");
            telegramBotsApi.registerBot(aksiHandlers);
        } catch (TelegramApiException e) {
            log.error("Error", e);
        }
    }

}