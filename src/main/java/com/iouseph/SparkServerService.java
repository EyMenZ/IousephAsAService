package com.iouseph;

import static spark.Spark.*;

import com.iouseph.Deezer.*;
import com.iouseph.Soundcloud.*;
import com.iouseph.Spotify.*;

public class SparkServerService {

	public static void main(String[] args){
		
		port(8000);
		
		get("/search", (req, res) -> {
			
			// return result of search
			return null;
		});
		
		get("/tracks", (req, res) -> {
			
			// return tracks
			return null;
		});
		
		get("/playlists", (req, res) -> {
			
			// return playlist
			return null;
		});
	}
}
