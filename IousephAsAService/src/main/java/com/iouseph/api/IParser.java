package com.iouseph.api;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;


public interface IParser {

	/**
	 * retourne un objet Track, il est parse a partir d'un JSONObject
	 *
	 * @param json	un JSONObject recupere depuis le service de streaming
	 * @return	Track
	 * @throws JSONException
	 * @see IParser#tracksParse(JSONObject)
	 */
	public Track trackParse(JSONObject json);

	/**
	 * retourne une List<Track>, elle est parse a partir d'un JSONObject
	 *
	 * @param json	un JSONObject recupere depuis le service de streaming
	 *
	 * @return	List<Track>
	 */
	public List<Track> tracksParse(JSONObject json);

	/**
	 * retourne un objet Playlist, il est parse a partir d'un JSONObject
	 *
	 * @param json	un JSONObject recupere depuis le service de streaming
	 * @return	Playlist
	 * @see IParser#playlistsParse(JSONObject)
	 */
	public Playlist playlistParse(JSONObject json);

	/**
	 * retourne une List<Playlist>, elle est parse a partir d'un JSONObject
	 *
	 * @param json	un JSONObject recupere depuis le service de streaming
	 *
	 * @return	List<Playlist>
	 */
	public List<Playlist> playlistsParse(JSONObject json);

	public List<Track> playlistIdParse(JSONObject json);

	/**
	 * retourne un JSONObject, il est parse a partir d'une List<Playlist>
	 *
	 * @param playliste	une List<Playlist> a envoyer vers le service de streaming
	 *
	 * @return	JSONObject
	 */
	public JSONObject playlistsParse(List<Playlist> playlists);

	public User userParse(JSONObject json);

	public List<Track> tracksParse(JSONArray json);
	}
