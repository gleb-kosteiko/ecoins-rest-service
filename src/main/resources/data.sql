insert into user (id, username, encrypted_password, role, city, image_url, coins_count, country, email) values ('4028e38168a33e030168a33f01dc0000', 'hkastseika', '$2a$10$mtwNN/rCHVeg2E23sLHOQeB1Rcvx9QIdWgRfa1VW.hK14.BJzrj9.', 'ADMIN', 'Minsk', 'bla', '0', 'Belarus', 'hkastseika@edmunds.com');
insert into user (id, username, encrypted_password, role, city, image_url, coins_count, country, email) values ('4028e38168a33e030168a12f01dc0000', 'rbohush', '$2a$10$mtwNN/rCHVeg2E23sLHOQeB1Rcvx9QIdWgRfa1VW.hK14.BJzrj9.', 'ADMIN', 'Minsk', 'bla', '0', 'Belarus', 'rbohush@edmunds.com');
insert into user (id, username, encrypted_password, role, city, image_url, coins_count, country, email) values ('4028e38168a33e031268a12f01dc0000', 'dbiruk', '$2a$10$mtwNN/rCHVeg2E23sLHOQeB1Rcvx9QIdWgRfa1VW.hK14.BJzrj9.', 'ADMIN', 'Minsk', 'bla', '0', 'Belarus', 'dbiruk@edmunds.com');
insert into user (id, username, encrypted_password, role, city, image_url, coins_count, country, email) values ('4028e38168a33e076268a12f01dc0000', 'ahaspadarsky', '$2a$10$mtwNN/rCHVeg2E23sLHOQeB1Rcvx9QIdWgRfa1VW.hK14.BJzrj9.', 'ADMIN', 'Minsk', 'bla', '0', 'Belarus', 'ahaspadarsky@edmunds.com');

insert into publication (id, user_id, title, text, is_published, created_date, updated_date, rating) values ('4028e31231a33e031268a12f01dc0000', '4028e38168a33e030168a33f01dc0000', 'How Hleb chose his first SUV!', 'Here is test article', true, '2019-01-29T08:22:12', '2019-01-29T08:22:12', 6);
insert into publication (id, user_id, title, text, is_published, created_date, updated_date, rating) values ('4028e31297a33e031268a12f01dc0000', '4028e38168a33e030168a33f01dc0000', 'Second Hleb''s article', 'I''m still working on it', true, '2019-01-30T18:20:12', '2019-01-30T18:20:12', 5);
insert into publication (id, user_id, title, text, is_published, created_date, updated_date, rating) values ('4028e31231a33e595268a12f01dc0000', '4028e38168a33e030168a12f01dc0000', 'Best vehicles of 2019 - R. Bohush''s top 10!', 'Top 10 vehicles of 2019 by R. Bohush', true, '2019-02-03T14:32:02', '2019-02-03T14:32:02', 4);