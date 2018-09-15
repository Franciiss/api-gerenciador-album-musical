package com.projetoAlbumMusical.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoAlbumMusical.model.Artista;
import com.projetoAlbumMusical.service.ArtistaService;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistaController {

	private final ArtistaService artistaService;

	@Autowired
	public ArtistaController(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}

	@GetMapping
	public ResponseEntity<?> listaAlbums() {

		List<Artista> albums = this.artistaService.obterTodosArtistas();

		if (albums.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(albums);
		}
	}

	@PostMapping
	public ResponseEntity<?> cria(@Validated @RequestBody Artista artista) {

		Artista artistaSalvo = artistaService.salva(artista);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(artistaSalvo.getArtistaId()).toUri();

		return ResponseEntity.created(uri).body(artistaSalvo);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Artista> atualiza(@PathVariable Integer id, @Validated @RequestBody Artista artista) {
		Artista categoriaManager = artistaService.atualiza(id, artista);
		return ResponseEntity.ok(categoriaManager);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void excluir(@PathVariable Integer id) {
		artistaService.excluir(id);
	}
}
