create table if not exists Chat
(
  id serial not null
    constraint "Chat_pkey"
    primary key,
  chat_id numeric(10) not null,
  title varchar(255),
  lang varchar(16),
  username varchar(255),
  silent_mode boolean,
  cooldown double precision,
  is_presented boolean,
  date_add timestamp,
  date_remove timestamp,
  is_ariphmetic_growth boolean,
  for_admin boolean
)
;

create unique index if not exists chat_chat_id_uindex
  on "Chat" (chat_id)
;

create table if not exists User
(
  id numeric(10) not null
    constraint "User_pkey"
    primary key,
  username varchar(255),
  first_name varchar(255),
  last_name varchar(255),
  img varchar(60),
  lang integer,
  last_updated timestamp,
  date_added timestamp,
  title varchar(32),
  cookies numeric(10)
)
;

create unique index if not exists user_id_uindex
  on "User" (id)
;

create table if not exists Karma
(
  user_id numeric(10) not null
    constraint karma_user_id_fk
    references "User",
  chat_id numeric(10) not null
    constraint karma_chat_id_fk
    references "Chat",
  counts numeric(10),
  last_updated timestamp,
  last_time_voted timestamp,
  toofast_showed numeric default 0
)
;

