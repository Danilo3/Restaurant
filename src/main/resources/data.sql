delete from dacha_user;
insert into dacha_user(id, username, password, role, avatar_url) values('1', 'user', 'user', 'USER', '/images/dasha.jpg');
insert into dacha_user(id, username, password, role, avatar_url) values('777', 'admin', 'admin', 'ADMIN', '/images/chuck.jpg');

delete from delivery_order_order_items;
delete from delivery_order_item;
delete from delivery_order;
delete from gallery_item;
delete from blog_post_tags;
delete from post_tag;
delete from blog_post;

/* Blog */
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9991','Romantic Restaurant!','2021-03-04 21:46:07.000000','/images/blog-07.jpg','Best places for Wine!', 'name1', 'BLOG', 'Автор', 0 );
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9992','Blog text preview','2021-03-04 20:46:07.000000','/images/blog-06.jpg','Spring...Spring...', 'name2', 'BLOG', 'Автор', 0 );
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9993','Blog text preview','2021-03-04 20:46:07.000000','/images/blog-04.jpg','Test Blog Post', 'name3', 'BLOG', 'Автор', 0  );
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9994','Blog text preview!','2021-03-04 20:46:07.000000','/images/blog-12.jpg','Sorry, now site is a bunch of memes', 'name4', 'BLOG', 'Автор', 20);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9995','Blog text preview!','2021-03-04 20:46:07.000000','/images/blog-14.jpg','Come with friends', 'name5', 'BLOG', 'Автор', 0 );
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9996','Blog text preview','2021-03-04 20:46:07.000000','/images/blog-15.jpg','You will never forget this dinner', 'name6', 'BLOG', 'Автор', 0);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9997','Blog text preview','2021-02-04 20:44:07.000000','/images/blog-11.jpg','Our waiters are awesome', 'name7', 'BLOG', 'Автор', 15);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9998','Blog text preview', '2021-02-03 20:46:07.000000','/images/blog-10.jpg','New youtube video!', 'name8', 'BLOG', 'Автор',  0);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('9999','I am a mouse', '2021-03-04 20:46:07.000000','/images/remi.jpg','Best Chef in France', 'name9', 'BLOG', 'Автор',  10);

/* About */
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('99911','Phasellus lorem enim, luctus ut velit eget, con-vallis egestas eros.','2021-03-04 20:44:07.000000','/images/intro-01.jpg','Романтичный ресторан', 'intro1', 'ABOUT', 'Автор', 0);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('99912','Aliquam eget aliquam magna, quis posuere risus ac justo ipsum nibh urna', '2021-03-04 20:46:07.000000','/images/intro-02.jpg','Изысканная еда', 'intro2', 'ABOUT', 'Автор', 0 );
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, author, views_count) values('99913','Sed ornare ligula eget tortor tempor, quis porta tellus dictum.', '2021-03-04 20:46:07.000000','/images/intro-04.jpg','Красные вина которые вы любите', 'intro3', 'ABOUT', 'Автор', 0 );

/* EVENTS */
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, event_background_image_url, event_date_time, author, views_count) values('99914','Phasellus lorem enim, luctus ut velit eget, con-vallis egestas eros.','2021-03-04 20:44:07.000000','background-image: url(/images/event-01.jpg)','Винные вечера на Даче', 'event1', 'EVENT', 'background-image: url(/images/bg-event-01.jpg)', '14 марта в 18:00', 'Автор', 0);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, event_background_image_url,  event_date_time, author, views_count) values('99915','Aliquam eget aliquam magna, quis posuere risus ac justo ipsum nibh urna', '2021-03-04 20:46:07.000000','background-image: url(/images/event-02.jpg)','Выступление итальянского музыканта', 'event2', 'EVENT','background-image: url(/images/bg-event-02.jpg)', '8 марта целый день', 'Автор', 0);
insert into blog_post(id, preview_text, create_date, image_url, title, url_title, category, event_background_image_url,  event_date_time, author, views_count) values('99916','Sed ornare ligula eget tortor tempor, quis porta tellus dictum.', '2021-03-04 20:46:07.000000','background-image: url(/images/event-06.jpg)','Дегустация Рибая', 'event3', 'EVENT', 'background-image: url(/images/bg-event-04.jpg)', 'когда-нибудь', 'Приколист', 0);

/*  Tags */

insert into post_tag (id, url_title, tag_name) values (1, 'recipe', 'Рецепт');
insert into post_tag (id, url_title, tag_name) values (2, 'news', 'Новости');
insert into post_tag (id, url_title, tag_name) values (3, 'design', 'Дизайн');
insert into post_tag (id, url_title, tag_name) values (4, 'cooking', 'Кухня');
insert into post_tag (id, url_title, tag_name) values (5, 'interesting', 'Интересное');

insert into blog_post_tags(blog_post_id, tags_id) values (9991, 1);
insert into blog_post_tags(blog_post_id, tags_id) values (9992, 2);
insert into blog_post_tags(blog_post_id, tags_id) values (9992, 1);

/* Gallery */

insert into gallery_item (id, image_url, is_published, category) values (9991, '/images/photo-gallery-01.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (9992, '/images/photo-gallery-02.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (9993, '/images/photo-gallery-03.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (9994, '/images/photo-gallery-04.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (9995, '/images/photo-gallery-05.jpg', true, 'events');
insert into gallery_item (id, image_url, is_published, category) values (9996, '/images/photo-gallery-06.jpg', true, 'guests');
insert into gallery_item (id, image_url, is_published, category) values (9997, '/images/photo-gallery-07.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (9998, '/images/photo-gallery-08.jpg', true, 'interior');
insert into gallery_item (id, image_url, is_published, category) values (9999, '/images/photo-gallery-09.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (9990, '/images/photo-gallery-10.jpg', true, 'food');
insert into gallery_item (id, image_url, is_published, category) values (99991, '/images/interior1.jpeg', true, 'interior');
insert into gallery_item (id, image_url, is_published, category) values (99992, '/images/interior2.jpg', true, 'interior');


update menu_item set image_url = '/images/salad.jpg' where category='САЛАТЫ';
update menu_item set image_url = '/images/meat.png' where category='Мясо и птица';
update menu_item set image_url = '/images/fish.jpg' where category='Рыба и морепродукты';
update menu_item set image_url = '/images/starter.jpg' where category='ЗАКУСКИ';
update menu_item set image_url = '/images/soup.jpg' where category='СУПЫ';
update menu_item set image_url = '/images/desserts.jpg' where category='ДЕСЕРТЫ';
update menu_item set image_url = '/images/grill.jpg' where category='ГРИЛЬ';

update menu_item set is_available_for_order=false where name='Подкопченная свиная грудинка с вешенками и муссом из моркови';
update menu_item set is_available_for_order=false where name='Том Ям с курицей';
