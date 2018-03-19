/**
 * 
 */
package com.openthinks.bookmarks.core;

import java.beans.IntrospectionException;

import com.openthinks.bookmarks.converter.json.JsonOutput;
import com.openthinks.bookmarks.core.model.BookmarkContainer;
import com.openthinks.bookmarks.core.model.TypeCode;
import com.openthinks.libs.utilities.Checker;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.libs.utilities.json.support.JSONElement;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class ModelUtils {
	private ModelUtils() {
	}

	public static BookmarkContainer fromJsonToNosql(JsonOutput output) throws IntrospectionException {
		Checker.require(output).notNull();
		BookmarkContainer container = new BookmarkContainer();
		JSONElement jsonRoot = output.getJsonElement();
		if (jsonRoot.isObject()) {
			processToNosql(container, jsonRoot.asObject());
		} else {
			throw new UnsupportedOperationException("Not support convert json array as root");
		}
		return container;
	}

	private static void processToNosql(BookmarkContainer container, JSONObject jsonObject) throws IntrospectionException {
		TypeCode actualTypeCode = TypeCode.toTypeCode(jsonObject.getProperty("typeCode", Integer.class));
		container.addBookmark(jsonObject,actualTypeCode);
		switch (actualTypeCode) {
		case BRANCH:
			JSONArray jsonArray = jsonObject.getProperty("children", JSONArray.class);
			if (jsonArray != null) {
				for (Object child : jsonArray) {
					JSONObject jsonChild = (JSONObject) child;
					processToNosql(container, jsonChild);
				}
			}
			break;
		case LEAF:
			break;
		}
	}
	

}
