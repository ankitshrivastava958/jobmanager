package com.jobmanager.prototype.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jobmanager.prototype.job.Job;

/**
 * 
 * This class listens the job status event and perform action on it. 
 *
 */
public class JobStatusUpdateListener implements JobEventListener {

	
	private static Logger LOG = LoggerFactory.getLogger(JobStatusUpdateListener.class);
	
	/**
	 * {@inheritDoc}
	 */
	public void notify(Job job) {
		/*
		 * We can add code here to update the job status in DB. This is
		 * prototype so I have not added any DB operation.
		 */
		LOG.info("{} status changes to {}.", job.getName(),job.getStatus());
		
	}

	

}
