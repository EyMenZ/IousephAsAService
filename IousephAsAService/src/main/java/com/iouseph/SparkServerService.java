package com.iouseph;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.iouseph.api.Iapi;
import com.iouseph.api.Deezer.*;
import com.iouseph.api.Soundcloud.*;
import com.iouseph.api.Spotify.*;
import com.iouseph.model.Track;
import com.iouseph.parsing.IousephParser;

public class SparkServerService {

	public static void main(String[] args){

		port(8000);

		Iapi deezer = new DeezerClient(),
				spotify = new SpotifyClient(),
				soundcloud = new SoundCloudClient();


		get("/track/:search", (req, res) -> {
			String query = req.params(":search");
			System.out.println(query);
			List<List<Track>> list = new ArrayList<List<Track>>();
			list.add(soundcloud.get_search(query));
			list.add(deezer.get_search(query));
			//FIXME list.add(spotify.get_search(query));
			return IousephParser.parseToJsonArray(mix(list));
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

	private static List<Track> mix(List<List<Track>> list) {
		List<Track> tracks = new ArrayList<Track>();
		int i = 0, j = 0;
		while(list.get(i).size() > j){
			tracks.add(list.get(i++).get(j));
			if (i == list.size()){
				i = 0;
				j++;
			}
		}
		return tracks;
	}

}
