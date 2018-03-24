package com.jobmanager.prototype.manger;

import com.jobmanager.prototype.job.Job;


/**
 * This class will execute the job as per the scheduled time. 
 * 
 *
 */
public class ScheduledJobExecutor implements Runnable {

	private Job job;
	
	public ScheduledJobExecutor(Job job){
		this.job = job;
	}
	
	
	@Override
	public void run() {
		job.execute();
	}

}
