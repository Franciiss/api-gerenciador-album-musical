package com.projetoAlbumMusical.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "musica")
public class Musica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "musica_id")
	private Integer musicaId;

	@Column(name = "nome")
	private String nome;

	@Column(name = "duracao")
	private Double duracao;

	@ManyToOne
	@JoinColumn(name = "album_id", nullable = false)
	@JsonBackReference
	private Album album;

	@ManyToMany(mappedBy = "musicas")
	private Set<Artista> artistas = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((musicaId == null) ? 0 : musicaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Musica other = (Musica) obj;
		if (musicaId == null) {
			if (other.musicaId != null)
				return false;
		} else if (!musicaId.equals(other.musicaId))
			return false;
		return true;
	}

	public Integer getMusicaId() {
		return musicaId;
	}

	public void setMusicaId(Integer musicaId) {
		this.musicaId = musicaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getDuracao() {
		return duracao;
	}

	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Set<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(Set<Artista> artistas) {
		this.artistas = artistas;
	}
}
