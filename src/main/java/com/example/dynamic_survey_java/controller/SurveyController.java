package com.example.dynamic_survey_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dynamic_survey_java.service.ifs.SurveyFillService;
import com.example.dynamic_survey_java.service.ifs.SurveyService;
import com.example.dynamic_survey_java.vo.SurveyBodyResponse;
import com.example.dynamic_survey_java.vo.SurveyFillResponse;
import com.example.dynamic_survey_java.vo.SurveyRequest;
import com.example.dynamic_survey_java.vo.SurveyResponse;


@CrossOrigin
@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private SurveyFillService surveyFillService;
	
	@PostMapping("create_survey")
	public SurveyResponse createSurvey(@RequestBody SurveyRequest req) {
	return surveyService.createSurvey(req);
	}
	
	
	@PostMapping("modify_survey")
	public SurveyResponse modiSurvey(@RequestBody SurveyRequest req) {
	return surveyService.modiSurvey(req);
	}
	
	@PostMapping("find_by_title")   //標題
	public SurveyResponse  findByTitle(@RequestBody SurveyRequest req) {
	return surveyService.findByTitle(req);
	}
	
	
	@PostMapping("find_by_priod")  //期間
	public SurveyResponse  findByPriod(@RequestBody SurveyRequest req) {
	return surveyService.findByPriod(req);
	}
	
	
	@PostMapping("find_by_title_and_period")  //標題+期間
	public SurveyResponse  findByTitleAndPeriod(@RequestBody SurveyRequest req) {
	return surveyService.findByTitleAndPeriod(req);
	}
	
	@PostMapping("find_by_period_start_or_end")  //期間其一
	public SurveyResponse  findByPeriodStartOrEnd(@RequestBody SurveyRequest req) {
	return surveyService.findByPeriodStartOrEnd(req);
	}
//	
	
	@PostMapping("find_by_title_and_one_Of_period")  //期間其一且有標題
	public SurveyResponse  findByTitleAndOneOfPeriod(@RequestBody SurveyRequest req) {
	return surveyService.findByTitleAndOneOfPeriod(req);
	}


	
	@PostMapping("create_survey_selection")
	public SurveyResponse  createSurveySelection(@RequestBody SurveyRequest req) {
	return surveyService.createSurveySelection(req);
	}
	@PostMapping("find_questionaire_By_Id")
	public SurveyResponse  findQuestionaireById(@RequestBody SurveyRequest req) {
	return surveyService.findQuestionaireById(req);
	}
	
	@PostMapping("find_by_all_colume")
	public SurveyResponse findbyAllColume(@RequestBody SurveyRequest req) {
	return surveyService.findbyAllColume(req);
	}

	
	@PostMapping("find_by_survey_selection") 
	public SurveyBodyResponse  findBySurveySelection(@RequestBody SurveyRequest req) {
	return surveyService.findBySurveySelection(req);
	}
	
	
	@PostMapping("delete_survey_selection") 
	public SurveyResponse  deleteSurveySelection(@RequestBody SurveyRequest req) {
	return surveyService.deleteSurveySelection(req);
	}
	
	@PostMapping("fill_survey") 
	public SurveyResponse  fillSurvey(@RequestBody SurveyRequest req) {
	return surveyFillService.fillSurvey(req);
	}
	
	
	@PostMapping("find_by_finish_survey_id_order_by_reg_time_desc") 
	public SurveyFillResponse  findByFinishSurveyIdOrderByRegTimeDesc(@RequestBody SurveyRequest req) {
	return surveyFillService.findByFinishSurveyIdOrderByRegTimeDesc(req);
	}
	
	@PostMapping("find_by_list_finish_survey_id_order_by_reg_time_desc") 
	public SurveyFillResponse  getStaticInfo(@RequestBody SurveyRequest req) {
	return surveyFillService.getStaticInfo(req);
	}
	
	
	
}
