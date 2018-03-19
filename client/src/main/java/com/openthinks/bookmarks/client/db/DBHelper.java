/**
 * 
 */
package com.openthinks.bookmarks.client.db;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.mapdb.Serializer;

import com.openthinks.bookmarks.client.Configs;
import com.openthinks.bookmarks.converter.json.JsonConverter;
import com.openthinks.bookmarks.converter.json.JsonConverterTest;
import com.openthinks.bookmarks.converter.json.JsonInput;
import com.openthinks.bookmarks.core.ModelUtils;
import com.openthinks.bookmarks.core.model.nosql.BookmarkContainer;

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

	public Optional<BookmarkContainer> find() {
		if (bmcMap != null) {
			BookmarkContainer mainBMC = bmcMap.get(Configs.MAIN_BOOKMARK_KEY);
			return Optional.ofNullable(mainBMC);
		}
		return Optional.empty();
	}

	public void save(BookmarkContainer bookmarkContainer) {
		if (bmcMap != null) {
			bmcMap.put(Configs.MAIN_BOOKMARK_KEY, bookmarkContainer);
			MapDBHelper.getDiskDB().commit();
		}
	}

	public static final void destroy() {
		MapDBHelper.destroy();
	}

	public static void main(String[] args) throws IntrospectionException, IOException {
		BookmarkContainer bc = ModelUtils.fromJsonToNosql(new JsonConverter()
				.convert(new JsonInput(JsonConverterTest.class.getResourceAsStream("/bookmarks-2018-01-26.min.json"))));
		new DBHelper().save(bc);
	}

}
