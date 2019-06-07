package com.pm.view;

/**Class implements actions to the objects (buttons) in relation to the tasks that are already created and edition of attributes are performed
 * @author ireneusz Seredyn
 */
import java.time.LocalDate;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Priority;
import com.pm.model.task.Task;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditTaskViewController {

	@FXML
	private JFXTextField txtTitle;

	@FXML
	private JFXTextArea txtDescription;

	@FXML
	private Label txtUserID;

	@FXML
	private JFXComboBox<String> comboBoxGroupID;

	@FXML
	private JFXComboBox<Category> comboBoxCategory;

	@FXML
	private JFXComboBox<Priority> comboBoxPriority;

	@FXML
	private JFXDatePicker dateCreation;

	@FXML
	private JFXDatePicker dateFinish;

	@FXML
	private JFXCheckBox cboxComplete;

	private Stage stage;
	private Task theTask;
	private ObservableList<String> projectList;
	/**
	 * method initiate combo objects for pririty and category enum classes used as one of the attributes defining task
	 */
	@FXML
	private void initialize() {
		comboBoxPriority.getItems().setAll(Priority.values());
		comboBoxCategory.getItems().setAll(Category.values());
	}
	/**
	 * Retrives single task with it's assocaited  details described by attributes
	 */
	public void loadTask() {
		txtUserID.setText(theTask.getUserId());
		comboBoxGroupID.setValue(theTask.getGroupId());
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

	public void setTask(Task theTask) {
		this.theTask = theTask;
		loadTask();
	}

	public void setProjectList(ObservableList<String> projectList) {
		this.projectList = projectList;
		comboBoxGroupID.setItems(this.projectList);
	}
	/**
	 * method that is associated to the button that cancel/closes the task window without saving it on server
	 */
	@FXML
	public void cancelButton() {
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	/**
	 * Method initialised by a button that creates a task upon completing all the fields with it's assicated attributes
	 */
	@FXML
	public void createTaskButton() {
		String userId = txtUserID.getText();
		String groupId = comboBoxGroupID.getValue();
		String title = txtTitle.getText();
		String comment = txtDescription.getText();
		Category category = comboBoxCategory.getValue();
		LocalDate createDate = dateCreation.getValue();
		LocalDate finishDate = dateFinish.getValue();
		Priority priority = comboBoxPriority.getValue();
		boolean finishStatus = cboxComplete.isSelected();

		PMClient client = new PMClient();
		client.putTask(new Task(theTask.getId(), userId, groupId, title, comment, category, createDate, finishDate,
				priority, finishStatus));

		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
