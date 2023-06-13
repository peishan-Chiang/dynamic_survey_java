package com.example.dynamic_survey_java.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.dynamic_survey_java.constants.RtnCode;
import com.example.dynamic_survey_java.entity.SurveyBody;
import com.example.dynamic_survey_java.entity.SurveyHead;
import com.example.dynamic_survey_java.respository.SurveyBodyDao;
import com.example.dynamic_survey_java.respository.SurveyHeadDao;
import com.example.dynamic_survey_java.service.ifs.SurveyService;
import com.example.dynamic_survey_java.vo.SurveyBodyResponse;
import com.example.dynamic_survey_java.vo.SurveyRequest;
import com.example.dynamic_survey_java.vo.SurveyResponse;
import com.example.dynamic_survey_java.vo.surveyQuestionListRequest;

@Service
public class SurveyImpl implements SurveyService {

	@Autowired
	private SurveyHeadDao surveyHeadDao;

	@Autowired
	private SurveyBodyDao surveBodyDao;

	@Override
	public SurveyResponse createSurvey(SurveyRequest surveyRequest) {// 新增問卷
		// 如果沒提供標題_錯誤
		if (!StringUtils.hasText(surveyRequest.getTitle())) {
			return new SurveyResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}

		if (surveyRequest.getEndDay().isBefore(LocalDate.now()) || surveyRequest.getStartDay().isBefore(LocalDate.now())
				|| surveyRequest.getEndDay().isBefore(surveyRequest.getStartDay())) {
			// 请求日期是过去日期
			// 结束日期早于开始日期
			// 执行相应的操作或返回错误信息

			return new SurveyResponse(RtnCode.DATE_IRRATIONAL.getCode(), RtnCode.DATE_IRRATIONAL.getMessage());

		}

		// 如果已經存在_錯誤
//		if (surveyHeadDao.existsById(surveyRequest.getSurveyId())) {
//			return new SurveyResponse(RtnCode.ACCOUNT_EXISTED.getCode(),RtnCode.ACCOUNT_EXISTED.getMessage());
//		}

		SurveyHead createItem = new SurveyHead();

		String surveyAutoIdFirst = String.valueOf(LocalDate.now()); // 第一節 今天 2023-05-31

		String[] surveyAutoIdFirstWithoutLabel = surveyAutoIdFirst.split("-");
		String surveyIdWithoutLabel = "";
		for (String item : surveyAutoIdFirstWithoutLabel) {
			surveyIdWithoutLabel = surveyIdWithoutLabel + item;
		}
		// surveyIdWithoutLabel->20230531
		String surveyEnsureId = "";
		List<Integer> surveyStr = new ArrayList<>(); // 將空的清單，來抓出今天的問卷
		List<SurveyHead> surveyResult = surveyHeadDao.findBySurveyIdContaining(surveyIdWithoutLabel);// 先嘗試用日期找出有的清單
		int orderStr = 1;

		if (surveyResult.isEmpty()) {
			String linkOrder = String.valueOf(orderStr);
			surveyEnsureId = surveyIdWithoutLabel + "_00" + linkOrder;
//			createItem.setSurveyid(surveyEnsureId);
			createItem.setSurveyId(surveyEnsureId);
		}

		if (surveyResult.size() > 0) {

			for (SurveyHead item : surveyResult) {

				String[] dateAndOrder = item.getSurveyId().split("_"); // 存入格式為20230531_001

//			Optional<SurveyHead> opSurveyID = surveyHeadDao.findById(item.getSurveyid()); //將ID去做搜尋存在

				Integer orderNum = Integer.valueOf(dateAndOrder[1]);// 001
				surveyStr.add(orderNum); // 需要知道ID 數字的清單

//			if (opSurveyID.isPresent()) {

				Collections.sort(surveyStr, Collections.reverseOrder()); // DSEC

				orderStr = Integer.valueOf(surveyStr.get(0));
				int calOrderStr = orderStr + 1;
				String linkOrder = String.valueOf(calOrderStr);
				if (linkOrder.length() == 1) { // 表示各位數
					surveyEnsureId = surveyIdWithoutLabel + "_00" + linkOrder;
				}
				if (linkOrder.length() == 2) { // 表示十位數
					surveyEnsureId = surveyIdWithoutLabel + "_0" + linkOrder;
				}
				if (linkOrder.length() == 3) { // 表示百位數
					surveyEnsureId = surveyIdWithoutLabel + "_" + linkOrder;
				}

				if (linkOrder.length() == 4) { // 表示千位數_問卷數量太多_拒絕
					return new SurveyResponse(RtnCode.FAILED.getCode(), RtnCode.FAILED.getMessage());
				}

			}
			createItem.setSurveyId(surveyEnsureId);
		}

		LocalDate newStartTime = surveyRequest.getStartDay();
		LocalDate newEndTime = surveyRequest.getEndDay();
		createItem.setStartDay(newStartTime);
		createItem.setEndDay(newEndTime);

		createItem.setDescription(surveyRequest.getDiscription());
		createItem.setTitle(surveyRequest.getTitle());

		surveyHeadDao.save(createItem);

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), createItem);
	}

	// ======================================================================================

	public SurveyResponse findByPriodAndTitle(SurveyRequest surveyRequest, boolean isPeriod) {

		if (surveyRequest.getEndDay().isBefore(surveyRequest.getStartDay())) {
			// 请求日期是过去日期
			// 结束日期早于开始日期
			// 执行相应的操作或返回错误信息

			return new SurveyResponse(RtnCode.DATE_IRRATIONAL.getCode(), RtnCode.DATE_IRRATIONAL.getMessage());

		}

		int pageRes = surveyRequest.getCurrentPage();
//        PageRequest pageRequest = PageRequest.of(pageRes, 5);
		if (isPeriod) {

			LocalDate newStartTime = surveyRequest.getStartDay();
			LocalDate newEndTime = surveyRequest.getEndDay();

			Page<SurveyHead> periodResult = surveyHeadDao.findByStartDayPeriodBetween(newStartTime, newEndTime,
					PageRequest.of(pageRes, 10)); // 一頁十筆

			return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), periodResult);

		}

		String titleRes = surveyRequest.getTitle();

		Page<SurveyHead> titleResult = surveyHeadDao.findByTitleContainingOrderByRegTimeDesc(titleRes,
				PageRequest.of(pageRes, 10));

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), titleResult);

	}

	// 需要DSEC 可能要要再DAO調整
	// periodResult還沒用

	@Override
	public SurveyResponse findByPriod(SurveyRequest surveyRequest) {

		return findByPriodAndTitle(surveyRequest, true);
	}

	@Override
	public SurveyResponse findByTitle(SurveyRequest surveyRequest) {

		return findByPriodAndTitle(surveyRequest, false);
	}

	// ===================================================================

	@Override
	public SurveyResponse modiSurvey(SurveyRequest surveyRequest) { // 修改單頭
		String surveyId = surveyRequest.getSurveyId();// request modify ID
		LocalDate modiStartDay = surveyRequest.getStartDay();// 開始日
		LocalDate modiEndDate = surveyRequest.getEndDay();// 結束日
		String modiTitle = surveyRequest.getTitle();// 標題
		String modiDiscription = surveyRequest.getDiscription();// 標題描述
		if (!StringUtils.hasText(surveyRequest.getTitle())) {
			return new SurveyResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		Optional<SurveyHead> opSurveyHead = surveyHeadDao.findById(surveyId);
		if (!opSurveyHead.isPresent()) {
			return new SurveyResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		SurveyHead getSurveyHead = opSurveyHead.get();

		getSurveyHead.setTitle(modiTitle);
		getSurveyHead.setDescription(modiDiscription);

		getSurveyHead.setEndDay(modiEndDate);
		getSurveyHead.setStartDay(modiStartDay);

		surveyHeadDao.save(getSurveyHead);

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(),getSurveyHead);
	}

	@Override
	public SurveyResponse deleteSurvey(SurveyRequest surveyRequest) {

		return null;
	}

	@Override
	public SurveyResponse createSurveySelection(SurveyRequest surveyRequest) {
		// 要有單頭 ID，單身標題+單身選項
		List<surveyQuestionListRequest> quesitonList = surveyRequest.getQuesitonList();

		// 依據問卷創建ID

		// for single case
//		if (!StringUtils.hasText(surveyRequest.getSurveyId())
//				||!StringUtils.hasText(surveyRequest.getSurveyQuestionTitle())
//				||!StringUtils.hasText(surveyRequest.getSurveySelection())) {
//			return new SurveyResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
//		}
//		
		List<SurveyBody> surveyBodyList = new ArrayList<>();
		List<SurveyHead> surveyHeadList = new ArrayList<>();
		for (surveyQuestionListRequest item : quesitonList) {
			if (!StringUtils.hasText(item.getSurveyId()) || !StringUtils.hasText(item.getSurveyQuestionTitle())
					|| !StringUtils.hasText(item.getSurveySelection())) {
				return new SurveyResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
			}

			// 用survey ID 查詢是否存在 單頭
			Optional<SurveyHead> opSurvey = surveyHeadDao.findById(item.getSurveyId());
			if (!opSurvey.isPresent()) {// 如果不存在
				return new SurveyResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());

			}
			// 獲取存在資料單頭
			SurveyHead existSurveyHead = opSurvey.get();

			// 如果單頭時間為已經開放，那我就不允續變更問項
			if (existSurveyHead.getStartDay().isBefore(LocalDate.now())) {
				return new SurveyResponse(RtnCode.MODIFY_REFUSE.getCode(), RtnCode.MODIFY_REFUSE.getMessage());
			}

			// 問卷單頭清單
			String QuestionList = existSurveyHead.getQuestionId();

//			// 單身新的一筆
			SurveyBody surveyBody = new SurveyBody();
			if (QuestionList == null || QuestionList.isEmpty()) { // 如果是新增問卷問項
				existSurveyHead.setQuestionId(existSurveyHead.getSurveyId() + "_001;");// 20150531_001_001
				surveyBody.setQuestionId(existSurveyHead.getSurveyId() + "_001");// 直接賦值

			} else {
				// 問卷單頭
				// 從單頭取出QuestionId欄位
				// 20150531_001_001;20150531_001_002
				// [20150531_001_001,20150531_001_002]
				String[] QuestionIdListHead = QuestionList.split(";");
				// 用來裝Arr
				List<String> QuestionIdHeadArr = new ArrayList<>();
				// SurveyBody [001,002,003]
				List<String> QuestionIdOrder = new ArrayList<>();
				for (String itemHead : QuestionIdListHead) {
					QuestionIdHeadArr.add(itemHead);// [20150531_001_001,20150531_001_002]
//				Optional<SurveyBody> opSurveyBody = surveBodyDao.findById(item);
					String[] QuestionIdAndOrder = itemHead.split("_"); // [20150531,001,001]
					QuestionIdOrder.add(QuestionIdAndOrder[2]); // [001,002,003]
				}
				// [001,002,003] 確認序號新的一筆序號為何
				Collections.sort(QuestionIdOrder, Collections.reverseOrder()); // DSEC

				String surveyEnsureQuestionId = "";
				Integer QuestionIdOrderInte = Integer.valueOf(QuestionIdOrder.get(0));
				int calOrder = QuestionIdOrderInte + 1;
				String linkOrder = String.valueOf(calOrder);
				if (linkOrder.length() == 1) { // 表示各位數
					surveyEnsureQuestionId = existSurveyHead.getSurveyId() + "_00" + linkOrder;
				}
				if (linkOrder.length() == 2) { // 表示十位數
					surveyEnsureQuestionId = existSurveyHead.getSurveyId() + "_0" + linkOrder;
				}
				if (linkOrder.length() == 3) { // 表示百位數
					surveyEnsureQuestionId = existSurveyHead.getSurveyId() + "_" + linkOrder;
				}

				if (linkOrder.length() == 4) { // 表示千位數_問卷數量太多_拒絕
					return new SurveyResponse(RtnCode.FAILED.getCode(), RtnCode.FAILED.getMessage());
				}

				String surveyHeadId = "";
				QuestionIdHeadArr.add(surveyEnsureQuestionId);// [20150531_001_001,20150531_001_002]
				for (String itemQuestionId : QuestionIdHeadArr) { // [20150531_001_001,20150531_001_002]
					surveyHeadId = surveyHeadId + itemQuestionId + ";";// 20150531_001_001;20150531_001_002;
				}

				existSurveyHead.setQuestionId(surveyHeadId);
				surveyBody.setQuestionId(surveyEnsureQuestionId);

			}

			surveyBody.setNeccessary(item.isNeccessary());
			surveyBody.setSubQuestion(item.getSurveyQuestionTitle());
			surveyBody.setType(item.getSurveyQuestionType());
			surveyBody.setSubQuestionSelection(item.getSurveySelection());

			surveyBodyList.add(surveyBody);
			surveyHeadList.add(existSurveyHead);

		}

		surveBodyDao.saveAll(surveyBodyList);
		surveyHeadDao.saveAll(surveyHeadList);
		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public SurveyResponse findQuestionaireById(SurveyRequest surveyRequest) {
		Optional<SurveyHead> opSurvey = surveyHeadDao.findById(surveyRequest.getSurveyId());
		if (!opSurvey.isPresent()) {
			return new SurveyResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		SurveyHead getSurveyData = opSurvey.get();
		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), getSurveyData);
	}

	@Override
	public SurveyResponse findByTitleAndPeriod(SurveyRequest surveyRequest) { // 全部條件都有
		LocalDate start = surveyRequest.getStartDay();
		LocalDate end = surveyRequest.getEndDay();
		String InputTitle = surveyRequest.getTitle();
		int pageNumber = surveyRequest.getCurrentPage(); // 頁數，從0開始
		int pageSize = 10; // 每頁的記錄數

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<SurveyHead> Item = surveyHeadDao.findByTitleContainingAndStartDayBetween(InputTitle, start, end, pageable);
//		for(SurveyHead singel:Item) {
//			String describe=singel.getDescription();
//		} 

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), Item);
	}

	@Override
	public SurveyResponse findByPeriodStartOrEnd(SurveyRequest surveyRequest) {
		LocalDate start = surveyRequest.getStartDay();
		LocalDate end = surveyRequest.getEndDay();
		int pageNumber = surveyRequest.getCurrentPage(); // 頁數，從0開始
		int pageSize = 10; // 每頁的記錄數

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<SurveyHead> Item = surveyHeadDao.findByStartDayOrBetween(start, end, pageable);
//		for(SurveyHead singel:Item) {
//			String describe=singel.getDescription();
//		} 

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), Item);
	}

	@Override
	public SurveyResponse findByTitleAndOneOfPeriod(SurveyRequest surveyRequest) {
		LocalDate start = surveyRequest.getStartDay();
		LocalDate end = surveyRequest.getEndDay();
		String InputTitle = surveyRequest.getTitle();
		int pageNumber = surveyRequest.getCurrentPage(); // 頁數，從0開始
		int pageSize = 10; // 每頁的記錄數

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<SurveyHead> Item = surveyHeadDao.findByTitleContainingOrStartDayBetween(InputTitle, start, end, pageable);
//		for(SurveyHead singel:Item) {
//			String describe=singel.getDescription();
//		} 

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), Item);
	}

	@Override
	public SurveyBodyResponse findBySurveySelection(SurveyRequest surveyRequest) {

		String questionIdInput = surveyRequest.getSurveyId();
//		int pageNumber = surveyRequest.getCurrentPage(); // 頁數，從0開始
//		int pageSize = 10; // 每頁的記錄數
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<SurveyBody> item = surveBodyDao.findByQuestionIdContaining(questionIdInput, pageable);
		List<SurveyBody> item = surveBodyDao.findByQuestionIdContaining(questionIdInput);
		return new SurveyBodyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), item);
	}

	@Override
	public SurveyResponse deleteSurveySelection(SurveyRequest surveyRequest) {
		String questionIdInput = surveyRequest.getSurveyId();
		List<SurveyBody> item = surveBodyDao.findByQuestionIdContaining(questionIdInput);
		Optional<SurveyHead> opSurveyHead = surveyHeadDao.findById(questionIdInput);
		SurveyHead getHeadInfo = opSurveyHead.get();
		getHeadInfo.setQuestionId(null);
		surveyHeadDao.save(getHeadInfo);
		surveBodyDao.deleteAll(item);

		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public SurveyResponse findbyAllColume(SurveyRequest surveyRequest) {
		int pageNumber = surveyRequest.getCurrentPage(); // 頁數，從0開始
		int pageSize = 10; // 每頁的記錄數
		Page<SurveyHead> getWholeList = surveyHeadDao.findAll(PageRequest.of(pageNumber, pageSize));
		return new SurveyResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), getWholeList);
	}

}
