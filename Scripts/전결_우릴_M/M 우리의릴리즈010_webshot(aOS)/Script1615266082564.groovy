import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*
import org.openqa.selenium.*
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import java.awt.image.BufferedImage as BufferedImage
import javax.imageio.ImageIO as ImageIO
import java.awt.Color as Color
import java.awt.Graphics as Graphics
import java.awt.image.BufferedImage as BufferedImage
import java.io.File as File
import java.io.FileNotFoundException as FileNotFoundException
import java.io.FileOutputStream as FileOutputStream
import java.io.IOException as IOException
import java.io.ObjectOutput as ObjectOutput
import java.io.ObjectOutputStream as ObjectOutputStream
import java.io.OutputStream as OutputStream
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

Mobile.delay(1)

//테스트 대상 문서로 진입
WebUI.navigateToUrl('https://m-approval.we.kakaowork.com/draft/complete/74358/341420')

//첨부파일 문서 선택
WebElement attachFilesBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/docView/attachFilesBtn'), 
    30)

WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(attachFilesBtn))

Mobile.delay(5)

//로딩 대기
WebUI.waitForPageLoad(0)

Mobile.swipe(100, 500, 100, 0)

//기대결과 이미지 
WebUI.takeScreenshot('/Users/kakao/Downloads/screenshot_aos.png')

BufferedImage expectedImg = ImageIO.read(new File('/Users/kakao/Downloads/screenshot_aos.png'))

//실제결과 이미지 
WebUI.takeScreenshot('/Users/kakao/Downloads/screenshot1.png')

BufferedImage actualImg = ImageIO.read(new File('/Users/kakao/Downloads/screenshot1.png'))

//이미지 비교 boolean
if ((expectedImg.getWidth() == actualImg.getWidth()) && (expectedImg.getHeight() == actualImg.getHeight())) {
    for (int x = 0; x < expectedImg.getWidth(); x += 100) {
        for (int y = 150; y < expectedImg.getHeight(); y += 100) {
            if (expectedImg.getRGB(x, y) != actualImg.getRGB(x, y)) {
                throw new StepErrorException('첨부파일 안맞음 확인필요')
            }
        }
    }
} else {
    throw new StepErrorException('첨부파일 안맞음 확인필요')
}

//뒤로가기 선택
WebElement backBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/docView/backBtn'), 
    30)

WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(backBtn))

//본문보기 선택
WebElement viewBtn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/전자결재/M_weTest/docView/viewBtn'), 
    30)

WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(viewBtn))

Mobile.delay(5)

//로딩 대기
WebUI.waitForPageLoad(0)

Mobile.swipe(100, 500, 100, 0)

//기대결과 이미지
BufferedImage expectedImg_view = ImageIO.read(new File('/Users/kakao/Downloads/screenshot_view_aos.png'))

//실제결과 이미지
WebUI.takeScreenshot('/Users/kakao/Downloads/screenshot2.png')

BufferedImage actualImg_view = ImageIO.read(new File('/Users/kakao/Downloads/screenshot2.png'))

//이미지 비교 boolean
if ((expectedImg_view.getWidth() == actualImg_view.getWidth()) && (expectedImg_view.getHeight() == actualImg_view.getHeight())) {
    for (int x = 0; x < expectedImg_view.getWidth(); x += 100) {
        for (int y = 150; y < expectedImg_view.getHeight(); y += 100) {
            if (expectedImg_view.getRGB(x, y) != actualImg_view.getRGB(x, y)) {
                throw new StepErrorException('본문보기 안맞음 확인필요')
            }
        }
    }
} else {
    throw new StepErrorException('본문보기 안맞음 확인필요')
}



