package com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator;

import java.util.Hashtable;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.model.FileBean;

public class LabelProvider implements ILabelProvider {
	private Hashtable<Program, Image> cache = new Hashtable<Program, Image>();

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(Object element) {
		FileBean file = (FileBean) element;
		String name = file.toString();
		Image image = null;
		
		int dot = name.indexOf('.');
		
		if (dot != -1) {
			String ext = name.substring(dot);
			Program program = Program.findProgram(ext);
			
			if (program != null) {
				image = getIconFromProgram(program);
			}
		}
		
		if (image == null) {
			image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
		}
		
		return file.isDirectory() ? PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER) :
			image;
	}

	private Image getIconFromProgram(Program program) {
		Image image = this.cache.get(program);
		if (image != null) {
			ImageData imageData = program.getImageData();
			if (imageData != null) {
				image = new Image(Display.getDefault(), imageData);
				this.cache.put(program, image);
			}
		}
		return image;
	}

	@Override
	public String getText(Object element) {
		return element.toString();
	}

}
