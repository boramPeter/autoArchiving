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

for (def row = 1; row <= findTestData('코멕스봇(그외)/공기청정기봇').getRowNumbers(); row++) {
    def utterance = findTestObject('코멕스봇/Shadow api', [('text') : findTestData('코멕스봇(그외)/공기청정기봇').getValue(1, row)])

	def recognizer = findTestData('코멕스봇(그외)/공기청정기봇').getValue(1, row)
	
    def expected = findTestData('코멕스봇(그외)/공기청정기봇').getValue(2, row)

    def intentName_expected = findTestData('코멕스봇(그외)/공기청정기봇').getValue(3, row)

    def speech = 'result.messages[0].text'

    def intentName = 'gdm.log.intent.name'

    WS.sendRequest(utterance)

    response = WS.sendRequestAndVerify(utterance)

    def speech_text = WS.getElementPropertyValue(response, speech)

    def intentName_text = WS.getElementPropertyValue(response, intentName)

    if (WS.verifyMatch('-', expected, true, FailureHandling.OPTIONAL) == false) {
		if (WS.verifyMatch(speech_text, expected, true, FailureHandling.CONTINUE_ON_FAILURE) == false) {
        value = expected

        KeywordLogger log = new KeywordLogger()

        log.logInfo(value)    
		}
    
    if (WS.verifyMatch(intentName_text, intentName_expected, true, FailureHandling.CONTINUE_ON_FAILURE) == false) {
        value1 = recognizer
		KeywordLogger log = new KeywordLogger()
		log.logInfo(value1)
    }
	/*if (WS.verifyMatch(speech_text, expected, true, FailureHandling.CONTINUE_ON_FAILURE) == false) {
		value = expected

		KeywordLogger log = new KeywordLogger()

		log.logInfo(value)*/
    }
    
    WS.delay(1)
}

