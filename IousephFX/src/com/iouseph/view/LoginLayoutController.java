package com.iouseph.view;

import com.iouseph.MainController;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginLayoutController {
	@FXML
	private TextField loginTextField;
	@FXML
    private PasswordField pwdPasswrodField;

	private Stage loginStage;
	private MainController mainController;


	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//TODO lancer le serveur
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
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

    @FXML
	public void handlelogin(){
		mainController.setUser(mainController.getApi().connect("login", loginTextField.getText(), pwdPasswrodField.getText()));
		//TODO fermer fenetre et changer username sur l interface
    }

    @FXML
	public void handleSignUp(){
    	mainController.setUser(mainController.getApi().connect("signup", loginTextField.getText(), pwdPasswrodField.getText()));
    }
}
