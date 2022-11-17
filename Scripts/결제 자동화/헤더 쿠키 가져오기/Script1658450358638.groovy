import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

RequestObject login = findTestObject('z_archive/회원가입/CMS_login/cms_login')

login2 = WS.sendRequest(login)

String token = login2.getHeaderFields()['Set-Cookie']

String token2 = token.replace("[","")

String token3 = token2.replace("]","")


println token3

//myCookie = new Cookie("JSESSIONID", token)
//def headerFields = login.getHeaders()//.get("Set-Cookie");
//def headerFields1 = login.headerFields.get()
//def headerFields = login.header('Content-Type')
//String test = WS.containsString(login, 'Set-Cookie:', false)
//String test = WS.getElementPropertyValue(login, 'Set-Cookie:')
//String test = login.responseHeaders()
//println(headerFields['Set-Cookie'])
//RequestObject product = findTestObject('z_archive/회원가입/CMS_login/cms_product', [('COOKIE') : token])
RequestObject product = findTestObject('z_archive/회원가입/CMS_login/cms_product')

ArrayList HTTPHeader_orderSheet = new ArrayList()

HTTPHeader_orderSheet.add(new TestObjectProperty('Cookie', ConditionType.EQUALS, token3))

HTTPHeader_orderSheet.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/x-www-form-urlencoded'))

product.setHttpHeaderProperties(HTTPHeader_orderSheet)


//KeywordLogger accessTokenText = new KeywordLogger()
//accessTokenText.logInfo()
//def test = 'productNo'
text123 = WS.sendRequestAndVerify(product)

WS.verifyResponseStatusCode(text123, 200)

