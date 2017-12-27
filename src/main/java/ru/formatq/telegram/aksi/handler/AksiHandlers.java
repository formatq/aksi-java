package ru.formatq.telegram.aksi.handler;

import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import ru.formatq.telegram.aksi.AksiApplication;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.repository.ChatDao;

import java.util.List;

@Component
public class AksiHandlers extends TelegramLongPollingBot {

    private static Logger log = LoggerFactory.getLogger(AksiApplication.class);

    @Autowired
    ChatDao chatDao;

    @Value("${bot.name}")
    String name;

    @Value("${bot.token}")
    String token;

    public AksiHandlers() {
        super();
        log.info("Register botHandler. Name '{}', Token '{}'", name, token);
    }

    public void onUpdateReceived(Update update) {
        System.out.println(update);
    }

    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            Chat chat = new Chat();
            log.info("isUserChat="+update.getMessage().getChat().isUserChat());
            chat.setChatId(update.getMessage().getChatId());
            chat.setTitle(update.getMessage().getChat().getTitle());
            System.out.println(chat);
            chatDao.insert(chat);
        }

    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return token;
    }

    public void onClosing() {

    }
}
