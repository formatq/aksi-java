package ru.formatq.telegram.aksi.model;

import java.io.Serializable;
import java.util.Date;

public class Karma implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long level = 0L;
    private User user;
    private Chat chat;
    private Integer toofastShowed = 0;

    private Date lastTimeVoted;
    private Date lastUpdated;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Integer getToofastShowed() {
        return toofastShowed;
    }

    public void setToofastShowed(Integer toofastShowed) {
        this.toofastShowed = toofastShowed;
    }

    public Date getLastTimeVoted() {
        return lastTimeVoted;
    }

    public void setLastTimeVoted(Date lastTimeVoted) {
        this.lastTimeVoted = lastTimeVoted;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
