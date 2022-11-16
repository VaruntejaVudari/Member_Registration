package com.cts.member.reg.portals.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.member.reg.portals.domain.MemberSubmitClaimsEntity;

/**
 * @author 91846
 *
 */
@Repository
public interface MemberSubmitClaimsRepository extends JpaRepository<MemberSubmitClaimsEntity, Long> {

	@Query("select msc.Memberid from MemberSubmitClaimsEntity msc where msc.Firstname =:firstName and msc.Lastname =:lastName and msc.Dob =:dob and msc.Providername =:providerName")
	Long findMemberSubmitClaimsAlreadyExists(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dob") Date dob, @Param("providerName") String providerName);

}
