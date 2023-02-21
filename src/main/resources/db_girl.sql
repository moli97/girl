create table t_u_girl
(
    id                 int           not null
        primary key,
    chineseName        varchar(100)  null,
    girlName           varchar(100)  null,
    englishName        varchar(100)  null,
    alias              varchar(100)  null,
    birthday           varchar(20)   null,
    constellation      varchar(20)   null,
    zodiac             varchar(10)   null,
    bloodType          varchar(10)   null,
    height             varchar(10)   null,
    weight             varchar(10)   null,
    threeCircumference varchar(50)   null,
    occupation         varchar(100)  null,
    birth              varchar(100)  null,
    introduction       varchar(2000) null,
    createTime         varchar(20)   null,
    hits               varchar(10)   null,
    cover              varchar(100)  null,
    interest           varchar(100)  null,
    status             int default 0 not null
)
    charset = utf8;


create table t_u_album
(
    id     int           not null
        primary key,
    girlId int           not null,
    title  varchar(255)  null,
    cover  varchar(100)  null,
    size   int default 0 not null,
    status int default 0 null
);

