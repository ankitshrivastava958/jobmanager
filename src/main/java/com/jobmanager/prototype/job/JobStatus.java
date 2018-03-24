package com.jobmanager.prototype.job;

/**
 * Enum for job status
 *
 */
public enum JobStatus {

	QUEUED(1),
	RUNNING(2),
	SUCCESS(3),
	FAILED(4);
	
	int status;
	
	JobStatus(int status){
		this.status = status;
	}
	
	public int getJobStatus(){
		return status;
	}
	
	
}
