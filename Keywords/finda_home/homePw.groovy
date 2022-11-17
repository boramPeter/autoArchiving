package finda_home

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows


import internal.GlobalVariable

public class homePw {
	@Keyword
	
	//초기화면에서 PW입력하는 코드 
	public void pw(){		
		//첫번째 자리 체크 후 입력
		for (def num = 1; num <= 11; num++) {
				String a = null
				String b = null
				
				//xpath 변수 선언 
				if ((num >= 1) && (num <= 3)) {
					a = 1
					b = num
				}
				
				if ((num >= 4) && (num <= 6)) {
					a = 2
					b = (num - 3)
				}
				
				if ((num >= 7) && (num <= 9)) {
					a = 3
					b = (num - 6)
				}
				
				if ((num >= 10) && (num <= 12)) {
					a = 4
					b = (num - 9)
				}
				
				def pw = Mobile.getText(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
				if (pw == '1') {
				
					Mobile.tap(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					break;
					}
			}
		
		//두번째 자리 체크 후 입력
		for (def num = 1; num <= 11; num++) {
				String a = null
				String b = null
					
				if ((num >= 1) && (num <= 3)) {
					a = 1
					b = num
				}
					
				if ((num >= 4) && (num <= 6)) {
					a = 2
					b = (num - 3)
				}
					
				if ((num >= 7) && (num <= 9)) {
					a = 3
					b = (num - 6)
				}
				
				if ((num >= 10) && (num <= 12)) {
					a = 4
					b = (num - 9)
				}
					
				String pw = Mobile.getText(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					
				if (pw == '2') {					
					Mobile.tap(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					break;
					}
		}
			
		//세번째 자리 체크 후 입력
		for (def num = 1; num <= 11; num++) {
				String a = null
				String b = null
					
				if ((num >= 1) && (num <= 3)) {
					a = 1
					b = num
				}
					
				if ((num >= 4) && (num <= 6)) {
					a = 2
					b = (num - 3)
				}
					
				if ((num >= 7) && (num <= 9)) {
					a = 3
					b = (num - 6)
				}
				
				if ((num >= 10) && (num <= 12)) {
					a = 4
					b = (num - 9)
				}
					
				String pw = Mobile.getText(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					
				if (pw == '3') {	
					Mobile.tap(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					break;
					}
		}
			
		//네번째 자리 체크 후 입력
		for (def num = 1; num <= 11; num++) {
				String a = null
				String b = null
				
				if ((num >= 1) && (num <= 3)) {
					a = 1
					b = num
				}
					
				if ((num >= 4) && (num <= 6)) {
					a = 2
					b = (num - 3)
				}
					
				if ((num >= 7) && (num <= 9)) {
					a = 3
					b = (num - 6)
				}
				
				if ((num >= 10) && (num <= 12)) {
					a = 4
					b = (num - 9)
				}
					
				String pw = Mobile.getText(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					
				if (pw == '4') {
					Mobile.tap(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					break;
					}
		}
			
		//다섯번째 자리 체크 후 입력
		for (def num = 1; num <= 11; num++) {
				String a = null
				String b = null
					
				if ((num >= 1) && (num <= 3)) {
					a = 1
					b = num
				}
					
				if ((num >= 4) && (num <= 6)) {
					a = 2
					b = (num - 3)
				}
					
				if ((num >= 7) && (num <= 9)) {
					a = 3
					b = (num - 6)
				}
				
				if ((num >= 10) && (num <= 12)) {
					a = 4
					b = (num - 9)
				}
					
				String pw = Mobile.getText(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
				
				if (pw == '5') {		
					Mobile.tap(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					break;
					}
		}
			
		//여섯번째 자리 체크 후 입력
		for (def num = 1; num <= 11; num++) {
				String a = null
				String b = null
					
				if ((num >= 1) && (num <= 3)) {
					a = 1
					b = num
				}
					
				if ((num >= 4) && (num <= 6)) {
					a = 2
					b = (num - 3)
				}
					
				if ((num >= 7) && (num <= 9)) {
					a = 3
					b = (num - 6)
				}
				
				if ((num >= 10) && (num <= 12)) {
					a = 4
					b = (num - 9)
				}
					
				String pw = Mobile.getText(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					
				if (pw == '6') {
					Mobile.tap(findTestObject('Object Repository/finda/pw/pw', [('a') : a, ('b') : b]), 0)
					break;
					}
		}
			
}
}
