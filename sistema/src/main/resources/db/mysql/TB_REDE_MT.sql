CREATE TABLE TB_REDE_MT (
  ID_REDE_MT     int(11) NOT NULL AUTO_INCREMENT, 
  ID_SUBESTACAO  int(11) NOT NULL, 
  CODIGO         varchar(5) NOT NULL UNIQUE, 
  NOME           varchar(100), 
  TENSAO_NOMINAL decimal(5, 2), 
  PRIMARY KEY (ID_REDE_MT));