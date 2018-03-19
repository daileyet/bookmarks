/**
 * 
 */
package com.openthinks.bookmarks.client.ctrl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.openthinks.bookmarks.client.Configs;
import com.openthinks.bookmarks.client.db.DBHelper;
import com.openthinks.bookmarks.core.model.nosql.Bookmark;
import com.openthinks.bookmarks.core.model.nosql.BookmarkContainer;
import com.openthinks.bookmarks.core.model.nosql.OrderGUID;
import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * @author dailey.yet@outlook.com
 *
 */
final class BookmarkControl {
	private Tree treeBookmark;
	private Text textLocation;
	private Browser browser;
	private DBHelper dbHelper;
	private final String BM_HTML_TEMPLATE;

	public BookmarkControl() {
		dbHelper = new DBHelper();
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

	void init() {
		initialBookmarkTree();
		initilaEventListeners();
	}

	private void initialBookmarkTree() {
		dbHelper.find().ifPresent((bmc) -> {
			TreeItem root = new TreeItem(treeBookmark, SWT.NONE);
			root.setText(Configs.MAIN_BOOKMARK_KEY);
			buildTree(root, bmc);
		});
	}

	private void buildTree(TreeItem parent, OrderGUID model) {
		TreeItem self = new TreeItem(parent,SWT.NONE);
		self.setData(model);
		if (model instanceof BookmarkContainer) {
			BookmarkContainer bmc = (BookmarkContainer) model;
			String name = bmc.getTitle();
			name = name == null || name.equals("") ? bmc.getGuid() : name;
			self.setText(name);
			bmc.getChildren().forEach((child) -> {
				buildTree(self, child);
			});
		} else {
			Bookmark bm = (Bookmark) model;
			self.setText(bm.getTitle());
		}

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
				OrderGUID model = (OrderGUID) treeItem.getData();
				if (model instanceof Bookmark) {
					Bookmark bm = (Bookmark) model;
					textLocation.setText(bm.getUri());
					browser.setUrl(bm.getUri());
					browser.setText(outputHtml(model));
				} else {
					textLocation.setText("");
					expandTree(treeItem);
				}
			}
		});
		
	}

	private void expandTree(TreeItem branch) {
		TreeItem[] treeItems = branch.getItems();
		if(treeItems!=null) {
			for(TreeItem treeItem : treeItems) {
				treeItem.setExpanded(true);
				expandTree(treeItem);
			}
		}
	}
	
	private String outputHtml(OrderGUID model) {
		String guid = "";
		String title = "";
		long dateAdded = 0;
		long lastModified = 0;
		if (model instanceof BookmarkContainer) {
			guid = ((BookmarkContainer) model).getGuid();
			title = ((BookmarkContainer) model).getTitle();
			dateAdded = ((BookmarkContainer) model).getDateAdded();
			lastModified = ((BookmarkContainer) model).getLastModified();
		} else if (model instanceof Bookmark) {
			guid = ((Bookmark) model).getGuid();
			title = ((Bookmark) model).getTitle();
			dateAdded = ((Bookmark) model).getDateAdded();
			lastModified = ((Bookmark) model).getLastModified();
		}
		String ret = BM_HTML_TEMPLATE.replace("${guid}", guid).replace("${title}", title)
				.replace("${dateAdded}", String.valueOf(dateAdded))
				.replace("${lastModified}", String.valueOf(lastModified));
		return ret;
	}

}
