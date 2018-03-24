#JobManager

This is the prototype of job manager. Follow the below instructions to import the project.

	1. Download the project.
	2. Import as Maven project
	3. Run clean install
	4. Use the generated jar as dependency in your project.
	5. Use JobBuilder to create the Job.
	6. Add job to JobManager and call execute method.
	
Sample Code:

// Creating Job from Job Builder

  Job readJob = JobBuilder.getInstance()
	    .setJobName("ReadingJob")
	    .setJobPriority(Priority.HIGH)
	    .setJobTasks(getTaskList("I am Reading File")).build(); //Implement Task interface to create Task
    
  Job writeJob = JobBuilder.getInstance()
			.setJobName("WritingJob")
			.setJobTasks(getTaskList("I am Writing File"))
			.setJobPriority(Priority.LOW).build();  
    
    //Adding Job to List
    List<Job> jobs = new ArrayList<Job>();
	    jobs.add(readJob);		
    jobs.add(writeJob);
    
    //Adding Job in Jobmanage queue
    jobManager.addJob(jobs);
    
    //Executing the Job
    jobManager.executeJob();
