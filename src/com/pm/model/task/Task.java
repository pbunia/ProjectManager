package com.pm.model.task;

import java.time.LocalDate;

/**
 * Class defines task object's parameters
 * @author Mateusz Szyprowski
 *
 */
public class Task {

	private Long id;
	private String userId;
	private String groupId;
	private String title;
	private String comment;
	private Category category;
	private LocalDate createDate;
	private LocalDate finishDate;
	private Priority priority;
	private boolean finishStatus;

	public Task() {

	}

	/**Method defining Exact task with it's associated id with the rest of parametes below
	 * that can be called/invoked by the program
	 * 
	 * @param id uniqe task id given by server when stored/creasted on server
	 * @param userId user name 
	 * @param groupId
	 * @param title
	 * @param comment
	 * @param category
	 * @param createDate
	 * @param finishDate
	 * @param priority
	 * @param finishStatus
	 */
	public Task(Long id, String userId, String groupId, String title, String comment, Category category,
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
		this.finishStatus = finishStatus;
	}
	/** Method defining task with it's paremeters that can be called/invoked by the program
	 * 
	 * @param userId
	 * @param groupId
	 * @param title
	 * @param comment
	 * @param category
	 * @param createDate
	 * @param finishDate
	 * @param priority
	 * @param finishStatus
	 */
	public Task(String userId, String groupId, String title, String comment, Category category, LocalDate createDate,
			LocalDate finishDate, Priority priority, boolean finishStatus) {
		this.userId = userId;
		this.groupId = groupId;
		this.title = title;
		this.comment = comment;
		this.category = category;
		this.createDate = createDate;
		this.finishDate = finishDate;
		this.priority = priority;
		this.finishStatus = finishStatus;
	}
/**
 * Standard Get method invoking one attribute from Task method
 * @return id retrive exact task id
 */
	public Long getId() {
		return id;
	}
/**
 * Standard method that sets/declare one of the attributes that belongs to method task
 * @param id sets task id
 */
	public void setId(Long id) {
		this.id = id;
	}
/**
 *  Standard Get method invoking one attribute from Task method
 * @return userId gets user id, login name that user defines when logs in
 */
	public String getUserId() {
		return userId;
	}
/**
 * Standard method that sets/declare one of the attributes that belongs to method task
 * @param userId sets user's name
 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
/**
 * Standard Get method invoking one attribute from Task method
 * @return groupId retrieve defined group id used to categorize task for better management by user 
 */
	public String getGroupId() {
		return groupId;
	}
/**
 * Standard method that sets/declare one of the attributes that belongs to method task
 * @param groupId sets group id name that 
 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
/**
 * Standard Get method invoking one attribute from Task method
 * @return title  Gest title of precise task
 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param title This method can be used to set task's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Standard Get method invoking one attribute from Task method
	 * @return comment when called gets comment String associated to exact task
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param comment  sets comment of defined task
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * Standard Get method invoking one attribute from Task method
	 * @return category invokes category chosen by user that is  associated to a task
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param category sets category that user wants to associate with the task
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * Standard Get method invoking one attribute from Task method
	 * @return createDate brings date that define when task was created
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param createDate sets creation task's date
	 */
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	/**
	 * Standard Get method invoking one attribute from Task method
	 * @return finishDate retrive end date of task
	 */
	public LocalDate getFinishDate() {
		return finishDate;
	}
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param finishDate sets end date for the task
	 */
	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}
	/**
	 * Standard Get method invoking one attribute from Task method
	 * @return priority retrives priority signature associated with a task
	 */
	public Priority getPriority() {
		return priority;
	}
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param priority sets priority on task 
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	/**
	 * Standard Get method invoking one attribute from Task method
	 * @return finishStatus  Invoke the finish status of the task. Task can be completed or not
	 */
	public boolean isFinishStatus() {
		return finishStatus;
	}
	/**
	 * Standard method that sets/declare one of the attributes that belongs to method task
	 * @param finishStatus sets status of the task to complete. task can be completed or not
	 */
	public void setFinishStatus(boolean finishStatus) {
		this.finishStatus = finishStatus;
	}
}
