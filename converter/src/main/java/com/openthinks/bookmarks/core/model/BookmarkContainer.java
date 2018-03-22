/**
 * 
 */
package com.openthinks.bookmarks.core.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.openthinks.libs.utilities.Result;
import com.openthinks.libs.utilities.json.JSONObject;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class BookmarkContainer implements Serializable {
	private static final long serialVersionUID = 5676628117500363210L;
	private final Map<GUID, Group> groupMap = new ConcurrentHashMap<>();
	private Bookmark topBookmark;

	public Bookmark addBookmark(Bookmark parentBookmark, JSONObject jsonObject, TypeCode typeCode) {
		Bookmark bookmark = new Bookmark();
		bookmark.setIndex(jsonObject.getProperty("index", Integer.class));
		bookmark.setGuid(jsonObject.getProperty("guid", String.class));
		bookmark.setTitle(jsonObject.getProperty("title", String.class));
		bookmark.setDateAdded(jsonObject.getProperty("dateAdded", Long.class));
		bookmark.setLastModified(jsonObject.getProperty("lastModified", Long.class));
		bookmark.setDescription(jsonObject.getProperty("description", String.class));
		if (typeCode == TypeCode.LEAF) {
			bookmark.setUri(jsonObject.getProperty("uri", String.class));
			bookmark.setIconuri(jsonObject.getProperty("iconuri", String.class));
		}
		if (parentBookmark != null) {
			bookmark.setParent(parentBookmark.guid());
			Group group = groupMap.get(parentBookmark);
			if (group == null) {
				group = new Group(parentBookmark);
				groupMap.put(parentBookmark, group);
			}
			group.add(bookmark);
		} else {
			topBookmark = bookmark;
			groupMap.put(bookmark, new Group(bookmark));
		}
		return bookmark;
	}

	/**
	 * get top level book mark
	 * 
	 * @return {@link Bookmark}
	 */
	public final Bookmark getTopBookmark() {
		return topBookmark;
	}

	/**
	 * get the children for assigned parent
	 * 
	 * @param parent
	 *            {@link GUID}
	 * @return list of {@link Bookmark}
	 */
	public final List<Bookmark> getChildren(GUID parent) {
		Group group = groupMap.get(parent);
		if (group == null) {
			return Collections.emptyList();
		}
		return group.children;
	}

	/**
	 * find {@link Bookmark} by assigned {@link GUID}
	 * 
	 * @param guid
	 *            {@link GUID}
	 * @return optional of {@link Bookmark}
	 */
	public Optional<Bookmark> find(final GUID guid) {
		boolean isGroup = groupMap.containsKey(guid);
		if (isGroup) {
			Group group = groupMap.get(guid);
			return Optional.ofNullable(group.head);
		}
		if (guid instanceof Bookmark) {
			String parentGuid = ((Bookmark) guid).getParent();
			if (parentGuid != null) {
				return find(() -> parentGuid, guid);
			}
		}
		final Result<Bookmark> rst = new Result<>();
		groupMap.values().stream().filter(group -> {
			group.search(guid).ifPresent(rst::set);
			return !rst.isNull();
		});
		return rst.getOptional();
	}

	private Optional<Bookmark> find(final GUID parentGuid, final GUID childGuid) {
		Group group = groupMap.get(parentGuid);
		if (group == null) {
			return Optional.empty();
		}
		return group.children.stream().filter(bm -> bm.equals(childGuid)).findFirst();
	}

	// Group class definition
	final class Group implements Serializable {
		private static final long serialVersionUID = 1804390757048401421L;
		final Bookmark head;
		final List<Bookmark> children = new LinkedList<>();

		Group(Bookmark head) {
			this.head = head;
		}

		void add(Bookmark bookmark) {
			children.add(bookmark);
		}

		Optional<Bookmark> search(GUID guid) {
			return children.stream().filter(bm -> bm.equals(guid)).findFirst();
		}

	}

}
