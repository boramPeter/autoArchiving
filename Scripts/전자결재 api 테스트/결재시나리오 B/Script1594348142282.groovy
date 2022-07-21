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
def LoginA = findTestObject('전자결재 api/우회로그인', [('아이디') : 'peter.bo'])

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


//상신함에서 회수버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'	

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


//회수버튼 선택 

RequestObject retrievebtn = findTestObject('전자결재 api/결재프로세스/결재 버튼/회수 버튼',[('문서번호') : docid])

retrievebtn.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(retrievebtn)

responseR = WS.sendRequestAndVerify(retrievebtn)

'200코드 확인'
WS.verifyResponseStatusCode(responseR, 200)


//회수함에 문서 확인
'회수함에 있는 문서번호 가져오기'

def getRdocid = 'result.contents[0].docId'


RequestObject retrievedraft = findTestObject('전자결재 api/결재프로세스/기안함/회수된 문서 조회')

retrievedraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(retrievedraft)

responseRD = WS.sendRequestAndVerify(retrievedraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseRD, 200)

'회수된의 문서번호 변수지정'
def Rdocid = WS.getElementPropertyValue(responseRD, getRdocid)

'문서번호 매칭 확인'
WS.verifyEqual(Rdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)


//회수된 문서함의 재기안버튼 확인
'펑션에서 버튼 가져오기'
def getBtn3 = 'result.functions[2]'

RequestObject Rbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DE'])

Rbtndraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(Rbtndraft)

responseRbtn = WS.sendRequestAndVerify(Rbtndraft)

'재기안버튼 변수지정'
def redraft = WS.getElementPropertyValue(responseRbtn, getBtn1)

'삭제버튼 변수지정'
def deleteBtn = WS.getElementPropertyValue(responseRbtn, getBtn2)

'인쇄버튼 변수지정'
def printR = WS.getElementPropertyValue(responseRbtn, getBtn3)

WS.verifyResponseStatusCode(responseRbtn, 200)

WS.verifyMatch(redraft, "redraft", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(deleteBtn, "delete", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printR, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//재기안올리기
RequestObject Redraft = findTestObject('전자결재 api/결재프로세스/기안작성/재기안 작성(기안1결재2참조1공람1)', [('문서번호') : docid, ('결재선유형') : 'DD', ('결재타입1') : 'AP', ('결재상태1') : 'AW'
	, ('결재타입2') : 'AP', ('결재상태2') : 'AW', ('결재타입3') : 'CC', ('결재상태3') : 'AC'])

Redraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(Redraft)


//재기안한 문서를 상신한에 문서 확인

WS.sendRequest(ssdraft)

'상신한에 있는 기안 문서번호 변수지정'

WS.verifyResponseStatusCode(response0, 200)

WS.verifyEqual(ssdocid, docid, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])

delete.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(delete)

response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)


