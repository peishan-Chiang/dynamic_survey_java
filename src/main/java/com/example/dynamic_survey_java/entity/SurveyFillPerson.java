package com.example.dynamic_survey_java.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "preson_fill_survey")
public class SurveyFillPerson {
	
	

		@Id
		@Column(name = "person_id")
		private int personId; 
		@Column(name = "person_name")
		private String personName;
		@Column(name = "email")
		private String email; 
		@Column(name = "phone")
		private String phone; 
		@Column(name = "age")
		private int age; // 子問項收集矩陣
		@Column(name = "install_time")
		private LocalDateTime  installTime = LocalDateTime.now();
		
		@Column(name = "finish_survey_id") //參與者submit的問卷ID
		private String finishSurveyId; 
		@Column(name = "finish_survey_answer")//參與者submit的問卷內容
		private String finishSurveyAnswer;
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
		public String getFinishSurveyAnswer() {
			return finishSurveyAnswer;
		}
		public void setFinishSurveyAnswer(String finishSurveyAnswer) {
			this.finishSurveyAnswer = finishSurveyAnswer;
		} 
		
		
		
		

}
