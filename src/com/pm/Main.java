package com.pm;

import com.pm.view.MainViewController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {

		ViewLoader<AnchorPane, MainViewController> viewLoader = new ViewLoader<>("view/TasksView.fxml");
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
