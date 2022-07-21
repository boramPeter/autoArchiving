import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable as GlobalVariable

def resultText = GlobalVariable.tc001 + GlobalVariable.tc002+GlobalVariable.tc003+GlobalVariable.tc004+GlobalVariable.tc005+GlobalVariable.tc006+GlobalVariable.tc007+GlobalVariable.tc008+GlobalVariable.tc009+GlobalVariable.tc010+GlobalVariable.tc011+GlobalVariable.tc012+GlobalVariable.tc013+GlobalVariable.tc014+GlobalVariable.tc015+GlobalVariable.tc016+GlobalVariable.tc017+GlobalVariable.tc018+GlobalVariable.tc019+GlobalVariable.tc020+GlobalVariable.tc021+GlobalVariable.tc022+GlobalVariable.tc023+GlobalVariable.tc024+GlobalVariable.tc025+GlobalVariable.tc027+GlobalVariable.tc028+GlobalVariable.tc029
def resultNo = resultText.count(' *Pass* ')

String percent = Math.round((resultNo / 28) * 100)

result = (('\n *-Pass rate* : ' + percent) + '%'+ " (Pass "+resultNo+"건 / 총 28건)")

cl = "\n\n N/A건은 정책변경중인 내용이라 매뉴얼테스트로 수행 예정입니다. 자세한 내용은 [기본기능 CheckList](http://kko.to/NYqCAcADT)를 참고해주세요."

cc = "\n\n cc @@kep.qa"

String title = '#PC자동화 \n # PC 기본기능 Checklist 자동화 결과 예시'

def desc =  GlobalVariable.tc001 + GlobalVariable.tc002+GlobalVariable.tc003+GlobalVariable.tc004+GlobalVariable.tc005+GlobalVariable.tc006+GlobalVariable.tc007+GlobalVariable.tc008+GlobalVariable.tc009+GlobalVariable.tc010+GlobalVariable.tc011+GlobalVariable.tc012+GlobalVariable.tc013+GlobalVariable.tc014+GlobalVariable.tc015+GlobalVariable.tc016+GlobalVariable.tc017+GlobalVariable.tc018+GlobalVariable.tc019+GlobalVariable.tc020+GlobalVariable.tc021+GlobalVariable.tc022+GlobalVariable.tc023+GlobalVariable.tc024+GlobalVariable.tc025+GlobalVariable.tc026+GlobalVariable.tc027+GlobalVariable.tc028+GlobalVariable.tc029 + result + cl + cc

def AT = WS.sendRequest(findTestObject('Object Repository/전자결재 api test/api', [('title') : title, ('desc') : desc]))

WS.verifyResponseStatusCode(AT, 200)