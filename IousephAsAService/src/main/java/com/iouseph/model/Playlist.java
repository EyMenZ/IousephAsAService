package com.iouseph.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Playlist implements Serializable{

	private String idUser_;
	private static final long serialVersionUID = 1L;
	private UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
	private String id;
	private String title;
	private String owner;
	private String source;
	private String url;
	private List<Track> tracks;
	private User user;

	/**
	 * Constructeur par defaut
	 */
	public Playlist() {
		String tempId=uid.randomUUID().toString();
		this.title = null;
		this.owner = null;
		this.source = null;
		this.tracks = new ArrayList<Track>();
		this.url=null;
	}
/**
 * Constructeur par parametres
 * @param id
 * @param title
 * @param owner
 * @param source
 * @param tracks
 * @param url
 */
	public Playlist(String id, String title, String owner, String source,
			List<Track> tracks, String url) {
		super();
		this.id = id;
		this.title = title;
		this.owner = owner;
		this.source = source;
		this.tracks = new ArrayList<Track>();
		this.url=url;
	}



	/**
	 * Cette methode retourne la liste des tracks contenue dans la playlist
	 * @return
	 */
	public List<Track> getTracks() {
		return tracks;
	}

	/**
	 * Cette methode permet l'ajout d'un track au contenu de la playlist
	 * @param track
	 * @return true en cas de succes, false sinon
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
	 * Methode permettant de supprimer un track de la playlist
	 * @param track
	 * @return true si le track a ete supprime, false sinon
	 */
	public boolean deleteTrack(Track track){
		return tracks.remove(track);
	}
	/**
	 * Cette methode permet de supprimer un track selon son id
	 * @param TrackId
	 * @return True si le track a ete supprime, false sinon
	 */
	public boolean deleteTrack(String trackId)
	{
		for(int i=0;i<tracks.size();i++)
		{
			if(tracks.get(i).getId().contentEquals(trackId))
			{
				tracks.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * Methode permettant de reinitialier la playlist
	 */
	public void clearPlaylist()
	{
		tracks.clear();
	}

	/**
	 * la methode renvoit le titre de la playlist
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * methode permet d'attribuer un titre a la playlist
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * methode qui retourne l'owner de la playlist
	 * @return
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * methode permetant d'attribuer la valeur de l'owner de la playlist
	 * @param owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * methode permettant de retourner la source de la playlist
	 * @return
	 */
	public String getSource() {
		return source;
	}
	/**
	 * methode permettant de specifier la source de la playlist
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * methode retournant l'id de la playlist
	 * @return
	 */

	public String getId() {
		return id;
	}

	/**
	 *methode permettant d'attribuer un id a la playlist
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return /*"Playlist [id=" + id + ", title=" + title + ", owner=" + owner
				+ ", source=" + source + ", tracks=" + tracks + "]"*/title;
	}
	/**
	 * methode permettant d'attribuer une list de tracks a la playlist
	 * @param tracks
	 */
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	/**
	 * methode permettant de recuperer l'url permettant de lancer la lecture de la playlist
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * methode permettant d'attribuer la valeur de l'url qui permet la lecture de la playlist
	 * @param myurl
	 */
	public void setUrl(String myurl) {
		this.url = myurl;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * methode permettant la recuperation de l'id de l'utilisateur proprietaire de la playlist
	 * @return
	 */
	public String getIdUser() {
		return idUser_;
	}

	/**
	 * methode permettant de specifier la valeur de l'id du proprietaire de la playlist
	 * @param idUser
	 */
	public void setIdUser(String idUser) {
		this.idUser_ = idUser;
	}



}
