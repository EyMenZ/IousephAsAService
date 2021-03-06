package com.iouseph.api.Deezer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.api.IParser;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;


/**
 * @author EyMenz
 *
 */
public class DeezerParser implements IParser{

	/**
	 * retourne un objet Track, il est parse a partir d'un JSONObject
	 *
	 * @param json	un JSONObject recupere depuis le service de streaming
	 * @return	Track
	 * @throws JSONException
	 * @see com.iouseph.api.IParser#tracksParse(JSONObject)
	 */
	@Override
	public Track trackParse(JSONObject json){
		Track track = new Track();
		try {
			track.setId(String.valueOf(json.getInt("id")));
			track.setTitle(json.getString("title"));
			track.setExternalUrl(json.getString("preview"));
			track.setArtist(((JSONObject) json.get("artist")).getString("name"));
			track.setAlbum(((JSONObject) json.get("album")).getString("title"));
			track.setImage(((JSONObject) json.get("album")).getString("cover_big"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		track.setSource("Deezer");
		return track;
	}

	/**
	 * retourne une List<Track>, elle est parse a partir d'un JSONObject
	 *
	 * @param json	un JSONObject recupere depuis le service de streaming
	 *
	 * @return	ArrayList<Track>
	 *
	 * @see com.iouseph.api.IParser#tracksParse(JSONObject)
	 */
	@Override
	public List<Track> tracksParse(JSONObject json) {
		List<Track> tracks = new ArrayList<>();
		int i = 0;
		try {
			while (((JSONObject) json.getJSONArray("data").get(i)) != null) {
				tracks.add(trackParse(((JSONObject) json.getJSONArray("data")
						.get(i))));
				i++;;
			}
		} catch (JSONException e) {

			return tracks;
		}
		return tracks;
	}

	/**
	 * retourne un objet Playlist, il est parse a partir d'un JSONObject
	 *
	 * @param json
	 * 				un JSONObject recupere depuis le service de streaming
	 * @return
	 * 				Playlist
	 * @see com.iouseph.api.IParser#playlistsParse(JSONObject)
	 */
	@Override
	public Playlist playlistParse(JSONObject json){
		Playlist playlist = new Playlist();
		try {
			playlist.setId(String.valueOf(json.getInt("id")));
			playlist.setTitle(json.getString("title"));
			playlist.setOwner(((JSONObject) json.get("user")).getString("name"));
			playlist.setSource("Deezer");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return playlist;
	}

	/**
	 * retourne une List<Playlist>, elle est parse a partir d'un JSONObject
	 *
	 * @param json
	 * 				un JSONObject recupere depuis le service de streaming
	 *
	 * @return
	 * 				ArrayList<Playlist>
	 * @see com.iouseph.api.IParser#playlistsParse(JSONObject)
	 */
	@Override
	public List<Playlist> playlistsParse(JSONObject json){
		List<Playlist> playlists = new ArrayList<>();
		int i = 0;
		try {
			while (((JSONObject) json.getJSONArray("data").get(i)) != null) {
				playlists.add(playlistParse(((JSONObject) json.getJSONArray("data")
						.get(i))));
				i++;
			}
		} catch (JSONException e) {
			return playlists;
		}

		return playlists;
	}

	/**
	 * @see com.iouseph.api.IParser#playlistIdParse(org.json.JSONObject)
	 */
	@Override
	public List<Track> playlistIdParse(JSONObject json) {
		/*List<Track> tracks = new ArrayList<>();
		int i = 0;
		System.out.println(((JSONObject) (json.getJSONArray("tracks")).get(0)));
		try {
			while (((JSONObject) (json.getJSONArray("tracks")).get(i)) != null) {
				tracks.add(trackParse(((JSONObject) json.getJSONArray("tracks")
						.get(i))));
				i++;
			}
		} catch (JSONException e) {
			return tracks;
		}*/
		return tracksParse(json);
	}

	/**
	 * @see com.iouseph.api.IParser#playlistsParse(java.util.List)
	 */
	@Override
	public JSONObject playlistsParse(List<Playlist> playlists) {
		JSONObject json = new JSONObject();
		// TODO Auto-generated method stub
		return json;
	}

	public User userParse(JSONObject json){
		User user = new User();
		@SuppressWarnings("unchecked")
		Iterator<String> i = json.keys();
		while (i.hasNext()) {
			String s = i.next();
			try {
				System.out.println(s + " : " + json.get(s));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * @see com.iouseph.api.IParser#tracksParse(org.json.JSONArray)
	 */
	@Override
	public List<Track> tracksParse(JSONArray json) {
		// TODO Auto-generated method stub
		return null;
	}
}
