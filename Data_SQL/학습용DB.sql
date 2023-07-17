-- tbl_memo TABLE

CREATE TABLE tbl_memo (
	MNO BIGINT NOT NULL AUTO_INCREMENT,
	MEMO_TEXT VARCHAR(200) NOT NULL,
	PRIMARY KEY(MNO)
) ENGINE=INNODB

-- guestbook TABLE

CREATE TABLE guestbook(
	GNO BIGINT NOT NULL AUTO_INCREMENT,
	MODDATE DATETIME(6),
	REGDATE DATETIME(6),
	CONTENT VARCHAR(1500) NOT NULL,
	TITLE VARCHAR(100) NOT NULL,
	WRITER VARCHAR(50) NOT NULL,
	PRIMARY KEY (GNO)
) ENGINE=INNODB



-- board TABLE
CREATE TABLE board(
	BNO BIGINT NOT NULL AUTO_INCREMENT,
	MODDATE DATETIME(6),
	REGDATE DATETIME(6),
	CONTENT VARCHAR(255),
	TITLE VARCHAR(255),
	WRITER_EMAIL VARCHAR(255),
	PRIMARY KEY(BNO)
) ENGINE = INNODB

-- MEMBER TABLE
CREATE TABLE member(
	EMAIL VARCHAR(255) NOT NULL,
	MODDATE DATETIME(6),
	REGDATE DATETIME(6),
	NAME VARCHAR(255),
	PASSWORD VARCHAR(255),
	PRIMARY KEY(EMAIL)
) ENGINE = INNODB

-- REPLY TABLE
CREATE TABLE reply (
	RNO BIGINT NOT NULL AUTO_INCREMENT,
	MODDATE DATETIME(6),
	REGDATE DATETIME(6),
	REPLYER VARCHAR(255),
	TEXT VARCHAR(255),
	BOARD_BNO BIGINT,
	PRIMARY KEY(RNO)
) ENGINE = INNODB

-- FORIEGN KEY APPLY TO BOARD / REPLY
ALTER TABLE board
	ADD CONTRAINT --자동으로 생성되는 외래키의 이름
	FOREIGN KEY (WRITER_EMAIL)
	REFERENCES member(EMAIL)
	
ALTER TABLE reply
	ADD CONTRAINT -- 자동으로 생성되는 외래키의 이름
	FOREIGN KEY (BOARD_BNO)
	REFERENCES board(BNO)	


-- Movie Table
Create table movie(
	mno bigint not null auto_increment,
	moddate datetime(6),
	regdate datetime(6),
	title	varchar(255),
	primary key(mno)
) ENGINE = INNODB

CREATE TABLE MOVIE_IMAGE (
	inum bigint not null auto_increment,
	img_name varchar(255),
	path varchar(255),
	uuid varchar(255),
	movie_mno bigint,
	primary key (inum)
) ENGINE = INNODB

ALTER TABLE MOVIE_IMAGE
ADD CONSTRAINT -- 외래키 자동 생성
FOREIGN KEY (MOVIE_MNO)
REFERENCES MOVIE (MNO)

CREATE TABLE `member` (
	`email` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`moddate` DATETIME(6) NULL DEFAULT NULL,
	`regdate` DATETIME(6) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`password` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`email`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


-- Club_Member
create table club_member (
       email varchar(255) not null,
        moddate datetime(6),
        regdate datetime(6),
        from_social bit not null,
        name varchar(255),
        password varchar(255),
        primary key (email)
    ) engine=InnoDB

-- club_member_role_set
    create table club_member_role_set (
       club_member_email varchar(255) not null,
        role_set integer
    ) engine=InnoDB

-- alter table club_membere_role_set
    alter table club_member_role_set 
       add constraint FKbfljk8ybtolixxc88osbodrek 
       foreign key (club_member_email) 
       references club_member (email)
