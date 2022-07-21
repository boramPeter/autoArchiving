package 전자결재_PC
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class home {

	@Keyword
	public void login(){
		//오픈 브라우저

		WebUI.openBrowser('https://approval.we.kakaowork.com/?org_id=57544')

		WebUI.setText(findTestObject('전자결재/home/login/login_id'), 'work.engine.tester@gmail.com')

		WebUI.setEncryptedText(findTestObject('전자결재/home/login/login_PW'), 'AQCMEzCCGByQ8DLodILUgg==')

		WebUI.click(findTestObject('전자결재/home/login/loginBtn'))

	}

	@Keyword
	public void adminChange(){
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//GNB >나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//GNB >나의프로필 > 관리자전환 선택
		WebUI.click(findTestObject('전자결재/home/config/admin/adminChangeBtn'))

		WebUI.switchToWindowIndex(1)

		WebDriver driver = DriverFactory.getWebDriver()

		WebUI.delay(2)

		//기초설정 레이어 확인
		try {
			driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/div/strong")).isDisplayed()

			driver.findElement(By.xpath("/html/body/div[3]/div/button")).click()

			WebUI.verifyElementPresent(findTestObject('전자결재/home/config/admin/adminTitle'),1)

			def title = WebUI.getWindowTitle()

			WebUI.verifyEqual(title, '결재 현황 | 전자결재 관리자')

			WebUI.closeWindowIndex(1)

			WebUI.switchToWindowIndex(0)

		}
		catch (def StepFailedException) {

			WebUI.verifyElementPresent(findTestObject('전자결재/home/config/admin/adminTitle'),1)

			def title = WebUI.getWindowTitle()

			WebUI.verifyEqual(title, '결재 현황 | 전자결재 관리자')

			WebUI.closeWindowIndex(1)

			WebUI.switchToWindowIndex(0)

		}

	}

	@Keyword
	public void approvalLine(){
		WebDriver driver = DriverFactory.getWebDriver()

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//GNB > 나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//로그아웃 선택
		WebUI.click(findTestObject('전자결재/home/config/logoutBtn'))

		//컨펌에서 확인선택
		WebElement logoutBtn_confirm = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/logoutBtn_confirm'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(logoutBtn_confirm))

		//결재암호 계정으로 로그인
		WebUI.setText(findTestObject('전자결재/home/login/login_id'), 'work.engine.tester+9@gmail.com')

		WebUI.setEncryptedText(findTestObject('전자결재/home/login/login_PW'), 'AQCMEzCCGByQ8DLodILUgg==')

		WebUI.click(findTestObject('전자결재/home/login/loginBtn'))

		//GNB >나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		'결재선관리 선택'
		WebUI.click(findTestObject('전자결재/home/config/approvalLine/approvalLineBtn'))

		'결재선관리 타이틀 확인'
		WebUI.verifyElementPresent(findTestObject('전자결재/home/config/approvalLine/approvalLineTitle'),1)

		'새결재선등록 선택'
		WebElement newApprovalLineBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/approvalLine/newApprovalLineBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(newApprovalLineBtn))

		//결재선 등록
		WebUI.delay(3)

		WebElement approvalLineTxt = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/div/div/input"))
		WebElement approvalNameDuplBtn = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/button"))

		Actions action = new Actions(driver)
		action.click(approvalLineTxt)
		action.perform()
		action.sendKeys("자동화테스트 결재선")
		action.perform()
		WebUI.delay(1)

		action.click(approvalNameDuplBtn)
		action.perform()


		WebUI.delay(1)

		//결재자 검색
		WebUI.switchToWindowIndex(0)

		WebElement nameLineTxt = driver.findElement(By.xpath('/html/body/div[3]/div/div[1]/div[2]/div[1]/div[1]/div/div/div/input'))

		action.click(nameLineTxt)
		action.perform()
		action.sendKeys("퇴사셔틀")
		action.perform()

		WebUI.delay(1)

		//결재자 등록

		WebElement Dplclick = driver.findElement(By.xpath('/html/body/div[3]/div/div[1]/div[2]/div[1]/div[2]/ul/li/ul/li/ul/li/div'))
		action.click(Dplclick)
		action.perform()

		WebUI.delay(1)


		//결재자 지정
		WebElement approvalSaveBtn = driver.findElement(By.xpath('/html/body/div[3]/div/div[1]/div[2]/div[2]/button[1]'))

		action.click(approvalSaveBtn)
		action.perform()

		WebUI.delay(1)

		//결재자 저장
		WebElement approvalPopSaveBtn = driver.findElement(By.xpath('/html/body/div[3]/div/div[2]/div/button[2]'))

		action.click(approvalPopSaveBtn)
		action.perform()

		WebUI.delay(1)

		//결재선 확인
		if(WebUI.verifyTextPresent('.*자동화테스트 결재선.*',true,FailureHandling.STOP_ON_FAILURE) ==false)
		{
			KeywordLogger logName = new KeywordLogger()
			logName.logInfo('자동화테스트 결재선 안됨')
		}

		//결재자선 확인
		WebElement newApprovalLine = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/approvalLine/newApprovalLine'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(newApprovalLine))

		//삭제버튼 선택
		WebElement newApprovalLinDeleteBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/approvalLine/newApprovalLinDeleteBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(newApprovalLinDeleteBtn))

		//컨펌에서 확인선택하기
		WebElement deleteCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/approvalLine/deleteCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(deleteCfmBtn))

		WebUI.delay(2)

		try {
			driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/span')).isDisplayed()

			KeywordLogger logName1 = new KeywordLogger()

			logName1.logInfo('자동화테스트 결재선 삭제완료')

		}
		catch (def StepFailedException) {
			KeywordLogger logexcep = new KeywordLogger()

			logexcep.logInfo('결재선을 등록해 주세요. 문구가 안보입니다. 매뉴얼로 확인해주세요. 해당케이스 종료합니다.')

			throw new StepErrorException("결재선을 등록해 주세요. 문구가 안보입니다. 매뉴얼로 확인해주세요. 해당케이스 종료합니다. ")

		}
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//GNB >나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//로그아웃 선택
		WebUI.click(findTestObject('전자결재/home/config/logoutBtn'))

		//컨펌 선택
		WebElement logoutBtn_confirm1 = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/logoutBtn_confirm'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(logoutBtn_confirm1))

		// 계정 원복
		WebUI.setText(findTestObject('전자결재/home/login/login_id'), 'work.engine.tester@gmail.com')

		WebUI.setEncryptedText(findTestObject('전자결재/home/login/login_PW'), 'AQCMEzCCGByQ8DLodILUgg==')

		WebUI.click(findTestObject('전자결재/home/login/loginBtn'))
	}

	@Keyword
	public void approvalPw(){
		WebDriver driver = DriverFactory.getWebDriver()

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//GNB > 나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//로그아웃 선택
		WebUI.click(findTestObject('전자결재/home/config/logoutBtn'))

		//컨펌에서 확인선택
		WebElement logoutBtn_confirm = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/logoutBtn_confirm'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(logoutBtn_confirm))

		//결재암호 계정으로 로그인
		WebUI.setText(findTestObject('전자결재/home/login/login_id'), 'dfaafd@naver.com')

		WebUI.setEncryptedText(findTestObject('전자결재/home/login/login_PW'), 'AQCMEzCCGByQ8DLodILUgg==')

		WebUI.click(findTestObject('전자결재/home/login/loginBtn'))

		//GNB >나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//결재암호 토글 on
		Actions action = new Actions(driver)

		WebElement togglePwOnBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/PW/togglePwOnBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(togglePwOnBtn))

		//컨펌에서 확인
		WebElement pwOnCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/PW/pwOnCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(pwOnCfmBtn))

		//비밀번호 입력
		WebElement password = driver.findElement(By.xpath('/html/body/div[3]/div/div[1]/form/div/div[2]/label/span[1]'))

		action.click(password)
		action.perform()
		action.sendKeys("000000")
		action.perform()
		WebUI.delay(2)

		WebElement passwordCfm = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/PW/passwordCfm'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(passwordCfm))

		//GNB >나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(togglePwOnBtn))

		WebElement password2 = driver.findElement(By.xpath('/html/body/div[3]/div/div[1]/form/div/div[2]/label/span[1]'))

		action.click(password2)
		action.perform()
		action.sendKeys("000000")
		action.perform()

		WebUI.delay(2)

		WebElement passwordCfm2 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/PW/passwordCfm'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(passwordCfm2))

		//GNB >나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//로그아웃 선택
		WebUI.click(findTestObject('전자결재/home/config/logoutBtn'))

		//컨펌 선택
		WebElement logoutBtn_confirm1 = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/logoutBtn_confirm'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(logoutBtn_confirm1))

		// 계정 원복
		WebUI.setText(findTestObject('전자결재/home/login/login_id'), 'work.engine.tester@gmail.com')

		WebUI.setEncryptedText(findTestObject('전자결재/home/login/login_PW'), 'AQCMEzCCGByQ8DLodILUgg==')

		WebUI.click(findTestObject('전자결재/home/login/loginBtn'))

	}

	@Keyword
	public void deleAppr(){
		WebDriver driver = DriverFactory.getWebDriver()

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//GNB > 나의프로필 선택
		WebUI.click(findTestObject('전자결재/home/config/myProf'))

		//대리결재자 버튼 설정
		WebUI.click(findTestObject('Object Repository/전자결재/home/config/deleAppr/deleApprBtn'))
		Actions action = new Actions(driver)

		WebElement userName = driver.findElement(By.xpath('//*[@id="deputySetUser"]'))

		//대리결재자 설정
		action.click(userName)
		action.perform()
		action.sendKeys("퇴사셔틀")
		action.perform()

		WebElement userNameLayer = driver.findElement(By.xpath('//*[@id="deputySetUser0"]'))
		action.click(userNameLayer)

		//대리결재기간 시작일자 선택
		WebElement fromDate = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div[1]/table/tbody/tr[2]/td/div/div[1]/div/div/input[2]'))
		action.click(fromDate)
		action.perform()

		WebElement nextFrom = driver.findElement(By.xpath('/html/body/div[5]/div[1]/span[2]/span'))
		action.click(nextFrom)
		action.perform()
		action.click(nextFrom)
		action.perform()
		action.click(nextFrom)
		action.perform()

		WebElement fromDay = driver.findElement(By.xpath('/html/body/div[5]/div[2]/div/div[2]/div/span[10]'))
		action.click(fromDay)
		action.perform()

		//대리결재기간 종료일자 선택
		WebElement toDate = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div[1]/table/tbody/tr[2]/td/div/div[2]/div/div/input[2]'))

		action.click(toDate)
		action.perform()

		WebElement toDay = driver.findElement(By.xpath('/html/body/div[6]/div[2]/div/div[2]/div/span[11]'))
		action.click(toDay)
		action.perform()

		//대리결재자 위임사유 입력
		WebElement reasonText = driver.findElement(By.xpath('//*[@id="deputySetReason"]'))
		action.click(reasonText)
		action.perform()
		action.sendKeys("테스트중입니다")
		action.perform()
		WebUI.delay(2)

		//대리결재자 저장 선택
		WebElement saveBtn = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div[2]/div/button'))
		action.click(saveBtn)
		action.perform()

		//저장에서 컨펌 선택
		WebElement deleApprCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/deleAppr/deleApprCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(deleApprCfmBtn))

		'대리결재는 과거날짜만 이력에 쌓이므로 해당케이스 삭제'
		/*//설정이력 탭
		 WebElement setHisTab = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[2]/ul/li[2]/a'))
		 action.click(setHisTab)
		 action.perform()
		 //설정여부 확인
		 WebElement cfmName = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div/table/tbody/tr[1]/td[2]'))
		 def	cfmNameValue = cfmName.getText();
		 KeywordLogger logvac = new KeywordLogger()
		 logvac.logInfo(cfmNameValue)
		 WebUI.verifyMatch(cfmNameValue, '.*dfaafd.*', true, FailureHandling.STOP_ON_FAILURE)
		 //설정 탭
		 WebElement setTab = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[2]/ul/li[1]/a'))
		 action.click(setTab)
		 action.perform()*/

		//수정 버튼 선택
		WebElement editBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/deleAppr/editBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(editBtn))

		//이름 수정 시도
		WebElement userEditName = driver.findElement(By.xpath('//*[@id="deputyEditUser"]'))
		action.click(userEditName)
		action.perform()
		action.sendKeys("peter.bo")
		action.perform()

		//수정에서 레이어 선택
		WebElement userEditNameLayer = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div[1]/table/tbody/tr[1]/td/div/div/div/div[2]/ul/li[1]'))
		action.click(userEditNameLayer)
		action.perform()

		//저장선택
		WebElement saveBtn1 = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div[2]/div/button[2]'))
		action.click(saveBtn1)
		action.perform()

		//컨펌에서 확인
		WebElement editCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/deleAppr/editCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(editCfmBtn))

		'대리결재는 과거날짜만 이력에 쌓이므로 해당케이스 삭제'
		/*//설정이력 탭
		 WebElement setHisTab1 = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[2]/ul/li[2]/a'))
		 action.click(setHisTab1)
		 action.perform()
		 //수정 여부 확인
		 WebElement cfmName1 = driver.findElement(By.xpath('/html/body/div[3]/div/div/div[3]/div/table/tbody/tr[1]/td[2]'))
		 def	cfmNameValue1 = cfmName1.getText();
		 KeywordLogger log = new KeywordLogger()
		 log.logInfo(cfmNameValue1)
		 WebUI.verifyMatch(cfmNameValue1, '.*peter.bo.*', true)
		 //대리결재자 설정탭 재진입
		 action.click(setTab)
		 action.perform()*/

		//삭제버튼 선택
		WebElement deleteBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/deleAppr/deleteBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(deleteBtn))

		//삭제에서 컨펌 선택
		WebElement deleteCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/config/deleAppr/deleteCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(deleteCfmBtn))
	}

	@Keyword
	public void myStatus(){
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//상신한 선택
		WebElement submitBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/mystatus/submitBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(submitBtn))

		def submitURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(submitURL, 'https://approval.we.kakaowork.com/box/dd')

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//반려된 선택
		WebElement rejectBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/mystatus/rejectBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rejectBtn))

		def relectURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(relectURL, 'https://approval.we.kakaowork.com/box/dr')

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//결재전 선택
		WebElement toApproveBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/mystatus/toApproveBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(toApproveBtn))

		def toApproveURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(toApproveURL, 'https://approval.we.kakaowork.com/box/ab')

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//수신 선택
		WebElement recipientBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/mystatus/recipientBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(recipientBtn))

		def recipientURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(recipientURL, 'https://approval.we.kakaowork.com/box/rb')

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//공람 선택
		WebElement circulationBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/mystatus/circulationBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(circulationBtn))

		def circulationURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(circulationURL, 'https://approval.we.kakaowork.com/box/sh')

	}

	@Keyword
	public void myDraft(){
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//내가 상신한 문서 확인
		def mySubmissionsTitle = WebUI.getAttribute(findTestObject('Object Repository/전자결재/home/mydraft/mySubmissions/mySubmissionsTitle'), 'textContent').replaceAll (" ","")

		//내가 상신한 문서 진입
		WebElement mySubmissionsTitleClick = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/mydraft/mySubmissions/mySubmissionsTitle'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(mySubmissionsTitleClick))

		def mySubmissionsTitleURL = WebUI.getUrl().replaceAll ("[0-9.]",'')

		//페이지 이동 확인
		WebUI.verifyEqual(mySubmissionsTitleURL, 'https://approvalwekakaoworkcom/doc/dd//')

		def mySubmissionsInnerTitle = WebUI.getAttribute(findTestObject('Object Repository/전자결재/home/mydraft/mySubmissions/mySubmissionsInnerTitle'), 'textContent').replaceAll (" ","")

		//선택한 기안 확인
		WebUI.verifyEqual(mySubmissionsTitle,mySubmissionsInnerTitle)


		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//내가 결재할 문서탭 진입
		WebElement pendingDocTab = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/mydraft/pendingDoc/pendingDocTab'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(pendingDocTab))

		//내가 결재할 문서 확인
		def pendingTitle = WebUI.getAttribute(findTestObject('Object Repository/전자결재/home/mydraft/pendingDoc/pendingTitle'), 'textContent').replaceAll (" ","")

		//내가 결재할 문서 진입
		WebElement pendingTitleClick = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/mydraft/pendingDoc/pendingTitle'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(pendingTitleClick))

		def pendingInnerTitle = WebUI.getAttribute(findTestObject('Object Repository/전자결재/home/mydraft/pendingDoc/pendingInnerTitle'), 'textContent').replaceAll (" ","")

		//선택한 기안 확인
		WebUI.verifyEqual(pendingTitle,pendingInnerTitle)

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')


		//최근 결재 의견 선택
		WebElement recent = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/mydraft/recent/recentTab'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(recent))

		def Opinion = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/Opinion3'), 'textContent').replaceAll (" ","")

		def Approve = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/Approve3'), 'textContent').replaceAll (" ","")

		def ApproveGruop = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/ApproveGruop3'), 'textContent').replaceAll (" ","")

		//결재 의견 진입
		WebElement recent_1st = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/mydraft/recent/Opinion3'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(recent_1st))

		def recent_1stURL = WebUI.getUrl().replaceAll ("[0-9.]",'')

		//페이지 이동 확인
		WebUI.verifyEqual(recent_1stURL, 'https://approvalwekakaoworkcom/doc/dd//')

		def inner_Approve  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve'), 'textContent').replaceAll (" ","")

		def inner_Opinion = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Opinion'), 'textContent').replaceAll (" ","")

		//결재인지 부서협조인지 먼저 체크해봄
		try {

			if (WebUI.verifyMatch(ApproveGruop, '결재', false, FailureHandling.OPTIONAL) == true)
			{

				if (WebUI.verifyMatch(Approve, inner_Approve, false, FailureHandling.OPTIONAL) == true)
				{

					WebUI.verifyMatch(Opinion,inner_Opinion, false)

				}
				else
				{
					def inner_Approve2  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve2'), 'textContent').replaceAll (" ","")
					def inner_Opinion2 = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Opinion2'), 'textContent').replaceAll (" ","")

					if (WebUI.verifyMatch(Approve, inner_Approve2, false, FailureHandling.OPTIONAL) == true)
					{
						WebUI.verifyMatch(Opinion,inner_Opinion2, false)

					}
					else
					{
						def inner_Opinion3  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve3'), 'textContent').replaceAll (" ","")
						def inner_Approve3  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve3'), 'textContent').replaceAll (" ","")
						if (WebUI.verifyMatch(Approve, inner_Approve3, false, FailureHandling.STOP_ON_FAILURE) == true)
						{
							WebUI.verifyMatch(Opinion,inner_Opinion3, false)
						}
					}

				}
			}
			else
			{
				if (WebUI.verifyMatch(Approve,'.*'+inner_Approve+'.*', true, FailureHandling.OPTIONAL) == true)
				{

					WebUI.verifyMatch(Opinion,inner_Opinion, true)

				}
				else
				{
					def inner_Approve2  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve2'), 'textContent').replaceAll (" ","")
					def inner_Opinion2 = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Opinion2'), 'textContent').replaceAll (" ","")

					if (WebUI.verifyMatch(Approve, '.*'+inner_Approve2+'.*' , true, FailureHandling.OPTIONAL) == true)
					{
						WebUI.verifyMatch(Opinion,inner_Opinion2, true)

					}
					else
					{
						def inner_Opinion3  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve3'), 'textContent').replaceAll (" ","")
						def inner_Approve3  = WebUI.getAttribute(findTestObject('전자결재/home/mydraft/recent/inner/inner_Approve3'), 'textContent').replaceAll (" ","")

						if (WebUI.verifyMatch(Approve, '.*'+inner_Approve3+'.*' , true, FailureHandling.STOP_ON_FAILURE) == true)
						{
							WebUI.verifyMatch(Opinion,inner_Opinion3, true)
						}
					}

				}
			}

		}

		catch (def StepFailedException) {


			KeywordUtil.markFailed('결재가 아닌 부서협조인데, 내역이 안쌓여있어서 (1줄만있거나 부서협조만 있거나) 매뉴얼로 테스트 해야함!! ')
		}

	}

	@Keyword
	public void recTemp(){
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')
		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)

		//기안작성 선택
		WebElement writeDraftBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/recTemp/writeDraft'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(writeDraftBtn))

		WebUI.delay(5)

		//레이어 확장
		WebElement layer_peter = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/home/recTemp/layer_peter'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(layer_peter))

		WebUI.delay(1)

		//템플릿 선택
		WebElement writeTempBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/ul/li[6]/div[2]/ul/li[4]'))

		action.click(writeTempBtn)
		action.perform()

		WebUI.switchToWindowIndex(1)


		//기안 제목 가져오기
		def tempTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/home/recTemp/tempTitle'), 'textContent')//.replaceAll (" ","")

		//기안제목 작성

		WebUI.scrollToPosition(0, 500)

		WebUI.delay(2)

		WebElement titleTxtBox = driver.findElement(By.xpath('//*[@id="docSubject"]'))
		action.click(titleTxtBox)
		action.perform()
		action.sendKeys("최근양식 자동화 테스트 확인")
		action.perform()


		//임시저장 선택
		WebElement DraftSaveBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/div[7]/button[1]'))
		action.click(DraftSaveBtn)
		action.perform()

		WebUI.delay(1)
		WebUI.switchToWindowIndex(0)


		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		def recentTempTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/home/recTemp/recentTempTitle'), 'textContent')//.replaceAll (" ","")

		//동일 템플릿 확인 기안 확인
		WebUI.verifyEqual(tempTitle,recentTempTitle)

		//최근 템플릿 진입
		WebElement recentTemp = driver.findElement(By.xpath('//*[@id="mainContent"]/ul[2]/li/div/ul/li[1]'))
		action.click(recentTemp)
		action.perform()

		WebUI.switchToWindowIndex(1)

		WebUI.closeWindowIndex(1)

		WebUI.switchToWindowIndex(0)

		WebUI.verifyEqual(recentTempTitle,tempTitle)


	}
}