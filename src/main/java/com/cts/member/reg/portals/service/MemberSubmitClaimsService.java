package com.cts.member.reg.portals.service;

import java.util.List;

import com.cts.member.reg.portals.dto.MemberRegistrationDTO;
import com.cts.member.reg.portals.dto.MemberSubmitClaimsDTO;

/**
 * @author 91846
 *
 */
public interface MemberSubmitClaimsService {

	/**
	 * @param requestDTO
	 * @return
	 */
	MemberSubmitClaimsDTO memberSubmitClaimsDetails(MemberSubmitClaimsDTO requestDTO);

	/**
	 * @param dto
	 * @return
	 */
	String validateMemberSumbitClaimsDetails(MemberSubmitClaimsDTO dto);

	/**
	 * @return List
	 */
	List<MemberSubmitClaimsDTO> retriveSubmitClaimsDetails();
}
