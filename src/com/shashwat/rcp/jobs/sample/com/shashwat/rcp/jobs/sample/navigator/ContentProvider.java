package com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.model.FileBean;
import com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.model.NavigatorRoot;

public class ContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof NavigatorRoot) {
			return ((NavigatorRoot) inputElement).getParentBeans();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof FileBean) {
			return ((FileBean) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof FileBean) {
			return ((FileBean) element).hasChildren();
		}
		return false;
	}
}
