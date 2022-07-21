<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>기안작성 후 임시저장(기안1결재2참조1공람1)</name>
   <tag></tag>
   <elementGuidId>ab774063-5d4d-4aef-b313-8cd74ca96241</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;document\&quot;: {\n   \&quot;formId\&quot;: 1,\n    \&quot;keepYear\&quot;: 1,\n    \&quot;openType\&quot;: \&quot;P\&quot;,\n    \&quot;draftEmpId\&quot;: 9986,\n    \&quot;draftEmpName\&quot;: \&quot;김보람\&quot;,\n    \&quot;draftAccountId\&quot;: \&quot;peter.bo\&quot;,\n    \&quot;draftPositionName\&quot;: \&quot;직위1\&quot;,\n    \&quot;draftDutyName\&quot;: \&quot;직책1\&quot;,\n    \&quot;draftDeptId\&quot;: 5,\n    \&quot;draftDeptName\&quot;: \&quot;서비스품질파트\&quot;,\n    \&quot;docSubject\&quot;: \&quot;문서 권한 테스트2\&quot;,\n    \&quot;attachFileIdList\&quot;: [8,9,10],\n    \&quot;referenceDocIdList\&quot;: [50,65],\n    \&quot;bodyInputUrl\&quot;: \&quot;http://alpha-api1-kage.kakao.com/dn/dSbrK9/ZSfysXma7L/Foc4ChQujKCA6NdGQYRTqK/PDF+%281%29.pdf?attach\u003d1\u0026knm\u003ditem.pdf\&quot;,\n    \&quot;bodyViewUrl\&quot;: \&quot;http://alpha-api1-kage.kakao.com/dn/dSbrK9/ZSfysXma7L/Foc4ChQujKCA6NdGQYRTqK/PDF+%281%29.pdf?attach\u003d1\u0026knm\u003ditem.pdf\&quot;,\n    \&quot;bodyData\&quot;: \&quot;{\\\&quot;name\\\&quot;: \\\&quot;value\\\&quot;}\&quot;,\n    \&quot;apprLineType\&quot;: \&quot;DD\&quot;,\n    \&quot;nextApproverEmpId\&quot;: 9988,\n    \&quot;nextApproverEmpName\&quot;: \&quot;황유진\&quot;,\n    \&quot;nextApproverAccountId\&quot;: \&quot;ellie.hwang\&quot;,\n    \&quot;nextApproverPositionName\&quot;: \&quot;직위1\&quot;,\n    \&quot;nextApproverDutyName\&quot;: \&quot;직책1\&quot;,\n    \&quot;nextApproverDeptName\&quot;: \&quot;플랫폼개발팀\&quot;\n  \n  }\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>http://eapproval-kep.dev.daumkakao.io/api/1/draft/temp</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
