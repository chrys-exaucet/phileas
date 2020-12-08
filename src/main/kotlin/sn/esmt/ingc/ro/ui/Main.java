package sn.esmt.ingc.ro.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	
	private Stage primaryStage;

	
	private BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		//Met le titre de la fenêtre
		this.primaryStage.setTitle("Shortest Path");
		
		initialize();
		
		setNode();
		
		//affiche la fenêtre
		primaryStage.show();
		
	}

	//Charge le Noeud Primaire
	private void setNode() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/sn/esmt/ingc/ro/ui/view/PrimaryNode.fxml"));
			BorderPane node = loader.load();
			this.root.setCenter(node);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	// Charge la racine 
	private void initialize() {


		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/sn/esmt/ingc/ro/ui/view/Root.fxml"));
			this.root = loader.load();
			Scene scene = new Scene(this.root);
			this.primaryStage.setScene(scene);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	//Getters and Setters
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
