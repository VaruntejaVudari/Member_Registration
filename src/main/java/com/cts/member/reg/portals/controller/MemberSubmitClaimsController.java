package com.cts.member.reg.portals.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.member.reg.portals.dto.MemberRegistrationDTO;
import com.cts.member.reg.portals.dto.MemberSubmitClaimsDTO;
import com.cts.member.reg.portals.service.MemberSubmitClaimsService;

@RestController
@RequestMapping(path = "/memberSubmitClaims", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberSubmitClaimsController {

	@Autowired
	private MemberSubmitClaimsService memberSubmitClaimsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberSubmitClaimsController.class);

	/**
	 * @return
	 */
	@PostMapping(path = "/submitMemberClaimsDetails")
	public MemberSubmitClaimsDTO submitClaimsDetails(@RequestBody final MemberSubmitClaimsDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info("MemberSubmitClaimsController - submitClaimsDetails : Provided input for MemberSubmitClaimsDTO["
				+ requestDTO + "]");

		MemberSubmitClaimsDTO result = null;
		try {
			String validationMessage = memberSubmitClaimsService.validateMemberSumbitClaimsDetails(requestDTO);
			if (validationMessage != null && !validationMessage.isEmpty()) {
				result = new MemberSubmitClaimsDTO();
				result.setHasError(validationMessage);
				ObjectError error = new ObjectError(validationMessage, validationMessage);
				bindingResult.addError(error);
			}
			if (!bindingResult.hasErrors()) {
				result = memberSubmitClaimsService.memberSubmitClaimsDetails(requestDTO);
			}
		} catch (final Exception e) {
			LOGGER.error("error while submit Member Claim Details ::" + e);
		}
		return result;
	}

	/**
	 * @return
	 */
	@GetMapping(path = "/retriveSubmitClaimsDetails")
	public List<MemberSubmitClaimsDTO> retriveSubmitMemberClaimsDetails() {
		LOGGER.info("MemberRegPortalController - retriveSubmitMemberClaimsDetails ::");

		List<MemberSubmitClaimsDTO> result = null;
		try {
			result = memberSubmitClaimsService.retriveSubmitClaimsDetails();
		} catch (final Exception e) {
			LOGGER.error("error while Login Member Portal Details ::" + e);
		}
		return result;
	}

}
