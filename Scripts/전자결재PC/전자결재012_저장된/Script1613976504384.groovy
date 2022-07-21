import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable


try {
CustomKeywords.'전자결재_PC.LNB_draftFolder.saved'()

    GlobalVariable.tc012 = '> 12. 기안함_저장된 테스트 결과 :  *Pass* \n'
}
catch (def StepFailedException) {
    GlobalVariable.tc012 = '> 12. 기안함_저장된 테스트 결과 :  *Fail* \n'

    KeywordUtil.markFailed('실패했습니다.')
} 

