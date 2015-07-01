package org.sitenv.directvendortools.web.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import org.sitenv.directvendortools.web.util.ApplicationConstants;
import org.sitenv.directvendortools.web.util.XMLGenerator;

@XStreamAlias(ApplicationConstants.FIELD_ERROR)
public class FieldErrorTO
{

	private String errorCode;

	private String errorMessage;

	private String fieldName;

	private Object fieldValue;

	public FieldErrorTO()
	{
		super();
	}

	public FieldErrorTO(final String errorCode)
	{
		this.errorCode = errorCode;
	}

	public FieldErrorTO(final String errorCode, final String errorMessage)
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public FieldErrorTO(final String errorCode, final String errorMessage,
			final String fieldName)
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.fieldName = fieldName;
	}

	public FieldErrorTO(final String errorCode, final String errorMessage,
			final String fieldName, final Object fieldValue)
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(final String errorCode)
	{
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * @param fieldName
	 *           the fieldName to set
	 */
	public void setFieldName(final String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue()
	{
		return fieldValue;
	}

	/**
	 * @param fieldValue
	 *            the fieldValue to set
	 */
	public void setFieldValue(final Object fieldValue)
	{
		this.fieldValue = fieldValue;
	}

	@Override
	public String toString()
	{
		return XMLGenerator.generateXML(this);
	}
}
