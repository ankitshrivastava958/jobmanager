/**
 * 
 */
package com.jobmanager.prototype.launcher;

import java.util.List;

import com.jobmanager.prototype.job.Job;
import com.jobmanager.prototype.manger.JobManager;
import com.jobmanager.prototype.manger.JobManagerImpl;

/**
 * This class provides the implementation to JobLauncher interface
 *
 */
public class JobLauncherImpl implements JobLauncher {

	private static JobLauncherImpl jobLauncher = new JobLauncherImpl();
	
	private JobLauncherImpl(){}
	
	public static JobLauncher getInstance(){
		return jobLauncher;
	}
	
	/* 
	 * {@inheritDoc}
	 */
	@Override
	public void launchJobs(List<Job> jobs) {
		
		if(jobs != null &&  !jobs.isEmpty()){
			JobManager jobManager = JobManagerImpl.getJobManagerInstance();
			jobManager.addJob(jobs);
			jobManager.executeJob();		
		} else{
			throw new IllegalArgumentException("No Jobs to Launch");
		}
		

	}

}
