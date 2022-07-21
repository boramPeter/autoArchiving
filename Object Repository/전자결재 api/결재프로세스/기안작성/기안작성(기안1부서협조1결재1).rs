<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>기안작성(기안1부서협조1결재1)</name>
   <tag></tag>
   <elementGuidId>1a20fdd5-e16e-40e8-9e29-aeb548e0b49d</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;document\&quot;: {\n    \&quot;formId\&quot;: 1,\n    \&quot;keepYear\&quot;: 1,\n    \&quot;openType\&quot;: \&quot;P\&quot;,\n    \&quot;draftEmpId\&quot;: 9986,\n    \&quot;draftEmpName\&quot;: \&quot;김보람\&quot;,\n    \&quot;draftAccountId\&quot;: \&quot;peter.bo\&quot;,\n    \&quot;draftPositionName\&quot;: \&quot;직위1\&quot;,\n    \&quot;draftDutyName\&quot;: \&quot;직책1\&quot;,\n    \&quot;draftDeptId\&quot;: 5,\n    \&quot;draftDeptName\&quot;: \&quot;서비스품질파트\&quot;,\n    \&quot;docSubject\&quot;: \&quot;문서 권한 테스트2\&quot;,\n    \&quot;attachFileIdList\&quot;: [8,9,10],\n    \&quot;referenceDocIdList\&quot;: [50,65],\n\t\&quot;bodyInputUrl\&quot;: \&quot;http://alpha-api1-kage.kakao.com/dn/dSbrK9/ZSfysXma7L/Foc4ChQujKCA6NdGQYRTqK/PDF+%281%29.pdf?attach\u003d1\u0026knm\u003ditem.pdf\&quot;,\n    \&quot;bodyViewUrl\&quot;: \&quot;http://alpha-api1-kage.kakao.com/dn/dSbrK9/ZSfysXma7L/Foc4ChQujKCA6NdGQYRTqK/PDF+%281%29.pdf?attach\u003d1\u0026knm\u003ditem.pdf\&quot;,    \&quot;bodyData\&quot;: \&quot;{\\\&quot;name\\\&quot;: \\\&quot;value\\\&quot;}\&quot;,\n    \&quot;apprLineType\&quot;: \&quot;${결재선유형}\&quot;,\n    \&quot;nextApproverEmpId\&quot;: 9996,\n    \&quot;nextApproverEmpName\&quot;: \&quot;박남순\&quot;,\n    \&quot;nextApproverAccountId\&quot;: \&quot;jenny.park\&quot;,\n    \&quot;nextApproverPositionName\&quot;: \&quot;직위1\&quot;,\n    \&quot;nextApproverDutyName\&quot;: \&quot;직책1\&quot;,\n    \&quot;nextApproverDeptName\&quot;: \&quot;카카오엔터프라이즈\&quot;\n  },\n  \&quot;approvalLine\&quot;: [\n    {\n      \&quot;apprType\&quot;: \&quot;DR\&quot;,\n      \&quot;apprStatus\&quot;: \&quot;AD\&quot;,\n      \&quot;approverEmpId\&quot;: 9986,\n      \&quot;approverEmpName\&quot;: \&quot;김보람\&quot;,\n      \&quot;approverAccountId\&quot;: \&quot;peter.bo\&quot;,\n      \&quot;approverPositionName\&quot;: \&quot;직위1\&quot;,\n      \&quot;approverDutyName\&quot;: \&quot;직책1\&quot;,\n      \&quot;approverDeptId\&quot;: 5,\n      \&quot;approverDeptName\&quot;: \&quot;서비스품질파트\&quot;,\n      \&quot;apprOrder\&quot;: 1,\n      \&quot;currApproverYn\&quot;: \&quot;N\&quot;,\n      \&quot;lastApproverYn\&quot;: \&quot;N\&quot;\n    },\n    {\n      \&quot;apprType\&quot;: \&quot;HD\&quot;,\n      \&quot;apprStatus\&quot;: \&quot;EW\&quot;,\n      \&quot;apprDeptId\&quot;: 1,\n      \&quot;apprDeptName\&quot;: \&quot;카카오엔터프라이즈\&quot;,\n      \&quot;apprOrder\&quot;: 2,\n      \&quot;currApproverYn\&quot;: \&quot;Y\&quot;,\n      \&quot;lastApproverYn\&quot;: \&quot;N\&quot;\n    },\n    {\n      \&quot;apprType\&quot;: \&quot;${결재타입2}\&quot;,\n      \&quot;apprStatus\&quot;: \&quot;${결재상태2}\&quot;,\n      \&quot;approverEmpId\&quot;: 9996,\n      \&quot;approverEmpName\&quot;: \&quot;박남순\&quot;,\n      \&quot;approverAccountId\&quot;: \&quot;jenny.park\&quot;,\n      \&quot;approverPositionName\&quot;: \&quot;직위1\&quot;,\n      \&quot;approverDutyName\&quot;: \&quot;직책1\&quot;,\n      \&quot;approverDeptId\&quot;: 1,\n      \&quot;approverDeptName\&quot;: \&quot;카카오엔터프라이즈\&quot;,\n      \&quot;apprOrder\&quot;: 3,\n      \&quot;currApproverYn\&quot;: \&quot;N\&quot;,\n      \&quot;lastApproverYn\&quot;: \&quot;Y\&quot;\n    }\n  ],\n  \&quot;docShareList\&quot;: [\n  ]\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>http://eapproval-kep.dev.daumkakao.io/api/1/draft</restUrl>
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
