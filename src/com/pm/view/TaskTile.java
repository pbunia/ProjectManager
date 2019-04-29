package com.pm.view;

import java.time.LocalDate;

import com.pm.model.task.Category;
import com.pm.model.task.Priority;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskTile extends VBox{

	private Long id;
	private String groupId;

	private Stage stage= new Stage();
	private HBox hBox1 = new HBox();
	private HBox hBox2 = new HBox();
	private VBox vBox = new VBox();
	private Scene scene= new Scene(hBox2);

	private Label categoryLabel;
	private Label createDateLabel;
	private Label finishDateLabel;
	private Label priorityLabel;
	private Label titleLabel;
	private Label userIdLabel;
	private Label commentLabel;
	private CheckBox finishCB;

	public TaskTile(Long id, String userId, String groupId, String title, String comment, Category category,
			LocalDate createDate, LocalDate finishDate, Priority priority, boolean finishStatus) {
		this.id = id;
		this.groupId = groupId;

		setHBox1(category, createDate, finishDate, priority);
		setVBox(title, userId, comment);
		setHBox2(finishStatus);
		
		stage.setScene(scene);
		stage.show();

	}

	public void setHBox1(Category category, LocalDate createDate, LocalDate finishDate, Priority priority) {
		categoryLabel = new Label(category.toString());
		createDateLabel = new Label(createDate.toString());
		finishDateLabel = new Label(finishDate.toString());
		priorityLabel = new Label(priority.toString());

		hBox1.getChildren().addAll(categoryLabel, createDateLabel, finishDateLabel, priorityLabel);
		hBox1.setSpacing(10);
	}

	public void setVBox(String title, String userId, String comment) {
		titleLabel = new Label(title);
		userIdLabel = new Label("Utworzone przez: " + userId);
		commentLabel = new Label(comment);
		commentLabel.setWrapText(true);
		commentLabel.setMaxWidth(300);

		vBox.getChildren().addAll(titleLabel, userIdLabel, commentLabel, hBox1);
	}

	public void setHBox2(boolean finishStatus) {
		finishCB = new CheckBox();
		finishCB.setSelected(finishStatus);

		hBox2.getChildren().addAll(finishCB, vBox);
		hBox2.setSpacing(20);
	}

//	public Long getId() {
//		return id;
//	}

	public String getGroupId() {
		return groupId;
	}

}
