package com.cts.member.reg.portals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.member.reg.portals.domain.MemberSubmitClaimsEntity;

/**
 * @author 91846
 *
 */
@Repository
public interface MemberSubmitClaimsRepository extends JpaRepository<MemberSubmitClaimsEntity, Long> {

}
