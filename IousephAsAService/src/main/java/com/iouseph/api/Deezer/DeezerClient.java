package com.iouseph.api.Deezer;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.iouseph.api.IParser;
import com.iouseph.api.Iapi;
import com.iouseph.api.NetworkWrapper;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;


/**
 * @author EyMenZ
 *
 */
public class DeezerClient implements Iapi{

	private final String host = "https://api.deezer.com/";
	private String app_id = "171795";
	private String secret = "a460f3efd5e3e0c98af00730d882b5f0";
	private String redirect_uri = "http://localhost:9999/callback";
	private String access_token = "";

	private IParser parser;

	public DeezerClient() {
		this.parser = new DeezerParser();
	}

	/**
	 * @see com.iouseph.api.Iapi#retreive_token()
	 */
	public String retreive_token() throws Exception {

		/*
		 * https://connect.deezer.com/oauth/auth.php?app_id=YOUR_APP_ID&redirect_uri
		 * =YOUR_REDIRECT_URI&perms=YOUR_PERMS
		 * http://redirect_uri?error_reason=user_denied
		 * http://redirect_uri?code=A_CODE_GENERATED_BY_DEEZER
		 * https://connect.deezer.com/oauth/access_token.php
		 * https://connect.deezer
		 * .com/oauth/access_token.php?app_id=YOU_APP_ID&secret
		 * =YOU_APP_SECRET&code=THE_CODE_FROM_ABOVE
		 */
		String url = "https://connect.deezer.com/oauth/auth.php?";
		String perms = "basic_access,email,offline_access,manage_library,listening_history";

		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("app_id", app_id));
		body_args.add(new BasicNameValuePair("redirect_uri", redirect_uri));
		body_args.add(new BasicNameValuePair("perms", perms));
		String paramString = URLEncodedUtils.format(body_args, "utf-8");

		//url += "app_id=" + app_id + "&redirect_uri=" + redirect_uri + "&perms=" + perms;
		url += paramString;
		System.out.println(url);
		java.awt.Desktop.getDesktop().browse(new URI(url));
		Method method = null;
		String code_retrieved ="";
		NetworkWrapper.runServerToListen(9999, this, method);
		System.out.println(code_retrieved);
		url = "https://connect.deezer.com/oauth/access_token.php?";
		String[] parts = code_retrieved.split("=");
		parts = parts[1].split(" ");
		code_retrieved = parts[0];

		body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("app_id", app_id));
		body_args.add(new BasicNameValuePair("secret", secret));
		body_args.add(new BasicNameValuePair("code", code_retrieved));
		paramString = URLEncodedUtils.format(body_args, "utf-8");

		//url += "app_id=" + app_id + "&secret=" + secret + "&code="+ code_retrieved;
		url += paramString;
		JSONObject res_json = NetworkWrapper.post(url, body_args);
		access_token = res_json.getString("access_token");

		return access_token;
	}

	/**
	 * @see com.iouseph.api.Iapi#get_personnal_info()
	 */
	public User get_personnal_info() {
		String url = host +"/infos";// me?oauth_token=" + token;
		return this.parser.userParse(NetworkWrapper.get(url));
	}

	/**
	 * @see com.iouseph.api.Iapi#get_search(java.lang.String)
	 */
	public List<Track> get_search(String search) {
		String url = host + "/search?q=" + search;//FIXME NetworkWrapper.encode(search);// +
													// "&index=0&limit=5";//me?oauth_token="
													// + token;
		return this.parser.tracksParse(NetworkWrapper.get(url));
	}

	/**
	 * retourne les informations de l'utilisateur connecte
	 *
	 * @param user_id
	 * 				l'id du user dans deezer
	 * @return
	 * 				un JSONObject contenant les informations de l'utilisateur
	 */
	public User get_user_info(String user_id) {
		String url = host + "user/" + NetworkWrapper.encode(user_id);// + "/playlists";// +
												// "?client_id=" + client_id;
		return this.parser.userParse(NetworkWrapper.get(url));
	}

	/**
	 *	retourne une liste de playlists
	 *
	 * @param search
	 * 				le String a rechercher dana deezer
	 * @return
	 * 				une List<Playlist> contenant une liste de playlists
	 * @see com.iouseph.api.Iapi#get_playlists(java.lang.String)
	 */
	public List<Playlist> get_playlists(String search) {
		String url = host + "/search/playlist?q=" + NetworkWrapper.encode(search);
		return this.parser.playlistsParse(NetworkWrapper.get(url));
	}

	/**
	 * retourne la liste des tracks de la playlist
	 *
	 * @see com.iouseph.api.Iapi#get_playlist(java.lang.String)
	 */
	@Override
	public List<Track> get_playlist(String playlist_id) {
		String url = host + "/playlist/" + NetworkWrapper.encode(playlist_id) + "/tracks";
		return this.parser.playlistIdParse(NetworkWrapper.get(url));
	}

	/**
	 * @see com.iouseph.api.Iapi#get_track(String)
	 */
	public Track get_track(String track_id) {
		String url = host + "track/" + NetworkWrapper.encode(track_id);
		return this.parser.trackParse(NetworkWrapper.get(url));
	}

	/**
	 * @see com.iouseph.api.Iapi#get_tracks()
	 */
	@Override
	public List<Track> get_tracks() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.iouseph.api.Iapi#set_playlists(List)
	 */
	@Override
	public boolean set_playlists(List<Playlist> playlists) {
		// TODO Auto-generated method stub
		return true;
	}

}
