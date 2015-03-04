insert into country (id, code,name) values (1,'DE', 'Germany');

insert into mdm_table (id,name,display_name) values (1,'COUNTRY','Country');
insert into mdm_column(id,name,display_name,data_type,table_id) values (2,'CODE','Code','STRING',1);
insert into mdm_column(id,name,display_name,data_type,table_id) values (3,'Name','Name','STRING',1);
insert into mdm_column(id,name,display_name,data_type,table_id,is_primary_key) values(4,'Id','Id','INTEGER',1,true);
COMMIT;