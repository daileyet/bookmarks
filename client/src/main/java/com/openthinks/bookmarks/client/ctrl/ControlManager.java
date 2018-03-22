/**
 * 
 */
package com.openthinks.bookmarks.client.ctrl;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import com.openthinks.bookmarks.client.db.DBHelper;

/**
 * @author dailey.yet@outlook.com
 *
 */
public enum ControlManager implements UIControl {
	INSTANCE;
	
	private final List<UIControl> cachedControlls;
	private final DBHelper dbHelper;
	
	private ControlManager() {
		dbHelper = new DBHelper();
		cachedControlls= new LinkedList<>();
	}
	public void newBookmarkControl(Tree treeBookmark, Text textLocation, Browser browser) {
		BookmarkControl control = new BookmarkControl(dbHelper);
		control.setComponent(treeBookmark);
		control.setComponent(textLocation);
		control.setComponent(browser);
		cachedControlls.add(control);
	}

	@Override
	public void init() {
		cachedControlls.forEach(UIControl::init);
	}
	
	@Override
	public void release() {
		cachedControlls.forEach(UIControl::release);
		dbHelper.destroy();
	}
}
