package com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

import com.shashwat.rcp.jobs.sample.com.shashwat.rcp.jobs.sample.navigator.model.NavigatorRoot;

public class FileView extends CommonNavigator {
	public static final String ID = "com.shashwat.rcp.jobs.sample.fileview";
	
	private Action action;

	public FileView() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Object getInitialInput() {
		return new NavigatorRoot();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		makeActions();
		hookContextMenu();
		contributeToActionBars();
	}
	
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(this.action);
		toolBarManager.add(new Separator());
	}

	private void fillLocalPullDown(IMenuManager menuManager) {
		menuManager.add(this.action);
		menuManager.add(new Separator());
	}

	private void hookContextMenu() {
		MenuManager menuManager = new MenuManager("#PopupMenu");
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				FileView.this.fillContextMenu(manager);
			}
		});
		
		Menu menu = menuManager.createContextMenu(getCommonViewer().getControl());
		getCommonViewer().getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, getCommonViewer());
	}

	protected void fillContextMenu(IMenuManager manager) {
		manager.add(this.action);
		manager.add(new Separator());
		
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(getCommonViewer().getControl().getShell(), "File Navigator View",
				message);
	}

	private void makeActions() {
		action = new Action() {
			@Override
			public void run() {
				showMessage("Action executed");
			}

			
		};
		
		action.setText("Action");
		action.setToolTipText("Action Tool Tip");
		action.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
