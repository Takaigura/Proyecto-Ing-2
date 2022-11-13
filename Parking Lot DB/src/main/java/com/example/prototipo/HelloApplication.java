package com.example.prototipo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 
 * @author Juan David, Satiago Restrepo, Stiven Alvarez
 *
 */
public class HelloApplication extends Application {

	/**
	 * 
	 * Start tiene la funcion de iniciar el escenario en el cual se va a dar uso al
	 * programa
	 * 
	 */
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Prototipo I ISII.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 620, 440);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Es el Main para iniciar y dar el lanzamiento al programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch();
	}
}