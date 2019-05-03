package com.pm.view;

import java.time.LocalDate;
import java.util.Calendar;

import com.pm.model.task.Category;
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

public class SingleTaskViewController {

ObservableList<String> prioritylist = FXCollections.observableArrayList("LOW","MEDIUM","HIGH");
ObservableList<String> categorylist = FXCollections.observableArrayList("DOM","PRACA");
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
private  ComboBox<String> comboBoxCategory;

@FXML
private ComboBox<String> comboBoxPriority;

@FXML
private DatePicker dateCreation;

@FXML
private DatePicker dateFinish;

@FXML
private CheckBox cboxComplete;

@FXML Button btnAdd;

@FXML Button btnReset;

@FXML Button btnCancel;


@FXML
private  String dateCreationNow() {

String date = String.valueOf(LocalDate.now());	
System.out.println(date);
return date;

}

@FXML
private void initialize() {
	
	//comboBoxPriority.setValue("LOW");
	comboBoxPriority.setItems(prioritylist);
	comboBoxCategory.setItems(categorylist);
}



public void cancelButton (ActionEvent event) {
	Stage stage = (Stage) btnCancel.getScene().getWindow();
	stage.close();
	
}
public void resetButton(ActionEvent event) {
	txtTitle.setText(null);
	txtDescription.setText(null);
	txtUserID.setText(null);
	txtGroupID.setText(null);
	
	
}

public void createTaskButton

}



