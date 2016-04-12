package com.iouseph;

import java.io.IOException;

import com.iouseph.api.Iapi;
import com.iouseph.api.IousephClient;
import com.iouseph.model.Playlist;
import com.iouseph.model.Track;
import com.iouseph.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

	private Stage mainStage;
	private BorderPane rootLayout;
	private ObservableList<Track> tracks = FXCollections.observableArrayList();
	private ObservableList<Playlist> playlists = FXCollections.observableArrayList();
	private IousephClient api = new IousephClient();
	private User user = null;

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from FXML file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			mainStage.setScene(scene);
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMainLayout() {
		try {
			// Load main layout.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("view/MainLayout.fxml"));
			VBox mainLayout = (VBox) loader.load();

			// Set main layout into the center of root layout.
			rootLayout.setCenter(mainLayout);

			// Give the controller access to the main app.
			MainLayoutController controller = loader.getController();
			controller.setMainController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 *
	 * @return
	 */
	public Stage getMainStage() {
		return mainStage;
	}

	/**
	 * Returns the data as an observable list of Tracks.
	 *
	 * @return
	 */
	public ObservableList<Track> getTracks() {
		return tracks;
	}

	/**
	 * Returns the data as an observable list of Playlists.
	 *
	 * @return
	 */
	public ObservableList<Playlist> getPlaylists() {
		return playlists;
	}

	/**
	 * Returns the api.
	 *
	 * @return
	 */
	public IousephClient getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		// TODO changer le nom dans l interface
	}

	public MainController() {

	}

	public MainController(Stage mainStage) {
		this();
		this.mainStage = mainStage;
		this.mainStage.setTitle("Iouseph");
		this.mainStage.getIcons().add(new Image("file:res/Iouseph-icon.png"));

		initRootLayout();

		showMainLayout();
	}



}
