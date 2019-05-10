package com.pm.view;

import java.time.LocalDate;

import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Priority;
import com.pm.model.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SingleTaskViewController {
	
	private String userId;
	private Stage stage;
	private ObservableList<String> projectList;
	
//	ObservableList<Priority> prioritylist = FXCollections.observableArrayList(Priority.values());
//	ObservableList<Category> categorylist = FXCollections.observableArrayList(Category.values());
// these are items on SingleTaskView form
	@FXML
	private TextField txtTitle;
	@FXML
	private TextArea txtDescription;

	@FXML
	private Label txtUserID;

	@FXML
	private ComboBox<String> comboBoxGroupID;

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

	
	
	/*  @FXML private String dateCreationNow() {
	 
	 String date = String.valueOf(LocalDate.now()); System.out.println(date);
	 return date;
	  
	  } */
	 
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
	
	@FXML
	private void initialize() {
		comboBoxPriority.getItems().setAll(Priority.values());
		comboBoxCategory.getItems().setAll(Category.values());
		resetButton();
	}
	@FXML
	public void cancelButton() {
		
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		
//		Stage stage = (Stage) btnCancel.getScene().getWindow();
//		stage.close();

	}
	@FXML
	public void resetButton() {
		txtTitle.setText("Title");
		txtDescription.setText("Comment");
		comboBoxGroupID.setValue("GroupId");
		dateCreation.setValue(LocalDate.now());
		dateFinish.setValue(LocalDate.now());
		comboBoxPriority.setValue(Priority.NORMALNY);
		comboBoxCategory.setValue(Category.PRACA);
	}
	@FXML
	public void createTaskButton () {
		
		String groupId = comboBoxGroupID.getValue();
		String title = txtTitle.getText();
		String comment = txtDescription.getText();
		Category category = comboBoxCategory.getValue();
		LocalDate createDate = dateCreation.getValue();
		LocalDate finishDate = dateFinish.getValue();
		Priority priority = comboBoxPriority.getValue();
		boolean finishStatus = cboxComplete.isSelected();  //.hasProperties(); <- drobna zmiana a robi kolosaln¹ ró¿nicê
		
		
		PMClient client = new PMClient();
		client.postTask(new Task( this.userId, groupId, title, comment, category,
				createDate, finishDate, priority, finishStatus));
		
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	
	}
		
}
