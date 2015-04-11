package com.shashwat.rcp.jobs.sample;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.FileView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.addView(FileView.ID, IPageLayout.LEFT, 0.33f, editorArea);
		layout.addView("org.eclipse.ui.views.ProgressView", IPageLayout.BOTTOM, 0.5f, editorArea);
	}
}
