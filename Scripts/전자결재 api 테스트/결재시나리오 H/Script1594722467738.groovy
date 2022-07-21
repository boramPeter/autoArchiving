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
def LoginB = findTestObject('전자결재 api/우회로그인', [('아이디') : 'sunny.sim']) //B협조부서자
def LoginC = findTestObject('전자결재 api/우회로그인', [('아이디') : 'jenny.park']) //C결재자

def getToken = 'result.accessToken'

WS.sendRequest(LoginA)

response = WS.sendRequestAndVerify(LoginA)

'인증토큰 발급'
def Token = WS.getElementPropertyValue(response, getToken)

//기안작성	 (기안자 : peter.bo, 결재자 B : lia.jung, 협조자 C : jenny.park)
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1부서협조1결재1)', [('결재선유형') : 'DD', ('결재타입2') : 'AP', ('결재상태2') : 'AW'])

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


//1차 부서협조자 부서문서함 > 협조함 문서 확인
'1차 협조자 인증토큰 발급'
WS.sendRequest(LoginB)

responseB = WS.sendRequestAndVerify(LoginB)

def TokenB = WS.getElementPropertyValue(responseB, getToken)

ArrayList HTTPHeaderB = new ArrayList()

HTTPHeaderB.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenB))

HTTPHeaderB.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

'부서협조전 및 기타 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid = 'result.contents[0].docId'
def awGetid = 'result.contents[0].draftEmpName'

RequestObject HEdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/부서문서함/부서협조 문서 조회')

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


//부서협조자 결재(승인,반려 = approval1)버튼, 상위결재선(upApprLine)버튼, 공람설정(share)버튼, 접수(receipt), 인쇄(print)버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'
def getBtn3 = 'result.functions[2]'
def getBtn4 = 'result.functions[3]'
def getBtn5 = 'result.functions[4]'

RequestObject btndraftB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'EH'])

btndraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(btndraftB)

responseBBB = WS.sendRequestAndVerify(btndraftB)

'결재 버튼 변수지정'
def approvalB = WS.getElementPropertyValue(responseBBB, getBtn1)

'상위결재선 버튼 변수지정'
def upApprLineB = WS.getElementPropertyValue(responseBBB, getBtn2)

'접수 버튼 변수지정'
def receiptB = WS.getElementPropertyValue(responseBBB, getBtn3)

'공람설정 버튼 변수지정'
def shareB = WS.getElementPropertyValue(responseBBB, getBtn4)

'인쇄버튼 변수지정'
def printB = WS.getElementPropertyValue(responseBBB, getBtn5)

WS.verifyResponseStatusCode(responseBBB, 200)

WS.verifyMatch(approvalB, "approval1", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(upApprLineB, "upApprLine", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(shareB, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(receiptB, "receipt", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printB, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//부서협조자 B 결재승인 확인
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
RequestObject confirmbtnB = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : applineB, ('문서번호') : docid])

confirmbtnB.setHttpHeaderProperties(HTTPHeaderB)

responseBBBBB=WS.sendRequest(confirmbtnB)

'200코드 확인'
WS.verifyResponseStatusCode(responseBBBBB, 200,FailureHandling.CONTINUE_ON_FAILURE)


//결재승인 후 1차 부서협조자 부서문서함 > 협조함 문서 확인
WS.sendRequest(HEdraft)
'200코드 확인'
WS.verifyResponseStatusCode(responseBB, 200)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)



//부서협조자 결재취소(cancel),인쇄버튼 존재 확인

RequestObject btndraftBB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'EH'])

btndraftBB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(btndraftBB)

responseB1 = WS.sendRequestAndVerify(btndraftBB)

'결재취소 버튼 변수지정'
def cancelB = WS.getElementPropertyValue(responseB1, getBtn1)

'인쇄버튼 변수지정'
def printBB = WS.getElementPropertyValue(responseB1, getBtn2)

WS.verifyResponseStatusCode(responseBBB, 200)

WS.verifyMatch(cancelB, "cancel", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printBB, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재전에 문서 확인
'결재자C 인증토큰 발급'
WS.sendRequest(LoginC)

responseC = WS.sendRequestAndVerify(LoginC)

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

'결재전의 문서번호 변수지정'
def APdocid = WS.getElementPropertyValue(responseCC, awGetdocid)
 
'결재전의 기안자 변수지정'
def APid = WS.getElementPropertyValue(responseCC, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(APdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(APid, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재(승인,반려 = approval1)버튼, 공람설정(share)버튼, 인쇄(print)버튼 존재 확인

RequestObject AWbtndraftC = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

AWbtndraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(AWbtndraftC)

responseCCC = WS.sendRequestAndVerify(AWbtndraftC)

'결재버튼 변수지정'
def approvalC = WS.getElementPropertyValue(responseCCC, getBtn1)

'공람 버튼 변수지정'
def shareC = WS.getElementPropertyValue(responseCCC, getBtn2)

'인쇄버튼변수 지정'
def printC = WS.getElementPropertyValue(responseCCC, getBtn3)

WS.verifyResponseStatusCode(responseCCC, 200)

WS.verifyMatch(approvalC, "approval1", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(shareC, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printC, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 승인버튼 선택

'결재선번호 가져오기'

RequestObject apprlinedraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(apprlinedraftC)

responseCCCC = WS.sendRequestAndVerify(apprlinedraftC)

'결재선번호변수 지정'
def appline1C = WS.getElementPropertyValue(responseCCCC, Getappline2,FailureHandling.CONTINUE_ON_FAILURE)

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


//기안자 공람버튼, 인쇄버튼 존재 확인

RequestObject btndraftA = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DC'])

btndraftA.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(btndraftA)

responseAA = WS.sendRequestAndVerify(btndraftA)

'공람 버튼 변수지정'
def shareA = WS.getElementPropertyValue(responseAA, getBtn1)

'인쇄버튼 변수지정'
def printA = WS.getElementPropertyValue(responseAA, getBtn2)

WS.verifyResponseStatusCode(responseAA, 200)

WS.verifyMatch(shareA, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printA, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//부서협조자B 공람버튼, 인쇄버튼 존재 확인

RequestObject btndraftBBB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'EH'])

btndraftBBB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(btndraftBBB)

responseBbtn = WS.sendRequestAndVerify(btndraftBBB)

'공람 버튼 변수지정'
def shareBbtn = WS.getElementPropertyValue(responseBbtn, getBtn1)

'인쇄버튼 변수지정'
def printBbtn = WS.getElementPropertyValue(responseBbtn, getBtn2)

WS.verifyResponseStatusCode(responseBbtn, 200)

WS.verifyMatch(shareBbtn, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printBbtn, "print", true, FailureHandling.CONTINUE_ON_FAILURE)



//결재자C 완료된에 문서 확인
'완료된에 기안 확인'
RequestObject fndraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/완료된 문서 조회',[('결재구분') : 'AP'])

fndraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(fndraftC)

responseCfn = WS.sendRequestAndVerify(fndraftC)

'완료된에 있는 기안 문서번호 변수지정'
def fndocidC = WS.getElementPropertyValue(responseCfn, ssgetdocid)

WS.verifyResponseStatusCode(responseCfn, 200)

WS.verifyEqual(fndocidC, docid, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 공람버튼, 인쇄버튼 존재 확인

RequestObject btndraftCCC = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AC'])

btndraftCCC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(btndraftCCC)

responseCbtn = WS.sendRequestAndVerify(btndraftCCC)

'공람 버튼 변수지정'
def shareCbtn = WS.getElementPropertyValue(responseCbtn, getBtn1)

'인쇄버튼 변수지정'
def printCbtn = WS.getElementPropertyValue(responseCbtn, getBtn2)

WS.verifyResponseStatusCode(responseBbtn, 200)

WS.verifyMatch(shareCbtn, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printCbtn, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])
delete.setHttpHeaderProperties(HTTPHeader)

response999=WS.sendRequest(delete)

//response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)


