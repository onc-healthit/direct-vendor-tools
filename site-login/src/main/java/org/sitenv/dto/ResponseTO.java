package org.sitenv.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.sitenv.util.ApplicationConstants;


@XStreamAlias(ApplicationConstants.RESPONSE)
public class ResponseTO
{

	@XStreamAsAttribute
	private int returnCode;

	private ResultSetTO resultSet;
	
	private int insertQueryStatus;

	private GenericErrorTO error;
	
	private UserLoginTO userLoginTo;
	
	private boolean isEmailAvailable;
	
	private String userAuthenticated;
	
	public void setError(final GenericErrorTO error)
	{
		this.error = error;
	}
	
	public ResponseTO (int insertQueryStatus)
	{
		this.insertQueryStatus = insertQueryStatus;
	}
	
	public ResponseTO (String userAuthenticated)
	{
		this.userAuthenticated = userAuthenticated;
	}
	
	
	public String getUserAuthenticated() {
		return userAuthenticated;
	}

	public void setUserAuthenticated(String userAuthenticated) {
		this.userAuthenticated = userAuthenticated;
	}

	public ResponseTO (UserLoginTO userLoginTo)
	{
		this.userLoginTo = userLoginTo;
	}
	
	
	public ResponseTO (boolean isEmailAvailable)
	{
		this.isEmailAvailable = isEmailAvailable;
	}

	public ResponseTO()
	{
	}
	
	public ResponseTO(final GenericErrorTO error)
	{
		this.returnCode = -1;
		this.error = error;
	}

	public ResponseTO(final ResultSetTO resultSet)
	{
		this.resultSet = resultSet;
		if (this.resultSet != null)
		{
			this.resultSet.initializeCounts();
		}
	}

	/**
	 * @return the returnCode
	 */
	public int getReturnCode()
	{
		return returnCode;
	}

	/**
	 * @param returnCode
	 *            the returnCode to set
	 */
	public void setReturnCode(final int returnCode)
	{
		this.returnCode = returnCode;
	}

	/**
	 * @return the resultSet
	 */
	public ResultSetTO getResultSet()
	{
		return resultSet;
	}

	/**
	 * @param resultSet
	 *            the resultSet to set
	 */
	public void setResultSet(final ResultSetTO resultSet)
	{
		this.resultSet = resultSet;
	}

	/**
	 * @return the error
	 */
	public GenericErrorTO getError()
	{
		return error;
	}

	public int getInsertQueryStatus() {
		return insertQueryStatus;
	}

	public void setInsertQueryStatus(int insertQueryStatus) {
		this.insertQueryStatus = insertQueryStatus;
	}

	public boolean isEmailAvailable() {
		return isEmailAvailable;
	}

	public void setEmailAvailable(boolean isEmailAvailable) {
		this.isEmailAvailable = isEmailAvailable;
	}

	public UserLoginTO getUserLoginTo() {
		return userLoginTo;
	}

	public void setUserLoginTo(UserLoginTO userLoginTo) {
		this.userLoginTo = userLoginTo;
	}
	
	
}
