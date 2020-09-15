create table userboard(
    user_id varchar2(40) primary key,
    user_password varchar2(40) not null,
    user_name varchar2(20) not null,
    address varchar2(80) not null,
    sextual varchar2(3) not null,
    user_number varchar2(40) not null,
    nickname varchar2(30) unique
);


create table answerboard( /*1대1 게시판*/
    nickname varchar2(30) constraint answerboard_nickname_fk references userboard(nickname),
    user_subject varchar2(150),
    user_content varchar2(900),
    admin_subject varchar2(150),
    admin_subject varchar2(900),
    num varchar2(30)
);


create table freetextboard( /*자유게시글*/
    nickname varchar2(30) constraint freetextboard_nickname_fk references userboard(nickname),
    free_subject varchar2(150),
    free_content varchar2(900)
);


create table alarmboard(/*공지사항*/
    nickname varchar2(30) constraint freetextboard_nickname_fk references userboard(nickname),
    free_subject varchar2(150),
    free_content varchar2(900)
);


CREATE SEQUENCE answerboard_num_seq
start with 1
increment BY 1
nocache;

