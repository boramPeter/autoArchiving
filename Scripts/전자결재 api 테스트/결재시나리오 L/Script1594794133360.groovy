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
def LoginA = findTestObject('전자결재 api/우회로그인', [('아이디') : 'sunny.sim']) //내부기안
def LoginB = findTestObject('전자결재 api/우회로그인', [('아이디') : 'ellie.hwang']) //B결재자
def LoginC = findTestObject('전자결재 api/우회로그인', [('아이디') : 'casey.han']) //C결재자
def LoginD = findTestObject('전자결재 api/우회로그인', [('아이디') : 'peter.bo']) //원문서 기안자
def LoginE = findTestObject('전자결재 api/우회로그인', [('아이디') : 'jenny.park']) //원문서 결재자
def getToken = 'result.accessToken'

WS.sendRequest(LoginD)

responseorgD = WS.sendRequestAndVerify(LoginD)

'인증토큰 발급'
def Tokendocid = WS.getElementPropertyValue(responseorgD, getToken)

//원문서 기안작성	 (기안자 : peter.bo, 결재자 B :jenny.park, 부서수신자)
RequestObject orgdraft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1결재1부서수신1)', [('결재선유형') : 'DD', ('결재타입2') : 'AP', ('결재상태2') : 'AW',("결재선상태"):'EW'])

ArrayList HTTPHeaderD = new ArrayList()

HTTPHeaderD.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Tokendocid))

HTTPHeaderD.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

orgdraft.setHttpHeaderProperties(HTTPHeaderD)

WS.sendRequest(orgdraft)


//원문서 문서번호 획득
def getDocid = 'result [0] .docId'

RequestObject orgmydraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서상신목록 조회')

orgmydraft.setHttpHeaderProperties(HTTPHeaderD)

WS.sendRequest(orgmydraft)

responseD = WS.sendRequestAndVerify(orgmydraft)

def orgdocid = WS.getElementPropertyValue(responseD, getDocid)


//원문서 상신한에 문서 확인
'상신한문서의 문서번호 가져오기'
def ssgetdocid = 'result.contents[0].docId'

'상신한에 기안 확인'
RequestObject ssdraftD = findTestObject('전자결재 api/결재프로세스/기안함/상신한 문서 조회')

ssdraftD.setHttpHeaderProperties(HTTPHeaderD)

WS.sendRequest(ssdraftD)

responseorgss = WS.sendRequestAndVerify(ssdraftD)

'상신한에 있는 기안 문서번호 변수지정'
def ssdocid = WS.getElementPropertyValue(responseorgss, ssgetdocid)

WS.verifyResponseStatusCode(responseorgss, 200)

WS.verifyEqual(ssdocid, orgdocid, FailureHandling.CONTINUE_ON_FAILURE)


//원문서 결재자 결재전에 문서 확인
'원문서 결재자 인증토큰 발급'
WS.sendRequest(LoginE)

responseE = WS.sendRequestAndVerify(LoginE)

def TokenE = WS.getElementPropertyValue(responseE, getToken)

ArrayList HTTPHeaderE = new ArrayList()

HTTPHeaderE.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenE))

HTTPHeaderE.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject orgAPdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

orgAPdraft.setHttpHeaderProperties(HTTPHeaderE)

WS.sendRequest(orgAPdraft)

responseEE = WS.sendRequestAndVerify(orgAPdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseEE, 200)

'결재전 및 기타 문서 번호,아이디넘버 확인 변수 지정'
def awGetdocid = 'result.contents[0].docId'
def awGetname= 'result.contents[0].draftEmpName'
def awGetid = 'result.contents[0].draftAccountId'

'결재전의 문서번호 변수지정'
def orgAPdocid = WS.getElementPropertyValue(responseEE, awGetdocid)
 
'결재전의 기안자 변수지정'
def orgAPid = WS.getElementPropertyValue(responseEE, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(orgAPdocid,orgdocid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(orgAPid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//버튼 변수 정의
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'
def getBtn2 = 'result.functions[1]'
def getBtn3 = 'result.functions[2]'
def getBtn4 = 'result.functions[3]'
def getBtn5 = 'result.functions[4]'


//원문서 결재자 승인버튼 선택

'결재선번호 가져오기'
def Getappline1 = 'result.apprLineList[1].apprLineId'
def Getappline2 = 'result.apprLineList[2].apprLineId'
def Getappline3 = 'result.apprLineList[3].apprLineId'


RequestObject orgapprlinedraftE = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : orgdocid])

orgapprlinedraftE.setHttpHeaderProperties(HTTPHeaderE)

WS.sendRequest(orgapprlinedraftE)

responseEEEE = WS.sendRequestAndVerify(orgapprlinedraftE)

'결재선번호변수 지정'
def orgappline1E = WS.getElementPropertyValue(responseEEEE, Getappline1,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject orgconfirmbtn2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : orgappline1E, ('문서번호') : orgdocid])

orgconfirmbtn2.setHttpHeaderProperties(HTTPHeaderE)

responseEEEEE=WS.sendRequest(orgconfirmbtn2)

'200코드 확인'
WS.verifyResponseStatusCode(responseEEEEE, 200,FailureHandling.CONTINUE_ON_FAILURE)


//부서수신담당자 수신함 문서 확인
'부서수신담당자'
WS.sendRequest(LoginA)

responseA = WS.sendRequestAndVerify(LoginA)

def TokenA = WS.getElementPropertyValue(responseA, getToken)

ArrayList HTTPHeaderA = new ArrayList()

HTTPHeaderA.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenA))

HTTPHeaderA.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject HEdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/부서문서함/부서수신 문서 조회')

HEdraft.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(HEdraft)

responseAA = WS.sendRequestAndVerify(HEdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseAA, 200)

'부서협조함의 문서번호 변수지정'
def hedocid = WS.getElementPropertyValue(responseAA, awGetdocid)
 
'부서협조함의 기안자 변수지정'
def heid = WS.getElementPropertyValue(responseAA, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,orgdocid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 기안작성	 
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/내부결재 기안작성(기안1결재3)', [('원문서번호') : orgdocid, ('결재선유형') : 'RD',('문서타입') : 'DR', ('결재타입1') : 'AP', ('결재상태1') : 'AW'
        , ('결재타입2') : 'AP', ('결재상태2') : 'AW'])

draft.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(draft)


//내부결재 문서번호 획득
RequestObject mydraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서상신목록 조회')

mydraft.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(mydraft)

responseAnb = WS.sendRequestAndVerify(mydraft)

def docid = WS.getElementPropertyValue(responseAnb, getDocid)


//내부결재에서 상신한에 문서 확인
RequestObject ssdraft = findTestObject('전자결재 api/결재프로세스/기안함/상신한 문서 조회')

ssdraft.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(ssdraft)

responsenb = WS.sendRequestAndVerify(ssdraft)

'상신한에 있는 기안 문서번호 변수지정'
def ssdocidA = WS.getElementPropertyValue(responsenb, ssgetdocid)

WS.verifyResponseStatusCode(responsenb, 200)

WS.verifyEqual(ssdocidA, docid, FailureHandling.CONTINUE_ON_FAILURE)


//원문서가 내부기안자 부서수신함에 있는지 확인
'200코드 확인'
WS.verifyResponseStatusCode(responseAA, 200)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,orgdocid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재문서에 회수버튼 확인
RequestObject orgbtndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DD'])

orgbtndraft.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(orgbtndraft)

responseorgbtn = WS.sendRequestAndVerify(orgbtndraft)

'회수버튼 변수지정'
def retrieve1 = WS.getElementPropertyValue(responseorgbtn, getBtn1)

'인쇄버튼 변수지정'
def print1 = WS.getElementPropertyValue(responseorgbtn, getBtn2)

WS.verifyResponseStatusCode(responseorgbtn, 200)

WS.verifyMatch(retrieve1, "retrieve", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(print1, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//원문서 기안자 완료된에 문서 확인
'완료된에 기안 확인'
RequestObject orgfndraft = findTestObject('Object Repository/전자결재 api/결재프로세스/기안함/완료된 문서 조회')

orgfndraft.setHttpHeaderProperties(HTTPHeaderD)

WS.sendRequest(orgfndraft)

responseorgfn = WS.sendRequestAndVerify(orgfndraft)

'완료된에 있는 기안 문서번호 변수지정'
def orgfndocid = WS.getElementPropertyValue(responseorgfn, ssgetdocid)

WS.verifyResponseStatusCode(responseorgfn, 200)

WS.verifyEqual(orgfndocid, orgdocid, FailureHandling.CONTINUE_ON_FAILURE)


//원문서에서 완료된 문서 버튼 구성 확인
RequestObject orgfnbtnD = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : orgdocid, ('문서함') : 'DC'])

orgfnbtnD.setHttpHeaderProperties(HTTPHeaderD)

WS.sendRequest(orgfnbtnD)

responseorgfnbtnD = WS.sendRequestAndVerify(orgfnbtnD)

'공람설정 버튼 변수지정'
def shareOrgD = WS.getElementPropertyValue(responseorgfnbtnD, getBtn1)

'인쇄 버튼 변수지정'
def printOrgD = WS.getElementPropertyValue(responseorgfnbtnD, getBtn2)

WS.verifyResponseStatusCode(responseorgfnbtnD, 200)

WS.verifyMatch(shareOrgD, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printOrgD, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//원문서 결재자 완료된에 문서 확인
'완료된에 기안 확인'
RequestObject orgfndraftE = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/완료된 문서 조회', [('결재구분') : 'AP'])

orgfndraftE.setHttpHeaderProperties(HTTPHeaderE)

WS.sendRequest(orgfndraftE)

responseorgfnE = WS.sendRequestAndVerify(orgfndraftE)

'완료된에 있는 기안 문서번호 변수지정'
def orgfndocidE = WS.getElementPropertyValue(responseorgfnE, ssgetdocid)

WS.verifyResponseStatusCode(responseorgfnE, 200)

WS.verifyEqual(orgfndocidE, orgdocid, FailureHandling.CONTINUE_ON_FAILURE)


//원문서에서 완료된 문서 버튼 구성 확인
RequestObject orgfnbtnE = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : orgdocid, ('문서함') : 'AC'])

orgfnbtnE.setHttpHeaderProperties(HTTPHeaderE)

WS.sendRequest(orgfnbtnE)

responseorgfnbtnE = WS.sendRequestAndVerify(orgfnbtnE)

'공람설정 버튼 변수지정'
def shareOrgE = WS.getElementPropertyValue(responseorgfnbtnE, getBtn1)

'인쇄 버튼 변수지정'
def printOrgE = WS.getElementPropertyValue(responseorgfnbtnE, getBtn2)

WS.verifyResponseStatusCode(responseorgfnbtnE, 200)

WS.verifyMatch(shareOrgE, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printOrgE, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재자C2 결재전에 문서 확인( C1 : 기안자, C2 : 1차결재자, C3 : 2차결재자)
'결재자B 인증토큰 발급'
WS.sendRequest(LoginB)

responseB = WS.sendRequestAndVerify(LoginB)

def TokenB = WS.getElementPropertyValue(responseB, getToken)

ArrayList HTTPHeaderB = new ArrayList()

HTTPHeaderB.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenB))

HTTPHeaderB.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject nbBdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

nbBdraft.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(nbBdraft)

responseBB = WS.sendRequestAndVerify(nbBdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseBB, 200)


'결재전의 문서번호 변수지정'
def APdocid = WS.getElementPropertyValue(responseBB, awGetdocid)
 
'결재전의 기안자 변수지정'
def APid = WS.getElementPropertyValue(responseBB, awGetname)

'문서번호 매칭 확인'
WS.verifyEqual(APdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(APid, "심민선", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 1차결재자B 결재(승인,반려 = approval1)버튼,상위결재선 변경 버튼(upApprLine),공람설정(share)버튼, 인쇄(print)버튼 존재 확인

RequestObject btndraftB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

btndraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(btndraftB)

responseBBB = WS.sendRequestAndVerify(btndraftB)

'결재 버튼 변수지정'
def approval1B = WS.getElementPropertyValue(responseBBB, getBtn1)

'상위결재선 버튼 변수지정'
def upApprLineB = WS.getElementPropertyValue(responseBBB, getBtn2)

'공람설정 버튼 변수지정'
def shareB = WS.getElementPropertyValue(responseBBB, getBtn3)

'인쇄버튼 변수지정'
def printB = WS.getElementPropertyValue(responseBBB, getBtn4)

WS.verifyResponseStatusCode(responseBBB, 200)

WS.verifyMatch(approval1B, "approval1", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(upApprLineB, "upApprLine", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(shareB, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printB, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 1차결재자B 승인버튼 선택

RequestObject apprlinedraftB = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(apprlinedraftB)

responseBBBB = WS.sendRequestAndVerify(apprlinedraftB)

'결재선번호변수 지정'
def appline1B = WS.getElementPropertyValue(responseBBBB, Getappline1,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject confirmbtn2 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : appline1B, ('문서번호') : docid])

confirmbtn2.setHttpHeaderProperties(HTTPHeaderB)

response9B=WS.sendRequest(confirmbtn2)

'200코드 확인'
WS.verifyResponseStatusCode(response9B, 200,FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 기안자A 에서 상신한에 문서 확인
WS.sendRequest(ssdraft)

WS.verifyEqual(ssdocidA, docid, FailureHandling.CONTINUE_ON_FAILURE)

//내부결재 기안자A 원문서가 수신함에 있는지 확인
'200코드 확인'
WS.verifyResponseStatusCode(responseAA, 200)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,orgdocid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 결재자 B 진행중에 문서 확인
RequestObject awingdraftB = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/진행중 문서 조회',[('결재구분') : 'AP'])

awingdraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(awingdraftB)

responseBBBBB = WS.sendRequestAndVerify(awingdraftB)

'200코드 확인'
WS.verifyResponseStatusCode(responseBBBBB, 200)

'결재중의 문서번호 변수지정'
def awingdocid = WS.getElementPropertyValue(responseBBBBB, awGetdocid)
 
'결재중의 기안자 변수지정'
def awingid = WS.getElementPropertyValue(responseBBBBB, awGetid)

'문서번호 매칭 확인'
WS.verifyEqual(awingdocid,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(awingid, "sunny.sim", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 결재자B 결재취소(cancel) 결재의견(changeOpinion), 인쇄버튼 존재 확인
RequestObject btndraftBB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AI'])

btndraftBB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(btndraftBB)

responseB1 = WS.sendRequestAndVerify(btndraftBB)

'결재취소 버튼 변수지정'
def cancelB = WS.getElementPropertyValue(responseB1, getBtn1)

'결재의견 버튼 변수지정'
def changeOpinionB = WS.getElementPropertyValue(responseB1, getBtn2)

'인쇄버튼 변수지정'
def printBB = WS.getElementPropertyValue(responseB1, getBtn3)

WS.verifyResponseStatusCode(responseB1, 200)

WS.verifyMatch(cancelB, "cancel", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(changeOpinionB, "changeOpinion", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printBB, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재자C3 결재전에 문서 확인( C1 : 기안자, C2 : 1차결재자, C3 : 2차결재자)
'결재자C 인증토큰 발급'
WS.sendRequest(LoginC)

responseC = WS.sendRequestAndVerify(LoginC)

def TokenC = WS.getElementPropertyValue(responseC, getToken)

ArrayList HTTPHeaderC = new ArrayList()

HTTPHeaderC.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + TokenC))

HTTPHeaderC.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

RequestObject nbCdraft= findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/결재전 문서 조회',[('결재구분') : 'AP'])

nbCdraft.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(nbCdraft)

responseCC = WS.sendRequestAndVerify(nbCdraft)

'200코드 확인'
WS.verifyResponseStatusCode(responseCC, 200)

'결재전의 문서번호 변수지정'
def APdocidC = WS.getElementPropertyValue(responseCC, awGetdocid)
 
'결재전의 기안자 변수지정'
def APidC = WS.getElementPropertyValue(responseCC, awGetname)

'문서번호 매칭 확인'
WS.verifyEqual(APdocidC,docid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(APidC, "심민선", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 2차결재자C 결재(승인,반려,전단계반려 = approval2)버튼,공람설정(share)버튼, 인쇄(print)버튼 존재 확인

RequestObject btndraftC = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AB'])

btndraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(btndraftC)

responseCCC = WS.sendRequestAndVerify(btndraftC)

'결재 버튼 변수지정'
def approval2C = WS.getElementPropertyValue(responseCCC, getBtn1)

'공람설정 버튼 변수지정'
def shareC = WS.getElementPropertyValue(responseCCC, getBtn2)

'인쇄버튼 변수지정'
def printC = WS.getElementPropertyValue(responseCCC, getBtn3)

WS.verifyResponseStatusCode(responseCCC, 200)

WS.verifyMatch(approval2C, "approval2", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(shareC, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printC, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 2차결재자C 승인버튼 선택

RequestObject apprlinedraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/조회 시리즈/문서전체결재선 조회',[('문서번호') : docid])

apprlinedraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(apprlinedraftC)

responseCCCC = WS.sendRequestAndVerify(apprlinedraftC)

'결재선번호변수 지정'
def appline1C = WS.getElementPropertyValue(responseCCCC, Getappline2,FailureHandling.CONTINUE_ON_FAILURE)

'결재승인 버튼누르기'
RequestObject confirmbtn3 = findTestObject('Object Repository/전자결재 api/결재프로세스/결재 버튼/승인 버튼',[('결재선번호') : appline1C, ('문서번호') : docid])

confirmbtn3.setHttpHeaderProperties(HTTPHeaderC)

response9C=WS.sendRequest(confirmbtn3)

'200코드 확인'
WS.verifyResponseStatusCode(response9C, 200,FailureHandling.CONTINUE_ON_FAILURE)


//원본기안자D 완료된에 문서 확인
WS.sendRequest(orgfndraft)

WS.verifyResponseStatusCode(responseorgfn, 200)

WS.verifyEqual(orgfndocid, orgdocid, FailureHandling.CONTINUE_ON_FAILURE)


//원문서에서 기안자D 완료된 문서 버튼 구성 확인
WS.verifyResponseStatusCode(responseorgfnbtnD, 200)

WS.verifyMatch(shareOrgD, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printOrgD, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//원문서 결재자E 완료된에 문서 확인
WS.sendRequest(orgfndraftE)

WS.verifyResponseStatusCode(responseorgfnE, 200)

WS.verifyEqual(orgfndocidE, orgdocid, FailureHandling.CONTINUE_ON_FAILURE)


//원문서에서 결재자E 완료된 문서 버튼 구성 확인
WS.verifyResponseStatusCode(responseorgfnbtnE, 200)

WS.verifyMatch(shareOrgE, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printOrgE, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//원문서가 내부기안자A 부서수신함에 있는지 확인
'200코드 확인'
WS.verifyResponseStatusCode(responseAA, 200)

'문서번호 매칭 확인'
WS.verifyEqual(hedocid,orgdocid,FailureHandling.CONTINUE_ON_FAILURE)

'기안자 아이디 매칭 확인'
WS.verifyMatch(heid, "peter.bo", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 문서가 기안자A 완료된에 있는지 확인
'완료된에 기안 확인'
RequestObject fndraft = findTestObject('Object Repository/전자결재 api/결재프로세스/기안함/완료된 문서 조회')

fndraft.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(fndraft)

responsefn = WS.sendRequestAndVerify(fndraft)

'완료된에 있는 기안 문서번호 변수지정'
def fndocid = WS.getElementPropertyValue(responsefn, ssgetdocid)

WS.verifyResponseStatusCode(responsefn, 200)

WS.verifyEqual(fndocid, docid, FailureHandling.CONTINUE_ON_FAILURE)

//내부결재 문서가 결재자B 완료된에 있는지 확인
'완료된에 기안 확인'
RequestObject fndraftB = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/완료된 문서 조회', [('결재구분') : 'AP'])

fndraftB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(fndraftB)

responsefnB = WS.sendRequestAndVerify(fndraftB)

'완료된에 있는 기안 문서번호 변수지정'
def fndocidB = WS.getElementPropertyValue(responsefnB, ssgetdocid)

WS.verifyResponseStatusCode(responsefnB, 200)

WS.verifyEqual(fndocidB, docid, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재 문서가 결재자C 완료된에 있는지 확인
'완료된에 기안 확인'
RequestObject fndraftC = findTestObject('Object Repository/전자결재 api/결재프로세스/결재함/완료된 문서 조회', [('결재구분') : 'AP'])

fndraftC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(fndraftC)

responsefnC = WS.sendRequestAndVerify(fndraftC)

'완료된에 있는 기안 문서번호 변수지정'
def fndocidC = WS.getElementPropertyValue(responsefnC, ssgetdocid)

WS.verifyResponseStatusCode(responsefnC, 200)

WS.verifyEqual(fndocidC, docid, FailureHandling.CONTINUE_ON_FAILURE)


//내부기안자A가 완료된에서 공람설정,인쇄 버튼 구성 확인
RequestObject fnbtnA = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'DC'])

fnbtnA.setHttpHeaderProperties(HTTPHeaderA)

WS.sendRequest(fnbtnA)

responsefnbtnA = WS.sendRequestAndVerify(fnbtnA)

'공람설정 버튼 변수지정'
def sharefnA = WS.getElementPropertyValue(responsefnbtnA, getBtn1)

'인쇄 버튼 변수지정'
def printfnA = WS.getElementPropertyValue(responsefnbtnA, getBtn2)

WS.verifyResponseStatusCode(responsefnbtnA, 200)

WS.verifyMatch(sharefnA, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printfnA, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재자B가 완료된에서 공람설정,인쇄 버튼 구성 확인
RequestObject fnbtnB = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AC'])

fnbtnB.setHttpHeaderProperties(HTTPHeaderB)

WS.sendRequest(fnbtnB)

responsefnbtnB = WS.sendRequestAndVerify(fnbtnB)

'공람설정 버튼 변수지정'
def sharefnB = WS.getElementPropertyValue(responsefnbtnB, getBtn1)

'인쇄 버튼 변수지정'
def printfnB = WS.getElementPropertyValue(responsefnbtnB, getBtn2)

WS.verifyResponseStatusCode(responsefnbtnB, 200)

WS.verifyMatch(sharefnB, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printfnB, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//내부결재자C가 완료된에서 공람설정,인쇄 버튼 구성 확인
RequestObject fnbtnC = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회',[('문서번호') : docid, ('문서함') : 'AC'])

fnbtnC.setHttpHeaderProperties(HTTPHeaderC)

WS.sendRequest(fnbtnC)

responsefnbtnC = WS.sendRequestAndVerify(fnbtnC)

'공람설정 버튼 변수지정'
def sharefC = WS.getElementPropertyValue(responsefnbtnC, getBtn1)

'인쇄 버튼 변수지정'
def printfnC = WS.getElementPropertyValue(responsefnbtnC, getBtn2)

WS.verifyResponseStatusCode(responsefnbtnC, 200)

WS.verifyMatch(sharefC, "share", true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printfnC, "print", true, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 내부결재문서 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제',[('문서번호') : docid])
delete.setHttpHeaderProperties(HTTPHeaderA)

response999=WS.sendRequest(delete)

//response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)

//올렸던 원문서 기안삭제

RequestObject deleteE = findTestObject('전자결재 api/기안삭제',[('문서번호') : orgdocid])
deleteE.setHttpHeaderProperties(HTTPHeaderE)

response9999=WS.sendRequest(deleteE)

//response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response9999, 200)

