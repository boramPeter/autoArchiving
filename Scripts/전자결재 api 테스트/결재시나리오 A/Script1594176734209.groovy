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


//1차 결재자 승인버튼 선택

'결재선번호 가져오기'
def Getappline1 = 'result.apprLineList[1].apprLineId'

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

 //WS.sendRequestAndVerify(confirmbtn1)

'200코드 확인'
WS.verifyResponseStatusCode(response9, 200,FailureHandling.CONTINUE_ON_FAILURE)


//기안자A 회수버튼 미존재 확인

//btndraft.setHttpHeaderProperties(HTTPHeader) 잘되면 이거 없애야지

WS.sendRequest(btndraft)

WS.verifyResponseStatusCode(response2, 200)

//WS.verifyNotMatch(retrieve1, "retrieve", false, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print1, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재함 > 결재중 문서 존재 확인
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


//결재취소(cancel)버튼=7, 결재의견(changeOpinion)버튼=8, 인쇄=9 존재 확인
'펑션에서 버튼 가져오기'
def getBtn7 = 'result.functions[0]'
def getBtn8 = 'result.functions[1]'
def getBtn9 = 'result.functions[2]'

RequestObject awingbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AI'])

awingbtndraft.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(awingbtndraft)

response11 = WS.sendRequestAndVerify(awingbtndraft)

'결재취소버튼 변수지정'
def cancel1 = WS.getElementPropertyValue(response11, getBtn7)

'결재의견 버튼 변수지정'
def changeOpinion1 = WS.getElementPropertyValue(response11, getBtn8)

'인쇄버튼변수 지정'
def print3 = WS.getElementPropertyValue(response11, getBtn9)

WS.verifyResponseStatusCode(response1, 200)

WS.verifyMatch(cancel1, "cancel", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(changeOpinion1, "changeOpinion", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print3, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자C 결재함 > 결재전 문서 존재 확인
'결재자 C 인증토큰 발급'
WS.sendRequest(LoginC)

response12 = WS.sendRequestAndVerify(LoginC)

def Token12 = WS.getElementPropertyValue(response12, getToken)

ArrayList HTTPHeader3 = new ArrayList()

HTTPHeader3.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token12))

HTTPHeader3.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject awdraft99 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

awdraft99.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(awdraft99)

response13 = WS.sendRequestAndVerify(awdraft99)

'200코드 확인'
WS.verifyResponseStatusCode(response13, 200)

'결재전의 문서번호 변수지정'
def awdocid1 = WS.getElementPropertyValue(response13, awGetdocid)
 
'결재전의 기안자 변수지정'
def awid1 = WS.getElementPropertyValue(response13, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(awdocid1,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awid1, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재(승인,반려,전단계반려 = approval2)버튼=3,공람설정(share)버튼=4, 인쇄(print)버튼=5 존재 확인
RequestObject awbtndraft1 = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

awbtndraft1.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(awbtndraft1)

response14 = WS.sendRequestAndVerify(awbtndraft1)

'결재버튼 변수지정'
def approval2 = WS.getElementPropertyValue(response14, getBtn1)

'공람설정 버튼 변수지정'
def share2 = WS.getElementPropertyValue(response14, getBtn2)

'인쇄 버튼 변수지정'
def print99 = WS.getElementPropertyValue(response14, getBtn3)

WS.verifyResponseStatusCode(response14, 200)

WS.verifyMatch(approval2, "approval2", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(share2, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print99, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//2차 결재자 승인버튼 선택

'결재선번호 가져오기'
def Getappline2 = 'result.apprLineList[2].apprLineId'

RequestObject apprlinedraft1 = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraft1.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(apprlinedraft1)

response15 = WS.sendRequestAndVerify(apprlinedraft1)

'결재선번호변수 지정'
def appline2 = WS.getElementPropertyValue(response15, Getappline2)

'결재승인 버튼누르기'
RequestObject confirmbtn2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : appline2, ('문서번호') : docid])

confirmbtn2.setHttpHeaderProperties(HTTPHeader3)

response16=WS.sendRequest(confirmbtn2)

 //= WS.sendRequestAndVerify(confirmbtn2)

'200코드 확인'
WS.verifyResponseStatusCode(response16, 200,  FailureHandling.CONTINUE_ON_FAILURE)


//기안함 > 완료된 문서 확인
'상신한문서의 문서번호 가져오기'
//def ssgetdocid = 'result.contents[0].docId' < 필요시 변경해서 사용
'완료된에 기안 확인'
RequestObject fndraft = findTestObject('Object Repository/전자결재 api/결재프로세스/기안함/완료된 문서 조회')

fndraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(fndraft)

response17 = WS.sendRequestAndVerify(fndraft)

'완료된에 있는 기안 문서번호 변수지정'
def fndocid = WS.getElementPropertyValue(response17, ssgetdocid)

WS.verifyResponseStatusCode(response17, 200)

WS.verifyEqual(fndocid, docid, FailureHandling.CONTINUE_ON_FAILURE)


//공람(share)버튼=1 존재 확인 (문서는 완료된상태)
RequestObject btndraft1 = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DC'])

btndraft1.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(btndraft1)

response18 = WS.sendRequestAndVerify(btndraft1)

'공람버튼 변수지정'
def share3 = WS.getElementPropertyValue(response18, getBtn1)

'인쇄버튼 변수지정'
def print4 = WS.getElementPropertyValue(response18, getBtn2)

WS.verifyResponseStatusCode(response18, 200)

WS.verifyMatch(share3, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print4, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재함 > 완료된 문서 존재 확인
'완료된의 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid2 = 'result.contents[0].docId'
def awGetid2 = 'result.contents[0].draftAccountId'

'1차결재자 인증토큰 발급'

RequestObject awingdraft2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/완료된 문서 조회',[('결재구분') : 'AP'])

awingdraft2.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(awingdraft2)

response19 = WS.sendRequestAndVerify(awingdraft2)

'200코드 확인'
WS.verifyResponseStatusCode(response19, 200)

'완료된의 문서번호 변수지정'
def awfndocid = WS.getElementPropertyValue(response19, awGetdocid2)
 
'완료된의 기안자 변수지정'
def awfnid = WS.getElementPropertyValue(response19, awGetid2)

'문서번호 매칭 확인'
WS.verifyEqual(awfndocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awfnid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자 B 공람(share)버튼=1 존재 확인 (문서는 완료된상태)
RequestObject Bbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AC'])

Bbtndraft.setHttpHeaderProperties(HTTPHeader2)

WS.sendRequest(Bbtndraft)

response20 = WS.sendRequestAndVerify(Bbtndraft)

'공람버튼 변수지정'
def share4 = WS.getElementPropertyValue(response20, getBtn1)

'인쇄버튼 변수지정'
def print5 = WS.getElementPropertyValue(response20, getBtn2)

WS.verifyResponseStatusCode(response20, 200)

WS.verifyMatch(share4, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print5, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자 C 결재함 > 완료된 문서 존재 확인
'1차결재자 인증토큰 발급'

RequestObject awfndraft2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/완료된 문서 조회',[('결재구분') : 'AP'])

awfndraft2.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(awfndraft2)

response21 = WS.sendRequestAndVerify(awfndraft2)

'200코드 확인'
WS.verifyResponseStatusCode(response21, 200)

'완료된의 문서번호 변수지정'
def awfndocid1 = WS.getElementPropertyValue(response21, awGetdocid2)
 
'완료된의 기안자 변수지정'
def awfnid1 = WS.getElementPropertyValue(response21, awGetid2)

'문서번호 매칭 확인'
WS.verifyEqual(awfndocid1,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awfnid1, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//결재자 C 공람(share)버튼=1 존재 확인 (문서는 완료된상태)
RequestObject Cbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AC'])

Cbtndraft.setHttpHeaderProperties(HTTPHeader3)

WS.sendRequest(Cbtndraft)

response22 = WS.sendRequestAndVerify(Cbtndraft)

'공람버튼 변수지정'
def share5 = WS.getElementPropertyValue(response22, getBtn1)

'인쇄버튼 변수지정'
def print6 = WS.getElementPropertyValue(response22, getBtn2)

WS.verifyResponseStatusCode(response22, 200)

WS.verifyMatch(share5, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print6, "print", true, FailureHandling.CONTINUE_ON_FAILURE)

//공람함에서 문서조회
'결재전의 문서 번호,아이디넘버 확인 변수 지정'
//def awGetdocid = 'result.contents[0].docId'
//def awGetid = 'result.contents[0].draftEmpName'

'공람자 인증토큰 발급'
WS.sendRequest(LoginE)

response99 = WS.sendRequestAndVerify(LoginE)

def Token99 = WS.getElementPropertyValue(response99, getToken)

ArrayList HTTPHeader99 = new ArrayList()

HTTPHeader99.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token99))

HTTPHeader99.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject sharedraft = findTestObject('Object Repository/전자결재 api/결재프로세스/공람함/공람한 문서 조회')

sharedraft.setHttpHeaderProperties(HTTPHeader99)

WS.sendRequest(sharedraft)

response98= WS.sendRequestAndVerify(sharedraft)

'200코드 확인'
WS.verifyResponseStatusCode(response98, 200, FailureHandling.CONTINUE_ON_FAILURE)

'공람함 문서번호 변수지정'
def sheredocid = WS.getElementPropertyValue(response98, awGetdocid)
 
'공람함 기안자 변수지정'
def shereid = WS.getElementPropertyValue(response98, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(sheredocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(shereid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])
delete.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(delete)

response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)


