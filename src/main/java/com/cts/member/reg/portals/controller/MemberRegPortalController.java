package com.cts.member.reg.portals.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.member.reg.portals.dto.MemberRegistrationDTO;
import com.cts.member.reg.portals.service.MemberRegPortalService;

/**
 * @author 91846
 *
 */
@RestController
@RequestMapping(path = "/memberRegPortal", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberRegPortalController {

	@Autowired
	private MemberRegPortalService memberRegPortalService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberRegPortalController.class);

	/**
	 * @return
	 */
	@PostMapping(path = "/registerMemberPortalDetails")
	public MemberRegistrationDTO regMemberPortalDetails(@RequestBody final MemberRegistrationDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info("MemberRegPortalController - regMemberPortalDetails : Provided input for MemberRegistrationDTO["
				+ requestDTO + "]");

		MemberRegistrationDTO result = null;
		try {
			String validationMessage = memberRegPortalService.validateRegisterMemberPortalDetails(requestDTO);
			if (validationMessage != null && !validationMessage.isEmpty()) {
				result = new MemberRegistrationDTO();
				result.setHasError(validationMessage);
				ObjectError error = new ObjectError(validationMessage, validationMessage);
				bindingResult.addError(error);
			}
			if (!bindingResult.hasErrors()) {
				result = memberRegPortalService.registerMemberPortalRegDetails(requestDTO);
			}
		} catch (final Exception e) {
			LOGGER.error("error while register Member Portal Details ::" + e);
		}
		return result;
	}

	/**
	 * @return
	 */
	@PostMapping(path = "/loginMemberPortalDetails")
	public MemberRegistrationDTO loginMemberRegPortalDetails(@RequestBody final MemberRegistrationDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info(
				"MemberRegPortalController - loginMemberRegPortalDetails : Provided input for MemberRegistrationDTO["
						+ requestDTO + "]");

		MemberRegistrationDTO result = null;
		try {
			String validationMessage = memberRegPortalService.validateLoginMemberPortalDetails(requestDTO);
			if (validationMessage != null && !validationMessage.isEmpty()) {
				result = new MemberRegistrationDTO();
				result.setHasError(validationMessage);
				ObjectError error = new ObjectError(validationMessage, validationMessage);
				bindingResult.addError(error);
			}
			if (!bindingResult.hasErrors()) {
				result = memberRegPortalService.loginMemberPortalRegDetails(requestDTO);
			}
		} catch (final Exception e) {
			LOGGER.error("error while Login Member Portal Details ::" + e);
		}
		return result;
	}

}
