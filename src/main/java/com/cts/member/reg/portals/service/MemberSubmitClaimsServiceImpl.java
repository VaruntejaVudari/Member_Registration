package com.cts.member.reg.portals.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.member.reg.portals.domain.MemberSubmitClaimsEntity;
import com.cts.member.reg.portals.dto.MemberRegistrationDTO;
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
			LOGGER.error("Error occuring while converting Dob, Dateofadministration and Dateofdischarge column datatype value string into date format ::" + e);
		}

		entity = memberSubmitClaimsRepository.save(entity);
		dto.setMemberId(entity.getMemberid());

		return dto;
	}
	
	@Override
	public List<MemberSubmitClaimsDTO> retriveSubmitClaimsDetails() {
		
		List<MemberSubmitClaimsEntity> entityList = new ArrayList<MemberSubmitClaimsEntity>();
		List<MemberSubmitClaimsDTO> dtoList = new ArrayList<MemberSubmitClaimsDTO>();
		entityList = memberSubmitClaimsRepository.findAll();
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
		
		return dtoList;
	}

	@Override
	public String validateMemberSumbitClaimsDetails(MemberSubmitClaimsDTO dto) {
		final StringBuilder builder = new StringBuilder();

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
		if (!builder.toString().isEmpty()) {
			builder.append(CANNOT_BE_NULL_EMPTY);
		}
		return builder.toString();
	}

}
