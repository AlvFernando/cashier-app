CREATE TABLE IF NOT EXISTS UNITTYPE(
    id bigint primary key auto_increment,
    unitType VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS PAYMENTMETHOD(
    id bigint primary key auto_increment,
    paymentmethod varchar(20)
);

CREATE TABLE IF NOT EXISTS item ( 
    id bigint primary key auto_increment, 
    itemName varchar(50), 
    itemPrice int, 
    itemQty int, 
    UNITTYPEID BIGINT NOT NULL REFERENCES UNITTYPE(id),
    UUID VARCHAR(40),
    createdAt datetime, 
    updatedAt datetime 
); 

CREATE TABLE IF NOT EXISTS transactionHeader( 
    id bigint primary key auto_increment, 
    transactionDate datetime,
    payment int,
    paymentmethodid BIGINT NOT NULL REFERENCES PAYMENTMETHOD(id),
    UUID VARCHAR(40)
); 

CREATE TABLE IF NOT EXISTS transactionDetail( 
    id bigint primary key auto_increment, 
    transactionHeaderId bigint not null, 
    itemId bigint not null, 
    amount int, foreign key(transactionHeaderId) references transactionHeader(id), 
    foreign key(itemId) references item(id) 
);

CREATE TABLE IF NOT EXISTS productkey(
    id bigint primary key auto_increment,
    productKey varchar(10),
    isActive boolean
);