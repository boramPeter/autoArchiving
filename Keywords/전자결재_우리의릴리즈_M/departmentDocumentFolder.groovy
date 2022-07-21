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

public class departmentDocumentFolder {

	@Keyword
	public void departmentCollaboration(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//부서문서함 선택
		WebElement departmentDocumentFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/departmentDocumentFolder/departmentDocumentFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(departmentDocumentFolderBtn))

		//부서협조함 선택
		WebElement departmentCollaborationBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/departmentDocumentFolder/departmentCollaboration/departmentCollaborationBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(departmentCollaborationBtn))

		//부서문서함 타이틀 확인
		def departmentDocumentFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/departmentDocumentFolder/departmentDocumentFolderTitle'),'textContent').trim()

		if(departmentDocumentFolderTitle != '부서문서함')
		{

			throw new StepErrorException("부서문서함 타이틀이 맞지 않습니다.")

		}

		//부서협조함 url 확인
		def departmentCollaborationURL = WebUI.getUrl()


		if(departmentCollaborationURL != 'https://m-approval.we.kakaowork.com/dept-receive/cooperate')
		{

			throw new StepErrorException("부서협조함 페이지 url이 안맞음 확인필요")

		}

	}

	@Keyword
	public void departmentInbox(){

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//부서문서함 선택
		WebElement departmentDocumentFolderBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/departmentDocumentFolder/departmentDocumentFolderBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(departmentDocumentFolderBtn))

		//부서수신함 선택
		WebElement departmentInboxBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/departmentDocumentFolder/departmentInbox/departmentInboxBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(departmentInboxBtn))

		//부서문서함 타이틀 확인
		def departmentDocumentFolderTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/departmentDocumentFolder/departmentDocumentFolderTitle'),'textContent').trim()

		if(departmentDocumentFolderTitle != '부서문서함')
		{

			throw new StepErrorException("부서문서함 타이틀이 맞지 않습니다.")

		}

		//부서수신함 url 확인
		def departmentInboxURL = WebUI.getUrl()


		if(departmentInboxURL != 'https://m-approval.we.kakaowork.com/dept-receive/receive')
		{

			throw new StepErrorException("부서수신함 페이지 url이 안맞음 확인필요")

		}

	}



}