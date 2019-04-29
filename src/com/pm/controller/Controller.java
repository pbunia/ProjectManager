package com.pm.controller;


import java.awt.List;
import java.time.LocalDate;

import org.glassfish.jersey.server.spi.internal.ValueParamProvider.Priority;

import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Task;
import com.pm.view.TaskTile;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Controller {

	
	@FXML
	private VBox vBoxSB;
	
	@FXML
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
	
	@FXML
	public void addTaskButton() {
		Task task = new Task ();
		
	}
	@FXML
	public void removeTaskButton() {
	
		
	}
	
	public void editTaskButton() {
		
	}
	
	public void initialize() {
		
	}
}  

