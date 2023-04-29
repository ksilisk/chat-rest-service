drop table if exists public."users";
create table public.users
(
    id bigint not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    unique (id, username, email),
    primary key (id)
);

drop table if exists public.groups;
create table public.groups
(
    id bigint not null,
    name varchar(255) not null,
    user_ids bigint ARRAY not null,
    unique (id),
    primary key (id)
);

drop table if exists public.messages;
create table public.messages
(
    id bigint not null,
    text varchar(255) not null,
    user_id bigint not null,
    chat_id bigint not null,
    chat_type char(5) not null,
    time timestamp not null,
    unique (id),
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (chat_id) references users (id),
    foreign key (chat_id) references groups (id),
    check ( chat_type in ('user', 'group') )
);
