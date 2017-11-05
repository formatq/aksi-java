package ru.formatq.telegram.aksi.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String img;
    private String lang;

    private String title;

    private Date dateAdded;
    private Date lastUpdated;

}
