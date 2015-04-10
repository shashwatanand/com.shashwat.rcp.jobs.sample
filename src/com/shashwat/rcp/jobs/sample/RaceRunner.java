package com.shashwat.rcp.jobs.sample;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

public class RaceRunner extends Job {
	private int maxDistance = 2000;
	private String name;
	private String groupName;

	public RaceRunner(final String name, String groupName) {
		super(name);
		this.name = name;
		this.groupName = groupName;
	}
	
	public void registerJobChangeAdapter(JobChangeAdapter adapter) {
		addJobChangeListener(adapter);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		int count = 0;
		
		try {
			monitor.beginTask("From group " + groupName, maxDistance);
			while (count++ < maxDistance) {
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				
				int power = (int) (Math.random() * 100) + 1;
				final long wait = (int) (Math.random() * 1000) / power;
				
				monitor.subTask("Distance left " + count + "/" + maxDistance
						+ " power " + power);
				
				monitor.worked(1);
				Thread.sleep(wait);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}
		
		return Status.OK_STATUS;
	}
	
	@Override
	public String toString() {
		return this.name + " from group " + this.groupName;
	}
	
	@Override
	public boolean belongsTo(Object family) {
		return family == groupName;
	}
	
	public void race() {
		schedule();
	}
}
