package com.example.dynamic_survey_java.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.dynamic_survey_java.entity.SurveyFillPerson;

public interface SurveyFillPersonDao  extends JpaRepository<SurveyFillPerson ,Integer> {
	//同份問卷有哪些人
	Page<SurveyFillPerson> findByFinishSurveyIdOrderByInstallTimeDesc(String surveyFillPerson,Pageable pageable); 
	public List<SurveyFillPerson> findByFinishSurveyIdOrderByInstallTimeDesc(String surveyFillPerson); //for statistic
	public List<SurveyFillPerson> findByFinishSurveyIdAndEmail(String surveyFillPerson,String email,Pageable pageable); 
	
	
	
	
	
}
