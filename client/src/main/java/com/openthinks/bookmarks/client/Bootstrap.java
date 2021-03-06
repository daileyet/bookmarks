package com.openthinks.bookmarks.client;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;

import com.openthinks.bookmarks.client.ctrl.ControlManager;

public class Bootstrap {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Bootstrap.class, "/image/favicon.ico"));
		shell.setSize(667, 529);
		shell.setText("Bookmark Tools");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("File");

		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);

		MenuItem mntmLoad = new MenuItem(menu_1, SWT.NONE);
		mntmLoad.setText("Load");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");

		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("Help");

		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);

		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.setText("About");

		SashForm sashForm = new SashForm(shell, SWT.BORDER | SWT.SMOOTH);
		sashForm.setSashWidth(0);

		Group group = new Group(sashForm, SWT.NONE);
		group.setText("书签");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));

		Tree treeBookmark = new Tree(group, SWT.BORDER);

		Composite composite = new Composite(sashForm, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, true);
		composite.setLayout(gl_composite);

		Text textLocation = new Text(composite, SWT.BORDER);
		textLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		sashForm.setWeights(new int[] { 1, 2 });
		Browser browser = new Browser(composite, SWT.BORDER);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ControlManager.INSTANCE.newBookmarkControl(treeBookmark, textLocation, browser);
		ControlManager.INSTANCE.init();
		shell.addShellListener(new ShellListener() {

			@Override
			public void shellIconified(ShellEvent e) {

			}

			@Override
			public void shellDeiconified(ShellEvent e) {

			}

			@Override
			public void shellDeactivated(ShellEvent e) {

			}

			@Override
			public void shellClosed(ShellEvent e) {
				e.doit=false;
				//ControlManager.INSTANCE.release();
				shell.setVisible(false);
			}

			@Override
			public void shellActivated(ShellEvent e) {

			}
		});
		final Tray tray = display.getSystemTray();
		Image image = new Image(display, 16, 16);
		if (tray != null) {
			Menu trayMenu = new Menu(shell);
			MenuItem quitItem = new MenuItem(trayMenu, SWT.PUSH); 
			quitItem.setText("Quit");
			quitItem.setImage(SWTResourceManager.getImage(Bootstrap.class, "/image/exit.png"));
			quitItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ControlManager.INSTANCE.release();
					tray.dispose();
					shell.close();
					System.exit(1);
				}
			});
			final TrayItem item = new TrayItem(tray, SWT.NONE);
			item.setToolTipText("Bookmark Respository");
			item.addListener(SWT.Show, event -> System.out.println("show"));
			item.addListener(SWT.Hide, event -> System.out.println("hide"));
			item.addListener(SWT.Selection, event -> System.out.println("selection"));
			item.addListener(SWT.DefaultSelection, event -> System.out.println("default selection"));
			item.addListener(SWT.MenuDetect,event->{
				trayMenu.setLocation(display.getCursorLocation());
				trayMenu.setVisible(true);
			});
			item.setImage(shell.getImage());
			item.setHighlightImage(image);
		}

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		image.dispose();
		display.dispose();
	}
}
