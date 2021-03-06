package com.pm.view;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jfoenix.controls.JFXComboBox;
import com.pm.ViewLoader;
import com.pm.model.client.PMClient;
import com.pm.model.task.Task;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class defines actions to objects on the main screen (Screen that holds list
 * of all created tasks)
 * 
 * @author Ireneusz Seredyn
 *
 */
public class MainViewController {

	private String userId;
	private ObservableList<String> projectList;
	private PMClient client;
	private LocalDateTime current = LocalDateTime.now();
	private volatile LocalDateTime time;
	private ExecutorService ex = Executors.newFixedThreadPool(1);
	private boolean t = true;

	@FXML
	private ScrollPane SPane;

	@FXML
	private volatile VBox vBoxSB;

	@FXML
	private JFXComboBox<String> projectCB;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Method retrieves all tasks that are saved/stored on server
	 */
	@FXML
	public void refresh() {
		List<Task> tasks = client.getAllTasks();
		Collections.sort(tasks, new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				boolean b1 = t1.isFinishStatus();
				boolean b2 = t2.isFinishStatus();
				return Boolean.compare(b1, b2);
			}
		});

		ObservableList<String> projectName = FXCollections.observableArrayList("Wszystkie Projekty");
		for (Task t : tasks) {
			if (!projectName.contains(t.getGroupId()))
				projectName.add(t.getGroupId());
		}

		projectList = projectName;
		projectCB.setItems(projectList);

		vBoxSB.getChildren().clear();
		TaskTile taskTile[] = new TaskTile[tasks.size()];

		for (int i = 0; i < tasks.size(); i++) {
			if (projectCB.getValue() == null || projectCB.getValue().equals("Wszystkie Projekty"))
				taskTile[i] = new TaskTile(tasks.get(i).getId(), tasks.get(i).getUserId(), tasks.get(i).getGroupId(),
						tasks.get(i).getTitle(), tasks.get(i).getComment(), tasks.get(i).getCategory(),
						tasks.get(i).getCreateDate(), tasks.get(i).getFinishDate(), tasks.get(i).getPriority(),
						tasks.get(i).isFinishStatus());
			else {
				if (projectCB.getValue().equals(tasks.get(i).getGroupId()))
					taskTile[i] = new TaskTile(tasks.get(i).getId(), tasks.get(i).getUserId(),
							tasks.get(i).getGroupId(), tasks.get(i).getTitle(), tasks.get(i).getComment(),
							tasks.get(i).getCategory(), tasks.get(i).getCreateDate(), tasks.get(i).getFinishDate(),
							tasks.get(i).getPriority(), tasks.get(i).isFinishStatus());
				else
					continue;
			}
			vBoxSB.getChildren().add(taskTile[i]);
		}
	}

	/**
	 * Method associated to the button and it initializes window to define new task
	 * prompting to declare attributes
	 */
	@FXML
	private void addTaskButton() {
		Stage addDialogStage = new Stage();
		ViewLoader<AnchorPane, SingleTaskViewController> viewLoader = new ViewLoader<>("view/SingleTaskView.fxml");
		viewLoader.getController().setUserId(userId);
		viewLoader.getController().setProjectList(projectList);
		viewLoader.getController().setStage(addDialogStage);
		AnchorPane anchorPane = viewLoader.getLayout();
		Scene scene = new Scene(anchorPane);
		addDialogStage.setTitle(" DODAWANIE NOWEGO ZADANIA");
		addDialogStage.getIcons().add(new Image("/images/Logo-icon-32x.png"));
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.setScene(scene);
		addDialogStage.setResizable(false);
		addDialogStage.showAndWait();
	}

	/**
	 * Method initiates refresh method within time loop that activates automatically
	 * and retrieves list of saved tasks
	 * 
	 */
	@FXML
	public void initialize() {
		client = new PMClient();
//		try {
//			refresh();
//
//		} catch (Exception e) {
//			System.out.println("Exception" + e);
//		}
		projectCB.setPromptText("Wszystkie Projekty");
		projectCB.setOnAction(event -> refresh());

		Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000), ae -> {
			try {
				if (time != null)
					if (!current.equals(time)) {
						current = time;
						refresh();
//						System.out.println("Refresh");
					}
			} catch (Exception e) {
				System.out.println("Exception" + e);
				e.printStackTrace();
			}
		}));
		timeline2.setCycleCount(Animation.INDEFINITE);
		timeline2.play();

		Runnable r = () -> {
			while (t == true) {
				try {
					time = client.getCurrent();
					Thread.sleep(2500);
				} catch (Exception e) {
					System.out.println("Exception" + e);
					e.printStackTrace();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		ex.execute(r);

	};

	public void close() {
		t = false;
		try {
			ex.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
