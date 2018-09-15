package com.projetoAlbumMusical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoAlbumMusical.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

	Artista findByNome(String nome);

}
