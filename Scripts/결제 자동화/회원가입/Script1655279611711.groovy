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
import java.util.Calendar as Calendar
import java.text.SimpleDateFormat as SimpleDateFormat

//테스트계정 변수 선언 
String now = new Date().format('ddHHmmS')
String IDnow = new Date().format('mmS')
String snsKeyValue = new Date().format('yyyyMMddHHmmS')

//유저아이디 입력
String ID = 'test' + IDnow

KeywordLogger ID1 = new KeywordLogger()

ID1.logInfo(ID)

/////////필수 입력영역 /////////////////////////////////////////////////////////////////////////////////////////////////////////////

//joinpath 입력,없으면 공란으로 넣어야함 
String joinpath = ""

//이름 입력
String name = '김보람'

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//snskey 추가  
String snsKey = snsKeyValue

KeywordLogger snsKey1 = new KeywordLogger()

snsKey1.logInfo(snsKey)


//닉네임 입력
String nickname = '닉네임' + now

KeywordLogger nickname1 = new KeywordLogger()

nickname1.logInfo(nickname)

//생일 입력
String birthday = null;

//성별 입력
String sex = null;

//연락처 입력
String mobile = null;

//CI 추가 
String CI = null;

//email 추가
String email = null;

switch (name) {
    case '김보람':
        birthday = '1987-09-10'
		sex = 'M'
		mobile = '010-8874-9244'
		CI = "870910"
        break;
		
    case '박소현':
        birthday = '1991-12-03'
		sex = 'F'
		mobile = '010-6682-5317'
		CI = "911203"
        break;
		
    case '정윤성':
        birthday = '1993-01-01'
		sex = 'M'
		mobile = '010-2979-4402'
		CI = "930101"
        break;
		
    case '이민우':
        birthday = '1994-11-17'
		sex = 'M'
		mobile = '010-2225-3716'
		CI = "941117"
        break;
		
    default:
        birthday = '2000-01-01'
		sex = 'M'
		mobile = '010-1111-1111'
		CI = "1234567"
        break;
}

KeywordLogger finish = new KeywordLogger()

finish.logInfo(birthday)

KeywordLogger sex1 = new KeywordLogger()

sex1.logInfo(sex)

KeywordLogger mobile1 = new KeywordLogger()

mobile1.logInfo(mobile)

KeywordLogger CI1 = new KeywordLogger()

CI1.logInfo(CI)


//이메일 체크 

switch (name) {
    case '김보람':
		switch (joinpath) {
			case 'A' :
			email = 'peter@bithumblive.com'
			break;
			
			case 'K':
			email = 'peter@bithumblive.com'
			break;
			
			case 'N':
			email = 'dfaafd@naver.com'
			break;
			
			default:
			email = 'bithumbliveqa@gmail.com'
			break;
		}

        break;
		
    case '박소현':
		switch (joinpath) {
			case 'A' :
			email = 'shoney@bithumblive.com'
			break;
		
			case 'K':
			email = 'shoneykao1@kakao.com'
			break;
		
			case 'N':
			email = 'shoneynav1@naver.com'
			break;
			
			default:
			email = 'bithumbliveqa@gmail.com'
			break;
		}

        break;
		
    case '정윤성':
        switch (joinpath) {
			case 'A' :
			email = 'jeffery@bithumblive.com'
			break;
		
			case 'K':
			email = 'jefferykao1@kakao.com'
			break;
		
			case 'N':
			email = 'jefferynav1@naver.com'
			break;
			
			default:
			email = 'bithumbliveqa@gmail.com'
			break;
		}

        break;
		
    case '이민우':
		switch (joinpath) {
			case 'A' :
			email = 'bread@bithumblive.com'
			break;
	
			case 'K':
			email = 'music1stt@naver.com'
			break;
	
			case 'N':
			email = 'music1stt@naver.com'
			break;
		
			default:
			email = 'bithumbliveqa@gmail.com'
			break;
		}

		break;
	
	default:
		email = 'bithumbliveqa@gmail.com'
		break;
}


KeywordLogger email1 = new KeywordLogger()

email1.logInfo(email)


if (joinpath == "") {
	//일반 회원가입 
	RequestObject join = findTestObject('Object Repository/z_archive/회원가입/일반회원가입', [('유저아이디') : ID, ('이메일') : email, ('이름') : name, ('닉네임') : nickname, ('생년월일') : birthday, ('성별') : sex, ('연락처') : mobile, ('CI') : CI ])

	String userPidValue = 'resultData.userPid'

	join_Response = WS.sendRequestAndVerify(join)

	String userPid = WS.getElementPropertyValue(join_Response, userPidValue)

	//슬랙 발송 
	String descText = "\n*계정생성 완료 되었습니다* \n\n" + "email : " + email +"\n\n ID : "+ ID + "\n\n 이름 : " + name + "\n\n PW : cip1977" + "\n\n 닉네임 : "+ nickname + "\n\n userPid : " + userPid + "\n\n CI : " + CI

	RequestObject slackWebhook = findTestObject('z_archive/결제 자동화/결제 API/webhook', [('Desc') : descText])

	WS.sendRequest(slackWebhook)
}

if (joinpath != "") {
	//소셜 회원가입
	RequestObject join = findTestObject('Object Repository/z_archive/회원가입/SNS회원가입', [('유저아이디') : ID,('이메일') : email, ('이름') : name, ('닉네임') : nickname, ('생년월일') : birthday, ('성별') : sex, ('연락처') : mobile, ('snsKey') : snsKey, ('joinPath') : joinpath, ('CI') : CI ])
	
	String userPidValue = 'resultData.userPid'
	
	join_Response = WS.sendRequestAndVerify(join)
	
	String userPid = WS.getElementPropertyValue(join_Response, userPidValue)
	
	//슬랙 발송
	String descText = "\n*계정생성 완료 되었습니다* \n\n" + "email : " + email + "\n\n ID : "+ ID + "\n\n 이름 : " + name + "\n\n 닉네임 : "+ nickname + "\n\n userPid : " + userPid + "\n\n snsKey : " + snsKey + "\n\n joinPath : " + joinpath + " (K : 카카오, N: 네이버, A: 애플)" + "\n\n CI : " + CI
	
	RequestObject slackWebhook = findTestObject('z_archive/결제 자동화/결제 API/webhook', [('Desc') : descText])
	
	WS.sendRequest(slackWebhook)
	
}


