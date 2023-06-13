package com.example.dynamic_survey_java.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dynamic_survey_java.entity.SurveyBody;




@ Repository
public interface SurveyBodyDao  extends JpaRepository<SurveyBody,String>{
//	Page<SurveyBody> findByQuestionIdContaining(String questionId,Pageable pageable);
	List<SurveyBody> findByQuestionIdContaining(String questionId);
	
}
