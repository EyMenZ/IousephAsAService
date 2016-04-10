package com.iouseph.view;

import java.io.IOException;

import com.iouseph.MainController;
import com.iouseph.api.Iapi;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	@FXML
	private Button connectButton;

	private MainController mainController;
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
			Media hit = new Media(track.getExternalUrl());// FIXME Unsupported protocol "https"
			mediaPlayer = new MediaPlayer(hit);
			handlePlay();
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
		playlist.setTracks(mainController.getApi().get_playlist(playlist.getId()));
		// TODO affecter les track a la playlist dans le parse
		mainController.getTracks().addAll(playlist.getTracks());
		showTrackDetails(playlist.getTracks().get(0));
	}

	@FXML
	public void handlePlay() {
		if (trackList.getSelectionModel().getSelectedItem()!=null)
		mediaPlayer.play();
	}

	@FXML
	public void handleStop() {
		if (trackList.getSelectionModel().getSelectedItem()!=null)
		mediaPlayer.stop();
	}

	@FXML
	public void handlePause() {
		if (trackList.getSelectionModel().getSelectedItem()!=null)
		mediaPlayer.pause();
	}

	@FXML
	public void handleNext() {
		if (trackList.getSelectionModel().getSelectedItem()!=null)
			mediaPlayer.stop();
		trackList.getSelectionModel().selectNext();
		handlePlay();
	}

	@FXML
	public void handlePrevious() {
		if (trackList.getSelectionModel().getSelectedItem()!=null)
			mediaPlayer.stop();
		trackList.getSelectionModel().selectPrevious();
		handlePlay();
	}

	@FXML
	private void handleDeezer() {
		// TODO activer/desactive Deezer
	}

	@FXML
	private void handleSpotify() {
		// TODO activer/desactive Spotify
	}

	@FXML
	private void handleSoundCloud() {
		// TODO activer/desactive SC
	}

	@FXML
	private void handleConnect() {
		if (mainController.getUser() == null){
			showLoginLayout();
		}else{
			mainController.setUser(null);
			connectButton.setText("connect");
			usernameLabel.setText("Guest");
		}
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
		mainController.getTracks().clear();
		mainController.getTracks().addAll(mainController.getApi().get_search(searchTextField.getText()));
		mainController.getPlaylists().clear();
		// mainController.getPlaylists().addAll(api.get_playlists(searchTextField.getText()));
	}

	@FXML
	private void handleAddToPlaylist(){

	}

	public void setUsernameLabel(String username) {
		this.usernameLabel.setText(username);
	}

	public boolean showLoginLayout() {
		try {
			// Load the FXML file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("view/LoginLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the login Stage.
			Stage loginStage = new Stage();
			loginStage.setTitle("Login");
			loginStage.getIcons().add(new Image("file:res/Iouseph-icon.png"));
			loginStage.initModality(Modality.WINDOW_MODAL);
			loginStage.initOwner(mainController.getMainStage());
			Scene scene = new Scene(page);
			loginStage.setScene(scene);

			LoginLayoutController controller = loader.getController();
			controller.setMainController(this);
			controller.setLoginStage(loginStage);

			loginStage.show();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Iapi getApi() {
		return mainController.getApi();
	}

	public void setUser(User user) {
		mainController.setUser(user);
	}

	public Button getConnectButton(){
		return connectButton;
	}
}
