/**
 * 
 */
package com.openthinks.bookmarks.core.model;

import java.io.Serializable;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class RelationShip implements Serializable {
	private static final long serialVersionUID = -6466127811463945624L;

	private String parent;
	private String child;
	private int childType;
	private int childIndex;

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public int getChildType() {
		return childType;
	}

	public void setChildType(int childType) {
		this.childType = childType;
	}

	public int getChildIndex() {
		return childIndex;
	}

	public void setChildIndex(int childIndex) {
		this.childIndex = childIndex;
	}

}
