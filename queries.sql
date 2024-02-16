 create table hibernate_sequence (next_val bigint) engine=MyISAM;
 insert into hibernate_sequence values ( 1 );
 create table tbl_s3bucket_data (id bigint not null, bucket_name varchar(255), creation_date datetime, owner_id varchar(255), owner_name varchar(255), primary key (id)) engine=MyISAM;
 create table BucketFileData (id bigint not null, bucket_Name varchar(255), files varchar(255), primary key (id)) engine=MyISAM