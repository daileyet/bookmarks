package com.openthinks.bookmarks.core.model;

public class Bookmark implements GUID {
	private String guid;
	private String title;
	private long dateAdded;
	private long lastModified;
	private String uri;
	private String iconuri;
	private String description;

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIconuri() {
		return iconuri;
	}

	public void setIconuri(String iconuri) {
		this.iconuri = iconuri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
