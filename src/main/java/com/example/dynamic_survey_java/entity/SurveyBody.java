package com.example.dynamic_survey_java.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "survey_body")
public class SurveyBody {
	@Id
	@Column(name="question_id")
    private String questionId; //創建子問項標題的ID 建構邏輯為 CREATE_DATE+_001+_001
	@Column(name="sub_question")
	 private String subQuestion; // 子問項標題
	@Column(name="type")
	private String type; //單選題或是複選題
	@Column(name="neccessary")
	private boolean neccessary; //是否為必填欄位
	
	@Column(name="sub_question_selection")
	private String subQuestionSelection; //會做問項收集

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(String subQuestion) {
		this.subQuestion = subQuestion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNeccessary() {
		return neccessary;
	}

	public void setNeccessary(boolean neccessary) {
		this.neccessary = neccessary;
	}

	public String getSubQuestionSelection() {
		return subQuestionSelection;
	}

	public void setSubQuestionSelection(String subQuestionSelection) {
		this.subQuestionSelection = subQuestionSelection;
	}
	

}
