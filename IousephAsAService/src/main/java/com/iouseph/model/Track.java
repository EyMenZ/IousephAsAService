package com.iouseph.model;

import java.io.Serializable;

public class Track implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String externalUrl;
	private float duration;
	private String artist;
	private String album;
	private String image;
	private String source;

	public Track() {
		this(null, null, null, 0, null, null, null, null);
	}



	public Track(String id, String title, String externalUrl, float duration,
			String artist, String album, String image, String source) {
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



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return /*"Track [id=" + id + ", title=" + title + ", externalUrl=" + externalUrl + ", duration="
				+ duration + ", artist=" + artist + ", album=" + album + ", image=" + image
				+ ", source=" + source + "]"*/source + "-" + title+"\n "+ artist+"\n "+album;
	}

}
