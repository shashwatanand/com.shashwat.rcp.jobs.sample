package com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.model;

import java.io.File;

public class FileBean {
	private File file;
	
	public FileBean(File file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return this.file.getName();
	}
	
	public boolean isDirectory() {
		return this.file.isDirectory();
	}
	
	public boolean hasChildren() {
		return file.list() != null;
	}
	
	public FileBean[] getChildren() {
		File[] files = this.file.listFiles();
		
		FileBean[] fileBeans = new FileBean[files.length];
		for (int i = 0; i < fileBeans.length; i++) {
			fileBeans[i] = new FileBean(files[i]);
		}
		
		return fileBeans;
	}

}
