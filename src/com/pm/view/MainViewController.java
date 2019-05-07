package com.pm.view;

import java.io.IOException;
import java.util.List;

import com.pm.Main;
import com.pm.model.client.PMClient;
import com.pm.model.task.Task;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainViewController {

	@FXML
	private VBox vBoxSB;
	
	private static Stage primaryStage;

	@FXML
	public void refresh() {

		vBoxSB.getChildren().clear();

		PMClient client = new PMClient();
		List<Task> tasks = client.getAllTasks();

		TaskTile taskTile[] = new TaskTile[tasks.size()];

		for (int i = 0; i < tasks.size(); i++) {
			taskTile[i] = new TaskTile(tasks.get(i).getId(), tasks.get(i).getUserId(), tasks.get(i).getGroupId(),
					tasks.get(i).getTitle(), tasks.get(i).getComment(), tasks.get(i).getCategory(),
					tasks.get(i).getCreateDate(), tasks.get(i).getFinishDate(), tasks.get(i).getPriority(),
					tasks.get(i).isFinishStatus());
			vBoxSB.getChildren().add(taskTile[i]);
		}		
	}
		// this is new task window 
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
	}

	@FXML	
	private void addTaskButton() throws IOException {
		showAddTaskScreen();
	}
	
	@FXML
	public void removeTaskButton() {

	}
	
	@FXML
	public void editTaskButton() {

	}
	
	@FXML
	public void initialize() {
		
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(2500),
		        ae -> refresh()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
	}
}
