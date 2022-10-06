BEGIN TRANSACTION;

--begin create users:

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

--begin create games:

INSERT INTO games (organizer_id,start_date,end_date,game_name, is_active)
    VALUES ('J_bezos', '2022-09-01', '2022-10-03', 'September Invest-Fest!', 'false');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
    VALUES ('audrina_ali', '2022-09-01','2022-09-30', 'Audrinas Game');

INSERT INTO games (organizer_id,start_date, end_date,game_name)
	VALUES ('Isaiah_Castillo81','2022-10-15', '2022-11-28', 'Fall Stock Contest');

INSERT INTO games (organizer_id,start_date, end_date,game_name)
	VALUES ('jaipuractuary', '2022-10-15','2022-11-10', 'Tech Stock Showdown');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
    VALUES ('J_bezos', '2022-09-10', '2022-10-09', 'Beat The Pro Challenge');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
    VALUES ('ClayParsons', '2022-01-01', '2022-12-31', 'Year-Long Invest-a-thon');

INSERT INTO games (organizer_id,start_date,end_date,game_name, is_active)
    VALUES ('Elizabeth_Kerr', '2022-07-01', '2022-09-30', 'Practice Game for Elizabeth', 'false');

INSERT INTO games (organizer_id,start_date,end_date,game_name, is_active)
    VALUES ('peregrinStork', '2022-06-01', '2022-10-03', 'Tolkein Fans Only', 'false');

INSERT INTO games (organizer_id,start_date,end_date,game_name)
    VALUES ('shadow_fax_kangaroo', '2022-05-01', '2022-10-15', 'All Tech All The Time');

INSERT INTO games (organizer_id,start_date,end_date,game_name, is_active)
    VALUES ('jaipuractuary', '2022-04-01', '2022-08-01', 'Q2 Tech Invest Challenge', 'false');

--begin putting players into games

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1006', '101', 'J_bezos', 'September Invest-Fest!', '104067.70', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1005', '101', 'InvestPro614', 'September Invest-Fest!', '106242.16', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1003', '101', 'ClayParsons', 'September Invest-Fest!', '103645.84', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1007', '101', 'Elizabeth_Kerr', 'September Invest-Fest!', '125093.41', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1010', '101', 'shadow_fax_kangaroo', 'September Invest-Fest!', '116677.20', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1004', '102', 'audrina_ali', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1008', '102', 'Isaiah_Castillo81', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1005', '102', 'InvestPro614', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1011', '102', 'peregrinStork', 'Audrinas Game', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1008', '103', 'Isaiah_Castillo81', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1003', '103', 'ClayParsons', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1009', '103', 'jaipuractuary', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1007', '103', 'Elizabeth_Kerr', 'Fall Stock Contest', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1009', '104', 'jaipuractuary', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1006', '104', 'J_bezos', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1010', '104', 'shadow_fax_kangaroo', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1005', '104', 'InvestPro614', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1011', '104', 'peregrinStork', 'Tech Stock Showdown', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1006', '105', 'J_bezos', 'Beat The Pro Challenge', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1009', '105', 'jaipuractuary', 'Beat The Pro Challenge', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1003', '105', 'ClayParsons', 'Beat The Pro Challenge', '100000', '0');

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1007', '107', 'Elizabeth_Kerr', 'Practice Game For Elizabeth', '153845.63', '0' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1003', '106', 'ClayParsons', 'Year-Long Invest-a-thon', '26435.46', '73564.54' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1010', '106', 'shadow_fax_kangaroo', 'Year-Long Invest-a-thon', '19514.7', '80485.30' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1008', '108', 'Isaiah_Castillo81', 'Tolkein Fans Only', '17514.7', '10485.30' );

INSERT INTO game_results (user_id, game_id, username, game_name, cash_to_trade, investment_value)
	VALUES ('1008', '109', 'Isaiah_Castillo81', 'All Tech All The Time', '100000', '0' );

--begin simulating trading activity

	--user 1006 buy 5 AAPL total(728.55)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'AAPL', '145.71', '5', '0', '5', 'Apple Inc.');

	--user 1006 buy 3 TSLA total(765.57)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'TSLA', '255.19', '3', '0', '3', 'Tesla Inc.');

	--user 1006 buy 2 UNH total(1043.02)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'UNH', '521.51', '2', '0', '2', 'UnitedHealth Group');

	--user 1006 buy 8 JNJ total(1320.96)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'JNJ', '165.12', '8', '0', '8', 'Johnson & Johnson');

	--user 1005 buy 6 PFE total(266.04)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'PFE', '44.34', '6', '0', '6', 'Pfizer Inc.');

	--user 1005 buy 3 JPM total(495.36)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'JPM', '111.92', '3', '0', '3', 'JPMorgan Chase & Co.');

		--user 1005 buy 12 MA total(3637.32)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'MA', '303.11', '12', '0', '12', 'Mastercard Inc Class A');

			--user 1005 buy 12 PEP total(1503.36)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'PEP', '167.04', '9', '0', '9', 'PepsiCo Inc.');

	--user 1003 buy 28 BAC total(904.96)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('ClayParsons', 101, 'BAC', '32.32', '28', '0', '28', 'Bank of America Corp');

	--user 1003 buy 20 WMT total(2689.00)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('ClayParsons', 101, 'WMT', '134.45', '20', '0', '20', 'Walmart Inc.');

	--user 1007 buy 25 LLY total(8143.00)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('Elizabeth_Kerr', 101, 'LLY', '325.72', '25', '0', '25', 'Eli Lilly and Company');

		--user 1007 buy 29 AVGO total(13829.00)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('Elizabeth_Kerr', 101, 'AVGO', '476.89', '29', '0', '29', 'Broadcom Inc.');

		--user 1010 buy 30 DHR total(8250.30)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('shadow_fax_kangaroo', 101, 'DHR', '275.01', '30', '0', '30', 'Danaher Corporation');

		--user 1010 buy 32 MCD total(7643.84)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('shadow_fax_kangaroo', 101, 'MCD', '238.87', '32', '0', '32', 'McDonalds Corporation');


	--user 1006 sell 5 AAPL total(752.30)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'AAPL', '150.46', '0', '5', '0', 'Apple Inc.');

	--user 1006 sell 3 TSLA total(863.49)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'TSLA', '287.83', '0', '3', '0', 'Tesla Inc.');

	--user 1006 sell 2 UNH total(1105.60)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'UNH', '552.80', '0', '2', '0', 'UnitedHealth Group');

	--user 1006 sell 8 JNJ total(1346.32)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('J_bezos', 101, 'JNJ', '168.29', '0', '8', '0', 'Johnson & Johnson');

	--user 1005 sell 6 PFE total(266.58)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'PFE', '44.43', '0', '6', '0', 'Pfizer Inc.');

	--user 1005 sell 3 JPM total(495.36)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'JPM', '116.61', '0', '3', '0', 'JPMorgan Chase & Co.');

		--user 1005 sell 12 MA total(3637.32)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'MA', '341.29', '0', '12', '0', 'Mastercard Inc Class A');

			--user 1005 sell 12 PEP total(1503.36)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('InvestPro614', 101, 'PEP', '170.03', '0', '9', '0', 'PepsiCo Inc.');

	--user 1003 sell 28 BAC total(904.96)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('ClayParsons', 101, 'BAC', '32.33', '0', '28', '0', 'Bank of America Corp');

	--user 1003 sell 20 WMT total(2689.00)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('ClayParsons', 101, 'WMT', '137.03', '0', '20', '0', 'Walmart Inc.');

	--user 1007 sell 25 LLY total(8143.00)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('Elizabeth_Kerr', 101, 'LLY', '339.30', '0', '25', '0', 'Eli Lilly and Company');

		--user 1007 sell 29 AVGO total(13829.00)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('Elizabeth_Kerr', 101, 'AVGO', '572.79', '0', '29', '0', 'Broadcom Inc.');

		--user 1010 sell 30 DHR total(8250.30)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('shadow_fax_kangaroo', 101, 'DHR', '292.12', '0', '30', '0', 'Danaher Corporation');

		--user 1010 sell 32 MCD total(7643.84)
INSERT INTO stocks (username, game_id, ticker, stock_price, shares_purchased, shares_sold, shares_per_ticker, company_name)
	VALUES ('shadow_fax_kangaroo', 101, 'MCD', '247.30', '0', '32', '0', 'McDonalds Corporation');

COMMIT TRANSACTION;




