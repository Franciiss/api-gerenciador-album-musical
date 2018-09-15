package com.projetoAlbumMusical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoAlbumMusical.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer> {

	Musica findByNome(String nome);

}
