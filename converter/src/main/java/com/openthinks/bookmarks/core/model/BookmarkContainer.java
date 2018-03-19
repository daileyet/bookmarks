/**
 * 
 */
package com.openthinks.bookmarks.core.model;

import com.openthinks.libs.utilities.json.JSONObject;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class BookmarkContainer {


	public Bookmark addBookmark(JSONObject jsonObject,TypeCode typeCode) {
		Bookmark bookmark = new Bookmark();
		bookmark.setIndex(jsonObject.getProperty("index", Integer.class));
		bookmark.setGuid(jsonObject.getProperty("guid", String.class));
		bookmark.setTitle(jsonObject.getProperty("title", String.class));
		bookmark.setDateAdded(jsonObject.getProperty("dateAdded", Long.class));
		bookmark.setLastModified(jsonObject.getProperty("lastModified", Long.class));
		bookmark.setDescription(jsonObject.getProperty("description", String.class));
		if(typeCode==TypeCode.LEAF) {
			bookmark.setUri(jsonObject.getProperty("uri", String.class));
			bookmark.setIconuri(jsonObject.getProperty("iconuri", String.class));
		}else {
			
		}
		return bookmark;
	}

}
