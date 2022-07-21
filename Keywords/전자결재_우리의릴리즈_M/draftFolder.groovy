package 전자결재_우리의릴리즈_M
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class draftFolder {

	@Keyword
	public void submitted(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//기안함 선택
		WebElement draftFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/draftFolder/draftFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftFolderBtn))

		//상신한 선택
		WebElement submittedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/submittedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(submittedBtn))

		//기안함 타이틀 확인
		def draftFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/draftFolderTitle'),'textContent').trim()

		if(draftFolderTitle != '기안함')
		{

			throw new StepErrorException("기안함 타이틀이 맞지 않습니다.")

		}

		//상신한 url 확인
		def submittedURL = WebUI.getUrl()


		if(submittedURL != 'https://m-approval.we.kakaowork.com/draft/draft')
		{

			throw new StepErrorException("상신한 페이지 url이 안맞음 확인필요")

		}
	}

	@Keyword
	public void completed(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//기안함 선택
		WebElement draftFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/draftFolder/draftFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftFolderBtn))

		//완료된 선택
		WebElement completedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/completed/completedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(completedBtn))

		//기안함 타이틀 확인
		def draftFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/draftFolderTitle'),'textContent').trim()

		if(draftFolderTitle != '기안함')
		{

			throw new StepErrorException("기안함 타이틀이 맞지 않습니다.")

		}

		//완료된 url 확인
		def completedURL = WebUI.getUrl()


		if(completedURL != 'https://m-approval.we.kakaowork.com/draft/complete')
		{

			throw new StepErrorException("완료된 페이지 url이 안맞음 확인필요")

		}

	}

	@Keyword
	public void saved(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//기안함 선택
		WebElement draftFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/draftFolder/draftFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftFolderBtn))

		//저장된 선택
		WebElement savedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/saved/savedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(savedBtn))

		//기안함 타이틀 확인
		def draftFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/draftFolderTitle'),'textContent').trim()

		if(draftFolderTitle != '기안함')
		{

			throw new StepErrorException("기안함 타이틀이 맞지 않습니다.")

		}

		//저장된 url 확인
		def savedURL = WebUI.getUrl()


		if(savedURL != 'https://m-approval.we.kakaowork.com/draft/temp')
		{

			throw new StepErrorException("저장된 페이지 url이 안맞음 확인필요")

		}
	}

	@Keyword
	public void rejected(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//기안함 선택
		WebElement draftFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/draftFolder/draftFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftFolderBtn))

		//반려된 선택
		WebElement rejectedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/rejected/rejectedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rejectedBtn))

		//기안함 타이틀 확인
		def draftFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/draftFolderTitle'),'textContent').trim()

		if(draftFolderTitle != '기안함')
		{

			throw new StepErrorException("기안함 타이틀이 맞지 않습니다.")

		}

		//반려된 url 확인
		def rejectedURL = WebUI.getUrl()


		if(rejectedURL != 'https://m-approval.we.kakaowork.com/draft/reject')
		{

			throw new StepErrorException("반려된 페이지 url이 안맞음 확인필요")

		}
	}

	@Keyword
	public void returned(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//기안함 선택
		WebElement draftFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/draftFolder/draftFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftFolderBtn))

		//반송된 선택
		WebElement returnedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/returned/returnedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(returnedBtn))

		//기안함 타이틀 확인
		def draftFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/draftFolderTitle'),'textContent').trim()

		if(draftFolderTitle != '기안함')
		{

			throw new StepErrorException("기안함 타이틀이 맞지 않습니다.")

		}

		//반송된 url 확인
		def returnedURL = WebUI.getUrl()


		if(returnedURL != 'https://m-approval.we.kakaowork.com/draft/return')
		{

			throw new StepErrorException("반송된 페이지 url이 안맞음 확인필요")

		}
	}

	@Keyword
	public void collected(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//기안함 선택
		WebElement draftFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/draftFolder/draftFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(draftFolderBtn))

		//회수된 선택
		WebElement collectedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/collected/collectedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(collectedBtn))

		//기안함 타이틀 확인
		def draftFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/submitted/draftFolderTitle'),'textContent').trim()

		if(draftFolderTitle != '기안함')
		{

			throw new StepErrorException("기안함 타이틀이 맞지 않습니다.")

		}

		//회수된 url 확인
		def collectedURL = WebUI.getUrl()


		if(collectedURL != 'https://m-approval.we.kakaowork.com/draft/retrieve')
		{

			throw new StepErrorException("회수된 페이지 url이 안맞음 확인필요")

		}
	}

}