package com.cts.member.reg.portals.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author 91846
 *
 */
@Entity
@Table(name = "member_registration")
public class MemberRegistrationEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Memberid", nullable = false)
    private Long Memberid;

    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dob", nullable = false)
    private Date Dob;

    @Column(name = "Name", nullable = false)
    private String Name;
    
    @Column(name = "Address", nullable = false)
    private String Address;
    
    @Column(name = "State", nullable = false)
    private String State;
    
    @Column(name = "Country", nullable = false)
    private String Country;
    
    @Column(name = "Email", nullable = false)
    private String Email;
    
    @Column(name = "Pan", nullable = false)
    private String Pan;
    
    @Column(name = "Contactno", nullable = false)
    private Long Contactno;
    
    @Column(name = "lgIpMac", nullable = false)
    private String lgIpMac;
    
    @Column(name = "lgIpMacUpd", nullable = false)
    private String lgIpMacUpd;
    
    @Column(name = "Username", nullable = false)
    private String Username;
    
    @Column(name = "Password", nullable = false)
    private String Password;

	public Long getMemberid() {
		return Memberid;
	}

	public void setMemberid(Long memberid) {
		Memberid = memberid;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPan() {
		return Pan;
	}

	public void setPan(String pan) {
		Pan = pan;
	}

	public Long getContactno() {
		return Contactno;
	}

	public void setContactno(Long contactno) {
		Contactno = contactno;
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

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "MemberRegistrationEntity [Memberid=" + Memberid + ", Dob=" + Dob + ", Name=" + Name + ", Address="
				+ Address + ", State=" + State + ", Country=" + Country + ", Email=" + Email + ", Pan=" + Pan
				+ ", Contactno=" + Contactno + ", lgIpMac=" + lgIpMac + ", lgIpMacUpd=" + lgIpMacUpd + ", Username="
				+ Username + ", Password=" + Password + "]";
	}

}
