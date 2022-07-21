import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable


try {
	CustomKeywords.'전자결재_PC.LNB_draftFolder.collected'()
	
    GlobalVariable.tc015 = '> 15. 기안함_회수된 테스트 결과 :  *Pass* \n'
}
catch (def StepFailedException) {
    GlobalVariable.tc015 = '> 15. 기안함_회수된 테스트 결과 :  *Fail* \n'

    KeywordUtil.markFailed('실패했습니다.')
} 

