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
RequestObject orgdraft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성(기안1결재1부서수신1)', [('결재선유형') : 'DD', ('결재타입2') : 'AP', ('결재상태2') : 'AW'])

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
RequestObject ssdraft = findTestObject('전자결재 api/결재프로세스/기안함/상신한 문서 조회')

ssdraft.setHttpHeaderProperties(HTTPHeaderD)

WS.sendRequest(ssdraft)

responseorgss = WS.sendRequestAndVerify(ssdraft)

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

