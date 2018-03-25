package com.jobmanager.prototype.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.jobmanager.prototype.job.Job;
import com.jobmanager.prototype.job.JobBuilder;
import com.jobmanager.prototype.job.JobSchedule;
import com.jobmanager.prototype.job.JobStatus;
import com.jobmanager.prototype.job.Priority;
import com.jobmanager.prototype.job.Task;
import com.jobmanager.prototype.launcher.JobLauncher;
import com.jobmanager.prototype.launcher.JobLauncherImpl;


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
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(readJob);		
		jobs.add(writeJob);
		jobs.add(deleteJob);
		
		JobLauncher launcher = JobLauncherImpl.getInstance();
		launcher.launchJobs(jobs);
		
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
		
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(readJob);		
		jobs.add(writeJob);
		jobs.add(deleteJob);	
		
		JobLauncher launcher = JobLauncherImpl.getInstance();
		launcher.launchJobs(jobs);
		
		//Once the execution completed without exception status should be Success
		assertEquals(readJob.getStatus(),JobStatus.SUCCESS);
		assertEquals(writeJob.getStatus(),JobStatus.FAILED);
		assertEquals(deleteJob.getStatus(),JobStatus.SUCCESS);

	}
	
	
	/**
	 * This method test when job has no tasks defined then it shouldn't harm the other jobs
	 * and system
	 */
	@Test
	public void shouldExecuteTheJobWithoutAnyTask(){
		
		Job readJob = JobBuilder.getInstance()
		.setJobName("ReadingJob")
		.setJobPriority(Priority.HIGH)
		.setJobTasks(getTaskList("I am Reading File")).build();
		
		Job writeJob = JobBuilder.getInstance()
				.setJobName("WritingJob").build();
		
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(readJob);		
		jobs.add(writeJob);
		
		JobLauncher launcher = JobLauncherImpl.getInstance();
		launcher.launchJobs(jobs);
		
		//Once the execution completed without exception status should be Success
		assertEquals(readJob.getStatus(),JobStatus.SUCCESS);
		assertEquals(writeJob.getStatus(),JobStatus.SUCCESS);
		
	}

	/**
	 * This method tests the scenario where empty list of job passed to JobLauncher
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenJobLauncherCalledWithoutJob(){
		
		List<Job> jobs = new ArrayList<Job>();
		JobLauncher launcher = JobLauncherImpl.getInstance();
		launcher.launchJobs(jobs);		
		
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
