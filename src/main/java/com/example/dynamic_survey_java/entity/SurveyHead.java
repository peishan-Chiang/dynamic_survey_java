package com.example.dynamic_survey_java.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "survey_head")
public class SurveyHead {
	@Id
	@Column(name = "survey_id")
	private String surveyId; // 創建問卷標題的ID 建構邏輯為 CREATE_DATE+_001
	@Column(name = "title")
	private String title; // 問卷標題的名稱
	@Column(name = "start_day")
	private LocalDate startDay = LocalDate.now(); // 預設會是今天
	@Column(name = "end_day")
	private LocalDate endDay = LocalDate.now().plusDays(7); // 預設會是今天+7
	@Column(name = "question_Id")
	private String questionId; // 子問項收集矩陣
	@Column(name = "description")
	private String description;
	@Column(name = "create_date_time")
	private LocalDateTime regTime = LocalDateTime.now();

	public SurveyHead() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SurveyHead(String surveyId, String title, LocalDate startDay, LocalDate endDay, String questionId,
			String discription, LocalDateTime regTime) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.startDay = startDay;
		this.endDay = endDay;
		this.questionId = questionId;
		this.description = discription;
		this.regTime = regTime;
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

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

}
