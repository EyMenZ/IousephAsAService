package com.iouseph.api;

import java.util.List;

import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

public class IousephClient implements Iapi {

	@Override
	public String retreive_token() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> get_search(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Playlist> get_playlists(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> get_playlist(String playlist_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Track get_track(String track_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> get_tracks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean set_playlists(List<Playlist> playlists) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User get_personnal_info() {
		// TODO Auto-generated method stub
		return null;
	}

}
