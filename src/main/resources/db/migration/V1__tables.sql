create table user(
id bigint auto_increment primary key,
name varchar(100),
user_name varchar(100),
password varchar(100),
email varchar(30),
phone  varchar(30),
login_flag varchar(2)
);


create table leaving(
id bigint auto_increment primary key,
employee_id bigint,
start_date datetime,
end_date datetime,
reason varchar(300),
status varchar(10),
comment varchar(300),
create_date datetime,
terminated int
);