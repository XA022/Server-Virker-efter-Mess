CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS calendar
(
	calendarID int NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	Active tinyint,
	createdby bigint NOT NULL,
	-- 1 = public
	-- 2 = private
	PrivatePublic tinyint NOT NULL COMMENT '1 = public
	2 = private',
	PRIMARY KEY (calendarID)
);



CREATE TABLE IF NOT EXISTS dailyupdate
(
	date datetime NOT NULL UNIQUE,
	apparentTemperature double,
	summary text,
	qotd varchar(300) NOT NULL,
	windspeed double,
	msg_type varchar (100) NOT NULL,
	update_timestamp TIMESTAMP DEFAULT NOW() ON UPDATE NOW(),
	PRIMARY KEY (date)
);


CREATE TABLE IF NOT EXISTS events
(
	eventid int NOT NULL AUTO_INCREMENT,
	type int NOT NULL,
	location int,
	createdby bigint NOT NULL,
	start datetime NOT NULL,
	end datetime NOT NULL,
	name varchar(255) NOT NULL,
	text text NOT NULL,
	active int NOT NULL,
	-- Decides wether the event is an import-event or user created
	-- 
	customevent boolean COMMENT 'Decides wether the event is an import-event or user created
',
	calendarID int NOT NULL,
	PRIMARY KEY (eventid)
);


CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude int NOT NULL,
	latitude int UNIQUE,
	PRIMARY KEY (locationdataid)
);


CREATE TABLE IF NOT EXISTS notes
(
	noteId int NOT NULL AUTO_INCREMENT,
	eventId int NOT NULL,
	createdby bigint NOT NULL,
	text text,
	dateTime datetime NOT NULL,
	active int,
	PRIMARY KEY (noteid)
);


CREATE TABLE IF NOT EXISTS roles
(
	roleid int NOT NULL AUTO_INCREMENT,
	userid bigint NOT NULL,
	type varchar(200) NOT NULL,
	PRIMARY KEY (roleid)
);


CREATE TABLE IF NOT EXISTS userevents
(
	userid bigint NOT NULL,
	calendarID int NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
	userid bigint NOT NULL AUTO_INCREMENT,
	email varchar(40) NOT NULL,
	active boolean,
	created datetime NOT NULL DEFAULT NOW(),
	password varchar(200) NOT NULL,
	PRIMARY KEY (userid)
);



/* Create Dummy Account */

INSERT INTO `cbscalendar`.`users`
(`email`,
`active`,
`password`)
VALUES
("admin@admin.dk",
true,
"d6YSr320JnLXlp8YYxUcNQ==");



/* Create Foreign Keys */

ALTER TABLE events
	ADD FOREIGN KEY (calendarID)
	REFERENCES Calender (calendarID)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (calendarID)
	REFERENCES Calender (calendarID)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (eventid)
	REFERENCES events (eventid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (location)
	REFERENCES locationdata (locationdataid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE roles
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;
