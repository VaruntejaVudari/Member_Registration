package com.cts.member.reg.portals.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.member.reg.portals.domain.MemberSubmitClaimsEntity;
import com.cts.member.reg.portals.dto.MemberSubmitClaimsDTO;
import com.cts.member.reg.portals.repository.MemberSubmitClaimsRepository;
import com.cts.member.reg.portals.utility.MemberRegistrationPortalConstants;

@Service
@Transactional
public class MemberSubmitClaimsServiceImpl implements MemberSubmitClaimsService {

	@Autowired
	private MemberSubmitClaimsRepository memberSubmitClaimsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberSubmitClaimsServiceImpl.class);
	private static final String CANNOT_BE_NULL_EMPTY = " cannot be null or empty.";
	private static final String MEMBER_SUBMIT_CLAIMS_ALREADY_EXIT = "The Provided Member Submit Claims is already exist.";
	private static final String MEMBER_SUBMIT_CLAIMS_SUCCESSFUL = "Member Submit Claims was successful.";
	private static final String MEMBER_UPDATE_CLAIMS_SUCCESSFUL = "Member Update Claims was successful.";
	private static final String MEMBER_UPDATE_CLAIMS_PK_NOT_EXIT = "The Provided Primary Key id is not exist in DB.";
	
	@Override
	public MemberSubmitClaimsDTO memberSubmitClaimsDetails(MemberSubmitClaimsDTO requestDTO) {
		LOGGER.info(
				"MemberSubmitClaimsServiceImpl - memberSubmitClaimsDetails : Provided input for MemberRegistrationDTO["
						+ requestDTO + "]");

		MemberSubmitClaimsEntity entity = new MemberSubmitClaimsEntity();
		MemberSubmitClaimsDTO dto = new MemberSubmitClaimsDTO();

		BeanUtils.copyProperties(requestDTO, entity);
		entity.setFirstname(requestDTO.getFirstName());
		entity.setLastname(requestDTO.getLastName());
		entity.setProvidername(requestDTO.getProviderName());
		entity.setTotalbillamount(requestDTO.getTotalBillAmount());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			entity.setDob(formatter.parse(requestDTO.getDob()));
			entity.setDateofadmission(formatter.parse(requestDTO.getDateOfAdmission()));
			entity.setDateofdischarge(formatter.parse(requestDTO.getDateOfDischarge()));
		} catch (ParseException e) {
			LOGGER.error("Error occuring while converting Dob, Dateofadmission and Dateofdischarge column datatype value string into date format " + e);
		}

		entity = memberSubmitClaimsRepository.save(entity);
		if (entity.getMemberid() != null) {
			dto.setMemberId(entity.getMemberid());
			dto.setSuccessMessage(MEMBER_SUBMIT_CLAIMS_SUCCESSFUL);
		}

		return dto;
	}

	@Override
	public MemberSubmitClaimsDTO memberUpdateClaimsDetails(MemberSubmitClaimsDTO requestDTO) {
		LOGGER.info(
				"MemberSubmitClaimsServiceImpl - memberUpdateClaimsDetails : Provided input for MemberRegistrationDTO["
						+ requestDTO + "]");
		
		MemberSubmitClaimsEntity entity = new MemberSubmitClaimsEntity();
		MemberSubmitClaimsDTO dto = new MemberSubmitClaimsDTO();

		BeanUtils.copyProperties(requestDTO, entity);
		entity.setMemberid(requestDTO.getMemberId());
		entity.setFirstname(requestDTO.getFirstName());
		entity.setLastname(requestDTO.getLastName());
		entity.setProvidername(requestDTO.getProviderName());
		entity.setTotalbillamount(requestDTO.getTotalBillAmount());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			entity.setDob(formatter.parse(requestDTO.getDob()));
			entity.setDateofadmission(formatter.parse(requestDTO.getDateOfAdmission()));
			entity.setDateofdischarge(formatter.parse(requestDTO.getDateOfDischarge()));
		} catch (ParseException e) {
			LOGGER.error("Error occuring while converting Dob, Dateofadmission and Dateofdischarge column datatype value string into date format " + e);
		}

		entity = memberSubmitClaimsRepository.save(entity);
		if (entity.getMemberid() != null) {	
			dto.setMemberId(entity.getMemberid());
			dto.setSuccessMessage(MEMBER_UPDATE_CLAIMS_SUCCESSFUL);
		}

		return dto;
	}
	
	/**
	 *
	 */
	@Override
	public List<MemberSubmitClaimsDTO> retriveSubmitClaimsDetails() {
		LOGGER.info("MemberSubmitClaimsServiceImpl - retriveSubmitClaimsDetails :");

		List<MemberSubmitClaimsEntity> entityList = new ArrayList<MemberSubmitClaimsEntity>();
		List<MemberSubmitClaimsDTO> dtoList = null;
		entityList = memberSubmitClaimsRepository.findAll();
		
		if (entityList != null && !entityList.isEmpty()) {
			dtoList = new ArrayList<MemberSubmitClaimsDTO>();
			for (MemberSubmitClaimsEntity memberSubmitClaimsEntity : entityList) {
				MemberSubmitClaimsDTO dto = new MemberSubmitClaimsDTO();
				BeanUtils.copyProperties(memberSubmitClaimsEntity, dto);
				String pattern = "MM/dd/yyyy HH:mm:ss";
				DateFormat df = new SimpleDateFormat(pattern);
				dto.setDob(df.format(memberSubmitClaimsEntity.getDob()));
				dto.setDateOfAdmission(df.format(memberSubmitClaimsEntity.getDateofadmission()));
				dto.setDateOfDischarge(df.format(memberSubmitClaimsEntity.getDateofdischarge()));
				dto.setMemberId(memberSubmitClaimsEntity.getMemberid());
				dto.setFirstName(memberSubmitClaimsEntity.getFirstname());
				dto.setLastName(memberSubmitClaimsEntity.getLastname());
				dto.setProviderName(memberSubmitClaimsEntity.getProvidername());
				dto.setTotalBillAmount(memberSubmitClaimsEntity.getTotalbillamount());
				dtoList.add(dto);
			}
		}

		return dtoList;
	}
	
	@Override
	public ResponseEntity<?> validateMemberSumbitClaimsDetails(MemberSubmitClaimsDTO dto) {
		final StringBuilder builder = new StringBuilder();

		ResponseEntity<?> responseEntity = null;
		if (dto.getModeView() == 'U') {
			if (dto.getMemberId() == null) {
				builder.append(MemberRegistrationPortalConstants.MEMBER_ID_NOT_EMPTY);
			} else {
				boolean memberId = memberSubmitClaimsRepository.existsById(dto.getMemberId());
				if (!memberId) {
					builder.append(MEMBER_UPDATE_CLAIMS_PK_NOT_EXIT);
				}
			}
		}
		if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.FIRST_NAME);
		}
		if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.LAST_NAME);
		}
		if (dto.getDob() == null || dto.getDob().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.DOB);
		}
		if (dto.getDateOfAdmission() == null || dto.getDateOfAdmission().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.DATE_OF_ADMISSION);
		}
		if (dto.getDateOfDischarge() == null || dto.getDateOfDischarge().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.DATE_OF_DISCHARGE);
		}
		if (dto.getProviderName() == null || dto.getProviderName().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.PROVIDER_NAME);
		}
		if (dto.getTotalBillAmount() == null) {
			builder.append(MemberRegistrationPortalConstants.TOTAL_BILL_AMOUNT);
		}
		if (dto.getLgIpMac() == null || dto.getLgIpMac().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.LGIPMAC);
		}
		if (dto.getLgIpMacUpd() == null || dto.getLgIpMacUpd().isEmpty()) {
			builder.append(MemberRegistrationPortalConstants.LGIPMACUPD);
		}
		if (builder.toString().isEmpty() && (dto.getFirstName() != null && !dto.getFirstName().isEmpty())
				&& (dto.getLastName() != null && !dto.getLastName().isEmpty())
				&& (dto.getDob() != null && !dto.getDob().isEmpty())
				&& (dto.getProviderName() != null && !dto.getProviderName().isEmpty())) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = formatter.parse(dto.getDob());
			} catch (Exception e) {
				LOGGER.error("Error occuring while converting Dob column datatype value string into date format " + e);
			}
			Long Memberid = memberSubmitClaimsRepository.findMemberSubmitClaimsAlreadyExists(dto.getFirstName(),
					dto.getLastName(), date, dto.getProviderName());
			if (Memberid != null) {
				builder.append(MEMBER_SUBMIT_CLAIMS_ALREADY_EXIT);
			}
		}
		if (!builder.toString().isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(builder.append(CANNOT_BE_NULL_EMPTY));
		}
		return (responseEntity == null ? ResponseEntity.status(HttpStatus.OK).body("validated") : responseEntity);
	}

}
