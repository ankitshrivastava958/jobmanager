package com.jobmanager.prototype.manger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jobmanager.prototype.execption.JobExecutionException;
import com.jobmanager.prototype.job.Job;
import com.jobmanager.prototype.job.JobStatus;

/**
 * This is the implementation of JobManager
 * 
 *
 */
public class JobManagerImpl implements JobManager {
	
	
	private JobQueue jobQueue;
	
	private static JobManagerImpl jobManager = new JobManagerImpl();
	
	private JobManagerImpl(){}
	
	public static JobManager getJobManagerInstance(){
		return jobManager;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeJob() {
	 BlockingQueue<Job> jobs = jobQueue.getQueuedJobs();
	 int initialQueueSize = jobs.size();
	 for(int i=0;i<initialQueueSize;i++){
		 Job job = null;
		try {
			job = jobs.take();
		} catch (InterruptedException e) {			
			throw new JobExecutionException(e.getMessage(), e);
		}
		if(!job.isScheduled()) {
			 job.execute();
		}else {
			 executeScheduledJob(job);
		}
	 }
	 
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addJob(List<Job> jobs) {
		jobQueue = JobQueue.getJobQueueInstance();
		for(Job job : jobs) {
			jobQueue.addJob(job);
			job.setStatus(JobStatus.QUEUED);
		}
	}
	
	/**
	 * This method will add the job to ScheduledThreadPool, first it will calculate 
	 * the initial delay and then execute the job as per the JobSchedule.
	 * ZonedDateTime is used here to avoid any discrepancy in case of daylight saving 
	 * or timezone changes.
	 *  
	 * @param job
	 */
	private void executeScheduledJob(Job job){
		LocalDateTime localNow = LocalDateTime.now();
	    ZoneId currentZone = ZoneId.systemDefault();
	    ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
	    ZonedDateTime zonedNextRun ;
	    zonedNextRun = zonedNow.withHour(job.getSchedule().getHours())
	    			.withMinute(job.getSchedule().getMinutes())
	        		.withSecond(0);
	    if(zonedNow.compareTo(zonedNextRun) > 0){
	        	zonedNextRun = zonedNextRun.plusDays(1);
	    }
	    Duration duration = Duration.between(zonedNow, zonedNextRun);
	    long initalDelay = duration.getSeconds();		
		Runnable scheduleJob = new ScheduledJobExecutor(job);
		ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1);				 
		schedular.scheduleAtFixedRate(scheduleJob, initalDelay, 24*60*60, TimeUnit.SECONDS);
	}

}
