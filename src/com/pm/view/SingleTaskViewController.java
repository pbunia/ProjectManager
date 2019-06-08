package com.pm.view;



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
/**Class defines actions to the objects (like buttons) in relation to the creation of the task by setting it's attributes
 * @author ireneusz Seredyn
 */
public class SingleTaskViewController {

	private String userId;
	private Stage stage;
	private ObservableList<String> projectList;

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

	public void setUserId(String userId) {
		this.userId = userId;
		txtUserID.setText(userId);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setProjectList(ObservableList<String> projectList) {
		projectList.remove("Wszystkie");
		this.projectList = projectList;
		comboBoxGroupID.setItems(this.projectList);
	}
	
	/**
	 * method initiate combo objects for priority and category ENUM classes used as one of the attributes defining task
	 */
	@FXML
	private void initialize() {
		comboBoxPriority.getItems().setAll(Priority.values());
		comboBoxCategory.getItems().setAll(Category.values());
		resetButton();
	}		
	/**
	 * method that is associated to the button that cancel/closes the task window without saving it on server
	 */
	@FXML
	public void cancelButton() {
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));

	}
	/**
	 * method that clears/reset all filled attributes defining a new task before it's  submitted/created
	 * 
	 */
	@FXML
	public void resetButton() {
		txtTitle.setText("Tytul zadania...");
		txtDescription.setText("Opis zadania...");
		comboBoxGroupID.setValue("Projekt");
		dateCreation.setValue(LocalDate.now());
		dateFinish.setValue(LocalDate.now());
		comboBoxPriority.setValue(Priority.NORMALNY);
		comboBoxCategory.setValue(Category.PRACA);
	}
	/**
	 * Method initialized by a button that creates a task upon completing all the fields with it's associated attributes
	 */
	@FXML
	public void createTaskButton() {
		String groupId = comboBoxGroupID.getValue();
		String title = txtTitle.getText();
		String comment = txtDescription.getText();
		Category category = comboBoxCategory.getValue();
		LocalDate createDate = dateCreation.getValue();
		LocalDate finishDate = dateFinish.getValue();
		Priority priority = comboBoxPriority.getValue();
		boolean finishStatus = cboxComplete.isSelected(); // .hasProperties(); <- drobna zmiana a robi kolosaln¹ ró¿nicê

		PMClient client = new PMClient();
		client.postTask(new Task(this.userId, groupId, title, comment, category, createDate, finishDate, priority,
				finishStatus));

		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
