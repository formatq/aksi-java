package ru.formatq.telegram.aksi.model;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long chatId;
    private String title;
    private String lang;    //заменить на enum
    private String username;
    private boolean silentMode;
    private Double cooldown;
    private boolean isPresented;
    private Date dateAdd;
    private Date dateRemove;
    private boolean isAriphmeticGrowth;
    private boolean forAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSilentMode() {
        return silentMode;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }

    public Double getCooldown() {
        return cooldown;
    }

    public void setCooldown(Double cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isPresented() {
        return isPresented;
    }

    public void setPresented(boolean presented) {
        isPresented = presented;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateRemove() {
        return dateRemove;
    }

    public void setDateRemove(Date dateRemove) {
        this.dateRemove = dateRemove;
    }

    public boolean isAriphmeticGrowth() {
        return isAriphmeticGrowth;
    }

    public void setAriphmeticGrowth(boolean ariphmeticGrowth) {
        isAriphmeticGrowth = ariphmeticGrowth;
    }

    public boolean isForAdmin() {
        return forAdmin;
    }

    public void setForAdmin(boolean forAdmin) {
        this.forAdmin = forAdmin;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", title='" + title + '\'' +
                ", lang='" + lang + '\'' +
                ", username='" + username + '\'' +
                ", silentMode=" + silentMode +
                ", cooldown=" + cooldown +
                ", isPresented=" + isPresented +
                ", dateAdd=" + dateAdd +
                ", dateRemove=" + dateRemove +
                ", isAriphmeticGrowth=" + isAriphmeticGrowth +
                ", forAdmin=" + forAdmin +
                '}';
    }
}
