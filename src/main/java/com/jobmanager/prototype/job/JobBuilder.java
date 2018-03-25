package com.jobmanager.prototype.job;

import java.util.List;


/**
 * JobBuilder is provided to make job implementation easier. To implement 
 * new job developer doesn't need to implement the AbstractJob class, they 
 * Just need to create instance of job builder and set the required properties
 * and call build method to get the instance of Job.
 * 
 * 
 *
 */
public class JobBuilder {

	private Priority priority;
	
	private JobSchedule schedule;
	
	private List<Task> tasks;
	
	private String name;
	
	
	
	/**
	 * Set the name of Job
	 * 
	 * @param name
	 * @return JobBuilder
	 */
	public JobBuilder setJobName(String name){
		this.name = name;
		return this;
	}
	
	/**
	 * Set the JobSchedule for job, if you want to run job at specific time of day
	 * It is optional
	 * 
	 * @param schedule
	 * @return JobBuilder
	 */
	public JobBuilder setJobSchedule(JobSchedule schedule){
		this.schedule=schedule;
		return this;
	}
	
	
	/**
	 * Set the Priority of Job.
	 * It is optional
	 * 
	 * @param priority
	 * @return JobBuilder
	 */
	public JobBuilder setJobPriority(Priority priority){
		this.priority = priority;
		return this;
	}
	
	/**
	 * Set the tasks to be performed by job
	 * 
	 * @param tasks
	 * @return JobBuilder
	 */
	public JobBuilder setJobTasks(List<Task> tasks){
		this.tasks= tasks;
		return this;
	}

	/**
	 * This method returns the instance of Job
	 * 
	 * @return Job
	 */
	public Job build(){
		return new JobImpl(this);
	}
	
	public Priority getPriority() {
		return priority;
	}

	
	public JobSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(JobSchedule schedule) {
		this.schedule = schedule;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public String getName() {
		return name;
	}

	
	
		
	
}
