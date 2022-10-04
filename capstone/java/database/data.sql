BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) 
    VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role) 
    VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username, password_hash, role)
    VALUES ('demo-user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
    VALUES ('JohnDoe123','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username,password_hash,role)
    VALUES ('InvestPro614','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO games (organizer_id,start_date,end_date,game_name) 
    VALUES ('JohnDoe123', '2022-10-01', '2022-10-03', 'October Invest-Fest!');

INSERT INTO games (organizer_id,start_date,end_date,game_name) 
    VALUES ('demo-user', '2022-10-28', '2022-11-28', 'Demo Game 1');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
	VALUES ('demo-user', '2022-10-28', '2022-11-28', 'Demo Game 2');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1004', '101', 'JohnDoe123', 'October Invest-Fest!', '100000', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1005', '101', 'InvestPro614', 'October Invest-Fest!', '99960', '40' );

INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('demo-user', 101, 'NFLX', '20.00', '5', '0', '5', 'Netflix');

INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('demo-user', 101, 'NFLX', '20.00', '0', '3', '2', 'Netflix');


COMMIT TRANSACTION;




