package com.pm;

import java.util.Optional;

import com.pm.view.MainViewController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


/**Main class launching entire program ,implementing Login window 
 * 
 * @author ireneusz seredyn
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("MENEDZER ZADAN");
		dialog.setHeaderText("Logowanie Uzytkownika");

		dialog.setGraphic(new ImageView(this.getClass().getResource("/images/Login_64x.png").toString()));

		ButtonType loginButtonType = new ButtonType("Zaloguj", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Uzytkownik");
		PasswordField password = new PasswordField();
		password.setPromptText("Haslo");

		grid.add(new Label("Uzytkownik:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Haslo:"), 0, 1);
		grid.add(password, 1, 1);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> username.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
			System.out
					.println("Uzytkownik = " + usernamePassword.getKey() + ", Haslo = " + usernamePassword.getValue());
			
			ViewLoader<AnchorPane, MainViewController> viewLoader = new ViewLoader<>("view/TasksView.fxml");
			viewLoader.getController().setUserId(usernamePassword.getKey());

			AnchorPane anchorPane = viewLoader.getLayout();

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/images/Logo-icon-32x.png"));
			primaryStage.setTitle(" MENEDZER ZADAN");
			primaryStage.setResizable(false);
			primaryStage.show();
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
