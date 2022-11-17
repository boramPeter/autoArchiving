<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>일반회원가입</name>
   <tag></tag>
   <elementGuidId>77c8687f-ef48-4228-95bd-57d3e8b58ac9</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;userId\&quot;: \&quot;${유저아이디}\&quot;,\n    \&quot;email\&quot;: \&quot;${이메일}\&quot;,\n    \&quot;name\&quot;: \&quot;${이름}\&quot;,\n    \&quot;nickname\&quot;: \&quot;${닉네임}\&quot;,\n    \&quot;pwd\&quot;: \&quot;cip1977\&quot;,\n    \&quot;birthday\&quot;: \&quot;${생년월일}\&quot;,\n    \&quot;sex\&quot;: \&quot;${성별}\&quot;,\n    \&quot;tel\&quot;: \&quot;031-111-1111\&quot;,\n    \&quot;mobile\&quot;: \&quot;${연락처}\&quot;,\n    \&quot;zip\&quot;: \&quot;12345\&quot;,\n    \&quot;addr\&quot;: \&quot;빗썸라이브\&quot;,\n    \&quot;addr2\&quot;: \&quot;이니셜타워\&quot;,\n    \&quot;dispYn\&quot;: true,\n    \&quot;foreignerYn\&quot;: false,\n    \&quot;countryCode\&quot;: \&quot;KR\&quot;,\n    \&quot;authYn\&quot;: true,\n    \&quot;serviceAgreeYn\&quot;: true,\n    \&quot;mobileAuthAgreeYn\&quot;: true,\n    \&quot;marketingAgreeYn\&quot;: true,\n    \&quot;ci\&quot;: \&quot;${CI}\&quot;\n}&quot;,
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
      <webElementGuid>0f12cb2a-c5ae-4f2e-b278-aae9aafa08a3</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>apiKey</name>
      <type>Main</type>
      <value>live123</value>
      <webElementGuid>3023b129-3fa7-4ce4-bb23-9ce34f434fa1</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://dev-auth.bithumblive.com:443/auth/join</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
