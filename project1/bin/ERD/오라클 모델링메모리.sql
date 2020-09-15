Create table userset(
id varchar2(20) constraint userset_id_pk primary key,
password varchar2(40) constraint userset_password_nk not null,
name varchar2(30) constraint userset_name_pk not null,
sextual varchar2(3) constraint userset_sextual_nk not null,
address varchar2(80) constraint userset_address_nk not null,
number1 varchar2(60) constraint userset_number1_nk not null,
nickname varchar2(20) constraint userset_nickname_nk UNIQUE);


create table binggoscore(
nickname VARCHAR2(20) CONSTRAINT binggoscore_nickname_fk references userset(nickname),
win number(4),
lose number(4),
draw number(4),
winningrate number(6,2));


create table recordgame_score(
nickname varchar2(20) constraint recordgame_score_nickname_fk references userset(nickname),
count number(4),
record number(4));


create table coin_score(
nickname varchar2(20) constraint coin_score_nickname_fk references userset(nickname),
score number(5));




