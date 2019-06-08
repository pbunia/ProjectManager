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
/**
 * This is a testing class consisting of 7 tests 
 * @author Mateusz Szyprowki
 *
 */
public class Tests {

	Task taskTest = new Task("U_Test", "Project_Test", "Title_Test", "d", Category.DOM, LocalDate.now(), LocalDate.now().plusDays(10), Priority.WYSOKI,
			true);
	
	/**
	 * Method check DELETE method. Invokes tasks and those that match U_Test are deleted
	 */
	@After
	public void tearDown() {
		PMClient client = new PMClient();
		List<Task> tasks = client.getAllTasks();
		for (Task t : tasks) {
			if(t.getUserId().equals("U_Test"))
				client.deleteTask(t.getId());
		}
	}
	
	/**
	 * Method tests POST function. 
	 * Once tasks is posted into server this method invokes all tasks and checks if posted task is saved on origin server
	 */
	@Test
	public void postOneTaskAndCheckIfServerNotNull() {	
		PMClient client = new PMClient();
		client.postTask(taskTest);
		List<Task> tasks = client.getAllTasks();
		assertNotNull(tasks);
	}
	/**
	 * Method creates a task and is testing if can be saved on origin server.
	 */
	@Test
	public void setOneTaskAndgetResponse() {
		PMClient client = new PMClient();
		assertNotNull(client.postTask(taskTest));
	}
	/**
	 * Method posts task into server and subsequently check each task's attribute 
	 */
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
	/**
	 * Method is testing if attributes of posted task into origin server can be invoked/retrieved.
	 */
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
	
	/**
	 * Method is testing if PUT function is working properly by editing task's attributes and then invoking them
	 */
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
	/**
	 * Method is testing if PUT function is working properly by editing task's attributes and then invoking them
	 */
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
	/**
	 * Method is checking if POST and DELETE functions are working correctly 
	 * @return null 
	 */
	@Test
	public void postOneTaskDeleteAndCheckIfNull() {
		PMClient client = new PMClient();
		Task t = client.postTask(taskTest);
		client.deleteTask(t.getId());
		assertNull(client.getTask(t.getId()));
	}
	

}
