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

public class documentClassificationList {

	@Keyword
	public void documentClassification(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')
		
		Mobile.delay(1)

		//사이드메뉴 선택
		WebElement sidemenuBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/draftFolder/sidemenuBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(sidemenuBtn))

		//문서대장 선택
		WebElement documentClassificationListBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/documentClassificationList/documentClassificationListBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(documentClassificationListBtn))

		//문서대장 타이틀 확인
		def documentClassificationListTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/documentClassificationList/documentClassificationListTitle'),'textContent').trim()

		if(documentClassificationListTitle != '문서대장')
		{

			throw new StepErrorException("문서대장 타이틀이 맞지 않습니다.")

		}

		//문서대장 url 확인
		def documentClassificationLisURL = WebUI.getUrl()


		if(documentClassificationLisURL != 'https://m-approval.we.kakaowork.com/ledger')
		{

			throw new StepErrorException("문서대장 페이지 url이 안맞음 확인필요")

		}
	}

}