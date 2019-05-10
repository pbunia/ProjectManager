package com.pm.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pm.ViewLoader;
import com.pm.model.client.PMClient;
import com.pm.model.task.Task;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainViewController {

	private String userId;
	
	@FXML
	private ScrollPane SPane;
	
	@FXML
	private VBox vBoxSB;

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@FXML
	public void refresh() {

		vBoxSB.getChildren().clear();

		PMClient client = new PMClient();
		List<Task> tasks = client.getAllTasks();
		Collections.sort(tasks, new Comparator<Task>() {
		    @Override
		    public int compare(Task t1, Task t2) {
		        boolean b1 = t1.isFinishStatus();
		        boolean b2 = t2.isFinishStatus();
		        return Boolean.compare(b1, b2);
		    }
		});
		TaskTile taskTile[] = new TaskTile[tasks.size()];

		for (int i = 0; i < tasks.size(); i++) {
			taskTile[i] = new TaskTile(tasks.get(i).getId(), tasks.get(i).getUserId(), tasks.get(i).getGroupId(),
					tasks.get(i).getTitle(), tasks.get(i).getComment(), tasks.get(i).getCategory(),
					tasks.get(i).getCreateDate(), tasks.get(i).getFinishDate(), tasks.get(i).getPriority(),
					tasks.get(i).isFinishStatus());
			vBoxSB.getChildren().add(taskTile[i]);
		}		
	}

	@FXML	
	private void addTaskButton() {
		Stage addDialogStage = new Stage();
		ViewLoader<AnchorPane, SingleTaskViewController> viewLoader = new ViewLoader<>("view/SingleTaskView.fxml");
		viewLoader.getController().setUserId(userId);
		AnchorPane anchorPane = viewLoader.getLayout();
		Scene scene  = new Scene(anchorPane);
		
		addDialogStage.setTitle("Add new Task");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
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
