package com.cts.member.reg.portals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.member.reg.portals.domain.MemberRegistrationEntity;

/**
 * @author 91846
 *
 */
@Repository
public interface MemberRegPortalRepository extends JpaRepository<MemberRegistrationEntity, Long> {

	@Query("select mre.Memberid from MemberRegistrationEntity mre where mre.Username =:username and mre.Password =:password")
    Long findLoginDetailsByUsingUsernameAndPwd(@Param("username") String username, @Param("password") String password);
	
}
