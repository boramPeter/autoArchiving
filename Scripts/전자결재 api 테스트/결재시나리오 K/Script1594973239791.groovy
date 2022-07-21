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
def LoginB = findTestObject('전자결재 api/우회로그인', [('아이디') : 'jenny.park']) //B결재자
def LoginC = findTestObject('전자결재 api/우회로그인', [('아이디') : 'sunny.sim']) //C협조수신부서자


def getToken = 'result.accessToken'

WS.sendRequest(LoginA)

response = WS.sendRequestAndVerify(LoginA)

'인증토큰 발급'
def Token = WS.getElementPropertyValue(response, getToken)

//기안작성	 (기안자 : peter.bo, 결재자 B : lia.jung, 협조자 C : jenny.park)
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1결재1부서수신1)', [('결재선유형') : 'DD', ('결재타입2') : 'AP', ('결재상태2') : 'AW', ("결재선상태"):'EW'])

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


//결재자B 결재전에 문서 확인
'결재자B 인증토큰 발급'
WS.sendRequest(LoginB)

responseC = WS.sendRequestAndVerify(LoginB)

def TokenC = WS.getElementPropertyValue(responseC, getToken)

ArrayList HTTPHeaderC = new ArrayList()

HTTPHeaderC.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenC))

HTTPHeaderC.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject APdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

APdraft.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(APdraft)

responseCC = WS.sendRequestAndVerify(APdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseCC, 200)

'결재전 및 기타 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid = 'result.contents[0].docId'
def awGetid = 'result.contents[0].draftEmpName'

'결재전의 문서번호 변수지정'
def APdocid = WS.getElementPropertyValue(responseCC, awGetdocid)
 
'결재전의 기안자 변수지정'
def APid = WS.getElementPropertyValue(responseCC, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(APdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(APid, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자B 결재(승인,반려,전단계반려 = approval2)버튼, 공람설정(share)버튼, 인쇄(print)버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'
def getBtn3 = 'result.functions[2]'
def getBtn4 = 'result.functions[3]'
def getBtn5 = 'result.functions[4]'


//결재자B 승인버튼 선택

'결재선번호 가져오기'
def Getappline1 = 'result.apprLineList[1].apprLineId'
def Getappline2 = 'result.apprLineList[2].apprLineId'

RequestObject apprlinedraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(apprlinedraftC)

responseCCCC = WS.sendRequestAndVerify(apprlinedraftC)

'결재선번호변수 지정'
def appline1C = WS.getElementPropertyValue(responseCCCC, Getappline1,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject confirmbtn2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : appline1C, ('문서번호') : docid])

confirmbtn2.setHttpHeaderProperties(HTTPHeaderC)

response9C=WS.sendRequest(confirmbtn2)

'200코드 확인'
WS.verifyResponseStatusCode(response9C, 200,FailureHandling.CONTINUE_ON_FAILURE)


//기안자 A 완료된에 문서 확인
'완료된에 기안 확인'
RequestObject fndraft = findTestObject('Object Repository/전자결재 api/결재프로세스/기안함/완료된 문서 조회')

fndraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(fndraft)

responseA = WS.sendRequestAndVerify(fndraft)

'완료된에 있는 기안 문서번호 변수지정'
def fndocid = WS.getElementPropertyValue(responseA, ssgetdocid)

WS.verifyResponseStatusCode(responseA, 200)

WS.verifyEqual(fndocid, docid, FailureHandling.CONTINUE_ON_FAILURE)


//부서수신담당자 수신함 문서 확인
'부서수신담당자'
WS.sendRequest(LoginC)

responseB = WS.sendRequestAndVerify(LoginC)

def TokenB = WS.getElementPropertyValue(responseB, getToken)

ArrayList HTTPHeaderB = new ArrayList()

HTTPHeaderB.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenB))

HTTPHeaderB.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject HEdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/부서문서함/부서수신 문서 조회')

HEdraft.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(HEdraft)

responseBB = WS.sendRequestAndVerify(HEdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseBB, 200)

'부서협조함의 문서번호 변수지정'
def hedocid = WS.getElementPropertyValue(responseBB, awGetdocid)
 
'부서협조함의 기안자 변수지정'
def heid = WS.getElementPropertyValue(responseBB, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//부서수신자 접수(receipt), 수신확인(okReceive), 반송(return), 인쇄(print)버튼 존재 확인

RequestObject btndraftB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'ER'])

btndraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(btndraftB)

responseBBB = WS.sendRequestAndVerify(btndraftB)

'상위결재선 버튼 변수지정'
def okReceiveB = WS.getElementPropertyValue(responseBBB, getBtn1)

'반송 버튼 변수지정'
def returnB = WS.getElementPropertyValue(responseBBB, getBtn2)

'접수 버튼 변수지정'
def receiptB = WS.getElementPropertyValue(responseBBB, getBtn3)

'인쇄버튼 변수지정'
def printB = WS.getElementPropertyValue(responseBBB, getBtn4)

WS.verifyResponseStatusCode(responseBBB, 200)

WS.verifyMatch(receiptB, "receipt", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(okReceiveB, "okReceive", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(returnB, "return", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printB, "print", true, FailureHandling.CONTINUE_ON_FAILURE)

/*
//부서협조자 B 수신확인 선택 

RequestObject apprlinedraftB = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(apprlinedraftB)

responseBBBB = WS.sendRequestAndVerify(apprlinedraftB)

'결재선번호변수 지정'
def applineB = WS.getElementPropertyValue(responseBBBB, Getappline1,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject confirmbtnB = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/수신확인 버튼',[('결재선번호') : applineB, ('문서번호') : docid])

confirmbtnB.setHttpHeaderProperties(HTTPHeaderB)

responseBBBBB=WS.sendRequest(confirmbtnB)

'200코드 확인'
WS.verifyResponseStatusCode(responseBBBBB, 200,FailureHandling.CONTINUE_ON_FAILURE)

//수신확인 후 수신함에 문서있는지 확인
'200코드 확인'
WS.verifyResponseStatusCode(responseBB, 200)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])
delete.setHttpHeaderProperties(HTTPHeader)

response999=WS.sendRequest(delete)

//response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)
*/

