/**
 * 
 */
package com.openthinks.bookmarks.core.model.nosql;

import org.junit.Test;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class BookmarkContainerTest {

	@Test
	public void testOrder() {
		BookmarkContainer parent = new BookmarkContainer();
		BookmarkContainer[] bmcs = new BookmarkContainer[10];
		for (int i = 0, j = bmcs.length; i < j; i++) {
			bmcs[i] = new BookmarkContainer();
			bmcs[i].setIndex(j-i);
			parent.addChild(bmcs[i]);
		}
		
		parent.sort().getChildren().forEach(System.out::println);
		
	}
}
