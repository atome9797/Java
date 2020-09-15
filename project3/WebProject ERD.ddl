ALTER TABLE POST DROP CONSTRAINT POST_ACCOUNT_NAME_FK;

ALTER TABLE WEBCOMMENT DROP CONSTRAINT COMMENT_POST_NUM_FK;

ALTER TABLE FOLLOW DROP CONSTRAINT FOLLOW_ACCOUNT_NUM_FK;

ALTER TABLE AFLAG_CATEGORY DROP CONSTRAINT AFLAG_CATEGORY_ACCOUNT_NUM_FK;

ALTER TABLE LIKE_TABLE DROP CONSTRAINT LIKE_TABLE_ACCOUNT_NUM_FK;

ALTER TABLE FLAG_TABLE DROP CONSTRAINT FLAG_TABLE_ACCOUNT_NUM_FK;

ALTER TABLE FLAG_CATEGORY DROP CONSTRAINT FLAG_CATEGORY_POST_NUM_FK;

ALTER TABLE LIKE_TABLE DROP CONSTRAINT LIKE_TABLE_POST_NUM_FK;

ALTER TABLE webaccount DROP CONSTRAINT ACCOUNT_ID_UN;

ALTER TABLE webaccount DROP CONSTRAINT ACCOUNT_EMAIL_UN;

ALTER TABLE webaccount DROP CONSTRAINT ACCOUNT_NAME_UN;

ALTER TABLE webaccount DROP CONSTRAINT ACCOUNT_PHONE_NUM_UN;

ALTER TABLE webaccount DROP PRIMARY KEY;

drop view webaccount_post_join;

drop view account_follow_join;

DROP TABLE aflag_category;

drop table webaccount;

drop table flag_table;

drop table post;

drop table flag_category;

drop table webcomment;

drop table follow;

drop table like_table;

drop sequence webaccount_account_num_seq;

drop sequence webcomment_comment_seq_seq;

drop sequence post_post_num_seq;

drop sequence follow_follow_seq_seq;

drop sequence like_table_like_num_seq;

drop sequence aflag_category_ac_num_seq;

drop sequence flag_category_fc_num_seq;

CREATE TABLE webaccount (
    account_num         NUMBER(3) NOT NULL,
    account_id          VARCHAR2(20) NOT NULL,
    account_password    VARCHAR2(20) NOT NULL,
    account_name        VARCHAR2(20) NOT NULL,
    account_email       VARCHAR2(20) NOT NULL,
    account_img         VARCHAR2(300 BYTE),
    account_phone_num   VARCHAR2(15),
    account_flag        NUMBER(5) NOT NULL,
    account_about       varchar2(500) 
)
LOGGING;

ALTER TABLE webaccount ADD CONSTRAINT account_pk PRIMARY KEY ( account_num );

ALTER TABLE webaccount ADD CONSTRAINT account_email_un UNIQUE ( account_email );

ALTER TABLE webaccount ADD CONSTRAINT account_id_un UNIQUE ( account_id );

ALTER TABLE webaccount ADD CONSTRAINT account_name_un UNIQUE ( account_name );

ALTER TABLE webaccount ADD CONSTRAINT account_phone_num_un UNIQUE ( account_phone_num );

CREATE TABLE webcomment(
    comment_seq     NUMBER(3) NOT NULL,
    post_num        NUMBER(5) NOT NULL,
    comment_ref     NUMBER(3) NOT NULL,
    comment_step    NUMBER(3) NOT NULL,
    comment_level   NUMBER(1) NOT NULL,
    comment_name    varchar2(20) not null,
    comment_content varchar2(1000)
)
LOGGING;

ALTER TABLE webcomment ADD CONSTRAINT comment_pk PRIMARY KEY ( comment_seq );

CREATE TABLE follow (
    follow_seq    NUMBER(3) NOT NULL,
    account_num   NUMBER(3) NOT NULL,
    follow_name     VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE follow ADD CONSTRAINT follow_pk PRIMARY KEY ( follow_seq );

CREATE TABLE post (
    post_num       NUMBER(5) NOT NULL,
    account_name   VARCHAR2(20) NOT NULL,
    post_content   VARCHAR2(500),
    post_img       VARCHAR2(300 BYTE) NOT NULL,
    post_like      NUMBER(5) NOT NULL,
    post_sysdate   DATE,
    post_flag      NUMBER(5) NOT NULL
)
LOGGING;

ALTER TABLE post ADD CONSTRAINT post_pk PRIMARY KEY ( post_num );

CREATE TABLE LIKE_TABLE (
    like_num       number(5) not null,
    post_num       number(5) not null,
    account_num    number(3) not null
)
LOGGING;

ALTER TABLE LIKE_TABLE ADD CONSTRAINT like_table_pk PRIMARY KEY ( like_num );

CREATE TABLE FLAG_TABLE (
    flag_num       number(5) not null,
    post_num       number(5) not null,
    account_num    number(3) not null
)
LOGGING;

ALTER TABLE FLAG_TABLE ADD CONSTRAINT flag_table_pk PRIMARY KEY ( flag_num );

CREATE TABLE FLAG_CATEGORY (
    fc_num            number(5) not null,
    post_num          number(5) not null,
    spam              number(5) not null,
    abusive           number(5) not null,
    obscene           number(5) not null,
    illegaladv        number(5) not null
    
)
LOGGING;

ALTER TABLE FLAG_CATEGORY ADD CONSTRAINT fc_num PRIMARY KEY ( fc_num );

CREATE TABLE AFLAG_CATEGORY (
    ac_num     number(5) not null,
    account_num       number(3) not null,
    spam              number(5) not null,
    abusive           number(5) not null,
    obscene           number(5) not null,
    illegaladv        number(5) not null
    
)
LOGGING;

ALTER TABLE AFLAG_CATEGORY ADD CONSTRAINT ac_num PRIMARY KEY ( ac_num );

ALTER TABLE webcomment
    ADD CONSTRAINT comment_post_num_fk FOREIGN KEY ( post_num )
        REFERENCES post ( post_num )
    NOT DEFERRABLE;

create sequence webaccount_account_num_seq
    start with 1
    increment by 1
    nocache
    nocycle;
    
create sequence webcomment_comment_seq_seq
    start with 1
    increment by 1
    nocache
    nocycle;
    
create sequence post_post_num_seq
    start with 1
    increment by 1
    nocache
    nocycle;
    
create sequence follow_follow_seq_seq
    start with 1
    increment by 1
    nocache
    nocycle;
    
create sequence like_table_like_num_seq
    start with 1
    increment by 1
    nocache
    nocycle;
    
create sequence flag_category_fc_num_seq
    start with 1
    increment by 1
    nocache
    nocycle;
    
create sequence aflag_category_ac_num_seq
    start with 1
    increment by 1
    nocache
    nocycle;

ALTER TABLE POST
add CONSTRAINT POST_ACCOUNT_NAME_FK FOREIGN KEY(account_name) REFERENCES webaccount(account_name)
    ON DELETE CASCADE;
    
ALTER TABLE FOLLOW
add CONSTRAINT FOLLOW_ACCOUNT_NUM_FK FOREIGN KEY(account_num) REFERENCES webaccount(account_num)
    ON DELETE CASCADE;    
    
ALTER TABLE LIKE_TABLE
add CONSTRAINT LIKE_TABLE_ACCOUNT_NUM_FK FOREIGN KEY(account_num) REFERENCES webaccount(account_num)
    ON DELETE CASCADE;   
    
ALTER TABLE LIKE_TABLE
add CONSTRAINT LIKE_TABLE_POST_NUM_FK FOREIGN KEY(post_num) REFERENCES post(post_num)
    ON DELETE CASCADE;   

ALTER TABLE FLAG_TABLE
add CONSTRAINT FLAG_TABLE_ACCOUNT_NUM_FK FOREIGN KEY(account_num) REFERENCES webaccount(account_num)
    ON DELETE CASCADE;   
    
ALTER TABLE FLAG_TABLE
add CONSTRAINT FLAG_TABLE_POST_NUM_FK FOREIGN KEY(post_num) REFERENCES post(post_num)
    ON DELETE CASCADE;   
    
CREATE VIEW webaccount_post_join 
AS
SELECT w.account_name, w.account_num, w.account_id, w.account_img, p.post_img, p.post_content, p.post_like, p.post_sysdate, p.post_flag, p.post_num
FROM webaccount w, post p
WHERE w.account_name=p.account_name;

CREATE VIEW account_follow_join
AS
SELECT w.account_img, w.account_name, f.FOLLOW_SEQ, f.account_num
FROM webaccount w, follow f
WHERE w.account_name=f.follow_name;

ALTER TABLE FLAG_CATEGORY
add CONSTRAINT FLAG_CATEGORY_POST_NUM_FK FOREIGN KEY(post_num) REFERENCES post(post_num)
    ON DELETE CASCADE; 
    
ALTER TABLE aflag_category
add CONSTRAINT aflag_category_ACCOUNT_NUM_FK FOREIGN KEY(account_num) REFERENCES webaccount(account_num)
    ON DELETE CASCADE; 



commit;
