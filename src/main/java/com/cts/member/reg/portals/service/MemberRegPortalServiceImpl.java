package com.cts.member.reg.portals.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.member.reg.portals.domain.MemberRegistrationEntity;
import com.cts.member.reg.portals.dto.MemberRegistrationDTO;
import com.cts.member.reg.portals.repository.MemberRegPortalRepository;
import com.cts.member.reg.portals.utility.MemberRegistrationPortalConstants;

/**
 * @author 91846
 *
 */
@Service
@Transactional
public class MemberRegPortalServiceImpl implements MemberRegPortalService {

	@Autowired
	private MemberRegPortalRepository memberRegPortalJpaRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberRegPortalServiceImpl.class);
	private static final String CANNOT_BE_NULL_EMPTY = " cannot be null or empty.";
	private static final String CANNOT_BE_ENIQUE = "Username and Password should not be same.";
	private static final String INVALID_USERNAME_PASSWORD = "Invalid Username and Password.";
	private static final String USERNAME_PWD_ALREADY_EXIT = "The Provided Username and Password is already exist.";
	private static final String LOGIN_SUCCESSFUL = "Login was successful.";
	private static final String MEMBER_REG_PORTAL_SUCCESSFUL = "Member Registration Portal was successful.";
	
	@Override
	public MemberRegistrationDTO registerMemberPortalRegDetails(MemberRegistrationDTO requestDTO) {
		LOGGER.info(
				"MemberRegPortalController - registerMemberPortalRegDetails : Provided input for MemberRegistrationDTO["
						+ requestDTO + "]");

		MemberRegistrationEntity entity = new MemberRegistrationEntity();
		MemberRegistrationDTO dto = new MemberRegistrationDTO();

		BeanUtils.copyProperties(requestDTO, entity);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			entity.setDob(formatter.parse(requestDTO.getDob()));
		} catch (ParseException e) {
			LOGGER.error("Error occuring while converting Dob column datatype value string into date format " + e);
		}

		entity = memberRegPortalJpaRepository.save(entity);
		if (entity.getMemberid() != null) {
			dto.setMemberId(entity.getMemberid());
			dto.setSuccessMessage(MEMBER_REG_PORTAL_SUCCESSFUL);
		}
		return dto;
	}

	@Override
	public MemberRegistrationDTO loginMemberPortalRegDetails(MemberRegistrationDTO requestDTO) {

		LOGGER.info(
				"MemberRegPortalController - loginMemberPortalRegDetails : Provided input for MemberRegistrationDTO["
						+ requestDTO + "]");

		Long Memberid = memberRegPortalJpaRepository.findLoginDetailsByUsingUsernameAndPwd(requestDTO.getUsername(),
				requestDTO.getPassword());
		if (Memberid != null) {
			requestDTO.setMemberId(Memberid);
			requestDTO.setSuccessMessage(LOGIN_SUCCESSFUL);
		} else {
			requestDTO.setHasError(INVALID_USERNAME_PASSWORD);
		}

		return requestDTO;
	}

	@Override
	public ResponseEntity<?> validateRegisterMemberPortalDetails(final MemberRegistrationDTO dto) {

		ResponseEntity<?> responseEntity = null;
		final StringBuilder builder = new StringBuilder();

		if (dto.getName() == null || dto.getName().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.NAME);
		}
		if (dto.getAddress() == null || dto.getAddress().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.ADDRESS);
		}
		if (dto.getState() == null || dto.getState().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.STATE);
		}
		if (dto.getCountry() == null || dto.getCountry().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.COUNTRY);
		}
		if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.EMAIL);
		}
		if (dto.getPan() == null || dto.getPan().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.PAN);
		}
		if (dto.getContactno() == null) {
			builder.append(MemberRegistrationPortalConstants.CONTACTNO);
		} else {
			Pattern p = Pattern.compile("^\\d{10}$");
			Matcher m = p.matcher(dto.getContactno().toString());
			if (!m.matches()) {
				builder.append(MemberRegistrationPortalConstants.INVALID_CONTACTNO);
			}
		}
		if (dto.getDob() == null || dto.getDob().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.DOB);
		}
		if (dto.getLgIpMac() == null || dto.getLgIpMac().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.LGIPMAC);
		}
		if (dto.getLgIpMacUpd() == null || dto.getLgIpMacUpd().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.LGIPMACUPD);
		}
		if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.USER_NAME);
		}
		if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.PASSWORD);
		}
		if (builder.toString().isEmpty() && (dto.getUsername() != null && !dto.getUsername().isEmpty())
				&& (dto.getPassword() != null && !dto.getPassword().isEmpty())) {
			Long Memberid = memberRegPortalJpaRepository.findLoginDetailsByUsingUsernameAndPwd(dto.getUsername(),
					dto.getPassword());
			if (Memberid != null) {
				builder.append(USERNAME_PWD_ALREADY_EXIT);
			}
		}
		if (!builder.toString().isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(builder.append(CANNOT_BE_NULL_EMPTY));
		}
		return (responseEntity == null ? ResponseEntity.status(HttpStatus.OK).body("validated") : responseEntity);
	}

	@Override
	public ResponseEntity<?> validateLoginMemberPortalDetails(MemberRegistrationDTO requestDTO) {

		ResponseEntity<?> responseEntity = null;
		final StringBuilder builder = new StringBuilder();

		if (requestDTO.getUsername() == null || requestDTO.getUsername().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.USER_NAME);
		}
		if (requestDTO.getPassword() == null || requestDTO.getPassword().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.PASSWORD);
		}
		if ((requestDTO.getUsername() != null && !requestDTO.getUsername().isEmpty())
				&& (requestDTO.getPassword() != null && !requestDTO.getPassword().isEmpty())) {
			if (requestDTO.getUsername().equals(requestDTO.getPassword())) {
				builder.append(CANNOT_BE_ENIQUE);
			}
		}
		if (!builder.toString().isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(builder.append(CANNOT_BE_NULL_EMPTY));
		}
		return (responseEntity == null ? ResponseEntity.status(HttpStatus.OK).body("validated") : responseEntity);
	}

}
