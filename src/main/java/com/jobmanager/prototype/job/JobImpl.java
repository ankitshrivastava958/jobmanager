package com.jobmanager.prototype.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the generic implementation of any job.
 * It sets the required property to execute the job.
 * JobBuilder is provide to user get the instance of JobImpl.
 * Still if user want to provide own implementation then user
 * need to extend AbstractJob or implement Job interface.
 *
 */
class JobImpl extends AbstractJob {

	private static Logger LOG = LoggerFactory.getLogger(JobImpl.class);

	JobImpl(JobBuilder jobBuilder){
		LOG.debug("Initializing {}", jobBuilder.getName());
		setJobName(jobBuilder.getName());
		setPriority(jobBuilder.getPriority());
		setSchedule(jobBuilder.getSchedule());
		setTasks(jobBuilder.getTasks());
	}
	
}
