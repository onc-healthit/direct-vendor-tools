package org.sitenv.directvendortools.web.util;

/**
 * The Class Application Exception.
 * 
 * @author TCS
 */
public class ApplicationException extends Exception
{
	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Application Exception.
	 * 
	 * @param input
	 *          String : errorMessage
	 */
	public ApplicationException(final String errorMessage)
	{
		super(errorMessage);
	}
}
