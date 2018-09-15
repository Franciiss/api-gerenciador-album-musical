package com.projetoAlbumMusical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoAlbumMusical.model.Album;
import com.projetoAlbumMusical.model.Artista;
import com.projetoAlbumMusical.model.Musica;
import com.projetoAlbumMusical.repository.AlbumRepository;
import com.projetoAlbumMusical.repository.ArtistaRepository;
import com.projetoAlbumMusical.repository.MusicaRepository;

@Service
public class MusicaService {

	private final MusicaRepository musicaRepository;
	private final AlbumRepository albumRepository;
	private final ArtistaRepository artistaRepository;

	private final GenericoService<Musica> genericoService;

	@Autowired
	public MusicaService(AlbumRepository albumRepository, MusicaRepository musicaRepository,
			ArtistaRepository artistaRepository) {
		this.albumRepository = albumRepository;
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
		this.genericoService = new GenericoService<Musica>(musicaRepository);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Musica salva(Musica musica) {
		validaArtistas((List<Artista>) musica.getArtistas());
		validaAlbums(musica.getAlbum());
		return this.musicaRepository.save(musica);
	}

	private void validaArtistas(List<Artista> artistas) {
		if (artistas != null && !artistas.isEmpty()) {

			artistas.forEach(artista -> {

				Integer id = artista.getArtistaId();

				if (id == null) {
					throw new IllegalArgumentException("O id do artista não pode ser nulo");
				}

				Optional<Artista> optional = artistaRepository.findById(id);
				artista = optional.orElseThrow(() -> new IllegalArgumentException("Artista Inválido " + id));
			});
		}
	}

	private void validaAlbums(Album album) {
		if (album != null) {

			Integer albumId = album.getAlbumId();

			if (albumId == null) {
				throw new IllegalArgumentException("O id do album não pode ser nulo");
			}

			Optional<Album> optional = albumRepository.findById(albumId);
			album = optional.orElseThrow(() -> new IllegalArgumentException("Album Inválido " + albumId));
		}
	}

	Optional<Musica> buscaPor(String nome) {
		return Optional.ofNullable(musicaRepository.findByNome(nome));
	}

	public Musica buscaPor(Integer id) {
		Optional<Musica> optionalMusica = musicaRepository.findById(id);
		return optionalMusica.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Transactional(readOnly = true)
	public List<Musica> obterTodasMusicas() {
		return genericoService.buscaTodasAsEntities();
	}

	@Transactional
	public void excluir(Integer id) {
		musicaRepository.deleteById(id);
	}

	@Transactional
	public Musica atualiza(Integer id, Musica musica) {
		Musica musicaManager = this.buscaPor(id);

		if (musicaManager == null) {

			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(musica, musicaManager, "id");

		this.salva(musicaManager);

		return musicaManager;
	}
}
