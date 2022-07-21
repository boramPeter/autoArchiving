package 전자결재_우리의릴리즈_PC

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class LNB_draftWrite {

	@Keyword
	public void draftWrite(){

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		//자동화 기안 진입
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/draft/form/1488219')

		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)

		//기안제목 작성
		WebUI.scrollToPosition(0, 500)

		WebUI.delay(2)

		WebElement titleTxtBox = driver.findElement(By.xpath('//*[@id="docSubject"]'))
		action.click(titleTxtBox)
		action.perform()
		action.sendKeys("기안작성 자동화 확인")
		action.perform()

		//상신하기 선택
		WebElement DraftBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/div[7]/button[2]'))
		action.click(DraftBtn)
		action.perform()

		//컨펌에서 확인선택하기
		WebElement deleteCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/approvalLine/deleteCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(deleteCfmBtn))

		WebUI.delay(2)


		//기안제목 입력
		WebElement draftTempTextbox = driver.findElement(By.xpath('//*[@id="keyword_docSubject"]'))
		action.click(draftTempTextbox)
		action.perform()
		action.sendKeys("기안작성 자동화 확인")
		action.perform()

		//검색어 입력 후 조회버튼 선택
		WebElement searchBtn2 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn2)
		action.perform()

		//조회버튼 기능 동작 확인
		def currentURL = WebUI.getUrl()
		def expectedURL = "https://approval.we.kakaowork.com/box/dd"
		if(currentURL == expectedURL)
		{
			KeywordLogger logBtn3 = new KeywordLogger()

			logBtn3.logInfo('url이 그대로임 조회버튼 기능확인 필요')
			throw new StepErrorException("url이 그대로임 조회버튼 기능확인 필요")

		}

		//검색 된거 확인
		def draftTemplateTitle2  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTitle2'), 'textContent').trim()

		WebUI.verifyEqual("기안작성 자동화 확인",draftTemplateTitle2)

		//검색된 기안 진입
		WebElement search = driver.findElement(By.xpath('//*[@id="mainContent"]/div[4]/table/tbody/tr/td[2]/div/a/span'))
		action.click(search)
		action.perform()

		//회수하기 선택
		WebElement rBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/div[7]/button'))
		action.click(rBtn)
		action.perform()

		//컨펌에서 확인 선택하기
		WebElement rCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/approvalLine/deleteCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rCfmBtn))
		WebUI.delay(2)


		//기안제목 입력 후 비교 확인
		WebElement draftTempTextbox1 = driver.findElement(By.xpath('//*[@id="keyword_docSubject"]'))
		action.click(draftTempTextbox1)
		action.perform()
		action.sendKeys("기안작성 자동화 확인")
		action.perform()

		//검색어 입력 후 조회버튼 선택
		WebElement searchBtn3 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn3)
		action.perform()

		def expected1URL = "https://approval.we.kakaowork.com/box/de"
		//조회버튼 기능검증
		if(currentURL == expected1URL)
		{
			KeywordLogger logBtn2 = new KeywordLogger()

			logBtn2.logInfo('url이 그대로임 조회버튼 기능확인 필요')
			throw new StepErrorException("url이 그대로임 조회버튼 기능확인 필요")

		}

		//검색 된거 확인
		def draftTemplateTitle3  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTitle2'), 'textContent').trim()

		WebUI.verifyEqual("기안작성 자동화 확인",draftTemplateTitle3)

		def draftTemplateTitle_fix  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/submmited/draftTemplateTitle'), 'textContent').trim()


		//검색된 기안 진입
		WebElement search1 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[4]/table/tbody/tr/td[2]/div/a/span'))
		action.click(search1)
		action.perform()

		WebUI.switchToWindowIndex(1)

		//기안상세에서 타이틀 가져온 뒤에 확인
		def draftDetailTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/draftTemp/saved/draftDetailTitle'), 'textContent').trim()

		//기안양식명과 진입한 타이틀 매칭 확인 (상신안한 문서의 경우 양식명으로 저장됨)
		WebUI.verifyEqual(draftTemplateTitle_fix,draftDetailTitle)

		//삭제버튼 선택
		WebElement deleteBtn = driver.findElement(By.xpath('//*[@id="mainContent"]/div[7]/button[1]'))
		action.click(deleteBtn)
		action.perform()

		//컨펌에서 확인 선택하기
		WebElement dCfmBtn = WebUiCommonHelper.findWebElement(findTestObject('전자결재/home/config/approvalLine/deleteCfmBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(dCfmBtn))
		WebUI.delay(2)

		//WebUI.closeWindowIndex(1)

		WebUI.switchToWindowIndex(0)

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/box/de')

		//기안제목 입력 후 비교 확인
		WebElement draftTempTextbox2 = driver.findElement(By.xpath('//*[@id="keyword_docSubject"]'))
		action.click(draftTempTextbox2)
		action.perform()
		action.sendKeys("기안작성 자동화 확인")
		action.perform()

		//검색어 입력 후 조회버튼 선택
		WebElement searchBtn4 = driver.findElement(By.xpath('//*[@id="mainContent"]/div[2]/div/button'))
		action.click(searchBtn4)
		action.perform()

		//조회버튼 기능검증
		if(currentURL == expected1URL)
		{
			KeywordLogger logBtn4 = new KeywordLogger()

			logBtn4.logInfo('url이 그대로임 조회버튼 기능확인 필요')
			throw new StepErrorException("url이 그대로임 조회버튼 기능확인 필요")

		}

		def noSearchTxt  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/LNB/writeDraft/noSearch'), 'textContent').trim()

		//정상삭제 확인
		if("조회 결과가 존재하지 않습니다." != noSearchTxt)
		{
			KeywordLogger finish = new KeywordLogger()
			finish.logInfo("문서 삭제가 안된거같음 확인필요")
			throw new StepErrorException("문서 삭제가 안된거같음 확인필요")
		}
	}
}
