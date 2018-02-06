/**
 * 
 */
package com.openthinks.bookmarks.core.model.nosql;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class BookmarkContainer extends com.openthinks.bookmarks.core.model.BookmarkContainer implements OrderGUID {
	private final List<OrderGUID> children;
	private int index;

	public BookmarkContainer() {
		this.children = new LinkedList<>();
	}

	public final List<OrderGUID> getChildren() {
		return children;
	}

	public final BookmarkContainer sort() {
		Collections.sort(children);
		return this;
	}

	public BookmarkContainer addChild(OrderGUID child) {
		this.children.add(child);
		return this;
	}

	public BookmarkContainer addChildren(Collection<OrderGUID> children) {
		this.children.addAll(children);
		return this;
	}

	public BookmarkContainer setIndex(int index) {
		this.index = index;
		return this;
	}

	@Override
	public int index() {
		return index;
	}
	
	public int size() {
		return children.size();
	}
	
	public boolean isEmpty() {
		return children.isEmpty();
	}

	@Override
	public String toString() {
		return "BookmarkContainer [index=" + index + "]";
	}

}
