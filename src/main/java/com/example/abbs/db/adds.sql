SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS anniversary;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS Reply;
DROP TABLE IF EXISTS Board;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE anniversary
(
	aid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	aname varchar(20) NOT NULL,
	adate char(8) NOT NULL,
	isHoliday int DEFAULT 0,
	PRIMARY KEY (aid)
);


CREATE TABLE Board
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	title varchar(64) NOT NULL,
	content varchar(3000),
	writeTime datetime DEFAULT (CURRENT_TIME),
	isDeleted int DEFAULT 0,
	viewCount int DEFAULT 0,
	replyCount int DEFAULT 0,
	likecount int DEFAULT 0,
	files varchar(512),
	PRIMARY KEY (bid)
);


CREATE TABLE likes
(
	lid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	bid int NOT NULL,
	clickTime datetime DEFAULT (CURRENT_DATE),
	PRIMARY KEY (lid)
);


CREATE TABLE Reply
(
	Rid int NOT NULL AUTO_INCREMENT,
	bid int NOT NULL,
	uid varchar(12) NOT NULL,
	content varchar(500) NOT NULL,
	replyTime datetime DEFAULT (CURRENT_TIME),
	isMine int DEFAULT 0,
	PRIMARY KEY (Rid)
);


CREATE TABLE schedule
(
	sid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	sdate char(8) NOT NULL,
	title varchar(40) NOT NULL,
	place varchar(40),
	startTime char(4),
	endTime char(4),
	isImportant int DEFAULT 0,
	memo varchar(100),
	PRIMARY KEY (sid)
);


CREATE TABLE users
(
	uid varchar(12) NOT NULL,
	pwd char(60) NOT NULL,
	uname varchar(16) NOT NULL,
	email varchar(32),
	-- 등록일자
	regDate date DEFAULT (CURRENT_DATE) COMMENT '등록일자',
	isDeleted int DEFAULT 0,
	profile varchar(40),
	github varchar(40),
	insta varchar(40),
	location varchar(20),
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE likes
	ADD FOREIGN KEY (bid)
	REFERENCES Board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Reply
	ADD FOREIGN KEY (bid)
	REFERENCES Board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE anniversary
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Board
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE likes
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Reply
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE schedule
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



