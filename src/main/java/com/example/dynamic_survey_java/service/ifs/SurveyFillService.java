package com.example.dynamic_survey_java.service.ifs;

import com.example.dynamic_survey_java.vo.SurveyFillResponse;
import com.example.dynamic_survey_java.vo.SurveyRequest;
import com.example.dynamic_survey_java.vo.SurveyResponse;


public interface SurveyFillService {
	public SurveyResponse fillSurvey (SurveyRequest surveyRequest);
	public SurveyFillResponse  findByFinishSurveyIdOrderByRegTimeDesc (SurveyRequest surveyRequest);
	public SurveyFillResponse  findByFinishSurveyIdAndEmail (SurveyRequest surveyRequest);
	public SurveyFillResponse  getStaticInfo (SurveyRequest surveyRequest);// 獲取已結束(前端卡控)的統計資料

}
