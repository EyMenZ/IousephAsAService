package com.iouseph;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Marcial Lopez-Ferrada, Youssef Zemmahi, Aymen Zalila
 * @version IousephFX
 *
 */
public class MainApp extends Application {

	@SuppressWarnings("unused")
	private MainController controller;

    /**
     * initializes the {@link MainController}
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage mainStage) {
       controller = new MainController(mainStage);
    }


	/**
	 * Main of IouspehFX
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
     * Constructor
     */
    public MainApp() {
    }

}
