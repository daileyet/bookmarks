package com.openthinks.bookmarks.converter.json;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.openthinks.bookmarks.converter.Converter;
import com.openthinks.bookmarks.converter.Output;


public class JsonConverterTest {

	
	@Test
	public void testParseJSonString() throws IOException {
		Converter c = new JsonConverter();
		Output output = c.convert(new JsonInput(JsonConverterTest.class.getResourceAsStream("/bookmarks-2018-01-26.json")));
		Assert.assertTrue(output instanceof JsonOutput);
		
		JsonOutput jsonOutput =  (JsonOutput) output;
		
		boolean actual =jsonOutput.getJsonElement().isObject();
		Assert.assertEquals(true, actual);
	}
	
//	@Test
	public void testParseJSonString2() throws IOException {
		Converter c = new JsonConverter();
		Output output = c.convert(new JsonInput(JsonConverterTest.class.getResourceAsStream("/bookmarks-2018-01-26.min.json")));
		Assert.assertTrue(output instanceof JsonOutput);
		
		JsonOutput jsonOutput =  (JsonOutput) output;
		
		boolean actual =jsonOutput.getJsonElement().isObject();
		Assert.assertEquals(true, actual);
		
		
	}
}
