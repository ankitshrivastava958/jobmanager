package com.jobmanager.prototype.job;

/**
 * Enum for priority of job
 *
 */
public enum Priority {

	HIGH(1),
	MEDIUM(2),
	LOW(3);
	
	int priority;
	
	Priority(int priority){
		this.priority = priority;
	}
	
	public Integer getSelectedPriority(){
		return priority;
	}
	
}
