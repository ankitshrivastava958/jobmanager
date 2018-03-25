/**
 * 
 */
package com.jobmanager.prototype.launcher;

import java.util.List;

import com.jobmanager.prototype.job.Job;

/**
 *	This interface provides the mechanism to launch the job
 *	by passing the list of job
 *
 */
public interface JobLauncher {

	
	/**
	 * This method will take List of Job as parameter and 
	 * launch the JobManger to execute the jobs
	 * @param jobs
	 */
	void launchJobs(List<Job> jobs);
	
	
}
