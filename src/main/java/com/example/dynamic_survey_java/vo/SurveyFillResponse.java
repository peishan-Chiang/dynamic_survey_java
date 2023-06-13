package com.example.dynamic_survey_java.vo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dynamic_survey_java.entity.SurveyFillPerson;

public class SurveyFillResponse {
	private String code;
	private String message;
	private Page<SurveyFillPerson>SurveyFillList;
	private List<SurveyFillPerson>SurveyFillAnsList;
	
	
	
	
	public SurveyFillResponse(String code, String message, List<SurveyFillPerson> surveyFillAnsList) {
		super();
		this.code = code;
		this.message = message;
		SurveyFillAnsList = surveyFillAnsList;
	}
	public SurveyFillResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public SurveyFillResponse(String code, String message, Page<SurveyFillPerson> surveyFillList) {
		super();
		this.code = code;
		this.message = message;
		SurveyFillList = surveyFillList;
	}
	public SurveyFillResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Page<SurveyFillPerson> getSurveyFillList() {
		return SurveyFillList;
	}
	public void setSurveyFillList(Page<SurveyFillPerson> surveyFillList) {
		SurveyFillList = surveyFillList;
	}
	public List<SurveyFillPerson> getSurveyFillAnsList() {
		return SurveyFillAnsList;
	}
	public void setSurveyFillAnsList(List<SurveyFillPerson> surveyFillAnsList) {
		SurveyFillAnsList = surveyFillAnsList;
	}
	
	
	

}
