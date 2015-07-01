package org.sitenv.directvendortools.web.util;

import com.google.gson.Gson;

import org.sitenv.directvendortools.web.dto.GenericErrorTO;
import org.sitenv.directvendortools.web.dto.ResponseTO;

/**
 * The utility class wraps the implementation of Google's Gson API 
 * and provides the utility of 'from' and 'to' conversions of 
 * JSON <-> Object
 * 
 */
public class JSONGenerator<T>
{

	private static Gson gson = new Gson();
	/**
	 * Generates a json string from an object
	 * 
	 * @param obj : Any  object
	 * @return a JSON string
	 */
	public static String createGson(final Object input)
	{
		return gson.toJson(input);
	}

	public T createObject(final String jsonString, final Class<T> temp)
	{
		return gson.fromJson(jsonString, temp);
	}

	public static String createErrorJSON(final Exception exception)
	{
		return createGson(new ResponseTO(new GenericErrorTO(exception)));
	}

	public static String createErrorJSON(final ApplicationException exception)
	{
		return createGson(new ResponseTO(new GenericErrorTO(exception)));
	}
}
