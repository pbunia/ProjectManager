package com.pm.view;

import java.time.LocalDate;

import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Priority;
import com.pm.model.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditTaskViewController {

	private Stage stage;
	private Long id;
	
// these are items on SingleTaskView form
	
	@FXML
	private TextField txtTitle;
	
	@FXML
	private TextArea txtDescription;

	@FXML
	private TextField txtUserID;

	@FXML
	private TextField txtGroupID;

	@FXML
	private ComboBox<Category> comboBoxCategory;

	@FXML
	private ComboBox<Priority> comboBoxPriority;

	@FXML
	private DatePicker dateCreation;

	@FXML
	private DatePicker dateFinish;

	@FXML
	private CheckBox cboxComplete;
	
	public EditTaskViewController (Long id) {
		this.id = id;
	}
	 

	@FXML
	private void initialize() {
		PMClient client = new PMClient();
		Task theTask = client.getTask(id);
		
		comboBoxPriority.getItems().setAll(Priority.values());
		comboBoxCategory.getItems().setAll(Category.values());
		
		txtUserID.setText(theTask.getUserId());
		txtGroupID.setText(theTask.getGroupId());
		txtTitle.setText(theTask.getTitle());
		txtDescription.setText(theTask.getComment());
		comboBoxCategory.setValue(theTask.getCategory());
		dateCreation.setValue(theTask.getCreateDate());
		dateFinish.setValue(theTask.getFinishDate());
		comboBoxPriority.setValue(theTask.getPriority());
		cboxComplete.setSelected(theTask.isFinishStatus());
	}
	
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	
	
	@FXML
	public void cancelButton() {
		new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST);
	}

	@FXML
	public void createTaskButton () {
		
		
		String userId = txtUserID.getText();
		String groupId = txtGroupID.getText();
		String title = txtTitle.getText();
		String comment = txtDescription.getText();
		Category category = comboBoxCategory.getValue();
		LocalDate createDate = dateCreation.getValue();
		LocalDate finishDate = dateFinish.getValue();
		Priority priority = comboBoxPriority.getValue();
		boolean finishStatus = cboxComplete.isSelected();
		
		
		PMClient client = new PMClient();
		client.putTask(new Task( id, userId, groupId, title, comment, category,
				createDate, finishDate, priority, finishStatus));
		
		new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST);
	
	}
		
}
