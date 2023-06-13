package com.example.dynamic_survey_java.service.ifs;

import com.example.dynamic_survey_java.vo.SurveyBodyResponse;
import com.example.dynamic_survey_java.vo.SurveyRequest;
import com.example.dynamic_survey_java.vo.SurveyResponse;

public interface SurveyService  {
	
	public SurveyResponse createSurvey (SurveyRequest surveyRequest);//創建問卷V fetch
	public SurveyResponse modiSurvey (SurveyRequest surveyRequest); //修改問卷
	public SurveyResponse deleteSurvey (SurveyRequest surveyRequest); //刪除問卷
	public SurveyResponse createSurveySelection (SurveyRequest surveyRequest); //建立問卷選項( V
	public SurveyResponse deleteSurveySelection (SurveyRequest surveyRequest); //刪除問卷選項(搭配二次送出) V
	
	public SurveyResponse findQuestionaireById (SurveyRequest surveyRequest); //查詢問卷(呈現單一畫面使用) V 撈出單頭
	//缺撈出單身
	public SurveyResponse findbyAllColume(SurveyRequest surveyRequest);//如果空的情況要做全部搜尋
	
	
	
	
	public SurveyResponse findByPriod (SurveyRequest surveyRequest); //查詢期間
	public SurveyResponse findByTitle (SurveyRequest surveyRequest); //查詢標題 V
	
	public SurveyResponse findByTitleAndPeriod (SurveyRequest surveyRequest);//查詢標題跟期間 V
	public SurveyResponse findByPeriodStartOrEnd (SurveyRequest surveyRequest);//查詢日期起迄其一
	public SurveyResponse findByTitleAndOneOfPeriod (SurveyRequest surveyRequest);///查詢標題跟期間其一
	
	public SurveyBodyResponse findBySurveySelection  (SurveyRequest surveyRequest); //查詢問卷(呈現選項畫面使用) 撈出單身
	
//	public SurveyBodyResponse getStaticInfo (SurveyRequest surveyRequest);// 獲取已結束(前端卡控)的統計資料
	
}
