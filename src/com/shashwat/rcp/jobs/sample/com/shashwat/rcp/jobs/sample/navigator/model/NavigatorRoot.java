package com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.model;

import java.io.File;

import org.eclipse.core.runtime.PlatformObject;

public class NavigatorRoot extends PlatformObject {
	private final String OSNAME = System.getProperty("os.name");
	private final boolean isWindows = OSNAME.indexOf("Windows") != -1;
	
	public FileBean[] getParentBeans() {
		File file = isWindows ? new File("c:/") : new File("/");
		FileBean parent = new FileBean(file);
		return parent.getChildren();
	}
}
