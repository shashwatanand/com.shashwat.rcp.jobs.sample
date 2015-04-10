package com.shashwat.rcp.jobs.sample;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.addView("org.eclipse.ui.views.ProgressView", IPageLayout.BOTTOM, 0.5f, editorArea);
	}
}
