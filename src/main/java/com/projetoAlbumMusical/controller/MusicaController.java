package com.projetoAlbumMusical.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoAlbumMusical.model.Musica;
import com.projetoAlbumMusical.service.MusicaService;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

	private final MusicaService musicaService;

	@Autowired
	public MusicaController(MusicaService musicaService) {
		this.musicaService = musicaService;
	}

	@GetMapping
	public List<Musica> listaDeMusicas() {
		return musicaService.obterTodasMusicas();
	}

	@PostMapping
	public ResponseEntity<?> cria(@Validated @RequestBody Musica musica) {

		Musica musicaSalvo = musicaService.salva(musica);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(musicaSalvo.getAlbum()).toUri();

		return ResponseEntity.created(uri).body(musicaSalvo);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Musica> atualiza(@PathVariable Integer id, @Validated @RequestBody Musica musica) {
		Musica categoriaManager = musicaService.atualiza(id, musica);
		return ResponseEntity.ok(categoriaManager);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		musicaService.excluir(id);
		return ResponseEntity.ok(true);
	}

}
