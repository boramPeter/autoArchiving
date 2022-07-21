import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as GlobalVariable

try {
	CustomKeywords.'전자결재_PC.home.login'()
		
	GlobalVariable.tc001 = '> 1. 로그인 테스트 결과 :  *Pass* \n'
}

catch (def StepFailedException) {
GlobalVariable.tc001 = '> 1. 로그인 테스트 결과 :  *Fail* \n'

KeywordUtil.markFailed('실패했습니다.')
}