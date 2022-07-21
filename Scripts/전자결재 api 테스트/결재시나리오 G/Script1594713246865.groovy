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
def LoginB = findTestObject('전자결재 api/우회로그인', [('아이디') : 'ellie.hwang']) //B협조자
def LoginC = findTestObject('전자결재 api/우회로그인', [('아이디') : 'jenny.park']) //C결재자

def getToken = 'result.accessToken'

WS.sendRequest(LoginA)

response = WS.sendRequestAndVerify(LoginA)

'인증토큰 발급'
def Token = WS.getElementPropertyValue(response, getToken)

//기안작성	 (기안자 : peter.bo, 결재자 B : lia.jung, 협조자 C : jenny.park)
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1결재1협조1)', [('결재선유형') : 'DD', ('결재타입1') : 'HE', ('결재상태1') : 'EW'
        , ('결재타입2') : 'AP', ('결재상태2') : 'AW'])

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


//1차 협조자 결재전에 문서 확인
'1차 협조자 인증토큰 발급'
WS.sendRequest(LoginB)

responseB = WS.sendRequestAndVerify(LoginB)

def TokenB = WS.getElementPropertyValue(responseB, getToken)

ArrayList HTTPHeaderB = new ArrayList()

HTTPHeaderB.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenB))

HTTPHeaderB.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

'결재전의 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid = 'result.contents[0].docId'
def awGetid = 'result.contents[0].draftEmpName'

RequestObject HEdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'HE'])

HEdraft.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(HEdraft)

responseBB = WS.sendRequestAndVerify(HEdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseBB, 200)

'결재전의 문서번호 변수지정'
def hedocid = WS.getElementPropertyValue(responseBB, awGetdocid)
 
'결재전의 기안자 변수지정'
def heid = WS.getElementPropertyValue(responseBB, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//협조자 B 반려버튼 선택
'결재선번호 가져오기'
def Getappline1 = 'result.apprLineList[1].apprLineId'
def Getappline2 = 'result.apprLineList[2].apprLineId'

RequestObject apprlinedraftB = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(apprlinedraftB)

responseBBBB = WS.sendRequestAndVerify(apprlinedraftB)

'결재선번호변수 지정'
def applineB = WS.getElementPropertyValue(responseBBBB, Getappline1,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject rejectbtnB = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/반려 버튼',[('결재선번호') : applineB, ('문서번호') : docid])

rejectbtnB.setHttpHeaderProperties(HTTPHeaderB)

responseBBBBB=WS.sendRequest(rejectbtnB)

'200코드 확인'
WS.verifyResponseStatusCode(responseBBBBB, 200,FailureHandling.CONTINUE_ON_FAILURE)


//협조자B 결재함 > 반려된에 문서 존재 확인
'반려된의 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid1 = 'result.contents[0].docId'
def awGetname1 = 'result.contents[0].draftEmpName'

'1차협조자 인증토큰 발급'
RequestObject awingdraft = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/반려된 문서 조회',[('결재구분') : 'HE'])

awingdraft.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(awingdraft)

responseBBBBBB = WS.sendRequestAndVerify(awingdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseBBBBBB, 200)

'반려된의 문서번호 변수지정'
def awingdocid = WS.getElementPropertyValue(responseBBBBBB, awGetdocid1)
 
'반려된의 기안자 변수지정'
def awingname = WS.getElementPropertyValue(responseBBBBBB, awGetname1)

'문서번호 매칭 확인'
WS.verifyEqual(awingdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awingname, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//기안자A 반려함에 문서 확인
'반려함에 있는 문서번호 가져오기'

def getRdocid = 'result.contents[0].docId'

RequestObject reject1draft = findTestObject('전자결재 api/결재프로세스/기안함/반려된 문서 조회')

reject1draft.setHttpHeaderProperties(HTTPHeader)

responseRD=WS.sendRequest(reject1draft)

//responseRD = WS.getResponseStatusCode(reject1draft)

'200코드 확인'
WS.verifyResponseStatusCode(responseRD, 200)

'반려된의 문서번호 변수지정'
def Rdocid = WS.getElementPropertyValue(responseRD, getRdocid)

'문서번호 매칭 확인'
WS.verifyEqual(Rdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)


//기안자 재기안(redraft)버튼, 삭제(delete)버튼, 인쇄(print)버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'
def getBtn3 = 'result.functions[2]'
def getBtn4 = 'result.functions[3]'

RequestObject Rbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DR'])

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


/*
//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])
delete.setHttpHeaderProperties(HTTPHeader)

response999=WS.sendRequest(delete)

//response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)

*/
