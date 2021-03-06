package com.iouseph.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * @author youssef zemmahi, aymen zalila, marcial lopez ferrada
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
	private String id;
	private String username;
	private String password;
	private Map<String,Playlist> playlists;

	/**
	 * constructeur par defaut
	 */
	public User()
	{
		this(new String(), new String());
	}

	public User(String username, String password){
		this(username, password, new HashMap<String, Playlist>());
	}

	@SuppressWarnings("static-access")
	public User(String username, String password, Map<String,Playlist> playlists){
		id= uid.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.playlists = playlists;
	}

	/**
	 * methode retournant l'id de l'utilisateur
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * methode permettant d'attribuer un id a l'utilisateur
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * methode permettant de retourner une playlist selon son id
	 * @param playlistId
	 * @return playlist ou null si playlist non trouve
	 */
	public Playlist getPlaylist(String playlistId)
	{
		System.out.println(playlists);
		Playlist playlist = playlists.get(playlistId);
		System.out.println(playlist);
		return playlists.get(playlistId);
	}

	/**
	 * methode permettant d'ajouter une playlist au playlists de l'utilisateur
	 * @param playlist
	 * @return true si la playlist a ete ajouter, false sinon
	 */
	public boolean addPlaylist(Playlist playlist)
	{
		System.out.println(playlist);
		System.out.println(playlists);
		System.out.println(playlists.size());
		if(!playlists.containsKey(playlist.getId()))
		{
			playlists.put(playlist.getId(), playlist);
			return true;
		}
		return false;
	}

	/**
	 * Fonction permettant de creer une nouvelle playlist
	 * @param title
	 * @return true si la playlist a bien ete cree, false sinon
	 */
	public boolean addPlaylist(String id, String title)
	{
		if(!playlists.containsKey(title))
		{
			Playlist playlist = new Playlist();
			playlist.setId(id);
			playlist.setTitle(title);
			playlist.setOwner(this.username);
			playlist.setSource("Iouseph");
			playlists.put(id, playlist);
			return true;
		}
		return false;
	}

	/**
	 * methode permettant de supprimer une playlist selon son id
	 * @param idPlaylist
	 * @return true si la playlist a ete supprime, false sinonz
	 */
	public boolean deletePlaylist(String idPlaylist)
	{
		if(playlists.containsKey(idPlaylist))
		{
			playlists.remove(idPlaylist);
			return true;
		}

		return false;
	}

	/**
	 * methode permettant de vider les playlist de l'utilisateur
	 */
	void emptyPlaylists()
	{
		playlists.clear();
	}

	/**
	 * accesseur au nom d'utilisateur
	 * @return
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * setter du nom d'utilisateur
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * getter du mot de passe
	 * @return
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * setter du mot de passe
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * methode permettant de recuperer les playlists de l'utilisateur
	 * @return
	 */
	public Map<String, Playlist> getPlaylists() {
		return playlists;
	}
	/**
	 * methode permettant de setter les playlists
	 * @param playlists
	 */
	public void setPlaylists(Map<String, Playlist> playlists)
	{
		this.playlists=playlists;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password
				+ ", playlists=" + playlists + "]";
	}
	/**
	 * methode permettant de cloner un object user sans son mot de passe
	 */
	public User clone(){
	   User user=new User();
	   user.setId(id);
	   user.setUsername(username);
	   user.setPassword("");
	   user.setPlaylists(new HashMap<String,Playlist> (playlists));
	   return user;
	}

}
