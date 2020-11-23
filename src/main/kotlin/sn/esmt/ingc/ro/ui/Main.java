package sn.esmt.ingc.ro.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sn.esmt.ingc.ro.ui.view.PrimaryNodeController;

public class Main extends Application {

	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Shortest Path");
		initialize();
		setNode();
		primaryStage.show();
	}

	private void setNode() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/sn/esmt/java/ro/ui/view/PrimaryNode.fxml"));
			BorderPane node = loader.load();
			// node.getCenter().
			this.root.setCenter(node);

			PrimaryNodeController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void initialize() {


		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("sn/esmt/ingc/ro/ui/view/Root.fxml"));
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
}
