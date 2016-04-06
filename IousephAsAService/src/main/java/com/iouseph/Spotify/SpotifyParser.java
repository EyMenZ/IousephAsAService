package com.iouseph.Spotify;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.model.*;
import com.iouseph.parsing.*;

public class SpotifyParser implements IParser {

	@Override
	public Track trackParse(JSONObject json) {
		Track track = new Track();
		try {
			track.setId(json.getString("id"));

			track.setTitle(json.getString("name"));
			track.setExternalUrl(json.getJSONObject("external_urls").getString("spotify"));
			track.setArtist(json.getJSONArray("artists").getJSONObject(0).getString("name"));
			track.setAlbum(json.getJSONObject("album").getString("name"));
			track.setImage(json.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			track.setSource("Spotify");
		return track;
	}

	@Override
	public List<Track> tracksParse(JSONObject json) {
		List<Track> currentUserTracks = new ArrayList<Track>();

		JSONArray jsonobjectsArray;
		try {
			jsonobjectsArray = (JSONArray) json.get("items");

		for (int i = 0; i < jsonobjectsArray.length(); i++) {
				currentUserTracks.add(trackParse((jsonobjectsArray.getJSONObject(i)).getJSONObject("track")));
		}} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentUserTracks;
	}


	public List<Track> tracksSearchedParse(JSONObject json) {
		List<Track> currentUserTracks = new ArrayList<Track>();
		try {
			json = json.getJSONObject("tracks");

			JSONArray jsonobjectsArray = (JSONArray) json.get("items");
		for (int i = 0; i < jsonobjectsArray.length(); i++) {
				currentUserTracks.add(trackParse(jsonobjectsArray.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentUserTracks;
	}

	@Override
	public Playlist playlistParse(JSONObject json) {
		Playlist myplaylist = new Playlist();
		try {
			myplaylist.setId(json.getString("id"));

		myplaylist.setOwner(json.getJSONObject("owner").getString("id"));
		myplaylist.setUrl(json.getJSONObject("tracks").getString("href"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myplaylist;
	}

	@Override
	public List<Playlist> playlistsParse(JSONObject json) {
		List<Playlist> myPlaylists = new ArrayList<Playlist>();
		JSONArray jsonobjectsArray;
		try {
			jsonobjectsArray = json.getJSONArray("items");

		for (int i = 0; i < jsonobjectsArray.length(); i++) {
			myPlaylists.add(playlistParse(jsonobjectsArray.getJSONObject(i)));
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myPlaylists;
	}

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

	@Override
	public User userParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	public String parseToJsonObject(Track track)
	{
		JSONObject jsonObject=new JSONObject();
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
	public String parseToJsonArray(List<Track> tracks)
	{
		JSONArray jsonArray = new JSONArray();
		try {
			for(int i=0;i<tracks.size();i++)
			{
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
	public String parseToJsonObject(Playlist playlist)
	{
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("id",playlist.getId());
			jsonObject.put("idUser", playlist.getIdUser());
			jsonObject.put("owner", playlist.getOwner());
			jsonObject.put("title", playlist.getTitle());
			jsonObject.put("source", playlist.getSource());
			jsonObject.put("externalUrl", playlist.getUrl());
			jsonObject.put("tracks", new JSONArray(this.parseToJsonArray(playlist.getTracks())));
			// TODO ajouter le client ( et voir la necessite de l'avoir dans la classe playlist)
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING PLAYLIST TO JSON");
			return null;
		}
		return jsonObject.toString();
	}
	public String parseToJsonObject(User user)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", user.getId());
			jsonObject.put("password", user.getPassword());
			jsonObject.put("username", user.getUsername());
			// TODO jsonObject.put("", user.get);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR WHEN PARSING USER TO JSON");
			return null;
		}

		return jsonObject.toString();


	}
}

