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
def LoginD = findTestObject('전자결재 api/우회로그인', [('아이디') : 'casey.han']) // D결재자
def LoginE = findTestObject('전자결재 api/우회로그인', [('아이디') : 'sio.oh']) //공람자

def getToken = 'result.accessToken'

WS.sendRequest(LoginA)

response = WS.sendRequestAndVerify(LoginA)

'인증토큰 발급'
def Token = WS.getElementPropertyValue(response, getToken)

//기안작성		(기안자 : peter.bo, 결재자 B : lia.jung, 결재자 C : jenny.park, 결재자D : casey.han, 공람E : sio.oh)
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1결재3공람1)', [('결재선유형') : 'DD', ('결재타입1') : 'AP', ('결재상태1') : 'AW'
        , ('결재타입2') : 'AP', ('결재상태2') : 'AW', ('결재타입3') : 'AP', ('결재상태3') : 'AW'])

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


//1차 결재자 승인버튼 선택

'1차결재자 인증토큰 발급'
WS.sendRequest(LoginB)

responseB = WS.sendRequestAndVerify(LoginB)

def Token5 = WS.getElementPropertyValue(responseB, getToken)

ArrayList HTTPHeader2 = new ArrayList()

HTTPHeader2.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token5))

HTTPHeader2.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))


'결재선번호 가져오기'
def Getappline1 = 'result.apprLineList[1].apprLineId'
def Getappline2 = 'result.apprLineList[2].apprLineId'
def Getappline3 = 'result.apprLineList[3].apprLineId'


RequestObject apprlinedraft = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraft.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(apprlinedraft)

response8 = WS.sendRequestAndVerify(apprlinedraft)

'결재선번호변수 지정'
def appline1 = WS.getElementPropertyValue(response8, Getappline1,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject confirmbtn1 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : appline1, ('문서번호') : docid])

confirmbtn1.setHttpHeaderProperties(HTTPHeader2)

response9=WS.sendRequest(confirmbtn1)

'200코드 확인'
WS.verifyResponseStatusCode(response9, 200,FailureHandling.CONTINUE_ON_FAILURE)


//2차 결재자 승인버튼 선택

'2차결재자 인증토큰 발급'
WS.sendRequest(LoginC)

responseC = WS.sendRequestAndVerify(LoginC)

def TokenC = WS.getElementPropertyValue(responseC, getToken)

ArrayList HTTPHeader1 = new ArrayList()

HTTPHeader1.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenC))

HTTPHeader1.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

'결재선번호 가져오기'

RequestObject apprlinedraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftC.setHttpHeaderProperties(HTTPHeader1)

WS.sendRequest(apprlinedraftC)

response8C = WS.sendRequestAndVerify(apprlinedraftC)

'결재선번호변수 지정'
def appline1C = WS.getElementPropertyValue(response8C, Getappline2,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject confirmbtn2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : appline1C, ('문서번호') : docid])

confirmbtn2.setHttpHeaderProperties(HTTPHeader1)

response9C=WS.sendRequest(confirmbtn2)

'200코드 확인'
WS.verifyResponseStatusCode(response9C, 200,FailureHandling.CONTINUE_ON_FAILURE)


//기안자 A 상신한에 문서 확인
WS.sendRequest(ssdraft)

WS.verifyResponseStatusCode(response0, 200)

WS.verifyEqual(ssdocid, docid, FailureHandling.CONTINUE_ON_FAILURE)


//결재자B 결재함 > 진행중 문서 존재 확인
'결재중의 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid1 = 'result.contents[0].docId'
def awGetid1 = 'result.contents[0].draftAccountId'

'1차결재자 인증토큰 발급'
RequestObject awingdraft = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/진행중 문서 조회',[('결재구분') : 'AP'])

awingdraft.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(awingdraft)

response10 = WS.sendRequestAndVerify(awingdraft)

'200코드 확인'
WS.verifyResponseStatusCode(response10, 200)

'결재중의 문서번호 변수지정'
def awingdocid = WS.getElementPropertyValue(response10, awGetdocid1)
 
'결재중의 기안자 변수지정'
def awingid = WS.getElementPropertyValue(response10, awGetid1)

'문서번호 매칭 확인'
WS.verifyEqual(awingdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awingid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재함 > 진행중 문서 존재 확인

'2차결재자 인증토큰 발급'
RequestObject awingdraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/진행중 문서 조회',[('결재구분') : 'AP'])

awingdraftC.setHttpHeaderProperties(HTTPHeader1)

WS.sendRequest(awingdraftC)

response10c = WS.sendRequestAndVerify(awingdraftC)

'200코드 확인'
WS.verifyResponseStatusCode(response10c, 200)

'결재중의 문서번호 변수지정'
def awingdocidc = WS.getElementPropertyValue(response10c, awGetdocid1)
 
'결재중의 기안자 변수지정'
def awingidc = WS.getElementPropertyValue(response10c, awGetid1)

'문서번호 매칭 확인'
WS.verifyEqual(awingdocidc,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awingidc, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재취소(cancel) 결재의견(changeOpinion), 인쇄버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'
def getBtn3 = 'result.functions[2]'

RequestObject btndraftC = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AI'])

btndraftC.setHttpHeaderProperties(HTTPHeader1)

WS.sendRequest(btndraftC)

response2C = WS.sendRequestAndVerify(btndraftC)

'결재취소 버튼 변수지정'
def cancel1 = WS.getElementPropertyValue(response2C, getBtn1)

'결재의견 버튼 변수지정'
def changOpinion1 = WS.getElementPropertyValue(response2C, getBtn2)

'인쇄버튼 변수지정'
def printC = WS.getElementPropertyValue(response2C, getBtn3)

WS.verifyResponseStatusCode(response2C, 200)

WS.verifyMatch(cancel1, "cancel", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(changOpinion1, "changeOpinion", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printC, "print", true, FailureHandling.CONTINUE_ON_FAILURE)



//결재자D 결재함 > 결재전 문서 존재 확인
'결재자D 인증토큰 발급'
WS.sendRequest(LoginD)

response12D = WS.sendRequestAndVerify(LoginD)

def TokenD = WS.getElementPropertyValue(response12D, getToken)

ArrayList HTTPHeader3 = new ArrayList()

HTTPHeader3.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenD))

HTTPHeader3.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject awdraft99d = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

awdraft99d.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(awdraft99d)

response13d = WS.sendRequestAndVerify(awdraft99d)

'결재전의 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid = 'result.contents[0].docId'
def awGetid = 'result.contents[0].draftEmpName'

'200코드 확인'
WS.verifyResponseStatusCode(response13d, 200)

'결재전의 문서번호 변수지정'
def awdocid1d = WS.getElementPropertyValue(response13d, awGetdocid)
 
'결재전의 기안자 변수지정'
def awid1d = WS.getElementPropertyValue(response13d, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(awdocid1d,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awid1d, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)



//결재자D 결재(승인,반려,전단계반려 = approval2)버튼,공람설정(share)버튼, 인쇄(print)버튼 존재 확인

RequestObject awingbtndraftD = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

awingbtndraftD.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(awingbtndraftD)

responseD = WS.sendRequestAndVerify(awingbtndraftD)

'결재버튼 변수지정'
def approval2D = WS.getElementPropertyValue(responseD, getBtn1)

'공람 버튼 변수지정'
def shareD = WS.getElementPropertyValue(responseD, getBtn2)

'인쇄버튼변수 지정'
def printD = WS.getElementPropertyValue(responseD, getBtn3)

WS.verifyResponseStatusCode(responseD, 200)

WS.verifyMatch(approval2D, "approval2", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(shareD, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printD, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재취소 시나리오

'결재취소 버튼누르기'
RequestObject cancelbtn2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/취소 버튼',[('결재선번호') : appline1C, ('문서번호') : docid])

cancelbtn2.setHttpHeaderProperties(HTTPHeader1)

responsecencelC=WS.sendRequest(cancelbtn2)

'200코드 확인'
WS.verifyResponseStatusCode(responsecencelC, 200,FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재함 > 결재전 문서 존재 확인
RequestObject awdraft99c = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

awdraft99c.setHttpHeaderProperties(HTTPHeader1)

WS.sendRequest(awdraft99c)

responseingC = WS.sendRequestAndVerify(awdraft99c)

'200코드 확인'
WS.verifyResponseStatusCode(responseingC, 200)

'결재전의 문서번호 변수지정'
def awdocid1c = WS.getElementPropertyValue(responseingC, awGetdocid)
 
'결재전의 기안자 변수지정'
def awid1c = WS.getElementPropertyValue(responseingC, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(awdocid1c,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awid1c, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재(승인,반려,전단계반려 = approval2)버튼, 상위결재선변경(upApprLine)버튼, 공람설정(share)버튼, 인쇄버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn4 = 'result.functions[3]'

RequestObject btndraftCC = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

btndraftCC.setHttpHeaderProperties(HTTPHeader1)

WS.sendRequest(btndraftCC)

response2CC = WS.sendRequestAndVerify(btndraftCC)

'결재승인 버튼 변수지정'
def approval2CC = WS.getElementPropertyValue(response2CC, getBtn1)

'상위 결재선 변경 버튼 변수지정'
def upApprLineCC = WS.getElementPropertyValue(response2CC, getBtn2)

'공람버튼 변수지정'
def shareCC = WS.getElementPropertyValue(response2CC, getBtn3)

'인쇄버튼변수 지정'
def printCC = WS.getElementPropertyValue(response2CC, getBtn4)

WS.verifyResponseStatusCode(response2CC, 200)

WS.verifyMatch(approval2CC, "approval2", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(upApprLineCC, "upApprLine", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(shareCC, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printCC, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 승인버튼 선택
WS.sendRequest(confirmbtn2)


//결재자 D 전단계 반려 
'결재선번호 가져오기'
RequestObject apprlinedraftD = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftD.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(apprlinedraftD)

response8DD = WS.sendRequestAndVerify(apprlinedraftD)

'결재선번호변수 지정'
def appline1D = WS.getElementPropertyValue(response8DD, Getappline3,FailureHandling.CONTINUE_ON_FAILURE)

'전단계 반려 버튼누르기'
RequestObject previousbtn = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/전단계 반려 버튼',[('결재선번호') : appline1D, ('문서번호') : docid])

previousbtn.setHttpHeaderProperties(HTTPHeader3)

response9D=WS.sendRequest(previousbtn)

'200코드 확인'
WS.verifyResponseStatusCode(response9D, 200,FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재함 > 결재전 문서 존재 확인

WS.sendRequest(awdraft99c)

'200코드 확인'
WS.verifyResponseStatusCode(responseingC, 200)

'문서번호 매칭 확인'
WS.verifyEqual(awdocid1c,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awid1c, "김보람", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자B 결재함 > 진행중 문서 존재 확인

'200코드 확인'
WS.verifyResponseStatusCode(response10, 200)

'문서번호 매칭 확인'
WS.verifyEqual(awingdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awingid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])
delete.setHttpHeaderProperties(HTTPHeader)

response999=WS.sendRequest(delete)

//response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)


