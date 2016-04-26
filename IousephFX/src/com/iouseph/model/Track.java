package com.iouseph.model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Track{

	private StringProperty id;
	private StringProperty title;
	private StringProperty externalUrl;
	private FloatProperty duration;
	private StringProperty artist;
	private StringProperty album;
	private StringProperty image;
	private StringProperty source;

	/**
	 * Constructeur par d�faut
	 */
	public Track() {
		this(null, null, null, 0, null, null, null, null);
	}

	/**
	 * constructeur avec parametres
	 *
	 * @param id
	 *				l'identifient du Track
	 * @param title
	 * 				titre de la chanson
	 * @param externalUrl
	 * 				lien direct vers la chanson
	 * @param duration
	 * 				la dur�e du morceau
	 * @param artist
	 * 				le nom de l'artiste
	 * @param album
	 * 				le nom de l'album
	 * @param image
	 * 				lien vers la couverture de l'albim
	 * @param source
	 * 				le nom du service source
	 */
	public Track(String id, String title, String externalUrl, float duration,
			String artist, String album, String image, String source) {
		super();
		this.id = new SimpleStringProperty(id);
		this.title = new SimpleStringProperty(title);
		this.externalUrl = new SimpleStringProperty(externalUrl);
		this.duration = new SimpleFloatProperty(duration);
		this.artist = new SimpleStringProperty(artist);
		this.album = new SimpleStringProperty(album);
		this.image = new SimpleStringProperty(image);
		this.source = new SimpleStringProperty(source);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getExternalUrl() {
		return externalUrl.get();
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl.set(externalUrl);
	}

	public float getDuration() {
		return duration.get();
	}

	public void setDuration(float duration) {
		this.duration.set(duration);
	}

	public String getArtist() {
		return artist.get();
	}

	public void setArtist(String artist) {
		this.artist.set(artist);
	}

	public String getAlbum() {
		return album.get();
	}

	public void setAlbum(String album) {
		this.album.set(album);
	}

	public String getImage() {
		return image.get();
	}

	public void setImage(String image) {
		this.image.set(image);
	}

	public String getSource() {
		return source.get();
	}

	public void setSource(String source) {
		this.source.set(source);
	}

	@Override
	public String toString() {
		return /*"Track [id=" + id.get() + ", title=" + title.get() + ", externalUrl=" + externalUrl.get() + ", duration="
				+ duration.get() + ", artist=" + artist.get() + ", album=" + album.get() + ", image=" + image.get()
				+ ", source=" + source.get() + "]"*/source.get()+"\n"+title.get()+"\n"+ artist.get()+"\n"+album.get();
	}

}
