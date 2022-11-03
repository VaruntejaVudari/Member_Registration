package com.cts.member.reg.portals.service;

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
	String validateRegisterMemberPortalDetails(MemberRegistrationDTO dto);

	/**
	 * @param requestDTO
	 * @return
	 */
	String validateLoginMemberPortalDetails(MemberRegistrationDTO requestDTO);

	/**
	 * @param requestDTO
	 * @return
	 */
	MemberRegistrationDTO loginMemberPortalRegDetails(MemberRegistrationDTO requestDTO);

}
