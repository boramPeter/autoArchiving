package refer

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


import internal.GlobalVariable

public class refer {
	@Keyword
	public void offAtten(){
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/home')

		//사이드메뉴 오픈
		WebUI.click(findTestObject('engine(근태관리)/sidemenu/sidemenu_btn'))
		Mobile.delay(1)

		//결재관리 메뉴 확장
		WebElement apprMenuBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(apprMenuBtn))
		Mobile.delay(1)

		//비근무시간 버튼 선택
		WebElement offAttenBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprOffattenBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(offAttenBtn))
		Mobile.delay(2)

		//12월 전체부서 요청일기준 오름차순 확인
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/approval/outing?empId=&empName=&deptId=&deptName=&startDate=2020-12-01&endDate=2020-12-25&statusCode=READY&statusCode=PROGRESS&statusCode=ACCEPTED&statusCode=REJECTED&statusCode=ACCEPT_CANCELED')

		//1215-1 비근무내역 진입
		WebElement offAtten_detailBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/offAtten/offAtten_detailBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(offAtten_detailBtn))
		Mobile.delay(3)

		//첫번째꺼 날짜 가져오기
		def offTxt1 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/offAtten/offAtten_202012150930Txt'), 'textContent')
		def offNo1 = offTxt1.replaceAll ("[^\\d]", "")


		//상세페이지 타이틀 확인
		if(WebUI.verifyTextPresent('.*결재 처리.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logvacReq = new KeywordLogger()
			logvacReq.logInfo('상세 조회 노출 안됨')
		}

		//1215-1에서 뒤로가기
		WebElement dayatten_backBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/myAttenMenu/myAtten/myatten_backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//1215-2 근무내역 진입
		WebElement offAtten_detailBtn1 = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/offAtten/offAtten_detailBtn1'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(offAtten_detailBtn1))
		Mobile.delay(3)

		//두번째꺼 날짜 가져오기
		def offTxt2 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/offAtten/offAtten_202012150930Txt'), 'textContent')
		def offNo2 = offTxt2.replaceAll ("[^\\d]", "")

		//1215-2에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//첫번째 두번째 내림차순 확인
		if(offNo1 >= offNo2 ==false)
		{
			throw new StepErrorException("1~2내림차순 안맞음")
			KeywordUtil.markFailed('1~2내림차순이 안맞음.')
		}

		//1211 근무내역 진입
		WebElement offAtten_detailBtn2 = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/offAtten/offAtten_detailBtn2'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(offAtten_detailBtn2))
		Mobile.delay(3)

		//세번째꺼 날짜 가져오기
		def offTxt3 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/offAtten/offAtten_202012150930Txt'), 'textContent')
		def offNo3 = offTxt3.replaceAll ("[^\\d]", "")

		//1211에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//두번째 세번째 내림차순 확인
		if(offNo2 >= offNo3 ==false)
		{
			throw new StepErrorException("2~3내림차순 안맞음")
			KeywordUtil.markFailed('2~3내림차순이 안맞음.')
		}

		//1204 근무내역 진입
		WebElement offAtten_detailBtn3 = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/offAtten/offAtten_detailBtn3'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(offAtten_detailBtn3))
		Mobile.delay(3)

		//네번째꺼 날짜 가져오기
		def offTxt4 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/offAtten/offAtten_202012150930Txt'), 'textContent')
		def offNo4 = offTxt4.replaceAll ("[^\\d]", "")

		//1204에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)

		//세번째 네번째 내림차순 확인
		if(offNo3 >= offNo4 ==false)
		{
			throw new StepErrorException("3~4내림차순 안맞음")
			KeywordUtil.markFailed('3~4내림차순이 안맞음.')
		}

		//출퇴근내용에서 뒤로가기


	}

	@Keyword
	public void dayAtten(){
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/home')

		//사이드메뉴 오픈
		WebUI.click(findTestObject('engine(근태관리)/sidemenu/sidemenu_btn'))
		Mobile.delay(1)

		//결재관리 메뉴 확장
		WebElement apprMenuBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(apprMenuBtn))
		Mobile.delay(1)

		//일근무시간변경 버튼 선택
		WebElement dayAttenBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprDayAttenBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayAttenBtn))
		Mobile.delay(1)

		//12월 전체부서 요청일기준 오름차순 확인
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/approval/workHours?empId=&empName=&deptId=&deptName=&startDate=2020-12-01&endDate=2021-01-25&statusCode=READY&statusCode=PROGRESS&statusCode=ACCEPTED&statusCode=REJECTED')

		//1223 일근무시간변경내역 진입
		WebElement dayAtten_detailBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/dayAtten/dayAtten_detailBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayAtten_detailBtn))
		Mobile.delay(3)

		//첫번째꺼 날짜 가져오기
		def dayTxt1 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/dayAtten/dayAtten_20201223TXT'), 'textContent')
		def dayNo1 = dayTxt1.replaceAll ("[^\\d]", "")

		//상세페이지 타이틀 확인
		if(WebUI.verifyTextPresent('.*상세 조회.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logvacReq = new KeywordLogger()
			logvacReq.logInfo('상세 조회 노출 안됨')
		}


		//1223에서 뒤로가기
		WebElement dayatten_backBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/myAttenMenu/myAtten/myatten_backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//1209 근무내역 진입
		WebElement dayAtten_detailBtn1 = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/dayAtten/dayAtten_detailBtn1'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayAtten_detailBtn1))
		Mobile.delay(3)

		//두번째꺼 날짜 가져오기
		def dayTxt2 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/dayAtten/dayAtten_20201223TXT'), 'textContent')
		def dayNo2 = dayTxt2.replaceAll ("[^\\d]", "")

		//1209에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//첫번째 두번째 내림차순 확인
		if(dayNo1 >= dayNo2 ==false)
		{
			throw new StepErrorException("1~2내림차순 안맞음")
			KeywordUtil.markFailed('1~2내림차순이 안맞음.')
		}

		//1201 근무내역 진입
		WebElement dayAtten_detailBtn2 = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/appr/dayAtten/dayAtten_detailBtn2'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayAtten_detailBtn2))
		Mobile.delay(3)

		//세번째꺼 날짜 가져오기
		def dayTxt3 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/dayAtten/dayAtten_20201223TXT'), 'textContent')
		def dayNo3 = dayTxt3.replaceAll ("[^\\d]", "")

		//1201에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)

		//두번째 세번째 내림차순 확인
		if(dayNo2 >= dayNo3 ==false)
		{
			throw new StepErrorException("2~3내림차순 안맞음")
			KeywordUtil.markFailed('2~3내림차순이 안맞음.')
		}

		//일근무변경에서 뒤로가기
	}

	@Keyword
	public void minAtten(){
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/home')

		//사이드메뉴 오픈
		WebUI.click(findTestObject('engine(근태관리)/sidemenu/sidemenu_btn'))
		Mobile.delay(1)

		//결재관리 메뉴 확장
		WebElement apprMenuBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(apprMenuBtn))
		Mobile.delay(1)

		//최소근무시간변경 버튼 선택
		WebElement minAttenBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMinAttenBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(minAttenBtn))
		Mobile.delay(1)

		//12월 전체부서 요청일기준 오름차순 확인
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/approval/minWorkHours?empId=&empName=&deptId=&deptName=&startDate=2020-11-02&endDate=2020-12-25&statusCode=READY&statusCode=PROGRESS&statusCode=ACCEPTED&statusCode=REJECTED')

		//1223 최소근무시간변경내역 진입
		WebElement minAtten_detailBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/minAtten/minAtten_detailBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(minAtten_detailBtn))
		Mobile.delay(2)

		//첫번째꺼 날짜 가져오기
		def minTxt1 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/minAtten/minAtten_20201223Txt'), 'textContent')
		def minNo1 = minTxt1.replaceAll ("[^\\d]", "")

		//상세페이지 타이틀 확인
		if(WebUI.verifyTextPresent('.*상세 조회.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logvacReq = new KeywordLogger()
			logvacReq.logInfo('상세 조회 노출 안됨')
		}

		//1223에서 뒤로가기
		WebElement dayatten_backBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/myAttenMenu/myAtten/myatten_backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//1119 근무내역 진입
		WebElement minAtten_detailBtn1 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/minAtten/minAtten_detailBtn1'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(minAtten_detailBtn1))
		Mobile.delay(2)

		//두번째꺼 날짜 가져오기
		def minTxt2 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/minAtten/minAtten_20201223Txt'), 'textContent')
		def minNo2 = minTxt2.replaceAll ("[^\\d]", "")

		//1119에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//첫번째 두번째 내림차순 확인
		if(minNo1 >= minNo2 ==false)
		{
			throw new StepErrorException("1~2내림차순 안맞음")
			KeywordUtil.markFailed('1~2내림차순이 안맞음.')
		}

		//1113 근무내역 진입
		WebElement minAtten_detailBtn2 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/minAtten/minAtten_detailBtn2'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(minAtten_detailBtn2))
		Mobile.delay(2)

		//세번째꺼 날짜 가져오기
		def minTxt3 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/minAtten/minAtten_20201223Txt'), 'textContent')
		def minNo3 = minTxt3.replaceAll ("[^\\d]", "")

		//1113에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)

		//두번째 세번째 내림차순 확인
		if(minNo2 >= minNo3 ==false)
		{
			throw new StepErrorException("2~3내림차순 안맞음")
			KeywordUtil.markFailed('2~3내림차순이 안맞음.')
		}

		//최소근무변경에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)
	}

	@Keyword
	public void ovtAtten(){
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/home')

		//사이드메뉴 오픈
		WebUI.click(findTestObject('engine(근태관리)/sidemenu/sidemenu_btn'))
		Mobile.delay(1)

		//결재관리 메뉴 확장
		WebElement apprMenuBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(apprMenuBtn))
		Mobile.delay(1)

		//표준근무시간변경 버튼 선택
		WebElement otvAttenBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprOvtAttenBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(otvAttenBtn))
		Mobile.delay(2)

		//12월 전체부서 요청일기준 오름차순 확인
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/approval/overTime?empId=&empName=&deptId=&deptName=&startDate=2020-12-01&endDate=2020-12-27&statusCode=READY&statusCode=PROGRESS&statusCode=ACCEPTED&statusCode=REJECTED&statusCode=ACCEPT_CANCELED&workType=WEEKDAY_OVERWORK&workType=WEEKDAY_NIGHTWORK&workType=HOLIDAY_BASIC&workType=HOLIDAY_NIGHTWORK')

		//1209 표준근무시간변경내역 진입
		WebElement ovtAtten_detailBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/ovtAtten/ovtAtten_detailBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(ovtAtten_detailBtn))
		Mobile.delay(2)

		//첫번째꺼 날짜 가져오기
		def ovtTxt1 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/ovtAtten/ovtAtten_20201209Txt'), 'textContent')
		def ovtNo1 = ovtTxt1.replaceAll ("[^\\d]", "")

		//상세페이지 타이틀 확인
		if(WebUI.verifyTextPresent('.*결재 처리.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logvacReq = new KeywordLogger()
			logvacReq.logInfo('결재 처리 노출 안됨')
		}

		//1209에서 뒤로가기
		WebElement dayatten_backBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/myAttenMenu/myAtten/myatten_backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//1202 근무내역 진입
		WebElement ovtAtten_detailBtn1 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/ovtAtten/ovtAtten_detailBtn1'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(ovtAtten_detailBtn1))
		Mobile.delay(2)

		//두번째꺼 날짜 가져오기
		def ovtTxt2 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/ovtAtten/ovtAtten_20201209Txt'), 'textContent')
		def ovtNo2 = ovtTxt2.replaceAll ("[^\\d]", "")

		//1202에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)

		//첫번째 두번째 내림차순 확인
		if(ovtNo1 >= ovtNo2 ==false)
		{
			throw new StepErrorException("1~2내림차순 안맞음")
			KeywordUtil.markFailed('1~2내림차순이 안맞음.')
		}

		//표준외근무내역에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)
	}

	@Keyword
	public void vac(){
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/home')

		//사이드메뉴 오픈
		WebUI.click(findTestObject('engine(근태관리)/sidemenu/sidemenu_btn'))
		Mobile.delay(1)

		//결재관리 메뉴 확장
		WebElement apprMenuBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(apprMenuBtn))
		Mobile.delay(1)

		//휴가 버튼 선택
		WebElement vacBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprVacBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(vacBtn))
		Mobile.delay(1)

		//12월 전체부서 요청일기준 오름차순 확인
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/approval/vacation?empId=&empName=&deptId=&deptName=&startDate=2020-12-01&endDate=2020-12-25&statusCode=READY&statusCode=PROGRESS&statusCode=ACCEPTED&statusCode=REJECTED&statusCode=ACCEPT_CANCELED&vacationCode1=&vacationCode2=&vacationCodeName=%EC%A0%84%EC%B2%B4')

		//1223 휴가 진입
		WebElement vac_detailBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/vac/vac_detailBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(vac_detailBtn))
		Mobile.delay(2)

		//첫번째꺼 날짜 가져오기
		def vacTxt1 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/vac/vac_20201223Txt'), 'textContent')
		def vacNo1 = vacTxt1.replaceAll ("[^\\d]", "")

		//상세페이지 타이틀 확인
		if(WebUI.verifyTextPresent('.*상세 조회.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logvacReq = new KeywordLogger()
			logvacReq.logInfo('상세 조회 노출 안됨')
		}


		//1223에서 뒤로가기
		WebElement dayatten_backBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/myAttenMenu/myAtten/myatten_backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//1217 휴가 진입
		WebElement vac_detailBtn1 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/vac/vac_detailBtn1'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(vac_detailBtn1))
		Mobile.delay(2)

		//두번째꺼 날짜 가져오기
		def vacTxt2 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/vac/vac_20201223Txt'), 'textContent')
		def vacNo2 = vacTxt2.replaceAll ("[^\\d]", "")

		//1217에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//첫번째 두번째 내림차순 확인
		if(vacNo1 >= vacNo2 ==false)
		{
			throw new StepErrorException("1~2내림차순 안맞음")
			KeywordUtil.markFailed('1~2내림차순이 안맞음.')
		}

		//1202 근무내역 진입
		WebElement vac_detailBtn2 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/vac/vac_detailBtn2'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(vac_detailBtn2))
		Mobile.delay(2)

		//세번째꺼 날짜 가져오기
		def vacTxt3 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/vac/vac_20201223Txt'), 'textContent')
		def vacNo3 = vacTxt3.replaceAll ("[^\\d]", "")

		//1202에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)

		//두번째 세번째 내림차순 확인
		if(vacNo2 >= vacNo3 ==false)
		{
			throw new StepErrorException("2~3내림차순 안맞음")
			KeywordUtil.markFailed('2~3내림차순이 안맞음.')
		}

		//휴가에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)
	}

	@Keyword
	public void rewVac(){
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/home')

		//사이드메뉴 오픈
		WebUI.click(findTestObject('engine(근태관리)/sidemenu/sidemenu_btn'))
		Mobile.delay(1)

		//결재관리 메뉴 확장
		WebElement apprMenuBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/sidemenu_apprMenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(apprMenuBtn))
		Mobile.delay(1)

		//포상휴가 버튼 선택
		WebElement rewVacBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/sidemenu_apprRewVacBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rewVacBtn))
		Mobile.delay(2)

		//12월 전체부서 요청일기준 오름차순 확인
		WebUI.navigateToUrl('https://m-schedule.we.kakaowork.com/approval/reward?empId=&empName=&deptId=&startDate=2020-12-01&endDate=2020-12-25&statusCode=READY&statusCode=PROGRESS&statusCode=ACCEPTED&statusCode=REJECTED&statusCode=ACCEPT_CANCELED&vacationCode1=&vacationCode2=&vacationCodeName=%EC%A0%84%EC%B2%B4')

		//1223 포상휴가 진입
		WebElement rewVac_detailBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/rewVac/rewVac_detailBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rewVac_detailBtn))
		Mobile.delay(2)

		//첫번째꺼 날짜 가져오기
		def rewVacTxt1 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/rewVac/rewVac_20201223Txt'), 'textContent')
		def rewVacNo1 = rewVacTxt1.replaceAll ("[^\\d]", "")

		//상세페이지 타이틀 확인
		if(WebUI.verifyTextPresent('.*상세 조회.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logvacReq = new KeywordLogger()
			logvacReq.logInfo('상세 조회 노출 안됨')
		}

		//1223에서 뒤로가기
		WebElement dayatten_backBtn = WebUiCommonHelper.findWebElement(findTestObject('engine(근태관리)/sidemenu/myAttenMenu/myAtten/myatten_backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))

		//1222 포상휴가 진입
		WebElement rewVac_detailBtn1 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/rewVac/rewVac_detailBtn1'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rewVac_detailBtn1))
		Mobile.delay(2)

		//두번째꺼 날짜 가져오기
		def rewVacTxt2 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/rewVac/rewVac_20201223Txt'), 'textContent')
		def rewVacNo2 = rewVacTxt2.replaceAll ("[^\\d]", "")

		//1222에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(2)

		//첫번째 두번째 내림차순 확인
		if(rewVacNo1 >= rewVacNo2 ==false)
		{
			throw new StepErrorException("1~2내림차순 안맞음")
			KeywordUtil.markFailed('1~2내림차순이 안맞음.')
		}


		//1217 포상휴가 진입
		WebElement rewVac_detailBtn2 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/rewVac/rewVac_detailBtn2'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rewVac_detailBtn2))
		Mobile.delay(2)

		//세번째꺼 날짜 가져오기
		def rewVacTxt3 = WebUI.getAttribute(findTestObject('Object Repository/engine(근태관리)/sidemenu/appr/rewVac/rewVac_20201223Txt'), 'textContent')
		def rewVacNo3 = rewVacTxt3.replaceAll ("[^\\d]", "")

		//1217에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)

		//두번째 세번째 내림차순 확인
		if(rewVacNo2 >= rewVacNo3 ==false)
		{
			throw new StepErrorException("2~3내림차순 안맞음")
			KeywordUtil.markFailed('2~3내림차순이 안맞음.')
		}

		//포상휴가에서 뒤로가기
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dayatten_backBtn))
		Mobile.delay(1)
	}

}
