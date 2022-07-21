import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable


try {
	CustomKeywords.'전자결재_PC.home.deleAppr'()
	
    GlobalVariable.tc005 = '> 5. 대리결재자 설정 테스트 결과 :  *Pass* \n'
}
catch (def StepFailedException) {
    GlobalVariable.tc005 = '> 5. 대리결재자 설정 테스트 결과 :  *Fail* \n'

    KeywordUtil.markFailed('실패했습니다.')
} 

