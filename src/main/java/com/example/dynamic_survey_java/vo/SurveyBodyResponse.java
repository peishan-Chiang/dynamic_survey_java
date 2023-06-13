package com.example.dynamic_survey_java.vo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dynamic_survey_java.entity.SurveyBody;
import com.example.dynamic_survey_java.entity.SurveyHead;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class SurveyBodyResponse {
	private String code;
	private String message;
	private SurveyHead surveyHeadItem;
	private SurveyBody surveyBodyItem;
	private List<SurveyHead> surveyHeadList;
	private Page<SurveyHead> surveyHeadPage;
	private Page<SurveyBody>surveyBodyPage;
	private List<SurveyBody> surveyBodyList;
	
	
	
	
	
	
	
	
	
	
	public SurveyBodyResponse(String code, String message, List<SurveyBody> surveyBodyList) {
		super();
		this.code = code;
		this.message = message;
		this.surveyBodyList = surveyBodyList;
	}
	public SurveyBodyResponse(String code, String message, Page<SurveyBody> surveyBodyPage) {
		super();
		this.code = code;
		this.message = message;
		this.surveyBodyPage = surveyBodyPage;
	}
	public SurveyBodyResponse() {
		super();
		// TODO Auto-generated constructor stub
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
	public List<SurveyBody> getSurveyBodyList() {
		return surveyBodyList;
	}
	public void setSurveyBodyList(List<SurveyBody> surveyBodyList) {
		this.surveyBodyList = surveyBodyList;
	}
	
	
	
	
	


	
	
	
}


