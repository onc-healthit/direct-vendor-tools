package org.sitenv.directvendortools.web.dto;


public class DirectSystemTO {
	
	private int id;
	private String cehrtLabel;
	private String organizationName;
	private String directEmailAddress;
	private String pointOfContact;
	private String pocFirstName;
	private String pocLastName;
	private String timezone;
	private String directTrustMembership;
	private String availFromDate;
	private String availToDate;
	private String userEmailAddress;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserEmailAddress() {
		return userEmailAddress;
	}
	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}
	public String getCehrtLabel() {
		return cehrtLabel;
	}
	public void setCehrtLabel(String cehrtLabel) {
		this.cehrtLabel = cehrtLabel;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getDirectEmailAddress() {
		return directEmailAddress;
	}
	public void setDirectEmailAddress(String directEmailAddress) {
		this.directEmailAddress = directEmailAddress;
	}
	public String getPointOfContact() {
		return pointOfContact;
	}
	public void setPointOfContact(String pointOfContact) {
		this.pointOfContact = pointOfContact;
	}
	public String getPocFirstName() {
		return pocFirstName;
	}
	public void setPocFirstName(String pocFirstName) {
		this.pocFirstName = pocFirstName;
	}
	public String getPocLastName() {
		return pocLastName;
	}
	public void setPocLastName(String pocLastName) {
		this.pocLastName = pocLastName;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getDirectTrustMembership() {
		return directTrustMembership;
	}
	public void setDirectTrustMembership(String directTrustMembership) {
		this.directTrustMembership = directTrustMembership;
	}
	public String getAvailFromDate() {
		return availFromDate;
	}
	public void setAvailFromDate(String availFromDate) {
		this.availFromDate = availFromDate;
	}
	public String getAvailToDate() {
		return availToDate;
	}
	public void setAvailToDate(String availToDate) {
		this.availToDate = availToDate;
	}
}
