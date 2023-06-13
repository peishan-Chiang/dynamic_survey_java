package com.example.dynamic_survey_java.vo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dynamic_survey_java.entity.SurveyBody;
import com.example.dynamic_survey_java.entity.SurveyHead;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  SurveyResponse  {
	
	private String code;
	private String message;
	private SurveyHead surveyHeadItem;
	private SurveyBody surveyBodyItem;
	private List<SurveyHead> surveyHeadList;
	private Page<SurveyHead> surveyHeadPage;
	private Page<SurveyBody>surveyBodyPage;
	
	
	
	
	
	
	
	
	
	
	public SurveyResponse(String code, String message, SurveyBody surveyBodyItem) {
		super();
		this.code = code;
		this.message = message;
		this.surveyBodyItem = surveyBodyItem;
	}
	public SurveyResponse(String code, String message, SurveyHead surveyHeadItem) {
		super();
		this.code = code;
		this.message = message;
		this.surveyHeadItem = surveyHeadItem;
	}
	public SurveyResponse(String code, String message, Page<SurveyHead> surveyHeadPage) {
		super();
		this.code = code;
		this.message = message;
		this.surveyHeadPage = surveyHeadPage;
	}
	public SurveyResponse(String code, String message, List<SurveyHead> surveyHeadList) {
		super();
		this.code = code;
		this.message = message;
		this.surveyHeadList = surveyHeadList;
	}
	@JsonProperty("reg_time")
	private LocalDateTime regTime;
	
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
	public LocalDateTime getRegTime() {
		return regTime;
	}
	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}
	public SurveyResponse(String code, String message, LocalDateTime regTime) {
		super();
		this.code = code;
		this.message = message;
		this.regTime = regTime;
	}
	public SurveyResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public SurveyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<SurveyHead> getSurveyHeadList() {
		return surveyHeadList;
	}
	public void setSurveyHeadList(List<SurveyHead> surveyHeadList) {
		this.surveyHeadList = surveyHeadList;
	}
	public Page<SurveyHead> getSurveyHeadPage() {
		return surveyHeadPage;
	}
	public void setSurveyHeadPage(Page<SurveyHead> surveyHeadPage) {
		this.surveyHeadPage = surveyHeadPage;
	}
	public SurveyHead getSurveyHeadItem() {
		return surveyHeadItem;
	}
	public void setSurveyHeadItem(SurveyHead surveyHeadItem) {
		this.surveyHeadItem = surveyHeadItem;
	}
	public SurveyBody getSurveyBodyItem() {
		return surveyBodyItem;
	}
	public void setSurveyBodyItem(SurveyBody surveyBodyItem) {
		this.surveyBodyItem = surveyBodyItem;
	}
	public Page<SurveyBody> getSurveyBodyPage() {
		return surveyBodyPage;
	}
	public void setSurveyBodyPage(Page<SurveyBody> surveyBodyPage) {
		this.surveyBodyPage = surveyBodyPage;
	}
	
	
	
	
	


	
	
	
}
