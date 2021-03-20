drop table if exists post_tag;

create table post_tag
(
    id        integer not null
        constraint post_tag_pkey
            primary key,
    tag_name  varchar(255),
    url_title varchar(255)
);


INSERT INTO post_tag (id, tag_name, url_title) VALUES (1, 'Рецепт', 'recipe');
INSERT INTO post_tag (id, tag_name, url_title) VALUES (2, 'Новости', 'news');
INSERT INTO post_tag (id, tag_name, url_title) VALUES (3, 'Дизайн', 'design');
INSERT INTO post_tag (id, tag_name, url_title) VALUES (4, 'Кухня', 'cooking');
INSERT INTO post_tag (id, tag_name, url_title) VALUES (5, 'Интересное', 'interesting');drop table if exists table_booking;



drop table if exists blog_post;

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


INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9991, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/blog-07.jpg', null, 'Romantic Restaurant!', 'Best places for Wine!', 'name1', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9992, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/blog-06.jpg', null, 'Blog text preview', 'Spring...Spring...', 'name2', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9993, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/blog-04.jpg', null, 'Blog text preview', 'Test Blog Post', 'name3', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9994, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/blog-12.jpg', null, 'Blog text preview!', 'Sorry, now site is a bunch of memes', 'name4', 20);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9995, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/blog-14.jpg', null, 'Blog text preview!', 'Come with friends', 'name5', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9996, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/blog-15.jpg', null, 'Blog text preview', 'You will never forget this dinner', 'name6', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9997, 'Автор', 'BLOG', null, '2021-02-04', null, null, '/images/blog-11.jpg', null, 'Blog text preview', 'Our waiters are awesome', 'name7', 15);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9998, 'Автор', 'BLOG', null, '2021-02-03', null, null, '/images/blog-10.jpg', null, 'Blog text preview', 'New youtube video!', 'name8', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (9999, 'Автор', 'BLOG', null, '2021-03-04', null, null, '/images/remi.jpg', null, 'I am a mouse', 'Best Chef in France', 'name9', 10);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (99911, 'Автор', 'ABOUT', null, '2021-03-04', null, null, '/images/intro-01.jpg', null, 'Phasellus lorem enim, luctus ut velit eget, con-vallis egestas eros.', 'Романтичный ресторан', 'intro1', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (99912, 'Автор', 'ABOUT', null, '2021-03-04', null, null, '/images/intro-02.jpg', null, 'Aliquam eget aliquam magna, quis posuere risus ac justo ipsum nibh urna', 'Изысканная еда', 'intro2', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (99913, 'Автор', 'ABOUT', null, '2021-03-04', null, null, '/images/intro-04.jpg', null, 'Sed ornare ligula eget tortor tempor, quis porta tellus dictum.', 'Красные вина которые вы любите', 'intro3', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (99914, 'Автор', 'EVENT', null, '2021-03-04', 'background-image: url(/images/bg-event-01.jpg)', '14 марта в 18:00', 'background-image: url(/images/event-01.jpg)', null, 'Phasellus lorem enim, luctus ut velit eget, con-vallis egestas eros.', 'Винные вечера на Даче', 'event1', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (99915, 'Автор', 'EVENT', null, '2021-03-04', 'background-image: url(/images/bg-event-02.jpg)', '8 марта целый день', 'background-image: url(/images/event-02.jpg)', null, 'Aliquam eget aliquam magna, quis posuere risus ac justo ipsum nibh urna', 'Выступление итальянского музыканта', 'event2', 0);
INSERT INTO blog_post (id, author, category, content, create_date, event_background_image_url, event_date_time, image_url, modify_date, preview_text, title, url_title, views_count) VALUES (99916, 'Приколист', 'EVENT', null, '2021-03-04', 'background-image: url(/images/bg-event-04.jpg)', 'когда-нибудь', 'background-image: url(/images/event-06.jpg)', null, 'Sed ornare ligula eget tortor tempor, quis porta tellus dictum.', 'Дегустация Рибая', 'event3', 0);drop table if exists blog_post_tags;

create table blog_post_tags
(
    blog_post_id integer not null
        constraint fkb24ggwvd5worpe321akpuvtmd
            references blog_post,
    tags_id      integer not null
        constraint fkrf2xb10lrte8eka6l99w8524k
            references post_tag
);


INSERT INTO blog_post_tags (blog_post_id, tags_id) VALUES (9991, 1);
INSERT INTO blog_post_tags (blog_post_id, tags_id) VALUES (9992, 2);
INSERT INTO blog_post_tags (blog_post_id, tags_id) VALUES (9992, 1);drop table if exists dacha_user;

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


INSERT INTO dacha_user (id, avatar_url, password, role, username) VALUES (1, '/images/dasha.jpg', 'user', 'USER', 'user');
INSERT INTO dacha_user (id, avatar_url, password, role, username) VALUES (777, '/images/chuck.jpg', 'admin', 'ADMIN', 'admin');

drop table if exists delivery_order_item;

create table delivery_order_item
(
    id           integer not null
        constraint delivery_order_item_pkey
            primary key,
    menu_item_id integer,
    name         varchar(255),
    price        double precision,
    quantity     integer
);




drop table if exists delivery_order;

create table delivery_order
(
    id              integer not null
        constraint delivery_order_pkey
            primary key,
    address         varchar(512),
    comment         varchar(1024),
    email           varchar(255),
    name            varchar(255),
    phone           varchar(255),
    price           double precision,
    time_to_deliver varchar(255),
    username        varchar(255)
);


drop table if exists delivery_order_order_items;

create table delivery_order_order_items
(
    delivery_order_id integer not null
        constraint fk7w1he2smrx3ceiygne2t5qdqp
            references delivery_order,
    order_items_id    integer not null
        constraint fk98vk64cl8ioe9gj064ki0ih8j
            references delivery_order_item
);


drop table if exists gallery_item;

create table gallery_item
(
    id           integer not null
        constraint gallery_item_pkey
            primary key,
    category     varchar(255),
    image_url    varchar(255),
    is_published boolean not null
);


INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9991, 'food', '/images/photo-gallery-01.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9992, 'food', '/images/photo-gallery-02.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9993, 'food', '/images/photo-gallery-03.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9994, 'food', '/images/photo-gallery-04.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9995, 'events', '/images/photo-gallery-05.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9996, 'guests', '/images/photo-gallery-06.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9997, 'food', '/images/photo-gallery-07.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9998, 'interior', '/images/photo-gallery-08.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9999, 'food', '/images/photo-gallery-09.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (9990, 'food', '/images/photo-gallery-10.jpg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (99991, 'interior', '/images/interior1.jpeg', true);
INSERT INTO gallery_item (id, category, image_url, is_published) VALUES (99992, 'interior', '/images/interior2.jpg', true);drop table if exists menu_item;

create table menu_item
(
    id                     integer not null
        constraint menu_item_pkey
            primary key,
    category               varchar(255),
    description            varchar(1024),
    image_url              varchar(255),
    is_available_for_order boolean not null,
    name                   varchar(255),
    price                  varchar(255)
);


INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (47, 'Мясо и птица', null, '/images/meat.png', true, 'Филе говядины с хрустящим картофелем', '750.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (48, 'Мясо и птица', null, '/images/meat.png', true, 'Говяжьи щечки с картофельным пюре', '590.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (49, 'Мясо и птица', null, '/images/meat.png', true, 'Люля - кебаб из баранины с овощами - гриль', '660.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (20, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Паштет из утки с гренками', '350.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (21, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Сациви с индейкой и малиновым соусом', '380.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (22, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Ассорти сыров', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (23, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Мясные деликатесы /буженина, ростбиф, язык, парма/', '520.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (66, 'СУПЫ', null, '/images/soup.jpg', true, 'Борщ с бужениной', '280.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (67, 'СУПЫ', null, '/images/soup.jpg', true, 'Сырный крем-суп с креветками', '360.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (68, 'СУПЫ', null, '/images/soup.jpg', true, 'Ромэн с говядиной', '340.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (50, 'Мясо и птица', null, '/images/meat.png', true, 'Бифштекс из говядины с  печеным картофелем', '500.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (51, 'Мясо и птица', null, '/images/meat.png', true, 'Котлетки из индейки с булгуром и кедровым соусом', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (52, 'Мясо и птица', null, '/images/meat.png', true, 'Утиная грудка с черным рисом', '680.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (53, 'Мясо и птица', null, '/images/meat.png', true, 'Цыпленок с картофельным пюре', '490,00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (56, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Сливочный лосось с овощами', '650.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (57, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Окунь в цитрусовом соусе с жареными овощами', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (58, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Дорадо со шпинатом и соусом из сельдерея', '650.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (59, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Треска с ньоками из тыквы', '550.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (60, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Кальмар в рыбном демигляссе с хрустящим баклажаном', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (61, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Кебабы из щуки на лемонграссе с соусом гуацето', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (71, 'ПАСТА', null, null, true, 'Черные спагетти с морепродуктами', '550.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (72, 'ПАСТА', null, null, true, 'Карбонара', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (73, 'ПАСТА', null, null, true, 'Тальятелле с цыпленком и грибами', '420.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (62, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Томленый палтус с вешенками', '590.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (2, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Рибай', '540.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (3, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Флэт-айрон', '320.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (63, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Фаланги камчатского краба', '1200.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (4, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Фланк', '250.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (5, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Шато-бриан', '790.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (6, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Медальоны из говядины', '750.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (7, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Корейка телёнка', '850.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (8, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Тунец', '750.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (9, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Корейка ягненка', '890.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (10, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Лосось', '590.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (11, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Свиные рёбрышки BBQ', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (12, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Судак', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (13, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Стейк из свинины', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (14, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Кальмары', '350.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (15, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Куриное бедро и грудка', '390.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (74, 'ГАРНИРЫ', null, null, true, 'Цветная капуста в сухариках', '190.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (75, 'ГАРНИРЫ', null, null, true, 'Оладьи из тыквы', '180.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (76, 'ГАРНИРЫ', null, null, true, 'Печеный картофель по - итальянски', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (77, 'ГАРНИРЫ', null, null, true, 'Картофель фри', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (78, 'ГАРНИРЫ', null, null, true, 'Картофельное пюре', '120.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (79, 'ГАРНИРЫ', null, null, true, 'Овощи на гриле', '250.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (55, 'Мясо и птица', null, '/images/meat.png', false, 'Подкопченная свиная грудинка с вешенками и муссом из моркови', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (64, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Судак под сливочной эмульсией с картофельными дольками', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (80, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Штрудель вишневый', '350.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (36, 'САЛАТЫ', null, '/images/salad.jpg', true, '"Дачный" с говядиной и печеными баклажанами', '420.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (92, 'Кофе', null, null, true, 'Эспрессо / Двойной эспрессо', '120.00 / 180.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (93, 'Кофе', null, null, true, 'Американо', '120.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (94, 'Кофе', null, null, true, 'Капучино', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (95, 'Кофе', null, null, true, 'Латте', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (96, 'Кофе', null, null, true, 'Тыквенный латте', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (97, 'Кофе', null, null, true, 'Карамельный латте с халвой', '180.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (98, 'Кофе', null, null, true, 'Макиато', '120.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (99, 'Кофе', null, null, true, 'Флэт Уайт', '250.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (100, 'Кофе', null, null, true, 'Раф Классический/апельсиновый/лавандовый', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (101, 'Кофе', null, null, true, 'Гляссе', '150.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (102, 'Кофе', null, null, true, 'Фраппучино', '240.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (103, 'САЛАТЫ БИЗНЕС', null, null, true, 'салат из свежих овощей с цыпленком и ореховым соусом', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (104, 'САЛАТЫ БИЗНЕС', null, null, true, 'салат с кальмаром, огурцом и соусом унаги', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (105, 'САЛАТЫ БИЗНЕС', null, null, true, 'оливье с языком', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (106, 'СУПЫ БИЗНЕС', null, null, true, 'Щи с говядиной', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (107, 'СУПЫ БИЗНЕС', null, null, true, 'Финский суп с форелью', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (108, 'СУПЫ БИЗНЕС', null, null, true, 'Куриный суп с фетучини', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (109, 'ГОРЯЧЕЕ БИЗНЕС', null, null, true, 'Хрустящие блины с мясом со сметаной', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (110, 'ГОРЯЧЕЕ БИЗНЕС', null, null, true, 'Кусочки курицы в цитрусовом соусе с рисом', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (111, 'ГОРЯЧЕЕ БИЗНЕС', null, null, true, 'Треска-гриль с отварным картофелем с зеленью', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (37, 'САЛАТЫ', null, '/images/salad.jpg', true, 'С ростбифом, сладкими томатами, копченой сметаной', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (81, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Шоколадный фондан', '260.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (82, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Наполеон', '350.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (38, 'САЛАТЫ', null, '/images/salad.jpg', true, 'С угрем, кремом из авокадо, фундуком', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (83, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Французский яблочный пирог', '220.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (39, 'САЛАТЫ', null, '/images/salad.jpg', true, 'С языком и тыквой', '250.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (40, 'САЛАТЫ', null, '/images/salad.jpg', true, 'С цыпленком - гриль и вялеными томатами', '310.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (41, 'САЛАТЫ', null, '/images/salad.jpg', true, 'Чука с креветками', '420.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (84, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Тирамису', '290.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (85, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Десерт Павловой', '320.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (65, 'Рыба и морепродукты', null, '/images/fish.jpg', true, 'Филе сома с картофельным пюре', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (86, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Чизкейк', '320.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (24, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Карпаччо из говядины', '390.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (25, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Карпаччо из гребешка с кольраби', '420,00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (26, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Малосольная сёмга с соусом из сметаны и хрена', '390.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (87, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Вафли с голубикой и медовой меренгой', '290.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (27, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Форшмак из сельди с картофельными чипсами', '250.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (28, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Тар-тар из лосося с соусом понзу и кремом из авокадо', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (29, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Тар-тар из тунца', '370.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (30, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Селёдка с печёным картофелем', '220.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (16, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Креветки', '690.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (17, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Говяжий язык', '550.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (42, 'САЛАТЫ', null, '/images/salad.jpg', true, 'Листья романо с тунцом и каперсами', '420.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (43, 'САЛАТЫ', null, '/images/salad.jpg', true, 'Из краба и томатов', '600.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (44, 'САЛАТЫ', null, '/images/salad.jpg', true, '"Цезарь" с креветками / с цыпленком', '460.00 / 420.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (45, 'САЛАТЫ', null, '/images/salad.jpg', true, 'С морским гребешком и моцареллой', '600.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (46, 'САЛАТЫ', null, '/images/salad.jpg', true, 'С кальмаром, вешенками и арахисовым соусом', '250.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (54, 'Мясо и птица', null, '/images/meat.png', true, 'Бургер с бараниной / Бургер с уткой', '520.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (31, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Печеный камамбер', '600.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (33, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Креветки и кальмары в азиатском соусе', '490.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (32, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Ассорти брускетт /с ростбифом, с лососем, с пармой/', '450.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (34, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Креветки темпура', '550.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (35, 'ЗАКУСКИ', null, '/images/starter.jpg', true, 'Сет закусок к вину', '590.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (69, 'СУПЫ', null, '/images/soup.jpg', true, 'Уха из судака', '320.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (88, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Сметанник с хурмой', '280.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (89, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Мороженое', '100.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (90, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Сорбет', '100.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (91, 'ДЕСЕРТЫ', null, '/images/desserts.jpg', true, 'Ассорти фруктов', '420.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (18, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Плато морепродуктов', '1400.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (19, 'ГРИЛЬ', null, '/images/grill.jpg', true, 'Дорадо', '690.00');
INSERT INTO menu_item (id, category, description, image_url, is_available_for_order, name, price) VALUES (70, 'СУПЫ', null, '/images/soup.jpg', false, 'Том Ям с курицей', '350.00');


create table table_booking
(
    id           integer not null
        constraint table_booking_pkey
            primary key,
    date         varchar(255),
    email        varchar(255),
    name         varchar(255),
    person_count integer not null,
    phone        varchar(255),
    time         varchar(255)
);


drop table if exists user_email;

create table user_email
(
    id     integer not null
        constraint user_email_pkey
            primary key,
    email  varchar(255),
    status varchar(255)
);



INSERT INTO user_email (id, email, status) VALUES (151, 'danil.2010846@yandex.ru', 'подписан');
drop table if exists user_message;

create table user_message
(
    id      integer not null
        constraint user_message_pkey
            primary key,
    email   varchar(255),
    message varchar(255),
    name    varchar(255),
    phone   varchar(255)
);



INSERT INTO user_message (id, email, message, name, phone) VALUES (1, 'danil.2010846@yandex.ru', 'soob', 'MayorGromov', '+79995678534');