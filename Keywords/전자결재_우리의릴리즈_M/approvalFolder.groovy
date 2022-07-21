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

public class approvalFolder {

	@Keyword
	public void toApproval(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')


		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//결재함 선택
		WebElement approvalFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/approvalFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(approvalFolderBtn))

		//결재전 선택
		WebElement toApprovalBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/toApproval/toApprovalBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(toApprovalBtn))

		//결재함 타이틀 확인
		def toApprovalTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/toApprovalTitle'),'textContent').trim()

		if(toApprovalTitle != '결재함')
		{

			throw new StepErrorException("결재함 타이틀이 맞지 않습니다.")

		}

		//결재전 url 확인
		def toApprovalURL = WebUI.getUrl()


		if(toApprovalURL != 'https://m-approval.we.kakaowork.com/approval/before')
		{

			throw new StepErrorException("결재전 페이지 url이 안맞음 확인필요")

		}
	}

	@Keyword
	public void completed(){

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//결재함 선택
		WebElement approvalFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/approvalFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(approvalFolderBtn))

		//결재전 선택
		WebElement completedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/completed/completedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(completedBtn))

		//결재함 타이틀 확인
		def toApprovalTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/toApprovalTitle'),'textContent').trim()

		if(toApprovalTitle != '결재함')
		{

			throw new StepErrorException("결재함 타이틀이 맞지 않습니다.")

		}

		//완료된 url 확인
		def completedURL = WebUI.getUrl()


		if(completedURL != 'https://m-approval.we.kakaowork.com/approval/complete')
		{

			throw new StepErrorException("완료된 페이지 url이 안맞음 확인필요")

		}

	}


	@Keyword
	public void inProgress(){

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//결재함 선택
		WebElement approvalFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/approvalFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(approvalFolderBtn))

		//결재전 선택
		WebElement inProgressBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/inProgress/inProgressBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(inProgressBtn))

		//결재함 타이틀 확인
		def toApprovalTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/toApprovalTitle'),'textContent').trim()

		if(toApprovalTitle != '결재함')
		{

			throw new StepErrorException("결재함 타이틀이 맞지 않습니다.")

		}

		//진행중 url 확인
		def inProgressURL = WebUI.getUrl()


		if(inProgressURL != 'https://m-approval.we.kakaowork.com/approval/ing')
		{

			throw new StepErrorException("진행중 페이지 url이 안맞음 확인필요")

		}

	}

	@Keyword
	public void rejected(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//결재함 선택
		WebElement approvalFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/approvalFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(approvalFolderBtn))

		//반려된 선택
		WebElement rejectedBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/rejected/rejectedBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(rejectedBtn))

		//결재함 타이틀 확인
		def toApprovalTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/approvalFolder/toApprovalTitle'),'textContent').trim()

		if(toApprovalTitle != '결재함')
		{

			throw new StepErrorException("결재함 타이틀이 맞지 않습니다.")

		}

		//반려된 url 확인
		def rejectedURL = WebUI.getUrl()


		if(rejectedURL != 'https://m-approval.we.kakaowork.com/approval/reject')
		{

			throw new StepErrorException("반려된 페이지 url이 안맞음 확인필요")

		}


		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')
	}




}