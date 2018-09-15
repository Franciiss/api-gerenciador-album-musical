CREATE TABLE musica (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NULL,
  duracao DOUBLE NULL,
  album_id INT NULL,
  artista_id INT NULL,
  PRIMARY KEY (id)
) engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO musica (nome, duracao, album_id, artista_id) VALUES ('Eclipse', 2.01, '1', '1');
INSERT INTO musica (nome, duracao, album_id, artista_id) VALUES ('Hey you', 4.44, '2', '1' );
INSERT INTO musica (nome, duracao, album_id, artista_id) VALUES ('Wish you were here', 5.40, '3', 1);
