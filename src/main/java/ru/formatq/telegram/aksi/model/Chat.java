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
    private Boolean silentMode;
    private Double cooldown;
    private Boolean isPresented;
    private Date dateAdd;
    private Date dateRemove;
    private Boolean ariphmeticGrowth;
    private Boolean forAdmin;
}
