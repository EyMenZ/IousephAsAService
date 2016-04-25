package com.iouseph.model;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class User {

	private StringProperty id;
	private StringProperty username;
	private StringProperty password;
	private MapProperty<String, Playlist> playlists;

	/**
	 * constructeur par defaut
	 */
	public User() {
		this(new String(), new String(), new String(), new HashMap<String, Playlist>());
	}

	/**
	 * Constructeur avec parametres
	 *
	 * @param username
	 * 				le nom d'utilisateur
	 * @param password
	 * 				le mot de passe de l'utilisateur
	 */
	public User(String username, String password){
		this(new String(), username, password, new HashMap<String, Playlist>());
	}

	/**
	 * constructeur avec tous les parametres
	 *
	 * @param id
	 * 				l'identifient de l'utilisateur
	 * @param username
	 * 				le nom d'utilisateur
	 * @param password
	 * 				le mot de passe de l'utilisateur
	 * @param playlists
	 * 				un {@link HashMap} des Playlist de l'utilisateur
	 */
	public User(String id, String username, String password, HashMap<String, Playlist> playlists) {
		this.id = new SimpleStringProperty(id);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.playlists = new SimpleMapProperty<String, Playlist>(
				FXCollections.observableMap(new HashMap<String, Playlist>()));
	}

	/**
	 * methode retournant l'id de l'utilisateur
	 *
	 * @return
	 */
	public String getId() {
		return id.get();
	}

	/**
	 * methode permettant d'attribuer un id a l'utilisateur
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id.set(id);
	}

	/**
	 * methode permettant de retourner une playlist selon son id
	 *
	 * @param playlistId
	 * @return playlist ou null si playlist non trouve
	 */
	public Playlist getPlaylist(String playlistId) {
		return playlists.get(playlistId);
	}

	/**
	 * methode permettant d'ajouter une playlist au playlists de l'utilisateur
	 *
	 * @param playlist
	 * @return true si la playlist a ete ajouter, false sinon
	 */
	public boolean addPlaylist(Playlist playlist) {
		if (!playlists.containsKey(playlist)) {
			playlists.put(playlist.getTitle(), playlist);
			return true;
		}
		return false;
	}

	/**
	 * methode permettant de supprimer une playlist selon son id
	 *
	 * @param idPlaylist
	 * @return true si la playlist a ete supprime, false sinonz
	 */
	public boolean deletePlaylist(String idPlaylist) {
		if (!playlists.containsKey(idPlaylist)) {
			playlists.remove(idPlaylist);
			return true;
		}

		return false;
	}

	/**
	 * methode permettant de vider les playlist de l'utilisateur
	 */
	void emptyPlaylists() {
		playlists.clear();
	}

	/**
	 * accesseur au nom d'utilisateur
	 *
	 * @return
	 */

	public String getUsername() {
		return username.get();
	}

	/**
	 * setter du nom d'utilisateur
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username.set(username);
	}

	/**
	 * getter du mot de passe
	 *
	 * @return
	 */

	public String getPassword() {
		return password.get();
	}

	/**
	 * setter du mot de passe
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password.set(password);
	}

	public Map<String, Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Map<String, Playlist> playlists) {
		this.playlists = new SimpleMapProperty<String, Playlist>(
				FXCollections.observableMap(new HashMap<String, Playlist>()));
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", playlists=" + playlists
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


}
