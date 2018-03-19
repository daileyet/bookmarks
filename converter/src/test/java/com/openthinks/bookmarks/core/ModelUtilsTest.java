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
import com.openthinks.bookmarks.core.model.nosql.BookmarkContainer;
import com.openthinks.bookmarks.core.model.nosql.OrderGUID;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class ModelUtilsTest {

	@Test
	public void test() throws IOException, IntrospectionException {
		BookmarkContainer bc = ModelUtils.fromJsonToNosql(new JsonConverter()
				.convert(new JsonInput(JsonConverterTest.class.getResourceAsStream("/bookmarks-2018-01-26.min.json"))));

		printBC(bc);
	}

	private void printBC(OrderGUID bc) {
		if (bc instanceof BookmarkContainer) {
			System.out.println(bc);
			for(OrderGUID child:((BookmarkContainer)bc).getChildren()) {
				printBC(child);
			}
		} else {
			System.out.println(bc);
		}

	}
	
	public static void main(String[] args) throws IOException, IntrospectionException {
		ModelUtilsTest instance = new ModelUtilsTest();
		instance.test();
	}

}
