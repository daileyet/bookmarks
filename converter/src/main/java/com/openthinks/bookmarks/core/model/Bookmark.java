package com.openthinks.bookmarks.core.model;

public class Bookmark implements GUID {
	private static final long serialVersionUID = 475711665149195742L;
	// guid
	private String guid;
	// 标题
	private String title;
	// 创建时间
	private Long dateAdded;
	// 修改时间
	private Long lastModified;
	// 链接地址
	private String uri;
	// icon地址
	private String iconuri;
	// 描述
	private String description;
	// 父级
	private String parent;
	// 同级中的顺序
	private int index;
	//是否展开
	private boolean expand;

	@Override
	public String guid() {
		return getGuid();
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

	public Long getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Long dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public boolean isExpand() {
		return expand;
	}
	
	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public boolean isBranch() {
		return uri == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!GUID.class.isInstance(obj))
			return false;
		GUID other = (GUID) obj;
		if (guid() == null) {
			if (other.guid() != null)
				return false;
		} else if (!guid().equals(other.guid()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (isBranch())
			return "Bookmark [guid=" + guid + ", title=" + title + ", dateAdded=" + dateAdded + ", lastModified="
					+ lastModified + ", description=" + description + ", parent=" + parent + ", index=" + index + ", expand="+ expand+ "]";
		else
			return "Bookmark [guid=" + guid + ", title=" + title + ", dateAdded=" + dateAdded + ", lastModified="
					+ lastModified + ", uri=" + uri + ", iconuri=" + iconuri + ", description=" + description
					+ ", parent=" + parent + ", index=" + index + "]";
	}

}
