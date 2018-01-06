package ru.formatq.telegram.aksi.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.formatq.telegram.aksi.AksiApplication;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.service.ChatService;

@Component
public class AksiHandlers extends TelegramLongPollingBot {

    private static Logger log = LoggerFactory.getLogger(AksiApplication.class);

    private String botName = null;

    @Autowired
    ChatService chatService;

    @Value("${bot.name}")
    String name;

    @Value("${bot.token}")
    String token;

    public AksiHandlers() {
        super();
        log.info("Register botHandler. Name '{}', Token '{}'", name, token);
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Chat chat = new Chat();
            chat.setChatId(update.getMessage().getChatId());
            chat.setTitle(update.getMessage().getChat().getTitle());
            System.out.println(chat);
            Long asd = chatService.insert(chat);
            try {
                sendApiMethod(new SendMessage(update.getMessage().getChatId(),"Hello, "+ update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.getMessage().getLeftChatMember() != null) {
            if (getBotName().equals(update.getMessage().getLeftChatMember().getUserName())) {
                log.info("getLeftChatMember " + update.getMessage().getLeftChatMember());
                chatService.setPresented(update.getMessage().getChatId(), false);
            }
        }
        if (update.getMessage().getNewChatMembers() != null) {
            boolean isThisBot = false;
            for (User user : update.getMessage().getNewChatMembers()) {
                if (user.getUserName().equals(getBotName())) {
                    isThisBot = true;
                    break;
                }
            }

            if (isThisBot) {
                log.info("getNewChatMembers " + update.getMessage().getLeftChatMember());
                chatService.setPresented(update.getMessage().getChatId(), true);
            }
        }


    }

    private String getBotName() {
        if (botName == null) {
            try {
                botName = getMe().getUserName();
            } catch (TelegramApiException e) {
                log.error(e.getMessage(), e.getCause());
            }
        }
        return botName;
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
