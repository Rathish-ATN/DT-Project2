package com.niit.Dao;

import java.util.List;

import com.niit.Model.Job;

public interface JobDao {
	
	void saveJob(Job job);
	List<Job> getAllJobs();
	void deleteJob(int id);
	void updateJob(Job job);
	Job getJob(int id);

}
