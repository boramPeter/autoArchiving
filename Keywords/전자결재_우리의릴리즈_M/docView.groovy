package 전자결재_우리의릴리즈_M
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.awt.image.BufferedImage as BufferedImage
import javax.imageio.ImageIO as ImageIO
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;



public class docView {

	@Keyword
	public void docviewer(){
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/?org_id=57544')

		Mobile.delay(1)

		//테스트 대상 문서로 진입
		WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/draft/complete/74358/341420')

		//첨부파일 문서 선택
		WebElement attachFilesBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/docView/attachFilesBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(attachFilesBtn))

		Mobile.delay(5)

		//로딩 대기
		WebUI.waitForPageLoad(0)

		//문서뷰어 타이틀 확인
		def docViewerTitle  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/docView/docViewerTitle'),'textContent').trim()

		if(docViewerTitle != '문서뷰어')
		{

			throw new StepErrorException("문서뷰어 타이틀이 맞지 않습니다.")

		}

		//문서뷰어 url 확인
		def attachFilesURL = WebUI.getUrl()


		if(attachFilesURL != 'https://m-approval.we.kakaowork.com/doc-viewer')
		{

			throw new StepErrorException("첨부파일 페이지 url이 안맞음 확인필요")

		}


		//뒤로가기 선택
		WebElement backBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/docView/backBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(backBtn))

		//본문보기 선택
		WebElement viewBtn= WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/docView/viewBtn'),30)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(viewBtn))

		Mobile.delay(5)

		//로딩 대기
		WebUI.waitForPageLoad(0)

		//문서뷰어 타이틀 확인
		def docViewerTitle1  = WebUI.getAttribute(findTestObject('Object Repository/전자결재/M_weTest/docView/docViewerTitle'),'textContent').trim()

		if(docViewerTitle1 != '문서뷰어')
		{

			throw new StepErrorException("문서뷰어 타이틀이 맞지 않습니다.")

		}

		//문서뷰어 url 확인
		def viewURL = WebUI.getUrl()


		if(viewURL != 'https://m-approval.we.kakaowork.com/doc-viewer')
		{

			throw new StepErrorException("본문보기 페이지 url이 안맞음 확인필요")

		}
	}
}