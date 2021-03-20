create table dacha_user
(
    id         integer not null
        constraint dacha_user_pkey
            primary key,
    avatar_url varchar(255),
    password   varchar(255),
    role       varchar(255),
    username   varchar(255)
);

create table post_tag
(
    id        integer not null
        constraint post_tag_pkey
            primary key,
    tag_name  varchar(255),
    url_title varchar(255)
);


create table blog_post
(
    id                         integer not null
        constraint blog_post_pkey
            primary key,
    author                     varchar(255),
    category                   varchar(255),
    content                    text,
    create_date                date,
    event_background_image_url varchar(255),
    event_date_time            varchar(255),
    image_url                  varchar(255),
    modify_date                date,
    preview_text               varchar(255),
    title                      varchar(255),
    url_title                  varchar(255),
    views_count                integer default 0
);