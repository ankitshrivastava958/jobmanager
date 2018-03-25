#JobManager

This is the prototype of job manager. Follow the below instructions to import the project.

	1. Download the project.
	2. Import as Maven project
	3. Run clean install
	4. Use the generated jar as dependency in your project.
	5. Use JobBuilder to create the Job.
	6. Use JobLauncher to launch the jobs.
	
Sample Code:

// Creating Job from Job Builder

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
		
	//Getting the instance of JobLauncher and call the launchJobs method to launch the jobs	
		JobLauncher launcher = JobLauncherImpl.getInstance();
		launcher.launchJobs(jobs);
