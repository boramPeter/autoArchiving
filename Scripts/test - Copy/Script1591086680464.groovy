import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException

//for (def row = 1; row <= findTestData('조명봇').getRowNumbers(); row++) {
def utterance = findTestObject('코멕스봇/a_test')

def utterance2 = findTestObject('코멕스봇/b_test')

//def expected = findTestData('test').getValue(2, 1)
def speech = 'result.messages[0].text'

def intentName = 'gdm.log.intent.name'

response2 = WS.sendRequestAndVerify(findTestObject('코멕스봇/b_test'))

def speech_text = WS.getElementPropertyValue(response2, speech)

def intentName_text = WS.getElementPropertyValue(response2, intentName)

WS.sendRequest(utterance)

response1 = WS.sendRequestAndVerify(utterance)

WS.verifyElementPropertyValue(response1, speech, '어떤 조명이요?')

WS.sendRequest(findTestObject('코멕스봇/b_test'))

WS.verifyElementPropertyValue(WS.sendRequestAndVerify(findTestObject('코멕스봇/b_test')), 'result.speech', '취소했어', FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(intentName_text, 'lightTurnOn', false)

WS.getElementText(findTestObject(null), '')

///////12312313
12312313
