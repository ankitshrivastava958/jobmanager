/**
 * 
 */
package com.jobmanager.prototype.job;

/**
 * This interface defines the contract to implement the set of instructions
 * to performed by each task in job.
 *
 */	
public interface Task {
	
	/**
	 * This method will have the set of instructions to be executed be job.
	 * User doesn't need to handle any exception in his implementation. 
	 * It is already handled by job executer. 
	 *
	 * @throws Exception
	 */
	void performTask() throws Exception;

}
