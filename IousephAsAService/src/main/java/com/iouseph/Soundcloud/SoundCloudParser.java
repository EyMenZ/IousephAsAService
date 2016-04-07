package com.iouseph.Soundcloud;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iouseph.model.*;
import com.iouseph.parsing.IParser;

public class SoundCloudParser extends IParser{

	@Override
	public Track trackParse(JSONObject json) {
		// TODO Auto-generated method stub
		Track track = new Track();
		try {
			track.setId(json.getString("id"));
			track.setTitle(json.getString("title"));
			track.setExternalUrl(json.getString("stream_url"));
			track.setDuration(json.getInt("duration"));
			track.setImage(json.getString("artwork_url"));
			track.setSource(json.getString("uri"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return track;
	}

	@Override
	public List<Track> tracksParse(JSONArray json) {
		// TODO Auto-generated method stub
		
		List<Track> tracks = new ArrayList<>();
		
		for(int i = 0; i < json.length(); i++ ) {
			try {
				tracks.add(this.trackParse((JSONObject) json.get(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return tracks;
	}

	@Override
	public Playlist playlistParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Playlist> playlistsParse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
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

}