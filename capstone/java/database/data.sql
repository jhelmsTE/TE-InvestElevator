BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) 
    VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) 
    VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username, password_hash, role)
    VALUES ('demo-user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO games (organizer_id,start_date,end_date,game_name) 
    VALUES ('demo-user', '2022-09-28', '2022-10-28', 'Demo Game 1');

INSERT INTO games (organizer_id,start_date,end_date,game_name) 
    VALUES ('demo-user', '2022-10-28', '2022-11-28', 'Demo Game 2');

 INSERT INTO stocks (username, game_id, ticker, company_name, buy_price)
VALUES ('demo-user', 101, 'APL', 'Apple', 2.58);

 INSERT INTO stocks (username, game_id, ticker, company_name, buy_price)
 VALUES ('demo-user', 101, 'TSL', 'Tesla', 5.77);

COMMIT TRANSACTION;




