insert into country (id, code,name) values (1,'DE', 'Germany');
insert into country (id, code,name) values (2,'US', 'United States');
insert into country (id, code,name) values (3,'FR', 'France');
insert into country (id, code,name) values (4,'EN', 'England');
insert into country (id, code,name) values (5,'NL', 'Netherlands');
insert into country (id, code,name) values (6,'FI', 'Finland');
insert into country (id, code,name) values (7,'SE', 'Sweden');
insert into country (id, code,name) values (8,'NO', 'Norway');
insert into country (id, code,name) values (9,'AT', 'Austria');
insert into country (id, code,name) values (10,'DK', 'Denmark');
insert into country (id, code,name) values (11,'CH', 'Switzerland');
insert into country (id, code,name) values (12,'BE', 'Belgium');

insert into mdm_table (id,name,display_name) values (1,'COUNTRY','Country');
insert into mdm_table (id,name,display_name) values (2,'LOCATION','Location');
insert into mdm_table (id,name,display_name) values (3,'STATE_REGION','State Region');
insert into mdm_table (id,name,display_name) values (4,'VESSEL','Vessel');
insert into mdm_table (id,name,display_name) values (5,'BRANCH_OFFICE','Branch Office');
insert into mdm_table (id,name,display_name) values (6,'REGION','Region');
insert into mdm_table (id,name,display_name) values (7,'DEPARTMENT','Department');


insert into mdm_tag(id,name) values (1,'location');
insert into mdm_tag(id,name) values (2,'geographical');
insert into mdm_tag(id,name) values (3,'country');
insert into mdm_tag(id,name) values (4,'airfreight');
insert into mdm_tag(id,name) values (5,'searfreight');
insert into mdm_tag(id,name) values (6,'road');

insert into table_tags (table_id,tag_id) values(1,2);
insert into table_tags (table_id,tag_id) values(1,3);
insert into table_tags (table_id,tag_id) values(1,4);
insert into table_tags (table_id,tag_id) values(1,5);
insert into table_tags (table_id,tag_id) values(1,6);




insert into mdm_column(id,name,display_name,data_type,table_id) values (2,'CODE','Code','STRING',1);
insert into mdm_column(id,name,display_name,data_type,table_id) values (3,'NAME','Name','STRING',1);
insert into mdm_column(id,name,display_name,data_type,table_id,is_primary_key) values(4,'Id','Id','INTEGER',1,true);
COMMIT;