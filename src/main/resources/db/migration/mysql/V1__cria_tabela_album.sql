CREATE TABLE album (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NULL,
  PRIMARY KEY (id)
) engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO album (nome) VALUES ('Dark side of the moon');
INSERT INTO album (nome) VALUES ('The wall');
INSERT INTO album (nome) VALUES ('Wish you were here');