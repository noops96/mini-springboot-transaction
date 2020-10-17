create table cart(id serial primary key,
buyer_id integer ,
created_at timestamp);

create table cart_item(id serial primary key,
product_id integer ,
cart_id integer,
created_at timestamp,
qty integer ,
constraint fk foreign key (cart_id) references cart(id));

create table invoice(id serial primary key,
buyer_id integer ,
buyer_name varchar(255),
prcs_date timestamp ,
created_at timestamp,
tot_price numeric);

create table invoice_detail(id serial primary key,
seller_id integer ,
invoice_id integer,
product_id integer,
product_name varchar(255),
seller_name varchar(255),
created_at timestamp,
price numeric,
qty integer,
constraint fk foreign key (invoice_id) references invoice(id));