package com.iouseph.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.iouseph.NetworkWrapper;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

public class IousephClient {
	private final String host = "http://localhost:8000";
	private String access_token = "";

	private IParser parser;

	/**
	 * Default constructor
	 */
	public IousephClient() {
		this.parser = new IousephParser();
	}

	public String retreive_token(){
		// TODO creer un Oauth dans une prochaine version
		return access_token;
	}


	/**
	 * @see com.iouseph.api.Iapi#connect(String, String, String)
	 */
	public User connect(String type, String username, String pwd){
		String url = host + "/"+ type ;
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("username", username));
		body_args.add(new BasicNameValuePair("pwd", pwd));
		return this.parser.userParse(NetworkWrapper.post(url,body_args));
	}


	/**
	 * @see com.iouseph.api.Iapi#get_search(java.lang.String)
	 */
	public List<Track> get_search(String search) {
		String url = host + "/track/" + NetworkWrapper.encode(search);// +
													// "&index=0&limit=5";//me?oauth_token="
													// + token;
		return this.parser.tracksParse(NetworkWrapper.get_array(url));
	}

	/**
	 * recupere les playlists de l'utilisateur connecte
	 *
	 * @param user
	 * 			l'utilisateur
	 * @return
	 * 			un {@link Map} de Playlist
	 */
	public Map<String, Playlist> getUserPlaylists(User user){
		String url = host + "/playlists/" + user.getId();
		return this.parser.playlistsParse(NetworkWrapper.get_array(url));
	}

	/**
	 * recupere une playlist avec son id
	 *
	 * @param user
	 * 			l'utilisateur connecte
	 * @param id
	 * 			l'id de la playlist
	 * @return
	 * 			{@link Playlist}
	 */
	public Playlist getPlaylist(User user, String id){
		String url = host + "/playlist/" + user.getId() + "/" + id;
		return this.parser.playlistParse(NetworkWrapper.get(url));
	}

	/**
	 * supprime une playlist du compte de l'utilisateur sur le serveur
	 *
	 * @param user
	 * 			l'utilisateur connecte
	 * @param playlist
	 * 			la playlist a supprimer
	 * @return
	 * 			un message de validation ou d'erreur
	 */
	public String deletePlaylist(User user, Playlist playlist){
		String url = host + "/playlist/delete/" + user.getId() + "/" + playlist.getId();
		return this.parser.messageParse(NetworkWrapper.get(url));
	}


	/**
	 * ajout une Playlist au compte de l'utilisateur sur le serveur
	 *
	 * @param user
	 * 				l'utilisateur connecté
	 * @param playlist
	 * 				la playlist a sauvegarder
	 * @return
	 * 				un message de validation ou d'erreur
	 */
	public String addPlaylist(User user, Playlist playlist){
		String url = host + "/playlist/create";
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("user_id", user.getId()));
		body_args.add(new BasicNameValuePair("playlist_id", playlist.getId()));
		body_args.add(new BasicNameValuePair("title", playlist.getTitle()));
		return this.parser.messageParse(NetworkWrapper.post(url, body_args));
	}

	/**
	 * supprime un Track d'une playlist du compte sur le serveur
	 *
	 * @param user
	 * 				l'utilisateur connecté
	 * @param playlist
	 * 				la Playlist selectionnée
	 * @param track
	 * 				le Track a supprimer
	 * @return
	 * 				un message de validation ou d'erreur
	 */
	public String deleteTrack(User user, Playlist playlist, Track track){
		String url = host + "/playlist/delete/" + user.getId() + "/" + playlist.getId() + "/" + track.getId();
		return this.parser.messageParse(NetworkWrapper.get(url));
	}

	/**
	 * ajoute un Track a une playlist sur le serveur
	 *
	 * @param user
	 * 				l'utilisateur connecté
	 * @param playlist
	 * 				la Playlist selectionnée
	 * @param track
	 * 				le Track a ajouter
	 * @return
	 * 				un message de validation ou d'erreur
	 */
	public String addTrackToPlaylist(User user, Playlist playlist, Track track){
		String url = host + "/playlist/addtrack";
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("user_id", user.getId()));
		body_args.add(new BasicNameValuePair("playlist_id", playlist.getId()));
		body_args = addTrackToBodyArgs(body_args, track);
		return this.parser.messageParse(NetworkWrapper.post(url,body_args));
	}

	private List<NameValuePair> addTrackToBodyArgs(List<NameValuePair> body_args, Track track) {
		body_args.add(new BasicNameValuePair("id", track.getId()));
		body_args.add(new BasicNameValuePair("album", track.getAlbum()));
		body_args.add(new BasicNameValuePair("artist", track.getArtist()));
		body_args.add(new BasicNameValuePair("externalUrl", track.getExternalUrl()));
		body_args.add(new BasicNameValuePair("image", track.getImage()));
		body_args.add(new BasicNameValuePair("source", track.getSource()));
		body_args.add(new BasicNameValuePair("title", track.getTitle()));
		return body_args;
	}

	/**
	 * crée une nouvelle playlist sur le compte utilisateur sur le serveur
	 *
	 * @param user
	 * 				l'utilisateur connecté
	 * @param title
	 * 				le nom de la Playlist
	 * @return
	 * 				un message de validation ou d'erreur
	 */
	public String createPlaylist(User user, String title){
		String url = host + "/playlist/create/";
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("userId", user.getId()));
		body_args.add(new BasicNameValuePair("title", title));
		// TODO le track
		return this.parser.messageParse(NetworkWrapper.post(url,body_args));
	}


}
