CREATE TABLE artista (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NULL,
  nacionalidade VARCHAR(100) NULL,
  musica_id INT NULL,
  album_id INT NULL,
  PRIMARY KEY (id)
) engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO artista (nome, nacionalidade, album_id) VALUES ('Pink Floyd', 'Britânica', 1);
