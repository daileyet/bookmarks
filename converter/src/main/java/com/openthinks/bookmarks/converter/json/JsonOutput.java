/**
 * 
 */
package com.openthinks.bookmarks.converter.json;

import com.openthinks.bookmarks.converter.AbstractOutput;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.support.JSONElement;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class JsonOutput extends AbstractOutput {

	protected final JSONElement jsonElement;

	public JsonOutput(JSONElement root) {
		this.jsonElement = root;
		pureOutput = JSON.stringify(jsonElement);
	}

	public final JSONElement getJsonElement() {
		return jsonElement;
	}

}
