package com.jobmanager.prototype.manger;

import java.util.List;

import com.jobmanager.prototype.job.Job;

/**
 * 
 * This interface defines the method for execution of Job. Any Job
 * of type {@Job} will be executed by JobManger.
  *
 */
public interface JobManager {
	
	/**
	 * This method will execute the job as per their priority.
	 * 
	 */
	void executeJob();
	
	/**
	 * User can add the Jobs to Job manager using this method.
	 * 
	 * @param jobs
	 */
	void addJob(List<Job> jobs);

}
