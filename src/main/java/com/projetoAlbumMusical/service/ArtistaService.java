package com.projetoAlbumMusical.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
public class ArtistaService {

	private final ArtistaRepository artistaRepository;
	private final AlbumRepository albumRepository;
	private final MusicaRepository musicaRepository;

	private final GenericoService<Artista> genericoService;

	@Autowired
	public ArtistaService(ArtistaRepository artistaRepository, AlbumRepository albumRepository,
			MusicaRepository musicaRepository) {
		this.albumRepository = albumRepository;
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
		this.genericoService = new GenericoService<Artista>(artistaRepository);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Artista salva(Artista artista) {
		validaAlbums(artista.getAlbums());
		validaMusicas(artista.getMusicas());
		return this.artistaRepository.save(artista);
	}

	private void validaAlbums(Set<Album> albums) {
		if (albums != null && !albums.isEmpty()) {

			albums.forEach(album -> {

				Integer id = album.getAlbumId();

				if (id == null) {
					throw new IllegalArgumentException("O id do album não pode ser nulo");
				}

				Optional<Album> optional = albumRepository.findById(id);
				album = optional.orElseThrow(() -> new IllegalArgumentException("Album Inválido " + id));
			});
		}
	}

	private void validaMusicas(Set<Musica> musicas) {
		if (musicas != null && !musicas.isEmpty()) {

			musicas.forEach(musica -> {

				Integer id = musica.getMusicaId();

				if (id == null) {
					throw new IllegalArgumentException("O id da musica não pode ser nulo");
				}

				Optional<Musica> optional = musicaRepository.findById(id);
				musica = optional.orElseThrow(() -> new IllegalArgumentException("Musica Inválida " + id));
			});
		}
	}

	@Transactional(readOnly = true)
	public List<Artista> obterTodosArtistas() {
		return genericoService.buscaTodasAsEntities();
	}

	Optional<Artista> buscaPor(String nome) {
		return Optional.ofNullable(artistaRepository.findByNome(nome));
	}

	public Artista buscaPor(Integer id) {
		Optional<Artista> optionalArtista = artistaRepository.findById(id);
		return optionalArtista.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Transactional
	public void excluir(Integer id) {
		artistaRepository.deleteById(id);
	}

	@Transactional
	public Artista atualiza(Integer id, Artista artista) {
		Artista artistaManager = this.buscaPor(id);

		if (artistaManager == null) {

			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(artista, artistaManager, "id");

		this.salva(artistaManager);

		return artistaManager;
	}
}
