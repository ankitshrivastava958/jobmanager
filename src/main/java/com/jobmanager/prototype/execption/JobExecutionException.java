package com.jobmanager.prototype.execption;


/**
 * Custom exception to handle exception scenarios
 *	
 */
public class JobExecutionException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JobExecutionException(String cause, Exception ex){
		super(cause, ex);
	}
	

}
