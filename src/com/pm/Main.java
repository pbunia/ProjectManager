package com.pm;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {

		ViewLoader<AnchorPane, Object> viewLoader = new ViewLoader<AnchorPane, Object>("view/TasksView.fxml");
		AnchorPane anchorPane = viewLoader.getLayout();

		Scene scene = new Scene(anchorPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Project Manager");
		primaryStage.show();
	}
	
	public static void showAddTaskScreen() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/SingleTaskView.fxml"));
		AnchorPane addNewTask = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Add new Task");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene  = new Scene(addNewTask);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
