package com.shashwat.rcp.jobs.sample;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

public class Race {
	static List<RaceRunner> results = new ArrayList<RaceRunner>();
	
	static class Raferee extends Job {
		boolean done = false;
		int numOfRunners = 5;
		
		public Raferee(String name) {
			super(name);
		}
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			while (!done) {
				if (results.size() >= numOfRunners) {
					done = true;
				}
				
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Results : ");
			int place = 1;
			for (int index = 0; index <results.size(); index++) {
				RaceRunner raceRunner = results.get(index);
				
				if (raceRunner.getResult().getCode() == IStatus.OK) {
					System.out.println((place++) + " " + raceRunner);
				}
			}
			return Status.OK_STATUS;
		}
	}
	
	public Race() {}
	
	public void start() {
		RaceRunner first = new RaceRunner("First", "IN");
		RaceRunner second = new RaceRunner("Second", "GER");
		RaceRunner third = new RaceRunner("Third", "IN");
		RaceRunner fourth = new RaceRunner("Fourth", "IN");
		RaceRunner fifth = new RaceRunner("Fifth", "GER");
		
		
		Raferee raferee = new Raferee("Raferee");
		raferee.setSystem(true);
		raferee.schedule();
		
		
		JobChangeAdapter adapter = new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				results.add((RaceRunner)event.getJob());
			}
		};
		
		first.addJobChangeListener(adapter);
		second.addJobChangeListener(adapter);
		third.addJobChangeListener(adapter);
		fourth.addJobChangeListener(adapter);
		fifth.addJobChangeListener(adapter);
		
		first.race();
		second.race();
		third.race();
		fourth.race();
		fifth.race();
	}
}

