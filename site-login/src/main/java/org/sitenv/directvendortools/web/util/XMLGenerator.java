package org.sitenv.directvendortools.web.util;

import org.sitenv.directvendortools.web.dto.GenericErrorTO;
import org.sitenv.directvendortools.web.dto.ResponseTO;

import com.thoughtworks.xstream.XStream;

/**
 * Generates XML from transfer objects or exceptions.
 * 
 */
public final class XMLGenerator
{
	/**
	 * Add all the Classes to be Processed with Annotation to classes Array
	 */
	@SuppressWarnings("rawtypes")
	private static Class[] classes = {
	
	};
	/**
	 * Initialize XStream with classes Array
	 */

	private static XStream xstream = new XStream()
	{
		{
			omitField(Throwable.class, "stackTrace");
			processAnnotations(classes);
		}
	};

	/**
	 * Private constructor.
	 */
	private XMLGenerator()
	{

	}

	/**
	 * Generates the XML for an transfer object using XStream.
	 * 
	 * @param to
	 *            transfer object to build XML from.
	 * @return String XML
	 */
	public static String generateXML(final Object inputTO)
	{
		return null == inputTO ? ApplicationConstants.STR_EMPTY : xstream
				.toXML(inputTO);
	}

	/**
	 * Generates the transfer object from the given XML using XStream.
	 * 
	 * @param xml
	 * @return transfer object
	 */
	public static Object generateTOfromXML(final String xml)
	{
		return xstream.fromXML(xml);
	}
	/**
	 * Generates the error XML from the exception.
	 * 
	 * @param exception
	 * @return String
	 */
	public static String createErrorXML(final Exception exception)
	{
		return generateXML(new ResponseTO(new GenericErrorTO(exception)));
	}
	/**
	 * Generates the error XML from the Application Exception.
	 * 
	 * @param exception
	 * @return String
	 */
	public static String createErrorXML(final ApplicationException exception)
	{
		return generateXML(new ResponseTO(new GenericErrorTO(exception)));
	}
}
