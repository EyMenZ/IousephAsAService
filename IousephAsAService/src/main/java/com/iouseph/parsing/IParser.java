package com.iouseph.parsing;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

public abstract class IParser {

	/**
	 * retourne un objet Track, il est parse a partir d'un JSONObject
	 *
	 * @param json
	 *            un JSONObject recupere depuis le service de streaming
	 * @return Track
	 * @see IParser#tracksParse(JSONObject)
	 */
	public Track trackParse(JSONObject json) {

		return null;
	}

	/**
	 * retourne une List<Track>, elle est parse a partir d'un JSONObject
	 *
	 * @param json
	 *            un JSONObject recupere depuis le service de streaming
	 *
	 * @return List<Track>
	 */
	public List<Track> tracksParse(JSONObject json) {
		return null;
	}
	
	public List<Track> tracksParse(JSONArray json) {
		return null;
	}

	/**
	 * retourne un objet Playlist, il est parse a partir d'un JSONObject
	 *
	 * @param json
	 *            un JSONObject recupere depuis le service de streaming
	 * @return Playlist
	 * @see IParser#playlistsParse(JSONObject)
	 */
	public Playlist playlistParse(JSONObject json) {
		return null;
	}

	/**
	 * retourne une List<Playlist>, elle est parse a partir d'un JSONObject
	 *
	 * @param json
	 *            un JSONObject recupere depuis le service de streaming
	 *
	 * @return List<Playlist>
	 */
	public List<Playlist> playlistsParse(JSONObject json) {
		return null;
	}

	public List<Track> playlistIdParse(JSONObject json) {
		return null;
	}

	/**
	 * retourne un JSONObject, il est parse a partir d'une List<Playlist>
	 *
	 * @param playliste
	 *            une List<Playlist> a envoyer vers le service de streaming
	 *
	 * @return JSONObject
	 */
	public JSONObject playlistsParse(List<Playlist> playlists) {
		return null;
	}

	public User userParse(JSONObject json) {
		return null;
	}

	/**
	 * methode permettant de convertir un objet track en format json
	 *
	 * @param track
	 * @return String en format json
	 */
	public String parseToJsonObject(Track track) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("title", track.getTitle());
			jsonObject.put("id", track.getId());
			jsonObject.put("artist", track.getArtist());
			jsonObject.put("album", track.getAlbum());
			jsonObject.put("externalUrl", track.getExternalUrl());
			jsonObject.put("image", track.getImage());
			jsonObject.put("source", track.getSource());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING A TRACK TO JSON");
			return null;
		}
		return jsonObject.toString();
	}

	/**
	 * Methode permettant de convertir une list de tracks en String en format
	 * json
	 *
	 * @param tracks
	 * @return string en format json
	 */
	public String parseToJsonArray(List<Track> tracks) {
		JSONArray jsonArray = new JSONArray();
		try {
			for (int i = 0; i < tracks.size(); i++) {
				JSONObject jsonObject = new JSONObject(this.parseToJsonObject(tracks.get(i)));
				jsonArray.put(jsonObject);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING A LIST OF TRACKS TO JSON");
			return null;
		}
		return jsonArray.toString();
	}

	/**
	 * methode permettant de convertir un objet de type playlist en string en
	 * format json
	 *
	 * @param playlist
	 * @return string en format json
	 */
	public String parseToJsonObject(Playlist playlist) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("id", playlist.getId());
			jsonObject.put("idUser", playlist.getIdUser());
			jsonObject.put("owner", playlist.getOwner());
			jsonObject.put("title", playlist.getTitle());
			jsonObject.put("source", playlist.getSource());
			jsonObject.put("externalUrl", playlist.getUrl());
			jsonObject.put("tracks", new JSONArray(this.parseToJsonArray(playlist.getTracks())));
			// TODO ajouter le client ( et voir la necessite de l'avoir dans la
			// classe playlist)
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING PLAYLIST TO JSON");
			return null;
		}
		return jsonObject.toString();
	}

	/**
	 * methode permettant de convertir un map contenant des playlist en string
	 * de format json array
	 *
	 * @param map
	 * @return string en format json array
	 */
	public String parseToJsonArray(Map<String, Playlist> map) {
		JSONArray jsonArray = new JSONArray();

		try {
			for (Map.Entry<String, Playlist> entry : map.entrySet()) {

				jsonArray.put(new JSONObject(this.parseToJsonObject(entry.getValue())));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonArray.toString();
	}

	/**
	 * methode permettant de parser un objet de type user en string en format
	 * json object
	 *
	 * @param user
	 * @return string en format jsonObject
	 */
	public String parseToJsonObject(User user) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", user.getId());
			jsonObject.put("password", user.getPassword());
			jsonObject.put("username", user.getUsername());
			jsonObject.put("playlists", this.parseToJsonArray(user.getPlaylists()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING USER TO JSON");
			return null;
		}
		return jsonObject.toString();

	}
}
