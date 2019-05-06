package com.pm.view;

import java.io.IOException;
import java.time.LocalDate;

import com.pm.ViewLoader;
import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Priority;
import com.pm.model.task.Task;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
	private static Stage primaryStage;

	private Label categoryLabel;
	private Label createDateLabel;
	private Label finishDateLabel;
	private Label priorityLabel;
	private Label titleLabel;
	private Label userIdLabel;
	private Label commentLabel;
	private CheckBox finishCB;
	private Button delBtn;
	private Button editBtn;

	public TaskTile(Long id, String userId, String groupId, String title, String comment, Category category,
			LocalDate createDate, LocalDate finishDate, Priority priority, boolean finishStatus) {
		this.id = id;
		this.userId=userId;
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
		categoryLabel = new Label(category.toString());
		createDateLabel = new Label(createDate.toString());
		finishDateLabel = new Label(finishDate.toString());
		priorityLabel = new Label(priority.toString());

		hBox1.getChildren().addAll(categoryLabel, createDateLabel, finishDateLabel, priorityLabel);
		hBox1.setSpacing(10);
	}

	public void setVBox1(String title, String userId, String comment) {
		titleLabel = new Label(title);
		userIdLabel = new Label("Utworzone przez: " + userId);
		commentLabel = new Label(comment);
		commentLabel.setWrapText(true);
		commentLabel.setMaxWidth(300);

		vBox1.getChildren().addAll(titleLabel, userIdLabel, commentLabel, hBox1);
	}
	
	public void setVBox2() {
		delBtn = new Button("X");
		delBtn.setOnAction(e->deleteTask());
		editBtn = new Button("E");
		editBtn.setOnAction(e->{
			try {
				showEditTaskScreen();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		vBox2.getChildren().addAll(delBtn, editBtn);
		vBox2.setSpacing(5);
	}
	


	public void setHBox2(boolean finishStatus) {
		finishCB = new CheckBox();
		finishCB.setSelected(finishStatus);
		finishCB.setOnAction(e->changeStatus(finishCB.isSelected()));

		hBox2.getChildren().addAll(finishCB, vBox1, vBox2);
		hBox2.setSpacing(20);
	}

	public Long getTheId() {
		return id;
	}

	public String getGroupId() {
		return groupId;
	}
	
	public void changeStatus(boolean finishStatus) {
		PMClient client = new PMClient();
		client.putTask(new Task(id, userId, groupId, title, comment, category,
			createDate, finishDate, priority, finishStatus));
	}
	
	public void deleteTask() {
		PMClient client = new PMClient();
		client.deleteTask(this.getTheId());
	}
	
	public void showEditTaskScreen() throws IOException {
		new EditTaskViewController(id);
		
		ViewLoader<AnchorPane, Object> viewLoader = new ViewLoader<AnchorPane, Object>("view/EditTaskView.fxml");
		AnchorPane anchorPane = viewLoader.getLayout();
		
		Stage addDialogStage = new Stage();
//		((EditTaskViewController) viewLoader.getController()).setStage(addDialogStage);
		
		
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(Main.class.getResource("view/SingleTaskView.fxml"));
//		AnchorPane addNewTask = loader.load();
		
		
		addDialogStage.setTitle("Edit");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene  = new Scene(anchorPane);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
		

}
	

}