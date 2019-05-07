package com.pm.view;

import java.io.IOException;

import com.pm.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {

	@FXML
	private TextField nameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	public static void showMainScreen() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/TasksView.fxml"));
		AnchorPane addMainView = loader.load();

		Stage addDialogStage = new Stage();
		Scene scene = new Scene(addMainView);
		addDialogStage.setScene(scene);
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws IOException {

		Window owner = submitButton.getScene().getWindow();
		if (nameField.getText().isEmpty()) {
			LoginAlert.showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your login");
			return;
		}

		if (passwordField.getText().isEmpty()) {
			LoginAlert.showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password");
			return;
		}

		LoginAlert.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!",
				"Welcome " + nameField.getText());

		showMainScreen();
	}

}
