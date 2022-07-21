import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable


try {
	CustomKeywords.'전자결재_PC.LNB_approvalFolder.toApprove'()
	
    GlobalVariable.tc016 = '> 16. 결재함_결재전 테스트 결과 :  *Pass* \n'
}
catch (def StepFailedException) {
    GlobalVariable.tc016 = '> 16. 결재함_결재전 테스트 결과 :  *Fail* \n'

    KeywordUtil.markFailed('실패했습니다.')
} 

