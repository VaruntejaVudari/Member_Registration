package com.cts.member.reg.portals.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

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
	ResponseEntity<?> validateMemberSumbitClaimsDetails(MemberSubmitClaimsDTO dto);

	/**
	 * @param requestDTO
	 * @return
	 */
	MemberSubmitClaimsDTO memberUpdateClaimsDetails(MemberSubmitClaimsDTO requestDTO);

	/**
	 * @return List
	 */
	List<MemberSubmitClaimsDTO> retriveSubmitClaimsDetails();
}
