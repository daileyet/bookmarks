package com.openthinks.bookmarks.client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;

public class Bootstrap {
	private static Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(633, 525);
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
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(BorderLayout.NORTH);
		
		Browser browser = new Browser(composite, SWT.NONE);
		browser.setUrl("www.bing.com");
		sashForm.setWeights(new int[] {1, 2});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
