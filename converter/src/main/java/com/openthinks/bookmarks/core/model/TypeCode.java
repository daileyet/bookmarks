/**
 * 
 */
package com.openthinks.bookmarks.core.model;

/**
 * @author dailey.yet@outlook.com
 *
 */
public enum TypeCode {
	LEAF(1), BRANCH(2);
	private int val;

	private TypeCode(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	public static final TypeCode toTypeCode(int val) {
		for (TypeCode type : TypeCode.values()) {
			if (type.val == val) {
				return type;
			}
		}
		return null;
	}
}
