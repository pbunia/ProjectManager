package com.pm.view;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.pm.ViewLoader;
import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Priority;
import com.pm.model.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskTile extends Pane {

	private Long id;
	private String userId;
	private String groupId;
	private String title;
	private String comment;
	private Category category;
	private LocalDate createDate;
	private LocalDate finishDate;
	private Priority priority;

	private HBox hBox1 = new HBox();
	private HBox hBox2 = new HBox();
	private VBox vBox1 = new VBox();
	private VBox vBox2 = new VBox();

	private Label categoryLabel;
	private Label createDateLabel;
	private Label finishDateLabel;
	private Label priorityLabel;
	private Label titleLabel;
	private Label userIdLabel;
	private Label commentLabel;
	private JFXCheckBox finishCB;
	private JFXButton delBtn;
	private JFXButton editBtn;

	public TaskTile(Long id, String userId, String groupId, String title, String comment, Category category,
			LocalDate createDate, LocalDate finishDate, Priority priority, boolean finishStatus) {
		this.id = id;
		this.userId = userId;
		this.groupId = groupId;
		this.title = title;
		this.comment = comment;
		this.category = category;
		this.createDate = createDate;
		this.finishDate = finishDate;
		this.priority = priority;

		setHBox1(category, createDate, finishDate, priority);
		setVBox1(title, userId, comment);
		setVBox2();
		setHBox2(finishStatus);
		getChildren().add(hBox2);
	}

	public void setHBox1(Category category, LocalDate createDate, LocalDate finishDate, Priority priority) {
		if (category == null) {
			categoryLabel = new Label("");
		} else
			categoryLabel = new Label(category.toString());

		if (createDate == null) {
			createDateLabel = new Label("##.##.##");
		} else
			createDateLabel = new Label(createDate.toString());

		if (finishDate == null) {
			finishDateLabel = new Label("##.##.##");
		} else
			finishDateLabel = new Label(finishDate.toString());

		if (priority == null) {
			priorityLabel = new Label("");
		} else
			priorityLabel = new Label(priority.toString());

		hBox1.getChildren().addAll(categoryLabel, createDateLabel, finishDateLabel, priorityLabel);
		hBox1.setSpacing(10);
	}

	public void setVBox1(String title, String userId, String comment) {
		if (title == null) {
			titleLabel = new Label("");
		} else
			titleLabel = new Label(title);
		titleLabel.setStyle("-fx-font-size: 20px;");
		titleLabel.setStyle("-fx-underline: true;");

		if (userId == null) {
			userIdLabel = new Label("Zalozone przez: ");
		} else
			userIdLabel = new Label("Zalozone przez: " + userId);

		if (comment == null) {
			commentLabel = new Label("");
		} else
			commentLabel = new Label(comment);

		commentLabel.setWrapText(true);
		commentLabel.setMaxWidth(300);

		vBox1.getChildren().addAll(titleLabel, userIdLabel, commentLabel, hBox1);
		vBox1.setPrefWidth(505); //525
	}

	public void setVBox2() {
		delBtn = new JFXButton("X");
		delBtn.setStyle("-fx-background-color: #E5E7E9;" + "-fx-text-fill: #212121;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 15px");
		delBtn.setOnAction(e -> deleteTask());
		editBtn = new JFXButton("E");
		editBtn.setStyle("-fx-background-color: #E5E7E9;" + "-fx-text-fill: #212121;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 15px");
		editBtn.setOnAction(e -> showEditTaskScreen());

		vBox2.getChildren().addAll(delBtn, editBtn);
		vBox2.setSpacing(5);
		vBox2.setAlignment(Pos.CENTER);
	}

	public void setHBox2(boolean finishStatus) {
		finishCB = new JFXCheckBox();
		finishCB.setStyle("-jfx-unchecked-color: #e5e7e9;");
		finishCB.setPadding(new Insets(10));
		finishCB.setSelected(finishStatus);
		finishCB.setOnAction(e -> changeStatus(finishCB.isSelected()));

		hBox2.getChildren().addAll(finishCB, vBox1, vBox2);
		hBox2.setSpacing(10);
		hBox2.setAlignment(Pos.CENTER);
		checkWhenFinish(finishStatus);
	}

	public Long getTheId() {
		return id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void changeStatus(boolean finishStatus) {
		PMClient client = new PMClient();
		client.putTask(new Task(id, userId, groupId, title, comment, category, createDate, finishDate, priority,
				finishStatus));
	}

	public void deleteTask() {
		PMClient client = new PMClient();
		client.deleteTask(this.getTheId());
	}

	public void showEditTaskScreen() {
		Stage editDialogStage = new Stage();
		PMClient client = new PMClient();
		Task theTask = client.getTask(this.getTheId());
		List<Task> tasks = client.getAllTasks();

		ObservableList<String> projectName = FXCollections.observableArrayList();
		for (Task t : tasks) {
			if (!projectName.contains(t.getGroupId()))
				projectName.add(t.getGroupId());
		}

		ViewLoader<AnchorPane, EditTaskViewController> viewLoader = new ViewLoader<>("view/EditTaskView.fxml");
		viewLoader.getController().setStage(editDialogStage);
		viewLoader.getController().setTask(theTask);
		viewLoader.getController().setProjectList(projectName);
		AnchorPane anchorPane = viewLoader.getLayout();
		Scene scene = new Scene(anchorPane);
		editDialogStage.setTitle(" EDYCJA ZADANIA");
		editDialogStage.getIcons().add(new Image("/images/Logo-icon-32x.png"));
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.setScene(scene);
		editDialogStage.setResizable(false);
		editDialogStage.showAndWait();
	}

	public void checkWhenFinish(boolean finishStatus) {
		if (finishStatus) {
			hBox2.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #808080;");
			titleLabel.setStyle("-fx-text-fill:#CAC3C3;");
			userIdLabel.setStyle("-fx-text-fill:#CAC3C3;");
			commentLabel.setStyle("-fx-text-fill:#CAC3C3;");
			categoryLabel.setStyle("-fx-text-fill:#CAC3C3;");
			createDateLabel.setStyle("-fx-text-fill:#CAC3C3;");
			finishDateLabel.setStyle("-fx-text-fill:#CAC3C3;");
			priorityLabel.setStyle("-fx-text-fill:#CAC3C3;");
		} else {
			if (Period.between(createDate, finishDate).getDays() < 2) {
				hBox2.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
						+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #D91F1F;");
			} else if (Period.between(createDate, finishDate).getDays() < 5) {
				hBox2.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
						+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #FFFF00;");
			} else {
				hBox2.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
						+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #00FF00;");
			}
		}
	}

}