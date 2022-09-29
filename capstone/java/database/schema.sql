BEGIN TRANSACTION;

DROP TABLE IF EXISTS users
CASCADE;
DROP TABLE IF EXISTS games
CASCADE;
DROP TABLE IF EXISTS game_results;
DROP TABLE IF EXISTS stocks;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
-- Create Table Games
CREATE TABLE games (
	game_id SERIAL,
	organizer_id varchar(50) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	game_name varchar(50) NOT NULL,
	game_results varchar(50),
	CONSTRAINT PK_games PRIMARY KEY (game_id)

);
-- Create Table game_results
CREATE TABLE game_results (
	user_id int NOT NULL,
	game_id int NOT NULL,
	username varchar(50) NOT NULL,
	cash_to_trade decimal DEFAULT 100000,

	-- keep an eye on total_account_value's starting amount
	-- do we want to instantiate it with an equation(?) or
	-- with the starting $100k value? Not sure just yet.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	total_account_value decimal DEFAULT 100000,
	-- spot saved for (realtime / all-time results columns)
	--is there a way to include list of purchased stocks here, or is that a separate table?
	CONSTRAINT PK_game_results PRIMARY KEY(user_id, game_id),
	CONSTRAINT FK_game_results_from_users FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT FK_game_results_from_games FOREIGN KEY(game_id) REFERENCES games(game_id),
	CONSTRAINT FK_game_results_username FOREIGN KEY(username) REFERENCES users(username)
);

--what a separate stocks stable might look like:
CREATE TABLE stocks (
	username varchar(50) NOT NULL,
	game_id int NOT NULL,
	ticker varchar(10) NOT NULL,
	company_name varchar(50),
	buy_price decimal,
	--CONSTRAINT PK_stocks PRIMARY KEY (user_id),
	CONSTRAINT FK_stocks_from_users FOREIGN KEY (username) REFERENCES users(username),
	CONSTRAINT FK_stocks_from_games FOREIGN KEY (game_id) REFERENCES games(game_id)
);

ALTER TABLE games 
		ADD CONSTRAINT FK_games FOREIGN KEY (organizer_id) REFERENCES users(username);

COMMIT TRANSACTION;