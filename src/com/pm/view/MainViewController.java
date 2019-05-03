package com.pm.view;

import java.io.IOException;
import java.util.List;

import com.pm.Main;
import com.pm.model.client.PMClient;
import com.pm.model.task.Task;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainViewController {

	@FXML
	private VBox vBoxSB;
	
	private Main main;

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

	@FXML	
	private void addTaskButton() throws IOException {
		Main.showAddTaskScreen();
	}
	
	
	@FXML
	public void removeTaskButton() {

	}

	public void editTaskButton() {

	}

	public void initialize() {
		refresh();
	}
}
