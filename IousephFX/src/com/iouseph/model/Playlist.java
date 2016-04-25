package com.iouseph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Playlist {

	private static final long serialVersionUID = 1L;

	/**
	 * generateur d'identifient
	 */
	private UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
	private StringProperty id;
	private StringProperty title;
	private StringProperty owner;
	private StringProperty source;
	private StringProperty url;
	private ListProperty<Track> tracks;

	/**
	 * Constructeur par defaut
	 */
	public Playlist() {
		this.id = new SimpleStringProperty(uid.randomUUID().toString());
		this.title = new SimpleStringProperty(new String());
		this.owner = new SimpleStringProperty(new String());
		this.source = new SimpleStringProperty("Iouseph");
		this.tracks = new SimpleListProperty<Track>(javafx.collections.FXCollections.observableList(new ArrayList<Track>()));
		this.url = new SimpleStringProperty(new String());
	}


	/**
	 * constructeur avec parametres
	 *
	 * @param id
	 * 			l'identifient de la Playlist
	 * @param title
	 * 			le nom de la Playlist
	 * @param owner
	 * 			le nom du createur de la Playlist
	 * @param source
	 * 			le service dans lequel la Playlist a ete créée
	 * @param tracks
	 * 			la liste des Tracks de la Playlist
	 * @param url
	 * 			lien direct vers la Playlist
	 */
	public Playlist(String id, String title, String owner, String source,
			List<Track> tracks, String url) {
		this.id = new SimpleStringProperty(id);
		this.title = new SimpleStringProperty(title);
		this.owner = new SimpleStringProperty(owner);
		this.source = new SimpleStringProperty(source);
		this.tracks = new SimpleListProperty<Track>(javafx.collections.FXCollections.observableList(new ArrayList<Track>()));
		this.url=new SimpleStringProperty(url);
	}

	/**
	 * @return
	 * 			la liste de Tracks contenue dans la Playlist
	 */
	public List<Track> getTracks() {
		return tracks.get();
	}

	/**
	 * ajoute un Track dans la Playlist
	 *
	 * @param track
	 * 				le Track à ajouter
	 * @return
	 * 				un boolean de confirmation
	 */
	public boolean addTrack(Track track)
	{
		if(!tracks.contains(track)) {
			tracks.add(track);
			return true;
		}
		return false;
	}

	/**
	 * supprime un Track de la Playlist
	 *
	 * @param track
	 * 				le Track a supprimer
	 * @return
	 * 				un boolean de confirmation
	 */
	public boolean deleteTrack(Track track){
		return tracks.remove(track);
	}

	/**
	 * vide la Playlist
	 */
	public void clearPlaylist()
	{
		tracks.clear();
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getOwner() {
		return owner.get();
	}

	public void setOwner(String owner) {
		this.owner.set(owner);
	}

	public String getSource() {
		return source.get();
	}

	public void setSource(String source) {
		this.source.set(source);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	@Override
	public String toString() {
		return /*"Playlist [id=" + id + ", title=" + title + ", owner=" + owner
				+ ", source=" + source + ", tracks=" + tracks + "]"*/title.get();
	}

	public void setTracks(List<Track> tracks) {
		this.tracks.setAll(tracks);
	}

	public String getUrl() {
		return url.get();
	}

	public void setUrl(String myurl) {
		this.url.set(myurl);
	}
}
