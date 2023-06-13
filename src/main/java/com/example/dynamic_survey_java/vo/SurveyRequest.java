package com.example.dynamic_survey_java.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SurveyRequest {

	private String surveyId; // 創建問卷標題的ID 建構邏輯為 CREATE_DATE+_001

	private String title; // 問卷標題的名稱
	private int currentPage;// 指定頁面

	@JsonProperty("start_day")
	private LocalDate startDay = LocalDate.now(); // 預設會是今天
	@JsonProperty("end_day")
	private LocalDate endDay = LocalDate.now().plusDays(7); // 預設會是今天+7

	private String questionId; // 子問項收集矩陣

	private String discription;

	private LocalDateTime regTime = LocalDateTime.now();

	// =====================================

	private String surveyQuestionId; //問項ID
	private String surveyQuestionTitle; //問項標題
	private String surveyQuestionType; //問項分類
	private boolean neccessary; //是否必填
	private String surveySelection; //用分號區隔
	
	@JsonProperty("quesiton_list")
	List <surveyQuestionListRequest> quesitonList;
	
//	===========================================
	
	private String finishSurveyAnswer; //USER提供答案
	private int personId;
	private String personName;
	private String email; 
	private String phone; 
	private int age;
	private LocalDateTime  installTime = LocalDateTime.now();
	private String finishSurveyId;

	public List<surveyQuestionListRequest> getQuesitonList() {
		return quesitonList;
	}

	public void setQuesitonList(List<surveyQuestionListRequest> quesitonList) {
		this.quesitonList = quesitonList;
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

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDay() {
		return startDay;
	}

	public void setStartDay(LocalDate startDay) {
		this.startDay = startDay;
	}

	public LocalDate getEndDay() {
		return endDay;
	}

	public void setEndDay(LocalDate endDay) {
		this.endDay = endDay;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getFinishSurveyAnswer() {
		return finishSurveyAnswer;
	}

	public void setFinishSurveyAnswer(String finishSurveyAnswer) {
		this.finishSurveyAnswer = finishSurveyAnswer;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDateTime getInstallTime() {
		return installTime;
	}

	public void setInstallTime(LocalDateTime installTime) {
		this.installTime = installTime;
	}

	public String getFinishSurveyId() {
		return finishSurveyId;
	}

	public void setFinishSurveyId(String finishSurveyId) {
		this.finishSurveyId = finishSurveyId;
	}
	
	
	

}
