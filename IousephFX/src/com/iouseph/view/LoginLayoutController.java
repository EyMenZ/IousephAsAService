package com.iouseph.view;

import com.iouseph.MainController;
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
		User user = mainController.getApi().connect("login", loginTextField.getText(), pwdPasswrodField.getText());
		if (user != null){
			mainController.setUser(user);
			loginStage.close();
		}else{
			loginLabel.setTextFill(Color.web("#FF0000"));
			pwdLabel.setTextFill(Color.web("#FF0000"));
		}
    }

    @FXML
	public void handleSignUp(){
    	mainController.setUser(mainController.getApi().connect("signup", loginTextField.getText(), pwdPasswrodField.getText()));
    }

    @FXML
	private void handleLoginTextField(KeyEvent ke) {
		if (ke.getCode().toString().equals("ENTER")) {
			handlelogin();
		}
	}

    @FXML
	private void handlePasswordField(KeyEvent ke) {
		if (ke.getCode().toString().equals("ENTER")) {
			handlelogin();
		}
	}
}
