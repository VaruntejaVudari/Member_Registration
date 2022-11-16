package com.cts.member.reg.portals.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.member.reg.portals.dto.MemberSubmitClaimsDTO;
import com.cts.member.reg.portals.service.MemberSubmitClaimsService;

@RestController
@CrossOrigin
@RequestMapping(path = "/memberSubmitClaims", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberSubmitClaimsController {

	@Autowired
	private MemberSubmitClaimsService memberSubmitClaimsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberSubmitClaimsController.class);

	/**
	 * @return
	 */
	@PostMapping(path = "/submitMemberClaimsDetails")
	public ResponseEntity<?> submitClaimsDetails(@RequestBody final MemberSubmitClaimsDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info("MemberSubmitClaimsController - submitClaimsDetails : Provided input for MemberSubmitClaimsDTO["
				+ requestDTO + "]");

		MemberSubmitClaimsDTO result = null;
		ResponseEntity<?> responseEntity = null;
		try {
			ResponseEntity<?> validationEntity = memberSubmitClaimsService
					.validateMemberSumbitClaimsDetails(requestDTO);
			if (validationEntity.getStatusCode() == HttpStatus.OK) {
				result = memberSubmitClaimsService.memberSubmitClaimsDetails(requestDTO);
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
			LOGGER.error("Error while Member Submit Claims: " + ex.getMessage(), ex);
		}
		return responseEntity;
	}

	@PostMapping(path = "/memberUpdateClaimsDetails")
	public ResponseEntity<?> updateMemberClaimsDetails(@RequestBody final MemberSubmitClaimsDTO requestDTO,
			final HttpServletRequest request, final BindingResult bindingResult) {
		LOGGER.info(
				"MemberSubmitClaimsController - updateMemberClaimsDetails : Provided input for MemberSubmitClaimsDTO["
						+ requestDTO + "]");

		MemberSubmitClaimsDTO result = null;
		ResponseEntity<?> responseEntity = null;
		try {
			requestDTO.setModeView('U');
			ResponseEntity<?> validationEntity = memberSubmitClaimsService
					.validateMemberSumbitClaimsDetails(requestDTO);
			if (validationEntity.getStatusCode() == HttpStatus.OK) {
				result = memberSubmitClaimsService.memberUpdateClaimsDetails(requestDTO);
				if (result.getMemberId() == null) {
					responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
				} else {
					responseEntity = ResponseEntity.status(HttpStatus.OK).body(result);
				}
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationEntity);
			}
		} catch (final Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR!");
			LOGGER.error("Error while Member Update Claims: " + ex.getMessage(), ex);
		}
		return responseEntity;
	}
	
	/**
	 * @return
	 */
	@GetMapping(path = "/retriveSubmitClaimsDetails")
	public ResponseEntity<?> retriveSubmitMemberClaimsDetails() {
		LOGGER.info("MemberRegPortalController - retriveSubmitMemberClaimsDetails ::");

		List<MemberSubmitClaimsDTO> result = null;
		ResponseEntity<?> responseEntity = null;
		try {
			result = memberSubmitClaimsService.retriveSubmitClaimsDetails();
			if (result == null || result.isEmpty()) {
				responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (final Exception ex) {
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR!");
			LOGGER.error("Error while Member Retrieve Submitted Claims: " + ex.getMessage(), ex);
		}
		return responseEntity;
	}
}
