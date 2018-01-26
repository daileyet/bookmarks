/**
 * 
 */
package com.openthinks.bookmarks.converter;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class AbstractOutput implements Output {
	protected String pureOutput;
	
	public AbstractOutput() {
		super();
	}


	public AbstractOutput(String pureOutput) {
		super();
		this.pureOutput = pureOutput;
	}


	/* (non-Javadoc)
	 * @see com.openthinks.bookmarks.converter.Output#asText()
	 */
	@Override
	public String asText() {
		return pureOutput;
	}

}
