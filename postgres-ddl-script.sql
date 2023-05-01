/*
 Deleting table
 */
drop table if exists public.messages;
drop table if exists public.chat_users;
drop table if exists public.users;
drop table if exists public.chats;

/*
 Creating tables
 */
create table public.messages
(
    id bigint not null,
    text varchar(255) not null,
    user_id bigint not null,
    chat_id bigint not null,
    time timestamp not null,
    unique (id),
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (chat_id) references users (id)
);

create table public.users
(
    id bigint not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    unique (id, username, email),
    primary key (id)
);

create table public.chats
(
    id bigint not null,
    name varchar(255) not null,
    owner_id bigint not null,
    unique (id),
    primary key (id),
    foreign key (owner_id) references users (id)
);

create table public.chat_users
(
    chat_id bigint not null,
    user_id bigint not null,
    foreign key (chat_id) references chats (id),
    foreign key (user_id) references users (id)
);
