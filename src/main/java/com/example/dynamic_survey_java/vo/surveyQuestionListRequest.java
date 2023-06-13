package com.example.dynamic_survey_java.vo;

public class surveyQuestionListRequest {
	private String surveyId; // 創建問卷標題的ID 建構邏輯為 CREATE_DATE+_001
	private String surveyQuestionId; //問項ID
	private String surveyQuestionTitle; //問項標題
	private String surveyQuestionType; //問項分類
	private boolean neccessary; //是否必填
	private String surveySelection; //用分號區隔
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	public String getSurveyQuestionTitle() {
		return surveyQuestionTitle;
	}
	public void setSurveyQuestionTitle(String surveyQuestionTitle) {
		this.surveyQuestionTitle = surveyQuestionTitle;
	}
	public String getSurveyQuestionType() {
		return surveyQuestionType;
	}
	public void setSurveyQuestionType(String surveyQuestionType) {
		this.surveyQuestionType = surveyQuestionType;
	}
	public boolean isNeccessary() {
		return neccessary;
	}
	public void setNeccessary(boolean neccessary) {
		this.neccessary = neccessary;
	}
	public String getSurveySelection() {
		return surveySelection;
	}
	public void setSurveySelection(String surveySelection) {
		this.surveySelection = surveySelection;
	}

}
