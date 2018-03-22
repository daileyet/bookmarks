/**
 * 
 */
package com.openthinks.bookmarks.client.ctrl;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.openthinks.bookmarks.client.Configs;
import com.openthinks.bookmarks.client.db.DBHelper;
import com.openthinks.bookmarks.core.model.Bookmark;
import com.openthinks.bookmarks.core.model.BookmarkContainer;
import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * @author dailey.yet@outlook.com
 *
 */
final class BookmarkControl implements UIControl {
	private Tree treeBookmark;
	private Text textLocation;
	private Browser browser;
	private final DBHelper dbHelper;
	private final String BM_HTML_TEMPLATE;
	private Map<String, BookmarkContainer> bmcMap;

	public BookmarkControl(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
		BM_HTML_TEMPLATE = loadTemplate();
	}

	private String loadTemplate() {
		return Configs.getTemplateAsString("/template.html");
	}

	void setComponent(Tree treeBookmark) {
		this.treeBookmark = treeBookmark;
	}

	void setComponent(Text textLocation) {
		this.textLocation = textLocation;
	}

	void setComponent(Browser browser) {
		this.browser = browser;
	}

	public void init() {
		initialBookmarkTree();
		initilaEventListeners();
	}

	@Override
	public void release() {
		if (bmcMap != null) {
			bmcMap.entrySet().forEach((entry) -> {
				dbHelper.save(entry.getKey(), entry.getValue());
				BookmarkContainer bmc = entry.getValue();
				Bookmark bm = bmc.getTopBookmark();
				bmc.getChildren(bm).forEach(child -> ProcessLogger.debug(child.toString()));
			});
		}

	}

	private void initialBookmarkTree() {
		bmcMap = dbHelper.findAll();
		bmcMap.forEach((name, bmc) -> {
			TreeItem top = new TreeItem(treeBookmark, SWT.NONE);
			top.setText(name);
			top.setExpanded(true);
			Bookmark bm = bmc.getTopBookmark();
			buildTree(bmc, top, bm);
		});
	}

	private void buildTree(final BookmarkContainer bmc, TreeItem parent, Bookmark bm) {
		TreeItem childTreeItem = new TreeItem(parent, SWT.NONE);
		childTreeItem.setData(bm);
		String name = bm.getTitle();
		name = name == null || name.equals("") ? bm.getGuid() : name;
		childTreeItem.setText(name);
		childTreeItem.setExpanded(bm.isExpand());
		bmc.getChildren(bm).forEach((childBm) -> {
			buildTree(bmc, childTreeItem, childBm);
		});
	}

	private void initilaEventListeners() {
		textLocation.addTraverseListener((event) -> {
			if (event.keyCode == 13) {
				event.doit = true;
				browser.setUrl(textLocation.getText());
			}
		});
		treeBookmark.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {// double click
				TreeItem treeItem = (TreeItem) e.item;
				if (treeItem.getItemCount() > 0) {
					textLocation.setText("");
					ProcessLogger.debug("Double-click node:{0}", treeItem.getData());
				} else {
					Bookmark bm = (Bookmark) treeItem.getData();
					textLocation.setText(bm.getUri());
					browser.setUrl(bm.getUri());
					browser.setText(outputHtml(bm));
				}
			}
		});

		treeBookmark.addListener(SWT.Expand, (event) -> {
			if (TreeItem.class.isInstance(event.item))
				return;
			TreeItem treeItem = (TreeItem) event.item;
			Bookmark bm = (Bookmark) treeItem.getData();
			if (bm != null) {
				bm.setExpand(true);
			}
		});

		treeBookmark.addListener(SWT.Collapse, (event) -> {
			if (TreeItem.class.isInstance(event.item))
				return;
			TreeItem treeItem = (TreeItem) event.item;
			Bookmark bm = (Bookmark) treeItem.getData();
			if (bm != null) {
				bm.setExpand(false);
			}
		});
	}

	// private void expandTree(TreeItem branch, boolean recursion) {
	// if (branch == null)
	// return;
	// TreeItem[] treeItems = branch.getItems();
	// if (treeItems != null) {
	// for (TreeItem treeItem : treeItems) {
	// if (treeItem.getItemCount() == 0)
	// continue;
	// treeItem.setExpanded(true);
	// Bookmark bm = (Bookmark) treeItem.getData();
	// if (bm != null) {
	// bm.setExpand(true);
	// }
	// if (recursion)
	// expandTree(treeItem, recursion);
	// }
	// }
	// }

	private String outputHtml(Bookmark model) {
		String guid = "";
		String title = "";
		long dateAdded = 0;
		long lastModified = 0;
		guid = model.getGuid();
		title = model.getTitle();
		dateAdded = model.getDateAdded();
		lastModified = model.getLastModified();
		String ret = BM_HTML_TEMPLATE.replace("${guid}", guid).replace("${title}", title)
				.replace("${dateAdded}", String.valueOf(dateAdded))
				.replace("${lastModified}", String.valueOf(lastModified));
		return ret;
	}

}
