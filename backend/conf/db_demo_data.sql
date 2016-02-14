--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- INSERT INTO wallets (name, delete_flag, availability_flag) VALUES ('portofel1', true, true);
-- INSERT INTO wallets (name, delete_flag, availability_flag) VALUES ('portofel2', true, false);
-- INSERT INTO wallets (name, delete_flag, availability_flag) VALUES ('portofel3', false, true);
-- INSERT INTO wallets (name, delete_flag, availability_flag) VALUES ('portofel4', false, false);

INSERT INTO users (first_name, last_name, email, password, role, delete_flag) VALUES ('Daniel', 'Filiuta', 'daniel.filiuta@yahoo.com', 'abcdef', 'ADMIN', true);
INSERT INTO users (first_name, last_name, email, password, role, delete_flag) VALUES ('Razvan', 'Antohi', 'admin@yahoo.com', 'admin', 'ADMIN', false);
INSERT INTO users (first_name, last_name, email, password, role, delete_flag) VALUES ('Andrei', 'Mihalache', 'waiterDeleted@yahoo.com', 'waiter', 'WAITER', true);
INSERT INTO users (first_name, last_name, email, password, role, delete_flag) VALUES ('Marius', 'Constantin', 'waiter1@yahoo.com', 'waiter', 'WAITER', false);
INSERT INTO users (first_name, last_name, email, password, role, delete_flag) VALUES ('Gigel', 'Negoita', 'waiter2@yahoo.com', 'waiter', 'WAITER', false);

-- INSERT INTO shifts (wallet_id, user_id, start_sum, finish_sum, bank_sum, start_date, finish_date) 
-- 		VALUES (1, 3, 50, null, null, 2015-08-26 12:18:45.436, null);
-- INSERT INTO shifts (wallet_id, user_id, start_sum, finish_sum, bank_sum, start_date, finish_date) 
-- 		VALUES (2, 4, 50, null, null, current_timestamp, null);
-- INSERT INTO shifts (wallet_id, user_id, start_sum, finish_sum, bank_sum, start_date, finish_date) 
-- 		VALUES (3, 3, 50, null, null, current_timestamp, null);
-- INSERT INTO shifts (wallet_id, user_id, start_sum, finish_sum, bank_sum, start_date, finish_date) 
-- 		VALUES (4, 4, 50, null, null, current_timestamp, null);


-- INSERT INTO costs (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs1');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (2, 234, current_timestamp, 'sadfwsdfs2');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (3, 234, current_timestamp, 'sadfwsdfs3');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (4, 234, current_timestamp, 'sadfwsdfs4');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs5');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (2, 234, current_timestamp, 'sadfwsdfs6');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (3, 234, current_timestamp, 'sadfwsdfs7');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (4, 234, current_timestamp, 'sadfwsdfs8');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs9');
-- INSERT INTO costs (shift_id, sum, date, description) VALUES (2, 234, current_timestamp, 'sadfwsdfs10');

-- INSERT INTO income (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs1');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (2, 234, current_timestamp, 'sadfwsdfs2');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (3, 234, current_timestamp, 'sadfwsdfs3');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (4, 234, current_timestamp, 'sadfwsdfs4');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs5');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (2, 234, current_timestamp, 'sadfwsdfs6');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (3, 234, current_timestamp, 'sadfwsdfs7');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (4, 234, current_timestamp, 'sadfwsdfs8');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs9');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (2, 234, current_timestamp, 'sadfwsdfs10');
-- INSERT INTO income (shift_id, sum, date, description) VALUES (1, 234, current_timestamp, 'sadfwsdfs11');
