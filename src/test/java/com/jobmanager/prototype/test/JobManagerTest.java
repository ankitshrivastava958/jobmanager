package com.jobmanager.prototype.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jobmanager.prototype.job.Job;
import com.jobmanager.prototype.job.JobBuilder;
import com.jobmanager.prototype.job.JobStatus;
import com.jobmanager.prototype.job.Priority;
import com.jobmanager.prototype.job.Task;
import com.jobmanager.prototype.manger.JobManager;
import com.jobmanager.prototype.manger.JobManagerImpl;


/**
 * 
 * Integration test without mocking any thing. Three jobs are 
 * created to test the prototype.
 *
 */
public class JobManagerTest {
	
	
	/**
	 * This method tests all the three jobs are executed as per their priority
	 */
	@Test
	public void shouldExecuteJobSuccessfullyAsPerPriority() {
		
		Job readJob = JobBuilder.getInstance()
		.setJobName("ReadingJob")
		.setJobPriority(Priority.HIGH)
		.setJobTasks(getTaskList("I am Reading File")).build();
		
		Job writeJob = JobBuilder.getInstance()
				.setJobName("WritingJob")
				.setJobTasks(getTaskList("I am Writing File"))
				.setJobPriority(Priority.LOW).build();
		
		Job deleteJob = JobBuilder.getInstance()
				.setJobName("DeletingJob")
				.setJobPriority(Priority.MEDIUM)
				.setJobTasks(getTaskList("I am Deleting File")).build();	
		JobManager jobManager = JobManagerImpl.getJobManagerInstance();
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(readJob);		
		jobs.add(writeJob);
		jobs.add(deleteJob);
		
		jobManager.addJob(jobs);
		//After adding jobs in jobqueue the status of Job should be Queued
		assertEquals(readJob.getStatus(),JobStatus.QUEUED);
		assertEquals(writeJob.getStatus(),JobStatus.QUEUED);
		assertEquals(deleteJob.getStatus(),JobStatus.QUEUED);
		
		jobManager.executeJob();
		//Once the execution completed without exception status should be Success
		assertEquals(readJob.getStatus(),JobStatus.SUCCESS);
		assertEquals(writeJob.getStatus(),JobStatus.SUCCESS);
		assertEquals(deleteJob.getStatus(),JobStatus.SUCCESS);

	}
	
	
	/**
	 * This method tests when one job fails out of three then remaining two job should 
	 * execute without interruption.
	 */
	@Test
	public void shouldExecuteOtherJobSuccessfullyWhenAnyJobFailed() {
		
		Job readJob = JobBuilder.getInstance()
		.setJobName("ReadingJob")
		.setJobPriority(Priority.HIGH)
		.setJobTasks(getTaskList("I am Reading File")).build();
		
		Job writeJob = JobBuilder.getInstance()
				.setJobName("WritingJob")
				.setJobTasks(getTaskListThrowsException())
				.setJobPriority(Priority.MEDIUM).build();
				
		Job deleteJob = JobBuilder.getInstance()
				.setJobName("DeletingJob")
				.setJobPriority(Priority.LOW)
				.setJobTasks(getTaskList("I am Deleting File")).build();	
		JobManager jobManager = JobManagerImpl.getJobManagerInstance();
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(readJob);		
		jobs.add(writeJob);
		jobs.add(deleteJob);	
		
		jobManager.addJob(jobs);
		//After adding jobs in jobqueue the status of Job should be Queued
		assertEquals(readJob.getStatus(),JobStatus.QUEUED);
		assertEquals(writeJob.getStatus(),JobStatus.QUEUED);
		assertEquals(deleteJob.getStatus(),JobStatus.QUEUED);
		
		jobManager.executeJob();
		//Once the execution completed without exception status should be Success
		assertEquals(readJob.getStatus(),JobStatus.SUCCESS);
		assertEquals(writeJob.getStatus(),JobStatus.FAILED);
		assertEquals(deleteJob.getStatus(),JobStatus.SUCCESS);

	}

	/**
	 * This method creates the List of Task which will be executed by Job.
	 * 
	 * @param string to print
	 * @return
	 *  		List<Task> tasks
	 */
	private List<Task> getTaskList(final String action){
		List<Task> tasks = new ArrayList<Task>();
		Task task = new Task(){

			@Override
			public void performTask() throws Exception {
				System.out.println(action);
			}
			
		};
		tasks.add(task);
		return tasks;
		
	}
	
	/**
	 * This method return the List of Task which throws runtime exception
	 * in the performTask method to test the exception scenario
	 *  
	 * @return List<Task>
	 */
	private List<Task> getTaskListThrowsException(){
		List<Task> tasks = new ArrayList<Task>();
		Task task = new Task(){

			@Override
			public void performTask() throws Exception {
				throw new RuntimeException();
			}
			
		};
		tasks.add(task);
		return tasks;
		
	}

}
