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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.pm.model.task.Task;

public class PMClient {

	private List<Task> tasks;
	private Task task;

	public PMClient() {
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Task> getAllTasks() {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks/all").build();
		WebTarget webTarget = client.target(uri);
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		tasks = response.readEntity(new GenericType<List<Task>>() {
		});
		return tasks;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Task getTask(Long index) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.queryParam("index", index);
		task = webTarget.request().accept(MediaType.APPLICATION_JSON).get(Task.class);
		return task;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void postTask(Task task) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.json(task));
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void putTask(Task task) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(Entity.json(task));
	}

	@DELETE
	public void deleteTask(Long index) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromPath("https://jaztaskmanager.herokuapp.com/api/tasks").build();
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.queryParam("index", index);
		webTarget.request().delete();
	}

}
