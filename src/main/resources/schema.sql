create table if not exists "Chat"
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

