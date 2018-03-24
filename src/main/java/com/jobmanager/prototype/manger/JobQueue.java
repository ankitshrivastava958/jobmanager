package com.jobmanager.prototype.manger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.jobmanager.prototype.execption.JobExecutionException;
import com.jobmanager.prototype.job.Job;


/**
 * 
 * This class maintains the job queue. It is using PriorityBlockingQueue 
 * to hold the jobs. This is singleton class so only one instance will be created.
 *
 */
public class JobQueue {
	
	
	private BlockingQueue<Job> blockingJobQueue = new PriorityBlockingQueue<Job>();
	
	private static JobQueue jobQueue = new JobQueue();
	
	private JobQueue(){}
	
	public static JobQueue getJobQueueInstance(){
		return jobQueue;
	}
	
	/**
	 * Adds the job to queue, if queue is used by any another 
	 * thread then it will wait. Once another completes its operation 
	 * it puts the job object in queue.
	 * 
	 * @param job
	 */
	public void addJob(Job job){
		try {
			blockingJobQueue.put(job);
		} catch (InterruptedException e) {			
			throw new JobExecutionException(e.getMessage(), e);
		}
	}
	
	/**
	 * This method returns the queued jobs
	 * 
	 * @return blockingJobQueue
	 */
	public BlockingQueue<Job> getQueuedJobs(){
		return this.blockingJobQueue;
	}

		
}
