package com.pm.model.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.pm.model.task.Task;

/**This class communicates with server via 4 methods GET POST PUT DELETE
 * 
 * @author Mateusz Szyprowski
 *
 */
public class PMClient {

	private List<Task> tasks;
	private Task task;

	public PMClient() {
	}
	/**
	 * This is GET method that retrieves all tasks saved on server
	 * @return Tasks saved/available on server
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Task> getAllTasks() {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks/all").build();
		WebTarget webTarget = client.target(uri);
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		tasks = response.readEntity(new GenericType<List<Task>>() {});

		client.close();
		return tasks;
	}
	/**
	 * This is 2nd GET method that retrieves all available tasks with allocated ID number
	 * @param index request to retrieve exactly specified item/task
	 * @return task program retrieves exact task
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Task getTask(Long index) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.queryParam("index", index);
		try {
		task = webTarget.request().accept(MediaType.APPLICATION_JSON).get(Task.class);
		}
		catch (ResponseProcessingException e) {
			return null;
		}
		client.close();
		return task;
	}
	/**
	 * Method that is called when new task is created
	 * @param task Send request to save/store new task at the origin server
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Task postTask(Task task) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		Response r = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.json(task));
		Task t = r.readEntity(Task.class);
		client.close();
		return t;
	}
	/**
	 * Method used generally to updated existing data
	 * @param task Sends request to update existing record' details  and the origin server r
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Task putTask(Task task) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		Response r = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(Entity.json(task));
		Task t = r.readEntity(Task.class);
		client.close();
		return t;
	}
	/**
	 * Method called in order to delete existing record from the origin server
	 * @param index Call request to delete exactly specified record 
	 */
	@DELETE
	public void deleteTask(Long index) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.queryParam("index", index);
		webTarget.request().delete();
		client.close();
	}

}
