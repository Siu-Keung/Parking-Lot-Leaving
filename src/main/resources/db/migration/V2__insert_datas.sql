insert into user values(1, 'admin','admin', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','xwh123456@oocl.com','15903242354','1');
insert into user values(2, 'admin1','admin1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','leon1590432@oocl.com','13657393405','1');
insert into user values(3, 'boy1','boy1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','guoabby@oocl.com','18923453465','1');
insert into user values(4, 'boy2','boy2', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','dylan@oocl.com','12398340543','1');

insert into leaving(id, employee_id, start_date, end_date, reason, status, comment, create_date) values(1, 3, now(), now(), '生病了', 'pending', '', now());
insert into leaving(id, employee_id, start_date, end_date, reason, status, comment, create_date) values(2, 3, now(), now(), '喝喜酒', 'approval', '同意请假', now());
insert into leaving(id, employee_id, start_date, end_date, reason, status, comment, create_date) values(3, 3, now(), now(), '喝喜酒', 'rejected', '拒绝', now());



