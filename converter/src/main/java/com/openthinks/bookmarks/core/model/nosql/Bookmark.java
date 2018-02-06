/**
 * 
 */
package com.openthinks.bookmarks.core.model.nosql;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class Bookmark extends com.openthinks.bookmarks.core.model.Bookmark implements OrderGUID {
	private int index;

	@Override
	public int index() {
		return index;
	}

	public Bookmark setIndex(int index) {
		this.index = index;
		return this;
	}

}
