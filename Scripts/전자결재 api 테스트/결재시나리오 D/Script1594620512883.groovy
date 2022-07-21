import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.junit.After as After
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
def LoginA = findTestObject('전자결재 api/우회로그인', [('아이디') : 'peter.bo'] //기안
    )

def LoginB = findTestObject('전자결재 api/우회로그인', [('아이디') : 'ellie.hwang'] //B결재자
    )

def LoginC = findTestObject('전자결재 api/우회로그인', [('아이디') : 'jenny.park'] //C결재자
    )

def LoginD = findTestObject('전자결재 api/우회로그인', [('아이디') : 'casey.han'] // 참조자
    )

def LoginE = findTestObject('전자결재 api/우회로그인', [('아이디') : 'sio.oh'] //공람자
    )

def getToken = 'result.accessToken'

WS.sendRequest(LoginA)

response = WS.sendRequestAndVerify(LoginA)

'인증토큰 발급'
def Token = WS.getElementPropertyValue(response, getToken)

//기안작성		(기안자 : peter.bo, 결재자 B : lia.jung, 결재자 C : jenny.park, 참조자D : pepper.hoon, 공람E : sio.oh)
RequestObject draft = findTestObject('전자결재 api/결재프로세스/기안작성/기안작성 후 임시저장(기안1결재2참조1공람1)')

ArrayList HTTPHeader = new ArrayList()

HTTPHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer' + Token))

HTTPHeader.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json;charset=UTF-8'))

draft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(draft)

//내가상신한목록 조회 >> 문서번호 따기용도

def getDocid = 'result [0] .docId'

RequestObject mydraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서상신목록 조회')

mydraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(mydraft)

response1 = WS.sendRequestAndVerify(mydraft)

def docid = WS.getElementPropertyValue(response1, getDocid)

//저장된에 문서 확인
'저장된문서의 문서번호 가져오기'
def ssgetdocid = 'result.contents[0].docId'

'저장된에 기안 확인'
RequestObject ssdraft = findTestObject('전자결재 api/결재프로세스/기안함/저장된 문서 조회')

ssdraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(ssdraft)

response0 = WS.sendRequestAndVerify(ssdraft)

'저장된에 있는 기안 문서번호 변수지정'
def ssdocid = WS.getElementPropertyValue(response0, ssgetdocid)

WS.verifyResponseStatusCode(response0, 200)

WS.verifyEqual(ssdocid, docid, FailureHandling.CONTINUE_ON_FAILURE)

//상신,저장,삭제 버튼 존재 확인
'펑션에서 버튼 가져오기'
def getBtn1 = 'result.functions[0]'

def getBtn2 = 'result.functions[1]'

def getBtn3 = 'result.functions[2]'

def getBtn4 = 'result.functions[3]'



RequestObject btndraft = findTestObject('전자결재 api/결재프로세스/조회 시리즈/문서기본정보 조회', [('문서번호') : docid, ('문서함') : 'DT'])

btndraft.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(btndraft)

response2 = WS.sendRequestAndVerify(btndraft)

'삭제 버튼 변수지정'
def deleteBtn = WS.getElementPropertyValue(response2, getBtn1)

'인쇄 버튼 변수지정'
def printBtn = WS.getElementPropertyValue(response2, getBtn2)


WS.verifyResponseStatusCode(response2, 200)

WS.verifyMatch(deleteBtn, 'delete', true, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(printBtn, 'print', true, FailureHandling.CONTINUE_ON_FAILURE)


//올렸던 기안삭제
RequestObject delete = findTestObject('전자결재 api/기안삭제', [('문서번호') : docid])

delete.setHttpHeaderProperties(HTTPHeader)

WS.sendRequest(delete)

response999 = WS.sendRequestAndVerify(delete)

WS.verifyResponseStatusCode(response999, 200)

