package com.pm.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.pm.model.client.PMClient;
import com.pm.model.task.Category;
import com.pm.model.task.Priority;
import com.pm.model.task.Task;

public class Tests {

	Task taskTest = new Task("U_Test", "Project_Test", "Title_Test", "d", Category.DOM, LocalDate.now(), LocalDate.now().plusDays(10), Priority.WYSOKI,
			true);
	@After
	public void tearDown() {
		PMClient client = new PMClient();
		List<Task> tasks = client.getAllTasks();
		for (Task t : tasks) {
			if(t.getUserId().equals("U_Test"))
				client.deleteTask(t.getId());
		}
	}
	
	@Test
	public void postOneTaskAndCheckIfServerNotNull() {	
		PMClient client = new PMClient();
		client.postTask(taskTest);
		List<Task> tasks = client.getAllTasks();
		assertNotNull(tasks);
	}
	
	@Test
	public void setOneTaskAndgetResponse() {
		PMClient client = new PMClient();
		assertNotNull(client.postTask(taskTest));
	}
	
	@Test
	public void postOneTaskAndCheckIfDataCorrect() {
		PMClient client = new PMClient();
		Task t = client.postTask(taskTest);
		assertEquals(t.getUserId(), "U_Test");
		assertEquals(t.getGroupId(), "Project_Test");
		assertEquals(t.getTitle(), "Title_Test");
		assertEquals(t.getComment(), "d");
		assertEquals(t.getCategory(), Category.DOM);
		assertEquals(t.getCreateDate(), LocalDate.now());
		assertEquals(t.getFinishDate(), LocalDate.now().plusDays(10));
		assertEquals(t.getPriority(), Priority.WYSOKI);
		assertTrue(t.isFinishStatus());
	}
	
	@Test
	public void postOneTaskGetPostedTaskAndCheckIfDataCorrect() {
		PMClient client = new PMClient();
		Task t = client.postTask(taskTest);
		Task tp = client.getTask(t.getId());
		assertEquals(tp.getUserId(), "U_Test");
		assertEquals(tp.getGroupId(), "Project_Test");
		assertEquals(tp.getTitle(), "Title_Test");
		assertEquals(tp.getComment(), "d");
		assertEquals(tp.getCategory(), Category.DOM);
		assertEquals(tp.getCreateDate(), LocalDate.now());
		assertEquals(tp.getFinishDate(), LocalDate.now().plusDays(10));
		assertEquals(tp.getPriority(), Priority.WYSOKI);
		assertTrue(tp.isFinishStatus());
	}
	
	
	@Test
	public void postOneTaskEditTaskAndCheckIfDataCorrect() {
		PMClient client = new PMClient();
		Task tp = client.postTask(taskTest);
		Task t = client.putTask(new Task(tp.getId(),"U_Test", "Project_Test2", "Title_Test2", "Comment", Category.DOM, LocalDate.now(), LocalDate.now().plusDays(10), Priority.WYSOKI,
				true));
		assertEquals(t.getUserId(), "U_Test");
		assertEquals(t.getGroupId(), "Project_Test2");
		assertEquals(t.getTitle(), "Title_Test2");
		assertEquals(t.getComment(), "Comment");
		assertEquals(t.getCategory(), Category.DOM);
		assertEquals(t.getCreateDate(), LocalDate.now());
		assertEquals(t.getFinishDate(), LocalDate.now().plusDays(10));
		assertEquals(t.getPriority(), Priority.WYSOKI);
		assertTrue(t.isFinishStatus());
	}
	
	@Test
	public void postOneTaskEditGetPostedTaskAndCheckIfDataCorrect() {
		PMClient client = new PMClient();
		Task tp = client.postTask(taskTest);
		Task ti = client.putTask(new Task(tp.getId(),"U_Test", "Project_Test2", "Title_Test2", "Comment", Category.DOM, LocalDate.now(), LocalDate.now().plusDays(10), Priority.WYSOKI,
				true));
		Task t = client.getTask(ti.getId());
		assertEquals(t.getUserId(), "U_Test");
		assertEquals(t.getGroupId(), "Project_Test2");
		assertEquals(t.getTitle(), "Title_Test2");
		assertEquals(t.getComment(), "Comment");
		assertEquals(t.getCategory(), Category.DOM);
		assertEquals(t.getCreateDate(), LocalDate.now());
		assertEquals(t.getFinishDate(), LocalDate.now().plusDays(10));
		assertEquals(t.getPriority(), Priority.WYSOKI);
		assertTrue(t.isFinishStatus());
	}
	
	@Test
	public void postOneTaskDeleteAndCheckIfNull() {
		PMClient client = new PMClient();
		Task t = client.postTask(taskTest);
		client.deleteTask(t.getId());
		assertNull(client.getTask(t.getId()));
	}
	

}
