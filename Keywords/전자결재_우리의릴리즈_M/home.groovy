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

public class home {

	@Keyword
	public void login(){
		//오픈 브라우저

		WebUI.openBrowser('https://m-approval.we.kakaowork.com/?org_id=57544')

		WebUI.setText(findTestObject('전자결재/home/login/login_id'), 'work.engine.tester@gmail.com')

		WebUI.setEncryptedText(findTestObject('전자결재/home/login/login_PW'), 'AQCMEzCCGByQ8DLodILUgg==')

		WebElement loginBtn= WebUiCommonHelper.findWebElement(findTestObject('전자결재/M_weTest/home/loginBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(loginBtn))
	}

	@Keyword
	public void homeUi(){
		Mobile.delay(1)

		//프로필 영역에서 이름 확인
		def homeName  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/homeName'),'textContent')

		if(homeName != '0호사원 (닉네임은별명)')
		{
			KeywordLogger logname = new KeywordLogger()

			logname.logInfo('이름이 맞지 않습니다')
			throw new StepErrorException("이름이 맞지 않습니다")

		}

		Mobile.delay(1)

		//상신한 문서 클릭
		WebElement Submitted= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/myDraft/Submitted'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(Submitted))

		def submitURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(submitURL, 'https://m-approval.we.kakaowork.com/draft/draft')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		//결재전 문서 확인
		WebElement ToApprove= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/myDraft/ToApprove'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(ToApprove))

		def toApproveURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(toApproveURL, 'https://m-approval.we.kakaowork.com/approval/before')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		//반려된 문서 확인
		WebElement Rejected= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/myDraft/Rejected'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(Rejected))

		def rejectedURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(rejectedURL, 'https://m-approval.we.kakaowork.com/draft/reject')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		//수신 문서 확인
		WebElement Recipient= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/myDraft/Recipient'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(Recipient))

		def recipientURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(recipientURL, 'https://m-approval.we.kakaowork.com/receive/before')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')


		//공람 문서 확인
		WebElement Circulation= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/myDraft/Circulation'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(Circulation))

		def circulationURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(circulationURL, 'https://m-approval.we.kakaowork.com/exhibit')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')


		//결재할문서 텍스트 확인
		def pendingDocument  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/pendingDocument'),'textContent')

		if(pendingDocument != '결재할 문서')
		{
			KeywordLogger logmust = new KeywordLogger()

			logmust.logInfo("결재할 문서 텍스트가 맞지않아요")
			throw new StepErrorException("결재할 문서 텍스트가 맞지않아요")
		}

		//스크롤 밑으로 내림
		int startX = (int)(Mobile.getDeviceWidth()/2)
		int endX = (int)(Mobile.getDeviceWidth()/2)
		int startY1 = (int)(Mobile.getDeviceHeight()/2)
		int endY1 = (int)(Mobile.getDeviceHeight()/4)
		Mobile.swipe(0, startY1, 0, endY1)

		Mobile.swipe(0, startY1, 0, endY1)

		Mobile.delay(1)

		//상신한 문서 현황에서 타이틀 확인
		def mySubmissions  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/mySubmissions'),'textContent')

		if(mySubmissions != '상신한 문서')
		{
			KeywordLogger logworktxt = new KeywordLogger()

			logworktxt.logInfo("상신한 문서. 텍스트가 맞지않아요")
			throw new StepErrorException("상신한 문서. 텍스트가 맞지않아요")
		}


		//스크롤 밑으로 내림
		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)

		Mobile.delay(1)

		//최근 결재의견 텍스트 확인
		def recent  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/recent'),'textContent')

		if(recent != '최근 결재의견')
		{
			KeywordLogger logcvoctxt = new KeywordLogger()

			logcvoctxt.logInfo("최근 결재의견 텍스트가 맞지않아요")
			throw new StepErrorException("최근 결재의견 텍스트가 맞지않아요")


		}

		//이용약관 확인
		WebElement termsOfUseLink= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/termsOfUseLink'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(termsOfUseLink))

		Mobile.delay(1)

		def termsOfUseLinkURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(termsOfUseLinkURL, 'https://policy.kakaoi.ai/terms')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)


		//개인정보 처리방침 확인
		WebElement privacyPolicyLink= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/privacyPolicyLink'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(privacyPolicyLink))

		Mobile.delay(1)

		def privacyPolicyLinkURL = WebUI.getUrl()

		//페이지 이동 확인
		WebUI.verifyEqual(privacyPolicyLinkURL, 'https://policy.kakaoi.ai/privacy')

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')


		//결재할 문서 첫번째 문서 진입확인 시나리오
		def pendingDocumentName  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/pendingDocument_approval'),'textContent').trim()

		WebElement pendingDocument_approval= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/pendingDocument_approval'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(pendingDocument_approval))

		Mobile.delay(1)

		def pendingDocumentName_detail  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/approvalTitle_detail'),'textContent').trim()

		if(pendingDocumentName != pendingDocumentName_detail)
		{
			throw new StepErrorException("결재할 문서 이름이 맞지 않습니다")

		}

		/*def pendingDocumentDocName_detail  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/draftTitle_detail'),'textContent').trim()
		 if(pendingDocumentDocName_detail != "결재전")
		 {
		 throw new StepErrorException("결재전 타이틀 이름이 맞지 않습니다")
		 }*/

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)

		//상신한 문서 첫번째 문서 진입확인 시나리오
		def mySubmissionsName  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/mySubmissions_approval'),'textContent').trim()

		WebElement mySubmissions_approval= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/mySubmissions_approval'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(mySubmissions_approval))

		Mobile.delay(1)

		def mySubmissionsName_detail  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/approvalTitle_detail'),'textContent').trim()

		if(mySubmissionsName != mySubmissionsName_detail)
		{
			throw new StepErrorException("상신한 문서 이름이 맞지 않습니다")

		}

		def mySubmissionsDocName_detail  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/draftTitle_detail'),'textContent').trim()

		if(mySubmissionsDocName_detail != "상신한")
		{
			throw new StepErrorException("상신한 타이틀 이름이 맞지 않습니다")

		}

		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)
		Mobile.swipe(0, startY1, 0, endY1)

		//최근결재의견  첫번째 문서 진입확인 시나리오
		def recentName  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/recent_approval'),'textContent').trim()

		WebElement recent_approval= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/recent_approval'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(recent_approval))

		Mobile.delay(1)

		def recentName_detail  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/home/approval_detail/approvalTitle_detail'),'textContent').trim()

		if(recentName != recentName_detail)
		{
			throw new StepErrorException("최근문서 이름이 맞지 않습니다")

		}


		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')
	}


}