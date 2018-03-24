#JobManager

This is the prototype of job manager. Follow the below instructions to import the project.

Download the project.
Import as Maven project
Run clean install
Use the generated jar as dependency in your project.
Use JobBuilder to create the Job.
Add job to JobManager and call execute method.
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
