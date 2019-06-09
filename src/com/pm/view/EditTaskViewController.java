package com.pm.view;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class implements actions to the objects (buttons) in relation to the tasks
 * that are already created and allow an edition of attributes on task
 * 
 * @author ireneusz Seredyn
 */
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
	 * Method initiate combo objects for priority and category ENUM classes used as
	 * one of the attributes defining task
	 */
	@FXML
	private void initialize() {
		comboBoxPriority.getItems().setAll(Priority.values());
		comboBoxCategory.getItems().setAll(Category.values());
	}

	/**
	 * Retrieves single task with it's associated details described by attributes
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
	 * Method that is associated to the button that cancel/closes the task window
	 * without saving it on server
	 */
	@FXML
	public void cancelButton() {
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	/**
	 * Method initialized by a button that creates a task upon completing all the
	 * fields with it's associated attributes
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

	@FXML
	public void checkTextInputLength() {
		Pattern pattern = Pattern.compile(".{0,250}");
		TextFormatter<?> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});

		txtDescription.setTextFormatter(formatter);
	}
}
