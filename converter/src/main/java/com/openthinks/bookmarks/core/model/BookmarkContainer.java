/**
 * 
 */
package com.openthinks.bookmarks.core.model;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class BookmarkContainer implements GUID {
	private String guid;
	private String title;
	private long dateAdded;
	private long lastModified;
	
	public BookmarkContainer() {
	}

	@Override
	public String guid() {
		return guid;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(long dateAdded) {
		this.dateAdded = dateAdded;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	
	
}
