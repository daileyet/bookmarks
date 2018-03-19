/**
 * 
 */
package com.openthinks.bookmarks.client.ctrl;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class Controls {

	public static void newBookmarkControl(Tree treeBookmark, Text textLocation, Browser browser) {
		BookmarkControl control = new BookmarkControl();
		control.setComponent(treeBookmark);
		control.setComponent(textLocation);
		control.setComponent(browser);
		control.init();
	}

}
