package com.projetoAlbumMusical.repository.filter.musica;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projetoAlbumMusical.model.Musica;
import com.projetoAlbumMusical.repository.filter.MusicaFiltro;

public interface MusicaRepositoryQuery {

    List<Musica> filtrar(MusicaFiltro filtro);

    Page<Musica> filtrar(MusicaFiltro filtro, Pageable pageable);
}
