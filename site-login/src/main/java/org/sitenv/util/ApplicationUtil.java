package org.sitenv.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.sitenv.dto.GenericErrorTO;
import org.sitenv.dto.ResponseTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class ApplicationUtil {
	
	private ApplicationUtil() {}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationUtil.class);
	/**
	 *  To log Errors.
	 * 
	 * @param input
	 *           Exception : exception
	 */
	public static void logError(final Exception exception){
		LOGGER.error(exception.getMessage(), exception);
	}

	/**
	 * Null check for Integer.
	 * 
	 * @param input
	 *            ResultSet : results String : constant
	 * @return the Integer
	 */
	public static Integer checkNullForInt(final ResultSet results,
			final String constant) throws SQLException {
		Integer result;
		result = results.getObject(constant) == null ? null : results
				.getInt(constant);
		return result;
	}

	/**
	 * Null check for Boolean.
	 * 
	 * @param input
	 *            ResultSet : results String : constant
	 * @return the Boolean
	 */
	public static Boolean checkNullForBoolean(final ResultSet results,
			final String constant) throws SQLException {
		Boolean result;
		result = results.getObject(constant) == null ? null : results
				.getBoolean(constant);
		return result;
	}

	/**
	 * Null check for Short.
	 * 
	 * @param input
	 *            ResultSet : results String : constant
	 * @return true, if input is null or its Short value.
	 */
	public static Short checkNullForShort(final ResultSet results,
			final String constant) throws SQLException {
		Short result;
		result = results.getObject(constant) == null ? null : results
				.getShort(constant);
		return result;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param input
	 *            List<T> : list
	 * @return true, if input is null or Empty
	 */
	public static <T> boolean isEmpty(final List<T> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param input
	 *            String : str
	 * @return true, if input is null or Zero
	 */
	public static boolean isEmpty(final String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param input
	 *            Integer : value
	 * @return true, input if is null or Zero
	 */
	public static boolean isEmpty(final Integer value) {
		return value == null || value.intValue() == 0;
	}

	public static boolean isEmpty(final int[] arr) {
		return arr.length == 0;
	}

	/**
	 * Checks if is null or Zero.
	 * 
	 * @param input
	 *            the value
	 * @return true, if input is null or Zero.
	 */
	public static boolean isEmpty(final Short value) {
		return value == null || value.intValue() == 0;
	}

	/**
	 * Checks if an array of String is empty
	 * 
	 * @param strArr
	 * @return true, if input empty.
	 */
	public static boolean isEmpty(final String[] strArr) {
		return strArr.length == 0;
	}

	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *            Integer : value
	 * @return Zero, if input is null or its value
	 */
	public static int getDefaultValue(final Integer value) {
		int returnValue;
		if (value == null) {
			returnValue = 0;
		} else {
			returnValue = value.intValue();
		}
		return returnValue;
	}

	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *            Integer : value
	 * @return Zero, if input is null or its value
	 */
	public static short getDefaultValue(final Short value) {
		short returnValue;
		if (value == null) {
			returnValue = 0;
		} else {
			returnValue = value.shortValue();
		}
		return returnValue;
	}

	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *            Integer : value
	 * @return Zero, if input is null or its value
	 */
	public static String getDefaultValue(final Timestamp value) {
		String returnValue;
		if (value == null) {
			returnValue = "";
		} else {
			returnValue = value.toString();
		}
		return returnValue;
	}

	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *            String : value
	 * @return Empty String, if input is null or its trimmed value
	 */
	public static String getDefaultValue(final String value) {
		String returnValue;
		if (value == null) {
			returnValue = "";
		} else {
			returnValue = value.trim();
		}
		return returnValue;
	}

	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *            Long : value
	 * @return Empty String, if input is null or its Long value
	 */
	public static Object getDefaultValue(final Long value) {
		Object returnValue;
		if (value == null) {
			returnValue = "";
		} else {
			returnValue = value.longValue();
		}
		return returnValue;
	}

	/**
	 * Checks if input is null
	 * 
	 * @param input
	 *            BigDecimal : value
	 * @return Empty String, if input is null or its BigDecimal value
	 */
	public static Object getDefaultValue(final BigDecimal value) {
		Object returnValue;
		if (value == null) {
			returnValue = "";
		} else
			returnValue = value;
		return returnValue;
	}

	/**
	 * This method will return a BigDecimal for a given input string and
	 * specified scale.
	 * 
	 * @param input
	 *            String : str int : scale
	 * @return the BigDecimal
	 */
	public static BigDecimal getBigDecimal(final String str, final int scale) {
		BigDecimal returnValue;
		try {
			returnValue = new BigDecimal(str).setScale(scale,
					RoundingMode.HALF_UP);
		} catch (Exception exception) {
			returnValue = new BigDecimal("0.00");
		}
		return returnValue;
	}

	/**
	 * This method is used for grouping the input phone number into 3-3-4
	 * format.
	 * 
	 * @param input
	 *            String : phoneNumber
	 * @return the String
	 */
	public static String formatPhoneNumber(final String phoneNumber) {
		final long phoneFmt = Long.parseLong(phoneNumber);
		// get a 12 digits String, filling with left '0' (on the prefix)
		final DecimalFormat phoneDecimalFmt = new DecimalFormat("0000000000");
		final String phoneRawString = phoneDecimalFmt.format(phoneFmt);

		final MessageFormat phoneMsgFmt = new MessageFormat("{0}-{1}-{2}");
		// grouping in format of 3-3-4
		final String[] phoneNumArr = { phoneRawString.substring(0, 3),
				phoneRawString.substring(3, 6), phoneRawString.substring(6) };

		return phoneMsgFmt.format(phoneNumArr);
	}

	/**
	 * This method is used to convert the input string into Date object of
	 * specified format.
	 * 
	 * @param input
	 *            String : string String : format
	 * @return Date
	 */
	public static java.sql.Date convertStringToDate(final String string,
			final String format) {
		java.sql.Date date = null;
		try {
			if (!ApplicationUtil.isEmpty(string)) {
				final DateFormat formatter = new SimpleDateFormat(format,
						Locale.ENGLISH);
				final Date utilDate = formatter.parse(string);
				date = new java.sql.Date(utilDate.getTime());
			}
		} catch (Exception exception) {
		}
		return date;
	}

	public static Timestamp getCurrentTimeStamp(int offsetDays) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(ts);
		calendar.add(Calendar.DAY_OF_YEAR, offsetDays);
		ts.setTime(calendar.getTime().getTime());
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp getTimeStampFromString(String timeStamp) {
		return Timestamp.valueOf(timeStamp);
	}

	public static Timestamp getTsFromString(String dateRangeFrom, String format) {

		Date d = convertStringToDate(dateRangeFrom, format);
		return new Timestamp(d.getTime());

	}

	/**
	 * <p>
	 * Checks if a calendar date is today.
	 * </p>
	 * 
	 * @param cal
	 *            the calendar, not altered, not null
	 * @return true if cal date is today
	 * @throws IllegalArgumentException
	 *             if the calendar is <code>null</code>
	 */
	public static boolean isToday(Calendar cal) {
		return isSameDay(cal, Calendar.getInstance());
	}

	/**
	 * <p>
	 * Checks if two calendars represent the same day ignoring time.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}

	public static int[] convertStringToIntegerArray(final String input,
			final String delimiter) {
		StringTokenizer tokenizer = new StringTokenizer(input, delimiter);
		int count = tokenizer.countTokens();
		int inputArr[] = new int[count];
		int i = 0;
		while (tokenizer.hasMoreElements()) {
			inputArr[i++] = Integer.parseInt((String) tokenizer.nextElement());
		}
		return inputArr;
	}
	
	/**
	 * This method is used to  convert the input object into  JSON/XML format based on input flag isJSon.
	 * 
	 * @param input
	 *             ResponseTO :	responseTO
	 *             boolean 	  :	isJSon
	 * @return the String
	 */
	public static String getFormattedOutput(final ResponseTO responseTO,
			final boolean isJSon)
	{
		String output;
		if (isJSon)
		{
			output = JSONGenerator.createGson(responseTO);
		}
		else
		{
			output = XMLGenerator.generateXML(responseTO);
		}
		return output;
	}
	
	public static Timestamp getSqlTimeStamp()
	{
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
	}
	
	/**
	 * This method is used to  convert the input object into  JSON/XML format based on input flag isJSon.
	 * 
	 * @param input
	 *             Exception :	exception
	 *             boolean 	  :	isJSon
	 * @return the String
	 */
	public static String getFormattedOutput(final Exception exception,
			final boolean isJSon)
	{
		String output;
		if (isJSon)
		{
			output = JSONGenerator.createGson(new ResponseTO(new GenericErrorTO(exception)));
		}
		else
		{
			output = XMLGenerator.createErrorXML(exception);
		}
		return output;
	}
	
	public static Connection createDataBaseConnection(String hostName,String portNumber,
													  String userName,String password)throws SQLException,ClassNotFoundException
	{
		Class.forName("oracle.jdbc.OracleDriver");  
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@"+hostName+":"+portNumber+":"+hostName,userName, password);
	}
	
	public static void closeAll(final ResultSet resultSet) {

		Statement statement = null;
		Connection connection = null;
		try {
			if (resultSet != null) {
				statement = resultSet.getStatement();
			}
			if (statement != null) {
				connection = statement.getConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(resultSet);
		close(statement);
		close(connection);
	}
	
	public static void closeStatementAndResultset(final ResultSet resultSet ){
		Statement statement = null;
		try {
			if (resultSet != null) {
				statement = resultSet.getStatement();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(resultSet);
		close(statement);
	}

	public static void close(final Statement statement) {
		if (statement == null) {
			return;
		}
		try {
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(final Connection connection) {
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(final ResultSet resultSet) {
		if (resultSet == null) {
			return;
		}
		try {
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
