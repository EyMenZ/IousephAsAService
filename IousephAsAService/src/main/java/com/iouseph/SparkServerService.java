package com.iouseph;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.iouseph.api.Iapi;
import com.iouseph.api.Deezer.*;
import com.iouseph.api.Soundcloud.*;
import com.iouseph.api.Spotify.*;
import com.iouseph.model.Track;
import com.iouseph.model.User;
import com.iouseph.parsing.IousephParser;
import com.iouseph.persistence.AccountManager;
import com.iouseph.persistence.Constants;
import com.iouseph.persistence.ObjectsManager;

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
			String idUser = req.params(":user_id");
			String playlistId = req.params(":playlist_id");
			User user = AccountManager.getInstance().getUser(idUser);

			return IousephParser.parseToJsonObject(user.getPlaylist(playlistId));
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

		get("playlist/delete/:user_id/:playlist_id/:track_id", (req, res) -> {
			String userId = req.params(":user_id");
			String playlistId = req.params(":playlist_id");
			String trackId = req.params(":track_id");

			User user = AccountManager.getInstance().getUser(userId);
			boolean deleted = user.getPlaylist(playlistId).deleteTrack(trackId);
			if(deleted)
			{
				ObjectsManager.SerializeUser(user);
				return Constants.TrackDeleted;
			}

			return Constants.TrackNotAdded;
		});

		post("/playlist/addtrack/:user_id/:playlist_id", (req, res) -> {
			String userId=req.params(":user_id");
			String playlistId=req.params(":playlist_id");
			Track track = new Track();
			track.setId(req.queryParams("id"));
			track.setAlbum(req.queryParams("album"));
			track.setArtist(req.queryParams("artist"));
			track.setExternalUrl(req.queryParams("externalUrl"));
			track.setImage(req.queryParams("image"));
			track.setSource(req.queryParams("source"));
			track.setTitle(req.queryParams("title"));
			User user=AccountManager.getInstance().getUser(userId);
			boolean added=user.getPlaylist(playlistId).addTrack(track);
				if(added)
				{
					ObjectsManager.SerializeUser(user);
					return Constants.TrackAdded;
				}

			return Constants.TrackNotAdded;
		});

		post("/create_playlist", (req, res) -> {
			String userId = req.queryParams("userId");
			String title = req.queryParams("title");
			User user=AccountManager.getInstance().getUser(userId);
			if(user.addPlaylist(title))
			{
				ObjectsManager.SerializeUser(user);
				return Constants.PlaylistAdded;
			}
			return Constants.PlaylistNotAdded;
		});

		post("/login", (req, res) -> {
			String username = req.queryParams("username");
			String password = req.queryParams("pwd");
			String idUser=AccountManager.getInstance().Authentification(username, password);
			if(idUser==null)
				return null;
			User user = ObjectsManager.DeserializeUser(idUser);
			// si jamais l'utilisateur est null la fonction de parsage retournera null
			return IousephParser.parseToJsonObject(user);

		});

		post("/signup", (req, res) -> {
			String username = req.queryParams("username");
			String password = req.queryParams("pwd");
			User user = AccountManager.getInstance().Enregistrement(username, password);
			ObjectsManager.SerializeUser(user);
			// si jamais l'utilisateur est null la fonction de parsage retournera null
			return IousephParser.parseToJsonObject(user);
		});

		post("/disconnect", (req,res) -> {
			String idUser=req.queryParams("idUser");
			User user = AccountManager.getInstance().getUser(idUser);
			if(user==null)
				return Constants.UserNotConnected;
			ObjectsManager.SerializeUser(user);
			return Constants.UserDisconnected;
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
