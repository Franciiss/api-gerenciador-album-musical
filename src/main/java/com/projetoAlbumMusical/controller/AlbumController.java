package com.projetoAlbumMusical.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoAlbumMusical.model.Album;
import com.projetoAlbumMusical.service.AlbumService;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

	private final AlbumService albumService;

	@Autowired
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@PostMapping
	public ResponseEntity<?> cria(@Validated @RequestBody Album album) {

		Album albumSalvo = albumService.salva(album);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(albumSalvo.getAlbumId()).toUri();

		return ResponseEntity.created(uri).body(albumSalvo);

	}

	@GetMapping
	public ResponseEntity<?> listaAlbums() {

		List<Album> albums = this.albumService.obterTodosAlbums();

		if (albums.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(albums);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Album> atualiza(@PathVariable Integer id, @Validated @RequestBody Album album) {
		Album categoriaManager = albumService.atualiza(id, album);
		return ResponseEntity.ok(categoriaManager);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void excluir(@PathVariable Integer id) {
		albumService.excluir(id);
	}

}
