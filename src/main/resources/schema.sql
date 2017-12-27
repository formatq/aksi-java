create table if not exists chat
(
	id serial not null
		constraint chat_pkey
			primary key,
	chat_id numeric(17) not null,
	title varchar(255),
	lang varchar(16),
	username varchar(255),
	silent_mode boolean,
	cooldown double precision,
	is_presented boolean,
	date_add timestamp,
	date_remove timestamp,
	is_ariphmetic_growth boolean,
	for_admin boolean,
	date_update timestamp
)
;

create unique index if not exists chat_chat_id_uindex
	on chat (chat_id)
;

create table if not exists users
(
	id numeric(17) not null
		constraint users_pkey
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

create unique index if not exists users_id_uindex
	on users (id)
;

create table if not exists karma
(
	user_id numeric(17) not null
		constraint karma_users_id_fk
			references users,
	chat_id integer not null
		constraint karma_chat_id_id_fk
			references chat,
	counts numeric(10),
	last_updated timestamp,
	last_time_voted timestamp,
	toofast_showed numeric default 0
)
;

