package com.jobmanager.prototype.job;

public class JobSchedule {
	
	private int hours;
	
	private int minutes;
	
	
	public JobSchedule(int hours, int minutes){
		if((hours < 0 && hours > 23) || (minutes <0 && minutes > 59)){
			throw new IllegalArgumentException("Hours should be between 0 to 23 and Minutes should be "
					+ "between 0 to 59");
		}
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}
	
	

	
}
