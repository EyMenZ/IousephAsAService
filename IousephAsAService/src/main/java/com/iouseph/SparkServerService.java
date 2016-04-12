package com.iouseph;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.List;

import com.iouseph.api.Iapi;
import com.iouseph.api.Deezer.DeezerClient;
import com.iouseph.api.Soundcloud.SoundCloudClient;
import com.iouseph.api.Spotify.SpotifyClient;
import com.iouseph.model.Track;
import com.iouseph.model.User;
import com.iouseph.parsing.IousephParser;
import com.iouseph.persistence.AccountManager;
import com.iouseph.persistence.ObjectsManager;

public class SparkServerService {

	public static void main(String[] args) {

		port(8000);

		Iapi deezer = new DeezerClient(), spotify = new SpotifyClient(), soundcloud = new SoundCloudClient();

		/**
		 * retourne la liste de tracks correspondant au resultat de la recherche
		 */
		get("/track/:search", (req, res) -> {
			String query = req.params(":search");
			List<List<Track>> list = new ArrayList<List<Track>>();
			list.add(soundcloud.get_search(query));
			list.add(deezer.get_search(query));
			list.add(spotify.get_search(query));
			return IousephParser.parseToJsonArray(mix(list));
		});
		/**
		 * retourne un utilisateur selon son id
		 */
		get("/users/:userid", (req,res)-> {
			String userid = req.params(":userid");
			try{
				User user=AccountManager.getInstance().getUser(userid);
				User copyUser = user.clone();
				return IousephParser.parseToJsonObject(user);
			}
			catch(NullPointerException e)
			{
				System.err.println("user not found");
			}
			return null;
		});
		/**
		 * retourne la liste des playlists d'un utilisateur
		 */
		get("/playlists/:user_id", (req, res) -> {
			String idUser = req.params(":user_id");
			try
			{
				User user = AccountManager.getInstance().getUser(idUser);
				return IousephParser.parseToJsonArray(user.getPlaylists());

			}
			catch(NullPointerException e)
			{
				System.out.println("user not found");
			}
			return null;
		});
		/**
		 * retourne une playlist en utilisant l'id de l'utilisateur et celui de la playlist
		 */
		get("playlist/:user_id/:playlist_id", (req, res) -> {
			String idUser = req.params(":user_id");
			String playlistId = req.params(":playlist_id");
			User user = AccountManager.getInstance().getUser(idUser);

			return IousephParser.parseToJsonObject(user.getPlaylist(playlistId));
		});
		/**
		 * methode supprime une playliste d'un utilisateur
		 */
		get("playlist/delete/:user_id/:playlist_id", (req, res) -> {
			String userId = req.params(":user_id");
			String playlistId = req.params(":playlist_id");
			try{
				User user = AccountManager.getInstance().getUser(userId);
				if(user.deletePlaylist(playlistId))
				{
					ObjectsManager.SerializeUser(user);
					return Constants.PlaylistDeleted;
				}
			}
			catch(Exception e)
			{
				System.err.println("user not found when trying to delete a playlist");
			}
			return Constants.PlaylistNotDeleted;
		});

		/**
		 * methode permettant de changer le nom d'une playlist selon son id
		 */
		get("playlist/edit/:user_id/:playlist_id/:new_title", (req, res) -> {
			String userId = req.params(":user_id");
			String playlistId = req.params(":playlist_id");
			String NTitle = req.params(":new_title");

			try
			{
				User user = AccountManager.getInstance().getUser(userId);
				user.getPlaylist(playlistId).setTitle(NTitle);
				ObjectsManager.SerializeUser(user);
				return Constants.PlaylistNameChanged;
			}
			catch(NullPointerException e)
			{
				System.err.println("Playlist not found when trying to set a playlist's title");
			}
			return Constants.PlaylistNameNotChanged;
		});
		/**
		 *  supprime un track de la playlist d'un utilisateur
		 */
		get("playlist/delete/:user_id/:playlist_id/:track_id", (req, res) -> {
			String userId = req.params(":user_id");
			String playlistId = req.params(":playlist_id");
			String trackId = req.params(":track_id");

			try
			{
				User user = AccountManager.getInstance().getUser(userId);
				boolean deleted = user.getPlaylist(playlistId).deleteTrack(trackId);
				if(deleted)
				{
					ObjectsManager.SerializeUser(user);
					return Constants.TrackDeleted;
				}
			}
			catch(NullPointerException e)
			{
				System.err.println("problem when trying to delete a track");
			}

			return Constants.TrackNotDeleted;
		});
		/**
		 * ajoute un track a une playlist dun utilisateur
		 */
		post("/playlist/addtrack", (req, res) -> {
			String userId = req.queryParams("user_id");
			String playlistId = req.queryParams("playlist_id");
			Track track = new Track();
			track.setId(req.queryParams("id"));
			track.setAlbum(req.queryParams("album"));
			track.setArtist(req.queryParams("artist"));
			track.setExternalUrl(req.queryParams("externalUrl"));
			track.setImage(req.queryParams("image"));
			track.setSource(req.queryParams("source"));
			track.setTitle(req.queryParams("title"));
			try{
			User user = AccountManager.getInstance().getUser(userId);
			boolean added = user.getPlaylist(playlistId).addTrack(track);
			if (added) {
				ObjectsManager.SerializeUser(user);
				return Constants.TrackAdded;
			}
			}
			catch(NullPointerException e)
			{
				System.err.println("problem when trying to add a track");
			}

			return Constants.TrackNotAdded;
		});
		/**
		 * cree une nouvelle playlist a un utilisateur
		 */
		post("/playlist/create", (req, res) -> {
			String userId = req.queryParams("userId");
			String title = req.queryParams("title");
			try{
				User user=AccountManager.getInstance().getUser(userId);
				if(user.addPlaylist(title))
				{
					ObjectsManager.SerializeUser(user);
					return Constants.PlaylistAdded;
				}
			}
			catch(NullPointerException e)
			{
				System.err.println(" user not found when trying to create a playlist");
			}
			return Constants.PlaylistNotAdded;
		});
		/**
		 * methode pour authentification
		 */
		post("/login", (req, res) -> {
			String username = req.queryParams("username");
			String password = req.queryParams("pwd");
			String idUser = AccountManager.getInstance().Authentification(username, password);
			if (idUser == null)
				return null;
			User user = ObjectsManager.DeserializeUser(idUser);
			// si jamais l'utilisateur est null la fonction de parsage
			// retournera null
			return IousephParser.parseToJsonObject(user);

		});
		/**
		 * methode pour enregistrement
		 */
		post("/signup", (req, res) -> {
			String username = req.queryParams("username");
			String password = req.queryParams("pwd");
			User user = AccountManager.getInstance().Enregistrement(username, password);
			ObjectsManager.SerializeUser(user);
			// si jamais l'utilisateur est null la fonction de parsage
			// retournera null
			return IousephParser.parseToJsonObject(user);
		});
		/**
		 * methode pour deconnection
		 */
		post("/disconnect", (req, res) -> {
			String idUser = req.queryParams("idUser");
			User user = AccountManager.getInstance().getUser(idUser);
			if (user == null)
				return Constants.UserNotConnected;
			ObjectsManager.SerializeUser(user);
			return Constants.UserDisconnected;
		});

	}

	private static List<Track> mix(List<List<Track>> list) {
		// TODO faire un truc plus efficace
		List<Track> tracks = new ArrayList<Track>();
		int i = 0, j = 0;
		List<Integer> banned = new ArrayList<Integer>();
		while (banned.size() < list.size()) {
			if (list.get(i).size() > j) {
				tracks.add(list.get(i).get(j));
			} else if (!banned.contains(i))
				banned.add(i);
			if (++i == list.size()) {
					i = 0;
					j++;
				}
		}
		return tracks;
	}

}
