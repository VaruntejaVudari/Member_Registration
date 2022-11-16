package com.cts.member.reg.portals.service;

import org.springframework.http.ResponseEntity;

import com.cts.member.reg.portals.dto.MemberRegistrationDTO;

/**
 * @author 91846
 *
 */
public interface MemberRegPortalService {

	/**
	 * @param requestDTO
	 * @return
	 */
	MemberRegistrationDTO registerMemberPortalRegDetails(MemberRegistrationDTO requestDTO);

	/**
	 * @param dto
	 * @return
	 */
	ResponseEntity<?> validateRegisterMemberPortalDetails(MemberRegistrationDTO dto);

	/**
	 * @param requestDTO
	 * @return
	 */
	ResponseEntity<?> validateLoginMemberPortalDetails(MemberRegistrationDTO requestDTO);

	/**
	 * @param requestDTO
	 * @return
	 */
	MemberRegistrationDTO loginMemberPortalRegDetails(MemberRegistrationDTO requestDTO);

}
