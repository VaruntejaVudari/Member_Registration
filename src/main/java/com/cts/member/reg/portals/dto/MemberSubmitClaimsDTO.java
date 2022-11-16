package com.cts.member.reg.portals.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberSubmitClaimsDTO implements Serializable {

	private static final long serialVersionUID = 1717151742896742433L;
	
	private Long memberId;
	private String firstName;
	private String lastName;
	private String dob;
	private String dateOfAdmission;
	private String dateOfDischarge;
	private String providerName;
	private BigDecimal totalBillAmount;
	private String lgIpMac;
	private String lgIpMacUpd;
    private String hasError;
    private String successMessage;
    private char modeView;
    
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(String dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	public String getDateOfDischarge() {
		return dateOfDischarge;
	}
	public void setDateOfDischarge(String dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public BigDecimal getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(BigDecimal totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
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
	public String getHasError() {
		return hasError;
	}
	public void setHasError(String hasError) {
		this.hasError = hasError;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public char getModeView() {
		return modeView;
	}
	public void setModeView(char modeView) {
		this.modeView = modeView;
	}
	
	@Override
	public String toString() {
		return "MemberSubmitClaimsDTO [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", dateOfAdmission=" + dateOfAdmission + ", dateOfDischarge=" + dateOfDischarge
				+ ", providerName=" + providerName + ", totalBillAmount=" + totalBillAmount + ", lgIpMac=" + lgIpMac
				+ ", lgIpMacUpd=" + lgIpMacUpd + ", hasError=" + hasError + ", successMessage=" + successMessage
				+ ", modeView=" + modeView + "]";
	}

}
