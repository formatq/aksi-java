package ru.formatq.telegram.aksi.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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

    public void onUpdateReceived(Update upd) {
        if (upd.hasMessage()) {
            Chat chat = chatService.getByChatId(upd.getMessage().getChatId());
            if (chat == null) {
                chat = new Chat();
                chat.setChatId(upd.getMessage().getChatId());
                chat.setTitle(upd.getMessage().getChat().getTitle());
                chat.setUsername(upd.getMessage().getChat().getUserName());
                chat.setPresented(true);
                log.info("New chat added " + chat);
                Long asd = chatService.insert(chat);
            }
            if (upd.getMessage().getLeftChatMember() != null && chat.isPresented()) {
                if (name.equals(upd.getMessage().getLeftChatMember().getUserName())) {
                    log.info("Bot removed from group " + upd.getMessage().getChatId());
                    chatService.setPresented(upd.getMessage().getChatId(), false);
                }
            }
            if (upd.getMessage().getNewChatMembers() != null && !chat.isPresented()) {
                boolean isThisBot = false;
                for (User user : upd.getMessage().getNewChatMembers()) {
                    if (user.getUserName().equals(name)) {
                        isThisBot = true;
                        break;
                    }
                }

                if (isThisBot) {
                    log.info("Bot added to group " + upd.getMessage().getChatId());
                    chatService.setPresented(upd.getMessage().getChatId(), true);
                }
            }
            if (upd.getMessage().getMigrateToChatId() != null) {
                chatService.migrateChatId(upd.getMessage().getChatId(), upd.getMessage().getMigrateToChatId());
            }

            if (upd.getMessage().getNewChatTitle() != null) {
                chatService.updateTitle(upd.getMessage().getChatId(), upd.getMessage().getNewChatTitle());
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
