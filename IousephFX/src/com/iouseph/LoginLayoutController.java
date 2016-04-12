package com.iouseph;

import com.iouseph.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginLayoutController {
	@FXML
	private TextField loginTextField;
	@FXML
	private PasswordField pwdPasswrodField;
	@FXML
	private Label loginLabel;
	@FXML
	private Label pwdLabel;

	private Stage loginStage;
	private MainLayoutController mainController;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// TODO lancer le serveur
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setLoginStage(Stage loginStage) {
		this.loginStage = loginStage;
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainController(MainLayoutController mainController) {
		this.mainController = mainController;
	}

	@FXML
	public void handlelogin() {
		connect("login");
	}

	@FXML
	public void handleSignUp() {
		connect("signup");
	}

	private void connect(String type){
		User user = mainController.getApi().connect(type, loginTextField.getText(), pwdPasswrodField.getText());
		if (user != null) {
			mainController.setUser(user);
			mainController.getConnectButton().setText("disconnect");
			mainController.setUsernameLabel(user.getUsername());
			loginStage.close();
		} else {
			loginLabel.setTextFill(Color.web("#FF0000"));
			pwdLabel.setTextFill(Color.web("#FF0000"));
		}
	}

	@FXML
	private void handleEnter(KeyEvent ke) {
		if (ke.getCode().toString().equals("ENTER")) {
			connect("login");
		}
	}

}
