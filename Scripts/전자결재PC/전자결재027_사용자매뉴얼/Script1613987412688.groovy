import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable


try {
	CustomKeywords.'전자결재_PC.LNB_userGuidelines.userManual'()
	
    GlobalVariable.tc027 = '> 27. 이용안내_사용자매뉴얼 테스트 결과 :  *Pass* \n'
}
catch (def StepFailedException) {
    GlobalVariable.tc027 = '> 27. 이용안내_사용자매뉴얼 테스트 결과 :  *Fail* \n'

    KeywordUtil.markFailed('실패했습니다.')
} 

