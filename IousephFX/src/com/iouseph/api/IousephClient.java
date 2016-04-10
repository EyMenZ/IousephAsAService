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


	/**
	 * @see com.iouseph.api.model.Iapi#get_search(java.lang.String)
	 */
	public List<Track> get_search(String search) {
		String url = host + "/track/" + NetworkWrapper.encode(search);// +
													// "&index=0&limit=5";//me?oauth_token="
													// + token;
		return this.parser.tracksParse(NetworkWrapper.get_array(url));
	}

	public Map<String, Playlist> getUserPlaylists(User user){
		String url = host + "/playlists/" + user.getId();
		return this.parser.playlistsParse(NetworkWrapper.get_array(url));
	}

	public Playlist getPlaylist(User user, String id){
		String url = host + "/playlist/" + user.getId() + "/" + id;
		return this.parser.playlistParse(NetworkWrapper.get(url));
	}

	public String deletePlaylist(User user, Playlist playlist){
		String url = host + "/playlist/delete/" + user.getId() + "/" + playlist.getId();
		return NetworkWrapper.get(url).toString();
	}

	public String addPlaylist(User user, Playlist playlist){
		String url = host + "/playlist/" + user.getId() + "/" + playlist.getId() + "/" + playlist.getTitle();
		return NetworkWrapper.get(url).toString();
	}

	public String deleteTrack(User user, Playlist playlist, Track track){
		String url = host + "/playlist/delete/" + user.getId() + "/" + playlist.getId() + "/" + track.getId();
		return NetworkWrapper.get(url).toString();
	}

	public String addTrackToPlaylist(User user, Playlist playlist, Track track){
		String url = host + "/playlist/addtrack/";
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("user_id", user.getId()));
		body_args.add(new BasicNameValuePair("playlist_id", playlist.getId()));
		// TODO le track
		return NetworkWrapper.post(url,body_args).toString();
	}

	public String createPlaylist(User user, String title){
		String url = host + "/playlist/create/";
		List<NameValuePair> body_args = new ArrayList<NameValuePair>();
		body_args.add(new BasicNameValuePair("userId", user.getId()));
		body_args.add(new BasicNameValuePair("title", title));
		// TODO le track
		return NetworkWrapper.post(url,body_args).toString();
	}


}
