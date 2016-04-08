package com.iouseph;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.iouseph.api.Iapi;
import com.iouseph.api.Deezer.*;
import com.iouseph.api.Soundcloud.*;
import com.iouseph.api.Spotify.*;
import com.iouseph.model.Track;
import com.iouseph.model.User;
import com.iouseph.parsing.IousephParser;

public class SparkServerService {

	public static void main(String[] args) {

		port(8000);

		Iapi deezer = new DeezerClient(), spotify = new SpotifyClient(), soundcloud = new SoundCloudClient();

		get("/track/:search", (req, res) -> {
			String query = req.params(":search");
			List<List<Track>> list = new ArrayList<List<Track>>();
			list.add(soundcloud.get_search(query));
			list.add(deezer.get_search(query));
			list.add(spotify.get_search(query));
			return IousephParser.parseToJsonArray(mix(list));
		});

		get("/playlists/:user_id", (req, res) -> {
			String user = req.params(":user_id");
			// return list of playlist
			return null;
		});

		get("playlist/:user_id/:playlist_id", (req, res) -> {
			String user = req.params(":user_id");

			// return specific playlist
			return null;
		});

		get("playlist/delete/:user_id/:playlist_id", (req, res) -> {
			String user = req.params(":user_id");
			// delete playlist
			// return specific playlist
			return null;
		});

		get("playlist/:user_id/:playlist_id/:new_title", (req, res) -> {
			String user = req.params(":user_id");
			//changer nom playlist
			// return specific playlist
			return null;
		});

		get("playlist/:user_id/:playlist_id/:track_id", (req, res) -> {
			String user = req.params(":user_id");
			//delete track
			// return specific playlist
			return null;
		});

		post("/playlist", (req, res) -> {

			// add Track
			return null;
		});

		get("/create_playlist/:user_id/:title", (req, res) -> {
			String user = req.params(":user_id");
			String title = req.params(":title");
			// request to create playlist
			return null;
		});

		post("/login/:username/:pwd", (req, res) -> {
			String username = req.params(":username");
			String password = req.params(":pwd");
			// request for authentification
			// TODO if do not exist returns null
			return IousephParser.parseToJsonObject(new User(username, password));

		});

		post("/signup/:username/:pwd", (req, res) -> {
			String username = req.params(":username");
			String password = req.params(":pwd");
			// request to create new user
			// TODO if exist returns null
			return IousephParser.parseToJsonObject(new User(username, password));
		});

	}

	private static List<Track> mix(List<List<Track>> list) {
		// TODO faire un truc plus efficace
		List<Track> tracks = new ArrayList<Track>();
		int i = 0, j = 0;
		while (list.get(i).size() > j) {
			tracks.add(list.get(i++).get(j));
			if (i == list.size()) {
				i = 0;
				j++;
			}
		}
		return tracks;
	}

}
