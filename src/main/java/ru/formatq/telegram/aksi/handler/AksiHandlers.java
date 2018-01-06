package ru.formatq.telegram.aksi.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.formatq.telegram.aksi.AksiApplication;
import ru.formatq.telegram.aksi.model.Chat;
import ru.formatq.telegram.aksi.model.User;
import ru.formatq.telegram.aksi.service.ChatService;
import ru.formatq.telegram.aksi.service.UserService;

@Component
public class AksiHandlers extends TelegramLongPollingBot {

    private static Logger log = LoggerFactory.getLogger(AksiApplication.class);

    private String botName = null;

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

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
            Chat chat = getChat(upd);
            if (upd.getMessage().getLeftChatMember() != null && chat.isPresented()) {
                if (name.equals(upd.getMessage().getLeftChatMember().getUserName())) {
                    log.info("Bot removed from group " + upd.getMessage().getChatId());
                    chatService.setPresented(upd.getMessage().getChatId(), false);
                }
            }
            if (upd.getMessage().getNewChatMembers() != null && !chat.isPresented()) {
                boolean isThisBot = false;
                for (org.telegram.telegrambots.api.objects.User user : upd.getMessage().getNewChatMembers()) {
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

            if (upd.getMessage().hasText()) {

                if (StringUtils.startsWithAny(upd.getMessage().getText(), "+", "-", "\uD83D\uDC4D", "\uD83D\uDC4E")) {
                    User user = getUser(upd.getMessage().getFrom());
                    log.info("User updated karma");
                }
                try {
                    sendApiMethod(new SendMessage(upd.getMessage().getChatId(),"Hello, "+ upd.getMessage().getFrom().getFirstName() + " " + upd.getMessage().getFrom().getLastName()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public Chat getChat(Update upd) {
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
        return chat;
    }

    public User getUser(org.telegram.telegrambots.api.objects.User tgUser) {
        User user = userService.getById(Long.valueOf(tgUser.getId()));
        if (user == null) {
            user = new User();
            user.setId(Long.valueOf(tgUser.getId()));
            user.setUsername(tgUser.getUserName());
            user.setFirstName(tgUser.getFirstName());
            user.setLastName(tgUser.getLastName());
            user.setLang(tgUser.getLanguageCode());
            log.info("New user added " + user);
            Long count = userService.insert(user);
        }
        return user;
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
