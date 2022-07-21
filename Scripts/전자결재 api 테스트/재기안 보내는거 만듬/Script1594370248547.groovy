import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.junit.After

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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.junit.After

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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

//우회로그인 
def LoginA = findTestObject('전자결재 api/우회로그인', [('아이디') : 'peter.bo']) //기안
def LoginB = findTestObject('전자결재 api/우회로그인', [('아이디') : 'ellie.hwang']) //B결재자
def LoginC = findTestObject('전자결재 api/우회로그인', [('아이디') : 'jenny.park']) //C결재자
def LoginD = findTestObject('전자결재 api/우회로그인', [('아이디') : 'casey.han']) // 참조자
def LoginE = findTestObject('전자결재 api/우회로그인', [('아이디') : 'sio.oh']) //공람자

def getToken = 'result.accessToken'

WS.sendRequest(LoginA)

response = WS.sendRequestAndVerify(LoginA)

'인증토큰 발급'
def Token = WS.getElementPropertyValue(response, getToken)

//기안작성		(기안자 : peter.bo, 결재자 B : lia.jung, 결재자 C : jenny.park, 참조자D : pepper.hoon, 공람E : sio.oh)
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1결재2참조1공람1)', [('결재선유형') : 'DD', ('결재타입1') : 'AP', ('결재상태1') : 'AW'
        , ('결재타입2') : 'AP', ('결재상태2') : 'AW', ('결재타입3') : 'CC', ('결재상태3') : 'AC'])

ArrayList HTTPHeader = new ArrayList()

HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token))

HTTPHeader.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

draft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(draft)


//내가상신한목록 조회
def getDocid = 'result [0] .docId'

RequestObject mydraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서상신목록 조회')

mydraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(mydraft)

response1 = WS.sendRequestAndVerify(mydraft)

def docid = WS.getElementPropertyValue(response1, getDocid)


//상신한에 문서 확인
'상신한문서의 문서번호 가져오기'
def ssgetdocid = 'result.contents[0].docId'

'상신한에 기안 확인'
RequestObject ssdraft = findTestObject('전자결재 api/결재프로세스/기안함/상신한 문서 조회')

ssdraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(ssdraft)

response0 = WS.sendRequestAndVerify(ssdraft)

'상신한에 있는 기안 문서번호 변수지정'
def ssdocid = WS.getElementPropertyValue(response0, ssgetdocid)

WS.verifyResponseStatusCode(response0, 200)

WS.verifyEqual(ssdocid, docid, FailureHandling.CONTINUE_ON_FAILURE)


//회수버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'	
def getBtn3 = 'result.functions[2]'
def getBtn4 = 'result.functions[3]'
def getBtn5 = 'result.functions[4]'
def getBtn6 = 'result.functions[5]'

RequestObject btndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DD'])

btndraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(btndraft)

response2 = WS.sendRequestAndVerify(btndraft)

'회수버튼 변수지정'
def retrieve1 = WS.getElementPropertyValue(response2, getBtn1)

'인쇄버튼 변수지정'
def print1 = WS.getElementPropertyValue(response2, getBtn2)

WS.verifyResponseStatusCode(response2, 200)

WS.verifyMatch(retrieve1, "retrieve", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print1, "print", true, FailureHandling.CONTINUE_ON_FAILURE)

//참조자 문서함 확인

'참조문서 번호,아이디넘버 확인 변수 지정'
def ccGetdocid = 'result.contents[0].docId'
def ccGetid = 'result.contents[0].draftAccountId'

'참조자로 인증토큰 발급'
WS.sendRequest(LoginD)

response3 = WS.sendRequestAndVerify(LoginD)

def Token6 = WS.getElementPropertyValue(response3, getToken)

ArrayList HTTPHeader1 = new ArrayList()

HTTPHeader1.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token6))

HTTPHeader1.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject ccdraft = findTestObject('Object Repository/전자결재 api/결재프로세스/참조함/참조한 문서 조회')

ccdraft.setHttpHeaderProperties(HTTPHeader1)

WS.sendRequest(ccdraft)

response4 = WS.sendRequestAndVerify(ccdraft)

'200코드 확인'
WS.verifyResponseStatusCode(response4, 200)

'참조함의 문서번호 변수지정'
def ccdocid = WS.getElementPropertyValue(response4, ccGetdocid)
 
'참조함의 기안자 변수지정'
def ccid = WS.getElementPropertyValue(response4, ccGetid)

'문서번호 매칭 확인'
WS.verifyEqual(ccdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(ccid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재함 > 결재전 문서 존재 확인
'결재전의 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid = 'result.contents[0].docId'
def awGetid = 'result.contents[0].draftAccountId'

'1차결재자 인증토큰 발급'
WS.sendRequest(LoginB)

response5 = WS.sendRequestAndVerify(LoginB)

def Token5 = WS.getElementPropertyValue(response5, getToken)

ArrayList HTTPHeader2 = new ArrayList()

HTTPHeader2.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token5))

HTTPHeader2.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject awdraft = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

awdraft.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(awdraft)

response6 = WS.sendRequestAndVerify(awdraft)

'200코드 확인'
WS.verifyResponseStatusCode(response6, 200)

'결재전의 문서번호 변수지정'
def awdocid = WS.getElementPropertyValue(response6, awGetdocid)
 
'결재전의 기안자 변수지정'
def awid = WS.getElementPropertyValue(response6, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(awdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재(승인,반려 = approval1)버튼,상위결재선(upApprLine)버튼,공람설정(share)버튼, 인쇄(print)버튼 존재 확인


RequestObject awbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

awbtndraft.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(awbtndraft)

response7 = WS.sendRequestAndVerify(awbtndraft)

'결재버튼 변수지정'
def approval1 = WS.getElementPropertyValue(response7, getBtn1)

'상위결재선 버튼 변수지정'
def upApprLine1 = WS.getElementPropertyValue(response7, getBtn2)

'공람설정 버튼 변수지정'
def share1 = WS.getElementPropertyValue(response7, getBtn3)

'인쇄 버튼 변수지정'
def print2 = WS.getElementPropertyValue(response7, getBtn4)

WS.verifyResponseStatusCode(response7, 200)

WS.verifyMatch(approval1, "approval1", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(upApprLine1, "upApprLine", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(share1, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print2, "print", true, FailureHandling.CONTINUE_ON_FAILURE)