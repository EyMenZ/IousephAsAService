package com.iouseph.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.iouseph.NetworkWrapper;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

public class IousephClient implements Iapi {
	private final String host = "http://localhost:8000";
	private String access_token = "";

	private IParser parser;

	public IousephClient() {
		this.parser = new IousephParser();
	}

	public String retreive_token(){
		// TODO creer un Oauth dans une prochaine version
		return access_token;
	}

	public User connect(String type, String username, String pwd){
		String url = host + "/"+ type ;
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("username", username));
		body_args.add(new BasicNameValuePair("pwd", pwd));
		return this.parser.userParse(NetworkWrapper.post(url,body_args));
		}


	public User get_personnal_info() {
		String url = host + "/infos";// me?oauth_token=" + token;
		return this.parser.userParse(NetworkWrapper.get(url));
	}


	/**
	 * @see com.iouseph.api.model.Iapi#get_search(java.lang.String)
	 */
	public List<Track> get_search(String search) {
		String url = host + "/track/" + NetworkWrapper.encode(search);// +
													// "&index=0&limit=5";//me?oauth_token="
													// + token;
		return this.parser.tracksParse(NetworkWrapper.get_array(url));
	}

	/**
	 * retourne les informations de l'utilisateur connecte
	 *
	 * @param user_id	l'id du user dans Iouseph
	 * @return un JSONObject contenant les informations de l'utilisateur
	 */
	public User get_user_info(String user_id) {
		String url = host + "user/" + NetworkWrapper.encode(user_id);// + "/playlists";// +
												// "?client_id=" + client_id;
		return this.parser.userParse(NetworkWrapper.get(url));
	}

	/**
	 *	retourne une liste de playlists
	 *
	 * @param search	le String a rechercher dana Iouseph
	 * @return une List<Playlist> contenant une liste de playlists
	 * @see com.iouseph.api.model.Iapi#get_playlists(java.lang.String)
	 */
	public List<Playlist> get_playlists(String search) {
		String url = host + "/search/playlist?q=" + NetworkWrapper.encode(search);
		return (List<Playlist>) this.parser.playlistsParse(NetworkWrapper.get_array(url));//FIXME
	}

	/*
	 * retourne la liste des tracks de la playlist
	 *
	 * @see iouseph.api.model.Iapi#get_playlist(java.lang.String)
	 */
	@Override
	public List<Track> get_playlist(String playlist_id) {
		String url = host + "/playlist/" + NetworkWrapper.encode(playlist_id) + "/tracks";
		return this.parser.playlistIdParse(NetworkWrapper.get(url));
	}

	public Track get_track(String track_id) {
		String url = host + "track/" + NetworkWrapper.encode(track_id);
		return this.parser.trackParse(NetworkWrapper.get(url));
	}

	@Override
	public List<Track> get_tracks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean set_playlists(List<Playlist> playlists) {
		// TODO Auto-generated method stub
		return true;
	}

}
