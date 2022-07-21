<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>주문서생성_멀티</name>
   <tag></tag>
   <elementGuidId>5fd1ac4a-ca8f-465b-9ee6-12b80e050b08</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;products\&quot;: [\n    {\n      \&quot;productNo\&quot;: 102361194,\n      \&quot;optionNo\&quot;: 38223905,\n      \&quot;orderCnt\&quot;: 10,\n      \&quot;optionInputs\&quot;: []\n    ,\n      \&quot;channelType\&quot;: \&quot;NAVER_EP\&quot;,\n      \&quot;recurringPaymentDelivery\&quot;: {\n        \&quot;cycleType\&quot;: \&quot;MONTH\&quot;,\n        \&quot;cycle\&quot;: 1,\n        \&quot;date\&quot;: 0\n      }\n    },\n        {\n      \&quot;productNo\&quot;: 102358377,\n      \&quot;optionNo\&quot;: 38192292,\n      \&quot;orderCnt\&quot;: 10,\n      \&quot;optionInputs\&quot;: []\n    ,\n      \&quot;channelType\&quot;: \&quot;NAVER_EP\&quot;,\n      \&quot;recurringPaymentDelivery\&quot;: {\n        \&quot;cycleType\&quot;: \&quot;MONTH\&quot;,\n        \&quot;cycle\&quot;: 1,\n        \&quot;date\&quot;: 0\n      }\n    },\n    {\n      \&quot;productNo\&quot;: 102361194,\n      \&quot;optionNo\&quot;: 38223906,\n      \&quot;orderCnt\&quot;: 10,\n      \&quot;optionInputs\&quot;: []\n    ,\n      \&quot;channelType\&quot;: \&quot;NAVER_EP\&quot;,\n      \&quot;recurringPaymentDelivery\&quot;: {\n        \&quot;cycleType\&quot;: \&quot;MONTH\&quot;,\n        \&quot;cycle\&quot;: 1,\n        \&quot;date\&quot;: 0\n      }\n    }\n  ],\n  \&quot;productCoupons\&quot;: [],\n  \&quot;cartNos\&quot;: [],\n  \&quot;trackingKey\&quot;: \&quot;\&quot;,\n  \&quot;channelType\&quot;: \&quot;NAVER_EP\&quot;\n}&quot;,
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
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>apiKey</name>
      <type>Main</type>
      <value>live123</value>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://dev-api.bithumblive.com/v2/order</restUrl>
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
