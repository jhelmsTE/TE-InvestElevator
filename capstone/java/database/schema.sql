BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, games, game_results, stocks CASCADE;

DROP SEQUENCE IF EXISTS seq_user_id, seq_game_id, seq_transaction_id;

--leaving no max value for demo - to scale up, would need to implement max values
CREATE SEQUENCE seq_user_id
INCREMENT BY 1
START WITH 1001
NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

--leaving no max value for demo - to scale up, would need to implement max values
CREATE SEQUENCE seq_game_id
INCREMENT BY 1
START WITH 101
NO MAXVALUE;

-- Create Table Games
CREATE TABLE games (
	game_id int NOT NULL DEFAULT nextval('seq_game_id'),
	organizer_id varchar(50) NOT NULL,
	start_date date DEFAULT CURRENT_DATE,
	end_date date NOT NULL,
	game_name varchar(50) NOT NULL,
	is_active BOOLEAN DEFAULT TRUE,
	game_results varchar(50),
	CONSTRAINT PK_games PRIMARY KEY (game_id)

);
-- Create Table game_results
CREATE TABLE game_results (
	user_id int NOT NULL,
	game_id int NOT NULL,
	username varchar(50) NOT NULL,
	game_name varchar(50) NOT NULL,
	cash_to_trade decimal DEFAULT 100000,
	investment_value decimal DEFAULT 0,
	-- spot saved for (realtime / all-time results columns)
	--is there a way to include list of purchased stocks here, or is that a separate table?
	CONSTRAINT PK_game_results PRIMARY KEY(user_id, game_id),
	CONSTRAINT FK_game_results_from_users FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT FK_game_results_from_games FOREIGN KEY(game_id) REFERENCES games(game_id),
	CONSTRAINT FK_game_results_username FOREIGN KEY(username) REFERENCES users(username)
);

--leaving no max value for demo - to scale up, would need to implement max values
CREATE SEQUENCE seq_transaction_id
INCREMENT BY 1
START WITH 1
NO MAXVALUE;

-- Here's the workhorse during each Stock Game
CREATE TABLE stocks (
	username varchar(50) NOT NULL,
	game_id int NOT NULL,
	ticker varchar(20) NOT NULL,
	stock_price decimal,
	shares_purchased int,
	shares_sold int,
	transaction_id int PRIMARY KEY DEFAULT nextval('seq_transaction_id'),
	shares_per_ticker int,
	company_name varchar(50),
	--CONSTRAINT PK_stocks PRIMARY KEY (user_id),
	CONSTRAINT FK_stocks_from_users FOREIGN KEY (username) REFERENCES users(username),
	CONSTRAINT FK_stocks_from_games FOREIGN KEY (game_id) REFERENCES games(game_id)
);

ALTER TABLE games 
		ADD CONSTRAINT FK_games FOREIGN KEY (organizer_id) REFERENCES users(username);

DROP VIEW IF EXISTS portfolio_values_vw, user_shares_vw, stock_prices_vw;
-- 
CREATE OR REPLACE VIEW stock_prices_vw AS
SELECT ticker, stock_price, game_id FROM stocks s
WHERE transaction_id =
(SELECT MAX(transaction_id) 
 FROM stocks sq WHERE sq.game_id = s.game_id AND sq.ticker = s.ticker);
 
--
CREATE OR REPLACE VIEW user_shares_vw AS
SELECT game_id, username, ticker,
SUM(shares_purchased) - SUM(shares_sold) AS shares_owned
FROM stocks
GROUP BY game_id, username, ticker;

--
CREATE OR REPLACE VIEW portfolio_values_vw AS
SELECT username, u.game_id, SUM(stock_price * shares_owned)
AS portfolio_value
FROM user_shares_vw u LEFT JOIN
stock_prices_vw s ON u.game_id = s.game_id AND u.ticker = s.ticker
GROUP BY username, u.game_id;

COMMIT TRANSACTION;
