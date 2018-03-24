package com.jobmanager.prototype.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jobmanager.prototype.listener.JobEventListener;
import com.jobmanager.prototype.listener.JobStatusUpdateListener;

/**
 * 
 * This Abstract class provides generic implementation of Job interface
 * which is used by all the jobs.
 * This class also implements Comparable interface which enables the job
 * to be executed as per their priority. If priority is not set by implementor 
 * then default priority will be used.
 *
 */
public abstract class AbstractJob implements Job, Comparable<AbstractJob> {
	
	private static Logger LOG = LoggerFactory.getLogger(AbstractJob.class);

	private String jobName;
	
	private JobStatus jobStatus;	
		
	private JobEventListener listener = new JobStatusUpdateListener();
	
	private Priority priority;
	
	private JobSchedule schedule;
	
	public List<Task> tasks;
	
	
	@Override
	public String getName(){
		return this.jobName;
	}
	
	
	/**
	 * {@inheritDoc}
	 */		
	@Override
	public void execute(){
		LOG.info("Execution of {} starts", getJobName());
		setStatus(JobStatus.RUNNING);
		try {
			for(Task task : getTasks()){
				task.performTask();
			}
		setStatus(JobStatus.SUCCESS);		
		LOG.info("Execution of {} ends", getJobName());
		}
		catch(Exception e) {
			LOG.error("Exception while Executing Job", e);
			setStatus(JobStatus.FAILED);
		}
				
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	public void setStatus(JobStatus status) {
		this.jobStatus = status;
		listener.notify(this);		
	};
	
	
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public boolean isScheduled() {
		return getSchedule()!=null;
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	public JobSchedule getSchedule() {
		return schedule;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobStatus getStatus(){
		return this.jobStatus;
	}
		
	/**
	 * {@inheritDoc}
	 * 
	 */	
	@Override
	public int compareTo(AbstractJob job) {
	
		if(getPriority() != null && job.getPriority() != null) {			
			return getPriority().getSelectedPriority()
					.compareTo(job.getPriority().getSelectedPriority());
		}
		return 0;
	}

	
	
	public JobStatus getJobStatus() {
		return jobStatus;
	}

		
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public Priority getPriority() {
		return priority;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}




	public void setSchedule(JobSchedule schedule) {
		this.schedule = schedule;
	}


	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	

}
