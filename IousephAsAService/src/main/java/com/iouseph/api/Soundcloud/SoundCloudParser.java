package com.iouseph.api.Soundcloud;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.api.IParser;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;


public class SoundCloudParser implements IParser{

	public Track trackParse(JSONObject json) {
		Track track = new Track();
		try {
			track.setId(json.getString("id"));
			track.setTitle(json.getString("title"));
			track.setAlbum("");//TODO
			track.setArtist("");//TODO
			track.setExternalUrl(json.getString("stream_url"));//json.getString("uri")
			track.setDuration(json.getInt("duration"));
			track.setImage(json.getString("artwork_url"));
			track.setSource("SoundCloud");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return track;
	}

	public List<Track> tracksParse(JSONArray json) {
		List<Track> tracks = new ArrayList<>();
		for(int i = 0; i < json.length(); i++ ) {
			try {
				tracks.add(this.trackParse((JSONObject) json.get(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return tracks;
	}

	public Playlist playlistParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Playlist> playlistsParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Track> playlistIdParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject playlistsParse(List<Playlist> playlists) {
		// TODO Auto-generated method stub
		return null;
	}

	public User userParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> tracksParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
}