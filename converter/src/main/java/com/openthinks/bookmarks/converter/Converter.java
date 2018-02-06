/**
 * 
 */
package com.openthinks.bookmarks.converter;

/**
 * @author dailey.yet@outlook.com
 *
 */
public interface Converter<T extends Output> {

	T convert(Input input);

}
