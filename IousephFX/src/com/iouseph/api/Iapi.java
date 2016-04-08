package com.iouseph.api;

import java.util.List;

import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

/**
 * Interface pour chaque client d'API public de l'application. Ceci est pour
 * repondre aux requis defini dans le tp1 1. outils de recherche de musique
 * independant des systemes de streaming. 2. gestion des listes de musique. 3.
 * reproduction des listes de musique. 4. bonus: system de parole de chanson.
 *
 * @author Marcial Lopez-Ferrada, Youssef Zemmahi, Aymen Zalila
 *
 */
public interface Iapi {

	public String retreive_token();

	/**
	 * methode servant a faire une recherche de chason de facon general
	 *
	 * @param search
	 *            String decrivant la recherche
	 * @return
	 */
	public List<Track> get_search(String search);

	public List<Playlist> get_playlists(String search);

	/**
	 * methode pour recuperer une playlist
	 *
	 * @param playlist_id
	 *            id de la playlist rechercher
	 */
	public List<Track> get_playlist(String playlist_id);

	/**
	 * methode pour recupere de l'information sur une chanson en particulier
	 *
	 * @param track_id
	 *            id de la chanson rechercher
	 */
	public Track get_track(String track_id);

	public List<Track> get_tracks();

	public boolean set_playlists(List<Playlist> playlists);


	/**
	 * methode pour recuperer les informations personnelles de l'utilisateur
	 *
	 */
	public User get_personnal_info();

	public User connect(String type, String username, String pwd);


}
