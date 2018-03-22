/**
 * 
 */
package com.openthinks.bookmarks.client.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.mapdb.Serializer;

import com.openthinks.bookmarks.client.Configs;
import com.openthinks.bookmarks.core.model.BookmarkContainer;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class DBHelper {
	@SuppressWarnings("unchecked")
	private static final Serializer<BookmarkContainer> BMC_SERIALIZER = Serializer.JAVA;
	private final Map<String, BookmarkContainer> bmcMap;

	{
		MapDBHelper.initialize();
		bmcMap = MapDBHelper.getDiskDB().hashMap(Configs.DATA_ROOT_NAME_KEY, Serializer.STRING, BMC_SERIALIZER)
				.createOrOpen();
	}

	public Map<String, BookmarkContainer> findAll() {
		Map<String, BookmarkContainer> bmcMapCopy = new HashMap<>();
		if (bmcMap != null) {
			bmcMap.keySet().forEach((bookmarkNamespace) -> {
				find(bookmarkNamespace).ifPresent(bmc -> bmcMapCopy.put(bookmarkNamespace, bmc));
			});
		}
		return bmcMapCopy;
	}

	public Optional<BookmarkContainer> find(String bookmarkNamespace) {
		if (bmcMap != null) {
			BookmarkContainer specialBMC = bmcMap.get(bookmarkNamespace);
			return Optional.ofNullable(specialBMC);
		}
		return Optional.empty();
	}

	public void save(String bookmarkNamespace, BookmarkContainer bookmarkContainer) {
		if (bmcMap != null) {
			bmcMap.put(bookmarkNamespace, bookmarkContainer);
			MapDBHelper.getDiskDB().commit();
		}
	}

	public final void destroy() {
		MapDBHelper.destroy();
	}

}
