package com.iouseph.model;

import java.io.Serializable;

public class Track implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String externalUrl;
	private float duration;
	private String artist;
	private String album;
	private String image;
	private String source;

	/**
	 * contructeur par defaut
	 */
	public Track() {
		this(null, null, null, 0, null, null, null, null);
	}

	/**
	 * contructeur par parametres
	 *
	 * @param id
	 * @param title
	 * @param externalUrl
	 * @param duration
	 * @param artist
	 * @param album
	 * @param image
	 * @param source
	 */
	public Track(String id, String title, String externalUrl, float duration, String artist, String album, String image,
			String source) {
		super();
		this.id = id;
		this.title = title;
		this.externalUrl = externalUrl;
		this.duration = duration;
		this.artist = artist;
		this.album = album;
		this.image = image;
		this.source = source;
	}

	/**
	 * methode permettant de recuperer l'id du track
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * methode permettant de setter l'id du track
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * method permettant de recuperer le titre de l'album
	 *
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * methode permettant de setter le titre de l'album
	 *
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Methode permettant de retourner le lien externe vers le player qui joue
	 * le track
	 *
	 * @return
	 */
	public String getExternalUrl() {
		return externalUrl;
	}

	/**
	 * methode permettant de setter le lien externe vers le player qui joue le
	 * track
	 *
	 * @param externalUrl
	 */
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	/**
	 * methode permettant de recuperer la duree du track
	 *
	 * @return
	 */
	public float getDuration() {
		return duration;
	}

	/**
	 * methode permettant de setter la duree du track
	 *
	 * @param duration
	 */
	public void setDuration(float duration) {
		this.duration = duration;
	}

	/**
	 * methode permettant de recuperer le nom de l'artiste
	 *
	 * @return
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * methode permettant de setter le nom de l'artiste
	 *
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * methode permettant de retourner le titre de l'album contenant le track
	 *
	 * @return
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * methode permettant de setter le titre de l'album contenant le track
	 *
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * method permettant de retouner le lien vers l'image de l'album
	 *
	 * @return
	 */
	public String getImage() {
		return image;
	}

	/**
	 * methode permettant de setter le lien vers l'image de l'album
	 *
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * methode permettant de recuperer la source du track
	 *
	 * @return
	 */
	public String getSource() {
		return source;
	}

	/**
	 * methode permettant de setter la source du track
	 *
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * methode permettant de retouner toutes les informations sur le track sous
	 * format string
	 */
	@Override
	public String toString() {
		return /*
				 * "Track [id=" + id + ", title=" + title + ", externalUrl=" +
				 * externalUrl + ", duration=" + duration + ", artist=" + artist
				 * + ", album=" + album + ", image=" + image + ", source=" +
				 * source + "]"
				 */source + "-" + title + "\n " + artist + "\n " + album;
	}

}
