package com.iouseph.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

public class IousephParser implements IParser {

	/**
	 * methode permetant de creer une instance track a partir d'un objet json
	 */
	@Override
	public Track trackParse(JSONObject json) {
		Track track = null;
		if (json != null) {
			track = new Track();
			track.setId(json.getString("id"));
			track.setTitle(json.getString("title"));
			track.setArtist(json.getString("artist"));
			track.setAlbum(json.getString("album"));
			track.setExternalUrl(json.getString("externalUrl"));
			track.setImage(json.getString("image"));
			track.setSource(json.getString("source"));
		}
		return track;
	}

	/**
	 * methode permettant de creer une liste de tracks a partir d'un objet de
	 * type jsonArray
	 */
	@Override
	public List<Track> tracksParse(JSONArray json) {
		List<Track> tracks = new ArrayList<Track>();
		if (json != null) {
			for (int i = 0; i < json.length(); i++) {
				Track track = this.trackParse(json.getJSONObject(i));
				tracks.add(track);
			}
		}
		return tracks;
	}

	/**
	 * methode permettant de creer une instance playlist a partir d'un objet de
	 * type json
	 */
	@Override
	public Playlist playlistParse(JSONObject json) {
		Playlist playlist = null;
		if (json != null) {
			playlist = new Playlist();
			playlist.setId(json.getString("id"));
			playlist.setOwner(json.getString("owner"));
			playlist.setSource(json.getString("source"));
			playlist.setTitle(json.getString("title"));
			playlist.setUrl(json.getString("externalUrl"));
			playlist.setTracks(this.tracksParse(json.getJSONArray("tracks")));
		}
		return playlist;
	}

	/**
	 * methode permettant de creer une map dont la cle correspond a l'id de la
	 * playlist et la valeur la playlist a partir d'un objet de type jsonarray
	 */
	@Override
	public Map<String, Playlist> playlistsParse(JSONArray json) {
		Map<String, Playlist> playlists = new HashMap<String, Playlist>();
		if (json != null) {
			for (int i = 0; i < json.length(); i++) {
				Playlist playlist = this.playlistParse(json.getJSONObject(i));
				playlists.put(playlist.getId(), playlist);
			}
		}
		return playlists;
	}

	/**
	 *
	 */
	@Override
	public List<Track> playlistIdParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject playlistsParse(List<Playlist> playlists) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * methode permettant de creer une instance de la classe User a partir d'un
	 * objet de type json
	 */
	@Override
	public User userParse(JSONObject json) {
		User user = null;
		if (json != null) {
			user = new User();
			user.setId(json.getString("id"));
			user.setPassword(json.getString("password"));
			user.setUsername(json.getString("username"));
			//user.setPlaylists(this.playlistsParse(json.getJSONArray("playlists")));
		}
		return user;
	}

}
