package com.pm.controller;










import java.awt.List;

import com.pm.model.client.PMClient;
import com.pm.model.task.Task;
import com.pm.view.TaskTile;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Controller {

	
	@FXML
	private VBox vBoxSB;
	
	public void refresh() {
		vBoxSB.getChildren().clear();
		
		PMClient client = new PMClient();
		List<Task> tasks = client.getAllTasks();
		
		TaskTile taskTile[] = new TaskTile[tasks.size()];
		
		for(int i=0; i<tasks.size(); i++) {
			taskTile[i] = new TaskTile(tasks.getId(), tasks.getUserId(), tasks.getGroupId(), tasks.getTitle(), tasks.getComment(), tasks.getCategory(),
					tasks.getCreateDate(), tasks.getFinishDate(), tasks.getPriority(), tasks.isFinishStatus());
			vBoxSB.getChildren().add(taskTile[i]);
		}
		
		}
	
	
}
