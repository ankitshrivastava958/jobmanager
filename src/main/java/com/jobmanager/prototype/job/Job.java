package com.jobmanager.prototype.job;

/**
 * 
 * Job interface defines the contract to implement new job. This interface provides four 
 * methods which covers the entire life-cycle of any job. 
 *
 */
public interface Job {

	
	
	/**
	 * Method to set the name of Job
	 * @return
	 *  	String
	 */
	String getName();
	
	
	/**
	 * This method will execute the List of Tasks provided by user.
	 */
	void execute();
	
	/**
	 * This method will check whether the job is scheduled or not. 
	 * 
	 * @return	true or false
	 */
	boolean isScheduled();
	
	/**
	 * This method updates the status of Job based on different event.
	 * 
	 * @param status
	 * 		Possible values of status QUEUED,RUNNING,SUCCESS, FAILED
	 */
	void setStatus(JobStatus status);
	
	/**
	 * This method returned the schedule of job, if job is scheduled.
	 * JobSchedule will be provided by Implementor, as of now it supports
	 * only time of job to execute.
	 * 
	 * @return 
	 * 		JobSchedule
	 */
	JobSchedule getSchedule();
	
	
	/**
	 * This method returns the status of Job
	 * 
	 * @return 
	 * 		JobStatus
	 */
	JobStatus getStatus();

}
