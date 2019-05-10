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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SingleTaskViewController {
	
	private String userId;
	
	ObservableList<Priority> prioritylist = FXCollections.observableArrayList(Priority.values());
	ObservableList<Category> categorylist = FXCollections.observableArrayList(Category.values());
// these are items on SingleTaskView form
	@FXML
	private TextField txtTitle;
	@FXML
	private TextArea txtDescription;

	@FXML
	private Label txtUserID;

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

	@FXML
	Button btnAdd;

	@FXML
	Button btnReset;

	@FXML
	Button btnCancel;

	
	
	/*  @FXML private String dateCreationNow() {
	 
	 String date = String.valueOf(LocalDate.now()); System.out.println(date);
	 return date;
	  
	  } */
	 
	public void setUserId(String userId) {
		this.userId = userId;
		txtUserID.setText(userId);
	}

	@FXML
	private void initialize() {
		
		comboBoxPriority.setItems(prioritylist);
		comboBoxCategory.setItems(categorylist);
	}
	@FXML
	public void cancelButton(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();

	}
	@FXML
	public void resetButton(ActionEvent event) {
		txtTitle.setText(null);
		txtDescription.setText(null);
		txtGroupID.setText(null);

	}
	@FXML
	public void createTaskButton () {
		
		String groupId = txtGroupID.getText();
		String title = txtTitle.getText();
		String comment = txtDescription.getText();
		Category category = comboBoxCategory.getValue();
		LocalDate createDate = dateCreation.getValue();
		LocalDate finishDate = dateFinish.getValue();
		Priority priority = comboBoxPriority.getValue();
		boolean finishStatus = cboxComplete.hasProperties();
		
		
		PMClient client = new PMClient();
		client.postTask(new Task( this.userId, groupId, title, comment, category,
				createDate, finishDate, priority, finishStatus));
		
	
	}
		
}
