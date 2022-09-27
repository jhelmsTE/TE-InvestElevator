
BEGIN TRANSACTION;

DROP TABLE IF EXISTS users
CASCADE;
DROP TABLE IF EXISTS games
CASCADE;
DROP TABLE IF EXISTS game_results;

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
	game_name varchar NOT NULL,
	game_results varchar,
	CONSTRAINT PK_games PRIMARY KEY (game_id)

);
-- Create Table game_results
CREATE TABLE game_results (
	user_id int NOT NULL,
	game_id int NOT NULL,
	-- spot saved for (realtime / all-time results columns)
	CONSTRAINT PK_game_results PRIMARY KEY(user_id, game_id),
	CONSTRAINT FK_game_results_from_users FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT FK_game_results_from_games FOREIGN KEY(game_id) REFERENCES games(game_id)
);

ALTER TABLE games 
		ADD CONSTRAINT FK_games FOREIGN KEY (organizer_id) REFERENCES users(username);

COMMIT TRANSACTION;