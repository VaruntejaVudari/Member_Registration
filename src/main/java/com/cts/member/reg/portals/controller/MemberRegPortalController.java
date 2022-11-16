package com.cts.member.reg.portals.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping(path = "/memberRegPortal", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberRegPortalController {

	@Autowired
	private MemberRegPortalService memberRegPortalService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberRegPortalController.class);

	/**
	 * @return
	 */
	@PostMapping(path = "/registerMemberPortalDetails")
	public ResponseEntity<?> regMemberPortalDetails(@RequestBody final MemberRegistrationDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info("MemberRegPortalController - regMemberPortalDetails : Provided input for MemberRegistrationDTO["
				+ requestDTO + "]");

		MemberRegistrationDTO result = null;
		ResponseEntity<?> responseEntity = null;
		try {
			ResponseEntity<?> validationEntity = memberRegPortalService.validateRegisterMemberPortalDetails(requestDTO);
			if (validationEntity.getStatusCode() == HttpStatus.OK) {
				result = memberRegPortalService.registerMemberPortalRegDetails(requestDTO);
				if (result.getMemberId() == null) {
					responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(result);
				} else {
					responseEntity = ResponseEntity.status(HttpStatus.OK).body(result);
				}
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationEntity);
			}
		} catch (final Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR!");
			LOGGER.error("Error while Register Member Portal: " + ex.getMessage(), ex);
		}
		return responseEntity;
	}

	/**
	 * @return
	 */
	@PostMapping(path = "/loginMemberPortalDetails")
	public ResponseEntity<?> loginMemberRegPortalDetails(@RequestBody final MemberRegistrationDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info(
				"MemberRegPortalController - loginMemberRegPortalDetails : Provided input for MemberRegistrationDTO["
						+ requestDTO + "]");

		MemberRegistrationDTO result = null;
		ResponseEntity<?> responseEntity = null;
		try {
			ResponseEntity<?> validationEntity = memberRegPortalService.validateLoginMemberPortalDetails(requestDTO);
			if (validationEntity.getStatusCode() == HttpStatus.OK) {
				result = memberRegPortalService.loginMemberPortalRegDetails(requestDTO);
				if (result.getMemberId() == null) {
					responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(result);
				} else {
					responseEntity = ResponseEntity.status(HttpStatus.OK).body(result);
				}
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationEntity);
			}
		} catch (final Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR!");
			LOGGER.error("Error while Login Member Submit Claims: " + ex.getMessage(), ex);
		}
		return responseEntity;
	}

}
