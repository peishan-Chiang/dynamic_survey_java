package com.example.dynamic_survey_java;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.dynamic_survey_java.entity.SurveyHead;
import com.example.dynamic_survey_java.respository.SurveyHeadDao;

@SpringBootTest
class DynamicSurveyJavaApplicationTests {
	
	@Autowired
	private SurveyHeadDao surveyHeadDao;
//
	@Test
	void contextLoads() {
		
		LocalDate start=LocalDate.parse("2023-06-01");
		LocalDate end=LocalDate.parse("2023-06-03");
		int pageNumber = 0; // 頁數，從0開始
		int pageSize = 10; // 每頁的記錄數

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<SurveyHead> Item = surveyHeadDao.findByTitleContainingAndStartDayBetween("台灣",start, end, pageable);
		for(SurveyHead singel:Item) {
			System.out.println(singel.getSurveyId());
		} 
		
		
	}

}
