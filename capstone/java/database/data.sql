BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) 
    VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role) 
    VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username, password_hash, role)
    VALUES ('ClayParsons','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
    VALUES ('audrina_ali','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username,password_hash,role)
    VALUES ('InvestPro614','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username, password_hash, role)
    VALUES ('J_bezos', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER' );

INSERT INTO users (username, password_hash, role)
    VALUES ('Elizabeth_Kerr', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER' );

INSERT INTO users (username, password_hash, role)
    VALUES ('Isaiah_Castillo81', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
	VALUES ('jaipuractuary', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
	VALUES ('shadow_fax_kangaroo', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO users (username, password_hash, role)
	VALUES ('peregrinStork', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
    VALUES ('J_bezos', '2022-09-01', '2022-10-03', 'September Invest-Fest!');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
    VALUES ('audrina_ali', '2022-08-28', '2022-09-30', 'Audrinas Game');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
	VALUES ('Isaiah_Castillo81', '2022-10-28', '2022-11-28', 'Fall Stock Contest');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
	VALUES ('jaipuractuary', '2022-10-10', '2022-11-10', 'Tech Stock Showdown');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1006', '101', 'J_bezos', 'October Invest-Fest!', '100000', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1005', '101', 'InvestPro614', 'October Invest-Fest!', '100000', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1003', '101', 'ClayParsons', 'October Invest-Fest!', '100000', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1007', '101', 'Elizabeth_Kerr', 'October Invest-Fest!', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1010', '101', 'shadow_fax_kangaroo', 'October Invest-Fest!', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1004', '102', 'audrina_ali', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1008', '102', 'Isaiah_Castillo81', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1005', '102', 'InvestPro614', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1011', '102', 'peregrinStork', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1008', '103', 'Isaiah_Castillo81', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1003', '103', 'ClayParsons', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1009', '103', 'jaipuractuary', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1007', '103', 'Elizabeth_Kerr', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1009', '104', 'jaipuractuary', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1006', '104', 'J_bezos', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1010', '104', 'shadow_fax_kangaroo', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1005', '104', 'InvestPro614', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, total_account_value)
	VALUES ('1011', '104', 'peregrinStork', 'Tech Stock Showdown', '100000', '0');

INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'NFLX', '20.00', '5', '0', '5', 'Netflix');

INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'NFLX', '20.00', '0', '3', '2', 'Netflix');


COMMIT TRANSACTION;




