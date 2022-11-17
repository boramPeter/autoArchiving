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

/////////////////////////필수입력//////////////////////////////////

//결제 건수 정의 
int finish = 2

//다중구매 여부
def multiOrder = "y"  //y,n으로 처리 y:3건짜리로 생성, n: 1건짜리 생성 

//아이디 비번 입력
def ID = "qatester3"
def PW = "cip1977"

//////////////////////////////////////////////////////////

//결제 건수 반복실행 
for (int i = 1; i <= finish; i++) {

//엑세스토큰 발급 
RequestObject accessToken_Login = findTestObject('z_archive/결제 자동화/결제 API/access token', [('아이디') : ID, ('비밀번호') : PW])

def getToken = 'resultData.accessToken'

token_Response = WS.sendRequestAndVerify(accessToken_Login)

String accessToken = WS.getElementPropertyValue(token_Response, getToken)

KeywordLogger accessTokenText = new KeywordLogger()

accessTokenText.logInfo(accessToken)

RequestObject orderSheetCreative = null;

//다중 주문서 생성 체크 
if (multiOrder == 'n') {
	 orderSheetCreative = findTestObject('z_archive/결제 자동화/결제 API/주문서생성')	
} else {
	 orderSheetCreative = findTestObject('z_archive/결제 자동화/결제 API/주문서생성_멀티')	
}
	ArrayList HTTPHeader_orderSheetCreative = new ArrayList()
	
	HTTPHeader_orderSheetCreative.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer ' + accessToken))
	
	HTTPHeader_orderSheetCreative.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
	
	HTTPHeader_orderSheetCreative.add(new TestObjectProperty('apiKey', ConditionType.EQUALS, 'live123'))
	
	orderSheetCreative.setHttpHeaderProperties(HTTPHeader_orderSheetCreative)
	
	def orderSheetNoValue = 'resultData.orderSheetNo'
	
	orderSheetCreative_Response = WS.sendRequestAndVerify(orderSheetCreative)
	
	WS.verifyResponseStatusCode(orderSheetCreative_Response, 200)
	
	String orderSheetNo = WS.getElementPropertyValue(orderSheetCreative_Response, orderSheetNoValue)
	
	KeywordLogger orderSheetNo1 = new KeywordLogger()
	
	orderSheetNo1.logInfo(orderSheetNo)


def URL = 'https://dev-app.bithumblive.com/orderNew/' + orderSheetNo + '?token=' + accessToken

//UI자동화 시작
WebUI.openBrowser(URL)

//주문페이지 진입 
WebDriver driver = DriverFactory.getWebDriver()

Actions action = new Actions(driver)

WebUI.delay(2)

//결제수단 가상계좌 확인 
def payment_method = WebUI.getAttribute(findTestObject('z_archive/결제 자동화/결제 API/결제수단'), 'textContent').trim()

KeywordLogger payment_methodText = new KeywordLogger()

payment_methodText.logInfo(payment_method)

if (payment_method != '가상계좌') {
    WebElement payment_methodBtn = driver.findElement(By.xpath('//*[@id="root"]/div/section[2]/div[4]/p'))

    action.click(payment_methodBtn)

    action.perform()

    WebUI.delay(1)

    //가상계좌 거래수단 클릭 
    WebElement payment_methodVcnt = driver.findElement(By.xpath('//*[@id="root"]/div/div/main/ul/li[4]'))

    action.click(payment_methodVcnt)

    action.perform()

    //저장 선택 
    WebElement payment_methodSaveBtn = driver.findElement(By.xpath('//*[@id="root"]/div/div/main/div[2]/button'))

    action.click(payment_methodSaveBtn)

    action.perform()

    WebUI.delay(1)
}

//결제하기 선택 
WebElement buyBtn = driver.findElement(By.xpath('//*[@id="root"]/div/section[3]/button'))

action.click(buyBtn)

action.perform()

WebUI.delay(2)

//PG 에서 전체동의 선택 
WebElement allAgreeBtn = driver.findElement(By.xpath('//*[@id="content"]/div[2]/ul[1]/li/div/label'))

action.click(allAgreeBtn)

action.perform()

WebUI.delay(2)

//입금은행 선택 
WebElement bankSelect = driver.findElement(By.xpath('//*[@id="select_bank"]'))

action.click(bankSelect)

action.perform()

WebUI.delay(1)

WebUI.selectOptionByValue(findTestObject('z_archive/결제 자동화/Page_NHN/select_Bank'), 'BK04', true)

WebUI.delay(1)

//현금영수증 미선택 
WebElement tr_code_option = driver.findElement(By.xpath('//*[@id="tr_code_option"]'))

action.click(tr_code_option)

action.perform()

WebUI.delay(2)

WebUI.selectOptionByValue(findTestObject('z_archive/결제 자동화/Page_NHN/select_Receipt'), '', false)

//다음버튼 선택 
WebElement nextBtn = driver.findElement(By.xpath('//*[@id="wrap"]/div[3]/a'))

action.click(nextBtn)

action.perform()

WebUI.delay(3)

//주문번호 가져오기 
def orderNoText = WebUI.getAttribute(findTestObject('z_archive/결제 자동화/결제 API/주문번호'), 'textContent').trim()

//주문정보에서 거래번호, 계좌번호, 이름 가져오기 
RequestObject orderSheet = findTestObject('z_archive/결제 자동화/결제 API/주문정보 가져오기', [('주문번호') : orderNoText])

ArrayList HTTPHeader_orderSheet = new ArrayList()

HTTPHeader_orderSheet.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer ' + accessToken))

HTTPHeader_orderSheet.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))

HTTPHeader_orderSheet.add(new TestObjectProperty('apiKey', ConditionType.EQUALS, 'live123'))

orderSheet.setHttpHeaderProperties(HTTPHeader_orderSheet)

//이름 저장 
def receiverNameValue = 'resultData.shippingAddress.receiverName'

//계좌 번호 
def accountValue = 'resultData.payInfo.bankInfo.account'

//거래번호 
def tradeNoValue = 'resultData.payInfo.tradeNo'

//거래상태
def orderStatusTypeLabelValue = 'resultData.orderOptionsGroupByPartner[0].orderOptionsGroupByDelivery[0].orderOptions[0].orderStatusTypeLabel'

orderSheet_Response = WS.sendRequestAndVerify(orderSheet)

def receiverName = WS.getElementPropertyValue(orderSheet_Response, receiverNameValue)

def account = WS.getElementPropertyValue(orderSheet_Response, accountValue)

def tradeNo = WS.getElementPropertyValue(orderSheet_Response, tradeNoValue)

//가상계좌 입금
RequestObject TEST_Vcnt = findTestObject('z_archive/결제 자동화/결제 API/가상계좌 입금', [('거래번호') : tradeNo, ('계좌번호') : account, ('입금자명') : receiverName])

WS.sendRequest(TEST_Vcnt)

WebUI.delay(1)

WebUI.closeBrowser()

//거래정보 확인 
orderSheet_Response2 = WS.sendRequestAndVerify(orderSheet)

//주문상태 조회 
def orderStatusTypeLabel = WS.getElementPropertyValue(orderSheet_Response2, orderStatusTypeLabelValue)

KeywordLogger orderStatusTypeText = new KeywordLogger()

orderStatusTypeText.logInfo(orderStatusTypeLabel)

//몇건인지 확인 
KeywordLogger orderCount = new KeywordLogger()

orderCount.logInfo(i +"건 완료")


//슬랙으로 전송 
if (i == finish) {
	
	String descText = "*결제 완료 되었습니다* \n\n"+"ID : "+ ID + "\n\n PW : " + PW + "\n\n 결제상태 : "+ orderStatusTypeLabel + "\n\n 주문번호 : " + orderNoText + "\n\n 결제 완료 건수 : " + i + "건 생성완료"
	
	RequestObject slackWebhook = findTestObject('z_archive/결제 자동화/결제 API/webhook', [('Desc') : descText])
	
	WS.sendRequest(slackWebhook)
	}

}






