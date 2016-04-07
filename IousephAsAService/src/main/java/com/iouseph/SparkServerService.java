package com.iouseph;

import static spark.Spark.*;

import java.util.List;

import com.iouseph.Deezer.*;
import com.iouseph.Soundcloud.*;
import com.iouseph.Spotify.*;
import com.iouseph.api.Iapi;
import com.iouseph.model.Track;
import com.iouseph.parsing.IousephParser;

public class SparkServerService {

	public static void main(String[] args){

		port(8000);

		Iapi deezer = new DeezerClient(),
				spotify = new SpotifyClient(),
				soundcloud = new SoundCloudClient();


		get("/track", (req, res) -> {

			// return tracks
            String query = req.attribute("search");
            //List<Track> soundcloudResult = soundcloud.get_search(query);
            List<Track> deezerResult = deezer.get_search(query);
            //List<Track> spotifyResult = spotify.get_search(query);

            // TODO return appropriate json instead of string
			return IousephParser.parseToJsonArray(deezerResult);
		});

		get("/playlists", (req, res) -> {

			// return list of playlist
			return null;
		});

		get("playlist", (req, res) -> {

			// return specific playlist
			return null;
		});

		post("/playlist", (req, res) -> {

			// update a playlist
			return null;
		});

		get("/create_playlist", (req, res) -> {

			// request to create playlist
			return null;
		});

		post("/login", (req, res) -> {

			// request for authentification
			return null;
		});

		post("/sign_in", (req, res) -> {

			// request to create new user
			return null;
		});

	}
}
