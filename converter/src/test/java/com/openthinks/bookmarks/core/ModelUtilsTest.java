/**
 * 
 */
package com.openthinks.bookmarks.core;

import java.beans.IntrospectionException;
import java.io.IOException;

import org.junit.Test;

import com.openthinks.bookmarks.converter.json.JsonConverter;
import com.openthinks.bookmarks.converter.json.JsonConverterTest;
import com.openthinks.bookmarks.converter.json.JsonInput;
import com.openthinks.bookmarks.core.model.Bookmark;
import com.openthinks.bookmarks.core.model.BookmarkContainer;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class ModelUtilsTest {

	@Test
	public void test() throws IOException, IntrospectionException {
		BookmarkContainer bc = ModelUtils.fromJsonToNosql(new JsonConverter()
				.convert(new JsonInput(JsonConverterTest.class.getResourceAsStream("/bookmarks-2018-01-26.min.json"))));
		printBookmark(bc,bc.getTopBookmark());
	}

	private void printBookmark(BookmarkContainer bc,Bookmark bm) {
		System.out.println(bm);
		bc.getChildren(bm).forEach((child)->{
			printBookmark(bc,child);
		});
	}
	
	public static void main(String[] args) throws IOException, IntrospectionException {
		ModelUtilsTest instance = new ModelUtilsTest();
		instance.test();
	}

}
