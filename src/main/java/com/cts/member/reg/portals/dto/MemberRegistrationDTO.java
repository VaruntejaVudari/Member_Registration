package com.cts.member.reg.portals.dto;

import java.io.Serializable;

/**
 * @author 91846
 *
 */
public class MemberRegistrationDTO implements Serializable {

	private static final long serialVersionUID = 3779152464102143798L;
	
	private Long memberId;
	private String name;
	private String address;
	private String state;
	private String country;
	private String email;
	private String pan;
	private Long contactno;
	private String dob;
	private String username;
	private String password;
    private String lgIpMac;
    private String lgIpMacUpd;
    private String hasError;
    private String alreadyExists;
    private String successMessage;
    
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Long getContactno() {
		return contactno;
	}
	public void setContactno(Long contactno) {
		this.contactno = contactno;
	}
	public String getAlreadyExists() {
		return alreadyExists;
	}
	public void setAlreadyExists(String alreadyExists) {
		this.alreadyExists = alreadyExists;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getLgIpMac() {
		return lgIpMac;
	}
	public void setLgIpMac(String lgIpMac) {
		this.lgIpMac = lgIpMac;
	}
	public String getLgIpMacUpd() {
		return lgIpMacUpd;
	}
	public void setLgIpMacUpd(String lgIpMacUpd) {
		this.lgIpMacUpd = lgIpMacUpd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHasError() {
		return hasError;
	}
	public void setHasError(String hasError) {
		this.hasError = hasError;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "MemberRegistrationDTO [memberId=" + memberId + ", name=" + name + ", address=" + address + ", state="
				+ state + ", country=" + country + ", email=" + email + ", pan=" + pan + ", contactno=" + contactno
				+ ", dob=" + dob + ", username=" + username + ", password=" + password + ", lgIpMac=" + lgIpMac
				+ ", lgIpMacUpd=" + lgIpMacUpd + ", hasError=" + hasError + ", alreadyExists=" + alreadyExists
				+ ", successMessage=" + successMessage + "]";
	}
    
}
