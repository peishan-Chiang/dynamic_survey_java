package com.example.dynamic_survey_java.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.dynamic_survey_java.constants.RtnCode;
import com.example.dynamic_survey_java.entity.SurveyFillPerson;
import com.example.dynamic_survey_java.entity.SurveyHead;
import com.example.dynamic_survey_java.respository.SurveyBodyDao;
import com.example.dynamic_survey_java.respository.SurveyFillPersonDao;
import com.example.dynamic_survey_java.respository.SurveyHeadDao;
import com.example.dynamic_survey_java.service.ifs.SurveyFillService;
import com.example.dynamic_survey_java.vo.SurveyFillResponse;
import com.example.dynamic_survey_java.vo.SurveyRequest;
import com.example.dynamic_survey_java.vo.SurveyResponse;


@Service
public class SurveyFillImpl  implements SurveyFillService {
	@Autowired
	private SurveyHeadDao surveyHeadDao;

	@Autowired
	private SurveyBodyDao surveBodyDao;
	
	@Autowired
	private SurveyFillPersonDao surveyFillPersonDao;

	@Override
	public SurveyResponse fillSurvey(SurveyRequest surveyRequest) {
		// 如果沒提供_錯誤
				if (!StringUtils.hasText(surveyRequest.getEmail())||!StringUtils.hasText(surveyRequest.getPersonName())||!StringUtils.hasText(surveyRequest.getPhone())
						||!StringUtils.hasText(surveyRequest.getFinishSurveyId())) {
					return new SurveyResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
				}

				

				

				SurveyFillPerson createItem = new SurveyFillPerson();
				
				createItem.setAge(surveyRequest.getAge());
				createItem.setFinishSurveyId(surveyRequest.getFinishSurveyId());
				createItem.setPersonName(surveyRequest.getPersonName());
				createItem.setFinishSurveyAnswer(surveyRequest.getFinishSurveyAnswer());
				createItem.setEmail(surveyRequest.getEmail());
				createItem.setPhone(surveyRequest.getPhone());
				
				
				
				

				surveyFillPersonDao.save(createItem);

				return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
			}
	

	@Override
	public SurveyFillResponse findByFinishSurveyIdOrderByRegTimeDesc(SurveyRequest surveyRequest) {//填問卷的人有誰
		
		int pageRes = surveyRequest.getCurrentPage();
		Page<SurveyFillPerson> fillResult = surveyFillPersonDao.findByFinishSurveyIdOrderByInstallTimeDesc(surveyRequest.getFinishSurveyId(), PageRequest.of(pageRes, 10));
		return new SurveyFillResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(),fillResult);
	}

	@Override
	public SurveyFillResponse findByFinishSurveyIdAndEmail(SurveyRequest surveyRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SurveyFillResponse getStaticInfo(SurveyRequest surveyRequest) {
		List<SurveyFillPerson> fillInfo = surveyFillPersonDao.findByFinishSurveyIdOrderByInstallTimeDesc(surveyRequest.getFinishSurveyId()); //同分Survey下有哪些人
		return new SurveyFillResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(),fillInfo);
	}
}
