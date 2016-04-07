package com.iouseph.view;

import com.iouseph.MainController;
import com.iouseph.api.Iapi;
import com.iouseph.api.IousephClient;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainLayoutController {
	@FXML
	private ListView<Track> trackList;
	@FXML
	private ListView<Playlist> playlistList;
	@FXML
	private Label trackTitleLabel;
	@FXML
	private Label artistNameLabel;
	@FXML
	private Label albumTitleLabel;
	@FXML
	private ImageView coverImage;
	@FXML
	private TextField searchTextField;
	@FXML
	private Label usernameLabel;

	private MainController mainController;
	private Iapi api;
	private MediaPlayer mediaPlayer;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MainLayoutController() {
		super();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the track list
		// trackList.setCellFactory(cellData -> cellData.getTitle());

		// Clear person details.
		showTrackDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		trackList.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showTrackDetails(newValue));
		playlistList.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPlaylistDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;

		// Add observable list data to the ListViews
		trackList.setItems(this.mainController.getTracks());
		playlistList.setItems(this.mainController.getPlaylists());
	}

	/**
	 * Fills all text fields to show details about a track. If the specified
	 * track is null, all text fields are cleared.
	 *
	 * @param track
	 */
	private void showTrackDetails(Track track) {
		if (track != null) {
			// Fill the labels with info from the track object.
			trackTitleLabel.setText(track.getTitle());
			artistNameLabel.setText(track.getArtist());
			albumTitleLabel.setText(track.getAlbum());
			// duration.setText(Float.toString(track.getDuration()));
			coverImage.setImage(new Image(track.getImage()));
			Media hit = new Media(track.getExternalUrl());//FIXME Unsupported protocol "https"
			mediaPlayer = new MediaPlayer(hit);
		} else {
			// track is null, remove all the text.
			trackTitleLabel.setText("Title");
			artistNameLabel.setText("Artist");
			albumTitleLabel.setText("Album");
			coverImage.setImage(new Image("file:res/Iouseph-logo.png"));

		}
	}

	private void showPlaylistDetails(Playlist playlist) {
		mainController.getTracks().clear();
		playlist.setTracks(api.get_playlist(playlist.getId()));// TODO affecter
																// les track a
																// la playlist
																// dans le parse
		mainController.getTracks().addAll(playlist.getTracks());
		showTrackDetails(playlist.getTracks().get(0));
	}

	@FXML
	public void handlePlay() {
		mediaPlayer.play();
	}

	@FXML
	public void handleStop() {
		mediaPlayer.stop();
	}

	@FXML
	public void handlePause() {
		mediaPlayer.pause();
	}

	@FXML
	public void handleNext() {
		trackList.getSelectionModel().selectNext();
	}

	@FXML
	public void handlePrevious() {
		trackList.getSelectionModel().selectPrevious();
	}

	@FXML
	private void handleDeezer() {
		//TODO activer/desactive Deezer
	}

	@FXML
	private void handleSpotify() {
		//TODO activer/desactive Spotify
	}

	@FXML
	private void handleSoundCloud() {
		//TODO activer/desactive SC
	}

	@FXML
	private void handleConnect(){
		mainController.showLoginLayout();
	}

	/**
	 * verifie si le bouton ENTRER est cliquer, si c'est le cas la methode
	 * {@link MainLayoutController#handleSearch()} est executee
	 *
	 * @param ke
	 *            valeur du bouton clique
	 */
	@FXML
	private void handleSearchTextField(KeyEvent ke) {
		if (ke.getCode().toString().equals("ENTER")) {
			handleSearch();
		}
	}

	@FXML
	private void handleSearch() {
		api = new IousephClient();
		mainController.getTracks().clear();
		mainController.getTracks().addAll(api.get_search(searchTextField.getText()));
		mainController.getPlaylists().clear();
		//mainController.getPlaylists().addAll(api.get_playlists(searchTextField.getText()));
	}

}
