package com.cts.member.reg.portals.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "member_submitclaims")
public class MemberSubmitClaimsEntity implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Memberid", nullable = false)
    private Long Memberid;
	
	// ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOB", nullable = false)
    private Date Dob;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dateofadmission", nullable = false)
    private Date Dateofadmission;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dateofdischarge", nullable = false)
    private Date Dateofdischarge;

    @Column(name = "Firstname", nullable = false)
    private String Firstname;
    
    @Column(name = "Lastname", nullable = false)
    private String Lastname;
    
    @Column(name = "Providername", nullable = false)
    private String Providername;
    
    @Column(name = "Totalbillamount", nullable = false)
    private BigDecimal Totalbillamount;
    
    @Column(name = "lgIpMac", nullable = false)
    private String lgIpMac;
    
    @Column(name = "lgIpMacUpd", nullable = false)
    private String lgIpMacUpd;

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

	public Date getDateofadmission() {
		return Dateofadmission;
	}

	public void setDateofadmission(Date dateofadmission) {
		Dateofadmission = dateofadmission;
	}

	public Date getDateofdischarge() {
		return Dateofdischarge;
	}

	public void setDateofdischarge(Date dateofdischarge) {
		Dateofdischarge = dateofdischarge;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getProvidername() {
		return Providername;
	}

	public void setProvidername(String providername) {
		Providername = providername;
	}

	public BigDecimal getTotalbillamount() {
		return Totalbillamount;
	}

	public void setTotalbillamount(BigDecimal totalbillamount) {
		Totalbillamount = totalbillamount;
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

	@Override
	public String toString() {
		return "MemberSubmitClaimsEntity [Memberid=" + Memberid + ", Dob=" + Dob + ", Dateofadmission="
				+ Dateofadmission + ", Dateofdischarge=" + Dateofdischarge + ", Firstname=" + Firstname + ", Lastname="
				+ Lastname + ", Providername=" + Providername + ", Totalbillamount=" + Totalbillamount + ", lgIpMac="
				+ lgIpMac + ", lgIpMacUpd=" + lgIpMacUpd + "]";
	}
	
}
