package com.iouseph.parsing;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

public final class IousephParser {
	/**
	 * methode permettant de convertir un objet track en format json
	 *
	 * @param track
	 * @return String en format json
	 */
	public static String parseToJsonObject(Track track) {
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
	public static String parseToJsonArray(List<Track> tracks) {
		JSONArray jsonArray = new JSONArray();
		try {
			for (int i = 0; i < tracks.size(); i++) {
				JSONObject jsonObject = new JSONObject(parseToJsonObject(tracks.get(i)));
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
	public static String parseToJsonObject(Playlist playlist) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("id", playlist.getId());
			jsonObject.put("idUser", playlist.getIdUser());
			jsonObject.put("owner", playlist.getOwner());
			jsonObject.put("title", playlist.getTitle());
			jsonObject.put("source", playlist.getSource());
			jsonObject.put("externalUrl", playlist.getUrl());
			jsonObject.put("tracks", new JSONArray(IousephParser.parseToJsonArray(playlist.getTracks())));
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
	public static String parseToJsonArray(Map<String, Playlist> map) {
		JSONArray jsonArray = new JSONArray();

		try {
			for (Map.Entry<String, Playlist> entry : map.entrySet()) {

				jsonArray.put(new JSONObject(IousephParser.parseToJsonObject(entry.getValue())));
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
	public static String parseToJsonObject(User user) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", user.getId());
			jsonObject.put("password", user.getPassword());
			jsonObject.put("username", user.getUsername());
			jsonObject.put("playlists", IousephParser.parseToJsonArray(user.getPlaylists()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING USER TO JSON");
			return null;
		}
		return jsonObject.toString();

	}
}
