package com.example.dynamic_survey_java.respository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dynamic_survey_java.entity.SurveyHead;



@ Repository
public interface SurveyHeadDao extends JpaRepository<SurveyHead,String> {
	
	
	public List<SurveyHead> findBySurveyIdContaining(String surveyId);
	Page<SurveyHead> findByTitleContainingOrderByRegTimeDesc(String title,Pageable pageable);
//	Page<SurveyHead> findByStartDayBetweenOrderByRegTimeDesc(LocalDate start, LocalDate end,Pageable pageable);
//	Page<SurveyHead> findByTitleContainingAndStartDayBetweenOrderByRegTimeDesc(String title,LocalDate start, LocalDate end,Pageable pageable);
	
	
	
//	Page<SurveyHead> findByStartDayGreaterThanEqualOrEndDayLessThanEqualOrderByRegTimeDesc(LocalDate start, LocalDate end,Pageable pageable);
 //greaterthan lessthan
	
	//日期+標題同時存在(要求起始比起資料小且，要求結束筆資料大) VVV logical
		@Query(value = "SELECT new SurveyHead(s.surveyId, s.title, s.startDay, s.endDay, s.questionId, s.description, s.regTime) "
		        + " FROM SurveyHead s WHERE s.startDay >= :startInput AND :endInput >=s.endDay")
		Page<SurveyHead> findByStartDayPeriodBetween(
		       
		        @Param("startInput") LocalDate startInput,
		        @Param("endInput") LocalDate endInput,
		        Pageable pageable
		);
	
	
	
	//日期+標題同時存在(要求起始比起資料小且，要求結束筆資料大) VVV logical
	@Query(value = "SELECT new SurveyHead(s.surveyId, s.title, s.startDay, s.endDay, s.questionId, s.description, s.regTime) "
	        + " FROM SurveyHead s WHERE s.title LIKE CONCAT('%', :titleInput, '%') and (s.startDay >= :startInput AND :endInput >=s.endDay  )")
	Page<SurveyHead> findByTitleContainingAndStartDayBetween(
	        @Param("titleInput") String titleInput,
	        @Param("startInput") LocalDate startInput,
	        @Param("endInput") LocalDate endInput,
	        Pageable pageable
	);

	//日期起訖存在其一VVV logical  要Null
	@Query(value = "SELECT new SurveyHead(s.surveyId, s.title, s.startDay, s.endDay, s.questionId, s.description, s.regTime) "
	        + " FROM SurveyHead s WHERE (s.title LIKE CONCAT('%', :titleInput, '%') and s.startDay >= :startInput) or "
	        + "(s.title LIKE CONCAT('%', :titleInput, '%') and :endInput >= s.endDay)")
	Page<SurveyHead> findByTitleContainingOrStartDayBetween(
	        @Param("titleInput") String titleInput,
	        @Param("startInput") LocalDate startInput,
	        @Param("endInput") LocalDate endInput,
	        Pageable pageable
	);
	
	//期間起始OR
	@Query(value = "SELECT new SurveyHead(s.surveyId, s.title, s.startDay, s.endDay, s.questionId, s.description, s.regTime) "
	        + " FROM SurveyHead s WHERE s.startDay >= :startInput or :endInput >= s.endDay")
		Page<SurveyHead> findByStartDayOrBetween(
				
		        @Param("startInput") LocalDate startInput,
		        @Param("endInput") LocalDate endInput,
		        Pageable pageable
		);
	
	Page<SurveyHead> findAll(Pageable pageable);
	
}
