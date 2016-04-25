package com.iouseph;

import java.io.IOException;

import com.iouseph.api.Iapi;
import com.iouseph.api.IousephClient;
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

/**
 * @author Marcial Lopez-Ferrada, Youssef Zemmahi, Aymen Zalila
 * @version IousephFX
 *
 */
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
	@FXML
	private TextField addPlaylistTextField;


	private MainController mainController;
	private MediaPlayer mediaPlayer;
	private Track currentTrack;

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
	 *
	 * this method calls {@link #showTrackDetails(Track)} with default data
	 * and create {@link #trackList} and {@link #playlistList} Listeners
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
		/*playlistList.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPlaylistDetails(newValue));*/
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * it update the data of {@link #trackList} and {@link #playlistList}
	 *
	 * @param {@link MainController}
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
	 * Use {@link Media} and {@link MediaPlayer} to play {@link Track}
	 *
	 * @param track
	 */
	private void showTrackDetails(Track track) {
		this.currentTrack = track;
		if (track != null) {
			// Fill the labels with info from the track object.
			trackTitleLabel.setText(track.getTitle());
			artistNameLabel.setText(track.getArtist());
			albumTitleLabel.setText(track.getAlbum());
			// duration.setText(Float.toString(track.getDuration()));
			coverImage.setImage(new Image(track.getImage()));
			Media hit = new Media(track.getExternalUrl());// FIXME Unsupported
															// protocol "https"
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

	/**
	 * Shows list of {@link Track} in selected playlist
	 * and show details of first track with showTrackDetails(Track)
	 *
	 * @see #showTrackDetails(Track)
	 *
	 * @param playlist
	 */
	private void showPlaylistDetails(Playlist playlist) {
		mainController.getTracks().clear();
		//playlist.setTracks(mainController.getUser().getPlaylists().values());
		// TODO affecter les track a la playlist dans le parse
		mainController.getTracks().addAll(playlist.getTracks());
		showTrackDetails(playlist.getTracks().get(0));
	}

	/**
	 * play the selected Track
	 *
	 * is called play_button is clicked on #MainLayout.fxml
	 */
	@FXML
	public void handlePlay() {
		if (trackList.getSelectionModel().getSelectedItem() != null)
			mediaPlayer.play();
	}

	/**
	 * stop the current Track
	 *
	 * is called stop_button is clicked on #MainLayout.fxml
	 */
	@FXML
	public void handleStop() {
		if (trackList.getSelectionModel().getSelectedItem() != null)
			mediaPlayer.stop();
	}

	/**
	 * turns the music on pause
	 *
	 * is called pause_button is clicked on #MainLayout.fxml
	 */
	@FXML
	public void handlePause() {
		if (trackList.getSelectionModel().getSelectedItem() != null)
			mediaPlayer.pause();
	}

	/**
	 * play the next Track if it exists
	 *
	 * is called next_button is clicked on #MainLayout.fxml
	 */
	@FXML
	public void handleNext() {
		if (trackList.getSelectionModel().getSelectedItem() != null)
			mediaPlayer.stop();
		trackList.getSelectionModel().selectNext();
		handlePlay();
	}

	/**
	 * play the previous Track if it exists
	 *
	 * is called previous_button is clicked on #MainLayout.fxml
	 */
	@FXML
	public void handlePrevious() {
		if (trackList.getSelectionModel().getSelectedItem() != null)
			mediaPlayer.stop();
		trackList.getSelectionModel().selectPrevious();
		handlePlay();
	}


	/**
	 * to enable/disable Deezer Service
	 */
	@FXML
	private void handleDeezer() {
		// TODO activer/desactive Deezer
	}

	/**
	 * to enable/disable Spotify Service
	 */
	@FXML
	private void handleSpotify() {
		// TODO activer/desactive Spotify
	}

	/**
	 * to enable/disable SoundCloud Service
	 */
	@FXML
	private void handleSoundCloud() {
		// TODO activer/desactive SC
	}

	/**
	 * is called when {@link #connectButton} is clicked
	 * shows the LoyinLayout
	 *
	 * @see #showLoginLayout()
	 */
	@FXML
	private void handleConnect() {
		if (mainController.getUser() == null) {
			showLoginLayout();
		} else {
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

	/**
	 *
	 */
	@FXML
	private void handleSearch() {
		mainController.getTracks().clear();
		mainController.getTracks().addAll(mainController.getApi().get_search(searchTextField.getText()));
		mainController.getPlaylists().clear();
	}

	@FXML
	private void handleAddToPlaylist() {
		if ((currentTrack != null) && (playlistList.getSelectionModel().getSelectedItem() != null)) {
			mainController.getApi().addTrackToPlaylist(mainController.getUser(), playlistList.getSelectionModel().getSelectedItem(), currentTrack);
			playlistList.getSelectionModel().getSelectedItem().addTrack(currentTrack);
			System.out.println(mainController.getPlaylists());
		}
	}

	@FXML
	private void handleAddPlaylist() {
		if (!addPlaylistTextField.getText().equals(""))
			if (!mainController.getUser().getPlaylists().containsKey(addPlaylistTextField.getText())) {
				Playlist playlist = new Playlist();
				playlist.setTitle(addPlaylistTextField.getText());
				playlist.setOwner(mainController.getUser().getUsername());
				playlist.setSource("Iouseph");
				mainController.getUser().getPlaylists().put(playlist.getTitle(), playlist);
				mainController.getApi().addPlaylist(mainController.getUser(), playlist);
				refreshPlaylists();
			}
	}

	@FXML
	private void handleShowPlaylist(){
		if (playlistList.getSelectionModel().getSelectedItem() != null) {
			showPlaylistDetails(playlistList.getSelectionModel().getSelectedItem());
		}
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

	public IousephClient getApi() {
		return mainController.getApi();
	}

	public void setUser(User user) {
		mainController.setUser(user);
	}

	public Button getConnectButton() {
		return connectButton;
	}

	@FXML
	public void refreshPlaylists(){
		mainController.getPlaylists().clear();
		mainController.getPlaylists().addAll(mainController.getUser().getPlaylists().values());
	}
}
