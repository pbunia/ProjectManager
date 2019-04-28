package com.pm;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		ViewLoader<AnchorPane, Object> viewLoader = new ViewLoader<AnchorPane, Object>("view/TasksView.fxml");
		AnchorPane anchorPane = viewLoader.getLayout();

		Scene scene = new Scene(anchorPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Project Manager");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
