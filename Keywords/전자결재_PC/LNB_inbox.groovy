package 전자결재_PC

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class LNB_inbox {

	@Keyword
	public void unread(){

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)

		//수신함 선택
		WebElement inbox = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/ul/li[3]/a/span[1]'))
		action.click(inbox)
		action.perform()

		//수신전
		WebElement unread = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/ul/li[3]/ul/li[1]/a'))
		action.click(unread)
		action.perform()

		//기안양식 셀렉트박스 선택 후 검색되는지 케이스

		draftTemplateTitle :for (int i = 50; i <= 70; i++) {

			WebElement draftTemplate_selectBox = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTemplate_selectBox'),30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftTemplate_selectBox))


			def draftTemplateTitle_select  = WebUI.getAttribute(findTestObject('전자결재/LNB/draftTemp/submmited/selectBox_layer',['no' : i]), 'textContent').trim()

			WebElement selectBox_layer2 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/ul/li[2]/div[1]/div/div/div/div/div/ul/li['+i+']'))
			action.click(selectBox_layer2)
			action.perform()

			WebElement searchBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
			action.click(searchBtn)
			action.perform()

			//기안검색 안되는케이스 추가
			try {
				if(WebUI.verifyElementNotPresent(findTestObject('전자결재/LNB/draftTemp/returned/notSearchText'),1,FailureHandling.STOP_ON_FAILURE) == true)
				{

					def draftTemplateTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/returned/draftTemplateTitle'),'textContent').trim()

					if (i == 1) {
						if(draftTemplateTitle == '조회 결과가 존재하지 않습니다.')
						{
							throw new StepErrorException("검색조건 안맞으므로 케이스 종료함")
						}
					}
					if(draftTemplateTitle_select == draftTemplateTitle)
					{
						break draftTemplateTitle;
					}

					else
					{
						KeywordLogger log = new KeywordLogger()
						log.logInfo('기안양식 검색 > 셀렉트박스에서'+i+'번째 검색조건 안맞음')
					}
				}

			}
			catch (def StepFailedException) {

				KeywordLogger logtitle = new KeywordLogger()
				logtitle.logInfo(i+'번째 옵션으로 검색시에 검색결과가 없습니다.')
				continue;
			}

			finally
			{
				if(i == 60)
				{
					KeywordLogger finish = new KeywordLogger()
					finish.logInfo('셀렉트박스에서 기안양식 맞는조건 없음')
					throw new StepErrorException("검색조건 안맞으므로 케이스 종료함")
				}
			}
		}


		//전체 필터로 변경 후 조회버튼 누르기
		WebElement draftTemplate_selectBox1 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTemplate_selectBox'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftTemplate_selectBox1))

		WebElement selectBox_layer3 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/ul/li[2]/div[1]/div/div/div/div/div/ul/li['+'1'+']'))
		action.click(selectBox_layer3)
		action.perform()

		WebElement searchBtn1 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn1)
		action.perform()


		//기안제목 입력 후 비교 확인
		def draftTemplateTitle1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftTitle'), 'textContent').trim()
		WebElement draftTempTextbox = driver.findElement(By.xpath('//*[@id="keyword_docSubject"]'))
		action.click(draftTempTextbox)
		action.perform()
		action.sendKeys(draftTemplateTitle1)
		action.perform()

		//검색어 입력 후 조회버튼 선택
		WebElement searchBtn2 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn2)
		action.perform()

		//검색 된거 확인
		def draftTemplateTitle2  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftTitle2'), 'textContent').trim()

		WebUI.verifyEqual(draftTemplateTitle1,draftTemplateTitle2)

		//검색된 기안 진입
		WebElement search = driver.findElement(By.xpath('//*[@id="mainContent"]/div[4]/table/tbody/tr[1]/td[3]/div/a/span'))
		action.click(search)
		action.perform()

		//기안상세에서 타이틀 가져온 뒤에 확인
		def draftDetailTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftDetailTitle'), 'textContent').trim()

		WebUI.verifyEqual(draftTemplateTitle2,draftDetailTitle)

		//검색필터 초기화 용도
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/box/rb')

		//기안자 검색
		def drafterName1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/approvalFoler/completed/drafter'), 'textContent')

		int start = drafterName1.indexOf("(")+1;

		int end = drafterName1.indexOf(")");


		//리스트에서 기안자 첫번째 이름 가져오기
		def drafterName  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/approvalFoler/completed/drafter'), 'textContent').substring(start,end);

		//기안자 셀렉트박스 선택
		WebElement toApprovalSearchSelectBox = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/approvalFoler/toApproval/toApprovalSearchSelectBox'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(toApprovalSearchSelectBox))

		//셀렉트박스에서 기안자 선택 (절대값으로 해둠)
		WebElement drafterNameSearch = driver.findElement(By.xpath('/html/body/div[2]/main/section/div/article/div[2]/ul/li[2]/div[2]/div/div/div[1]/div/div/ul/li[1]/a/span'))
		action.click(drafterNameSearch)
		action.perform()

		//검색창에 기안자 이름 입력
		WebElement drafterNameSearchTxtBox = driver.findElement(By.xpath('//*[@id="keyword_empId"]'))
		action.click(drafterNameSearchTxtBox)
		action.perform()
		action.sendKeys(drafterName)
		action.perform()


		//검색레이어에서 첫번째꺼 선택
		WebElement drafterNameSearchLayer = driver.findElement(By.xpath(' //*[@id="keyword_empId0"]'))
		action.click(drafterNameSearchLayer)
		action.perform()

		//조회버튼 선택
		WebElement searchBtn3 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn3)
		action.perform()

		//검색결과에서 첫번째 리스트에 이름가져오기
		def drafterName_search  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/approvalFoler/completed/drafter'), 'textContent').substring(start,end);//replaceAll("[^a-z]", "")

		//검색결과 비교
		WebUI.verifyEqual(drafterName,drafterName_search)

		//검색필터 초기화 용도
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/box/rb')


		// 문서번호 검색케이스 추가 (내부결재경우 문서번호가 하이픈이라 검색이 안되니까 조건 추가해둠)
		for (int j = 1; j <= 13; j++) {
			WebElement draftComplete_SearchSelectBox = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftComplete_SearchSelectBox'),30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftComplete_SearchSelectBox))

			WebElement draftComplete_SearchSelect = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftComplete_SearchSelect'),30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftComplete_SearchSelect))

			def doctNum  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/doctNum',['no' : j]), 'textContent').trim()

			//내부결재 케이스 확인
			if (doctNum == '-')
			{
				KeywordLogger logdocnum = new KeywordLogger()
				logdocnum.logInfo(j+'번째 기안은 내부결재이거나 문서번호가 없습니다.')
				continue;

			}

			// 하이픈 없을경우 일반검색 케이스 (문서번호 검색)
			else
			{
				WebElement docNumSearch = driver.findElement(By.xpath('//*[@id="keyword_docNo"]'))
				action.click(docNumSearch)
				action.perform()
				action.sendKeys(doctNum)



				WebElement searchBtn4 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
				action.click(searchBtn4)
				action.perform()

				def doctNum1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/doctNum1'), 'textContent').trim()

				if(doctNum == doctNum1)
				{
					break;
				}
				else
				{
					KeywordLogger log = new KeywordLogger()
					log.logInfo('문서번호'+j+'번째 검색조건 안맞음')
				}
			}

			if(j == '13')
			{
				KeywordLogger finish = new KeywordLogger()
				finish.logInfo('셀렉트박스에서 기안양식 맞는조건 없음')
				throw new StepErrorException("검색조건 안맞으므로 케이스 종료함")
			}

		}
	}


	@Keyword
	public void completed(){

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)

		//수신함 선택
		WebElement inbox = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/ul/li[3]/a/span[1]'))
		action.click(inbox)
		action.perform()

		//완료된
		WebElement unread = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/ul/li[3]/ul/li[2]/a'))
		action.click(unread)
		action.perform()

		//기안양식 셀렉트박스 선택 후 검색되는지 케이스

		draftTemplateTitle :for (int i = 50; i <= 70; i++) {

			WebElement draftTemplate_selectBox = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTemplate_selectBox'),30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftTemplate_selectBox))


			def draftTemplateTitle_select  = WebUI.getAttribute(findTestObject('전자결재/LNB/draftTemp/submmited/selectBox_layer',['no' : i]), 'textContent').trim()

			WebElement selectBox_layer2 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/ul/li[2]/div[1]/div/div/div/div/div/ul/li['+i+']'))
			action.click(selectBox_layer2)
			action.perform()

			WebElement searchBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
			action.click(searchBtn)
			action.perform()

			//기안검색 안되는케이스 추가
			try {
				if(WebUI.verifyElementNotPresent(findTestObject('전자결재/LNB/draftTemp/returned/notSearchText'),1,FailureHandling.STOP_ON_FAILURE) == true)
				{

					def draftTemplateTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/returned/draftTemplateTitle'),'textContent').trim()

					if (i == 1) {
						if(draftTemplateTitle == '조회 결과가 존재하지 않습니다.')
						{
							throw new StepErrorException("검색조건 안맞으므로 케이스 종료함")
						}
					}

					if(draftTemplateTitle_select == draftTemplateTitle)
					{
						break draftTemplateTitle;
					}

					else
					{
						KeywordLogger log = new KeywordLogger()
						log.logInfo('기안양식 검색 > 셀렉트박스에서'+i+'번째 검색조건 안맞음')
					}
				}

			}
			catch (def StepFailedException) {

				KeywordLogger logtitle = new KeywordLogger()
				logtitle.logInfo(i+'번째 옵션으로 검색시에 검색결과가 없습니다.')
				continue;
			}

			finally
			{
				if(i == 60)
				{
					KeywordLogger finish = new KeywordLogger()
					finish.logInfo('셀렉트박스에서 기안양식 맞는조건 없음')
					throw new StepErrorException("검색조건 안맞으므로 케이스 종료함")
				}
			}
		}


		//전체 필터로 변경 후 조회버튼 누르기
		WebElement draftTemplate_selectBox1 = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTemplate_selectBox'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftTemplate_selectBox1))

		WebElement selectBox_layer3 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/ul/li[2]/div[1]/div/div/div/div/div/ul/li['+'1'+']'))
		action.click(selectBox_layer3)
		action.perform()

		WebElement searchBtn1 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn1)
		action.perform()


		//기안제목 입력 후 비교 확인
		def draftTemplateTitle1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftTitle'), 'textContent').trim()
		WebElement draftTempTextbox = driver.findElement(By.xpath('//*[@id="keyword_docSubject"]'))
		action.click(draftTempTextbox)
		action.perform()
		action.sendKeys(draftTemplateTitle1)
		action.perform()

		//검색어 입력 후 조회버튼 선택
		WebElement searchBtn2 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn2)
		action.perform()

		//검색 된거 확인
		def draftTemplateTitle2  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftTitle2'), 'textContent').trim()

		WebUI.verifyEqual(draftTemplateTitle1,draftTemplateTitle2)

		//검색된 기안 진입
		WebElement search = driver.findElement(By.xpath('//*[@id="mainContent"]/div[4]/table/tbody/tr[1]/td[3]/div/a/span'))
		action.click(search)
		action.perform()

		//기안상세에서 타이틀 가져온 뒤에 확인
		def draftDetailTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftDetailTitle'), 'textContent').trim()

		WebUI.verifyEqual(draftTemplateTitle2,draftDetailTitle)

		//검색필터 초기화 용도
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/box/rc')

		//기안자 검색
		def drafterName1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/approvalFoler/completed/drafter'), 'textContent')

		int start = drafterName1.indexOf("(")+1;

		int end = drafterName1.indexOf(")");


		//리스트에서 기안자 첫번째 이름 가져오기
		def drafterName  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/approvalFoler/completed/drafter'), 'textContent').substring(start,end);

		//기안자 셀렉트박스 선택
		WebElement toApprovalSearchSelectBox = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/approvalFoler/toApproval/toApprovalSearchSelectBox'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(toApprovalSearchSelectBox))

		//셀렉트박스에서 기안자 선택 (절대값으로 해둠)
		WebElement drafterNameSearch = driver.findElement(By.xpath('/html/body/div[2]/main/section/div/article/div[2]/ul/li[2]/div[2]/div/div/div[1]/div/div/ul/li[1]/a/span'))
		action.click(drafterNameSearch)
		action.perform()

		//검색창에 기안자 이름 입력
		WebElement drafterNameSearchTxtBox = driver.findElement(By.xpath('//*[@id="keyword_empId"]'))
		action.click(drafterNameSearchTxtBox)
		action.perform()
		action.sendKeys(drafterName)
		action.perform()


		//검색레이어에서 첫번째꺼 선택
		WebElement drafterNameSearchLayer = driver.findElement(By.xpath(' //*[@id="keyword_empId0"]'))
		action.click(drafterNameSearchLayer)
		action.perform()

		//조회버튼 선택
		WebElement searchBtn3 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn3)
		action.perform()

		//검색결과에서 첫번째 리스트에 이름가져오기
		def drafterName_search  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/approvalFoler/completed/drafter'), 'textContent').substring(start,end);//replaceAll("[^a-z]", "")

		//검색결과 비교
		WebUI.verifyEqual(drafterName,drafterName_search)

		//검색필터 초기화 용도
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/box/rc')


		// 문서번호 검색케이스 추가 (내부결재경우 문서번호가 하이픈이라 검색이 안되니까 조건 추가해둠)
		for (int j = 1; j <= 13; j++) {
			WebElement draftComplete_SearchSelectBox = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftComplete_SearchSelectBox'),30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftComplete_SearchSelectBox))

			WebElement draftComplete_SearchSelect = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/draftComplete_SearchSelect'),30)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftComplete_SearchSelect))

			def doctNum  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/doctNum',['no' : j]), 'textContent').trim()

			//내부결재 케이스 확인
			if (doctNum == '-')
			{
				KeywordLogger logdocnum = new KeywordLogger()
				logdocnum.logInfo(j+'번째 기안은 내부결재이거나 문서번호가 없습니다.')
				continue;

			}

			// 하이픈 없을경우 일반검색 케이스 (문서번호 검색)
			else
			{
				WebElement docNumSearch = driver.findElement(By.xpath('//*[@id="keyword_docNo"]'))
				action.click(docNumSearch)
				action.perform()
				action.sendKeys(doctNum)



				WebElement searchBtn4 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
				action.click(searchBtn4)
				action.perform()

				def doctNum1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/completed/doctNum1'), 'textContent').trim()

				if(doctNum == doctNum1)
				{
					break;
				}
				else
				{
					KeywordLogger log = new KeywordLogger()
					log.logInfo('문서번호'+j+'번째 검색조건 안맞음')
				}
			}

			if(j == '13')
			{
				KeywordLogger finish = new KeywordLogger()
				finish.logInfo('셀렉트박스에서 기안양식 맞는조건 없음')
				throw new StepErrorException("검색조건 안맞으므로 케이스 종료함")
			}

		}

	}


}
