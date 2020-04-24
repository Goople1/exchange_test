DROP TABLE IF EXISTS Client;

CREATE TABLE Client (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE DEFAULT NULL,
  fecha_creacion DATE DEFAULT NULL,
  edad INT
);


-- Crear indice por 'client_id'
-- used in tests that use HSQL
DROP TABLE IF EXISTS oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

-- Crear indice por 'authentication_id'
DROP TABLE IF EXISTS oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

-- token_id
-- authentication_id
-- user_name, client_id
-- user_name
-- client_id
-- refresh_token
DROP TABLE IF EXISTS oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

-- token_id
DROP TABLE IF EXISTS oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

-- code
DROP TABLE IF EXISTS oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

-- userId, clientId
-- userId, clientId, scope
DROP TABLE IF EXISTS oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- customized oauth_client_details table
DROP TABLE IF EXISTS ClientDetails;
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

-- The client_secret field using Bcrypt (10 iterations) and the plain value is 'password'
insert into oauth_client_details (client_id, client_secret, scope, authorities, authorized_grant_types)
 values ('trusted_client_id', '$2a$10$EPLVfWap12ZSP7SiAHY3zeANL9Jtz1y7SMwOpi8yHUNDvv5Z1JDV6','read,write', 'APPCLIENT', 'password,client_credentials');
DROP TABLE IF EXISTS users;
create table users(
	username VARCHAR(100) not null primary key,
	password VARCHAR(100) not null,
	enabled boolean not null
);
DROP TABLE IF EXISTS authorities;
create table authorities (
	username VARCHAR(100) not null,
	authority VARCHAR(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users values ('exchange', '$2a$10$odxyRRqKmEDUS64Tn75M8Ot7RblOnPPSk2waB5YJCclCI7oqSSoKy', 1);
insert into authorities values ('exchange', 'ADMIN');
DROP TABLE IF EXISTS Type_Monies;
create table Type_Monies(
    type_abbr VARCHAR(5)  PRIMARY KEY,
    symbol VARCHAR(20),
    description VARCHAR(80),
    url_imagen VARCHAR(200),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS Exchange_Monies;
create table Exchange_Monies(
id INT AUTO_INCREMENT  PRIMARY KEY,
from_one VARCHAR(5),
to_one VARCHAR(5),
rate_one VARCHAR(100),
from_two VARCHAR(5),
to_two VARCHAR(5),
rate_two VARCHAR(100),
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (from_one) REFERENCES Type_Monies(type_abbr),
FOREIGN KEY (to_one) REFERENCES Type_Monies(type_abbr),
FOREIGN KEY (from_two) REFERENCES Type_Monies(type_abbr),
FOREIGN KEY (to_two) REFERENCES Type_Monies(type_abbr),
CONSTRAINT UC_FROM_ONE UNIQUE (from_one,to_one),
CONSTRAINT UC_FROM_TWO UNIQUE (from_two,to_two)
);
insert into Type_Monies (type_abbr,symbol, description)
values
('PEN','S/', 'Nuevo Sol'),
('USD','$', 'Dolar Estadounidense'),
('EUR','€', 'Euro');

insert into Exchange_Monies(from_one, to_one, rate_one, from_two, to_two, rate_two)
values
('USD','PEN', '3.37','PEN','USD', '0.2967359050445104'),
('EUR','USD', '1.10','USD','EUR', '0.9090909090909091'),
('EUR','PEN', '3.70','PEN','EUR', '0.2702702702702703');
