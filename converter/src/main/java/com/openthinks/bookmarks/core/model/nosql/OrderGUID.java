/**
 * 
 */
package com.openthinks.bookmarks.core.model.nosql;

import com.openthinks.bookmarks.core.model.GUID;

/**
 * @author dailey.yet@outlook.com
 *
 */
public interface OrderGUID extends GUID, Comparable<OrderGUID> {

	public int index();

	@Override
	default int compareTo(OrderGUID o) {
		if (index() > o.index()) {
			return 1;
		} else if (index() == o.index()) {
			return 0;
		} else {
			return -1;
		}
	}

}
