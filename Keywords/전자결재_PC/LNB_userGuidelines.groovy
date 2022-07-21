package 전자결재_PC
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class LNB_userGuidelines {

	@Keyword
	public void userManual(){

		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)


		WebElement userManual = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/div/div/ul/li[1]/a'))
		action.click(userManual)
		action.perform()

		WebUI.switchToWindowIndex(1)

		WebUI.delay(3)

		def title = WebUI.getWindowTitle()

		WebUI.verifyEqual(title, '[Work Engine] 전자결재_사용자')

		WebUI.closeWindowIndex(1)

		WebUI.switchToWindowIndex(0)
	}

	@Keyword
	public void customerService(){
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)


		WebElement customerService = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/div/div/ul/li[2]/a'))
		action.click(customerService)
		action.perform()

		WebUI.switchToWindowIndex(1)

		WebUI.delay(3)

		def title1 = WebUI.getWindowTitle()

		WebUI.verifyEqual(title1, '카카오워크 - 전자결재 > 사용자 | kakao 고객센터')

		WebUI.closeWindowIndex(1)

		WebUI.switchToWindowIndex(0)
	}

	@Keyword
	public void contact(){
		WebUI.navigateToUrl('https://approval.we.kakaowork.com/')

		WebDriver driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)


		WebElement contact = driver.findElement(By.xpath('//*[@id="gnbContent"]/div/div/div/ul/li[3]/a'))
		action.click(contact)
		action.perform()

		WebUI.switchToWindowIndex(1)

		WebUI.delay(3)

		def title2 = WebUI.getWindowTitle()

		WebUI.verifyEqual(title2, '카카오워크 문의하기 | kakao 고객센터')

		WebUI.closeWindowIndex(1)

		WebUI.switchToWindowIndex(0)
	}
}