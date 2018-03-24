package com.jobmanager.prototype.listener;

import com.jobmanager.prototype.job.Job;

/**
 * This interface defines method to listen event during job execution
 * 
 */
public interface JobEventListener {
	
	/**
	 * This method will be called where we need to listen the event
	 * and it contains the set of instruction to perform at that event
	 * 
	 * @param job
	 */
	void notify(Job job);

}
