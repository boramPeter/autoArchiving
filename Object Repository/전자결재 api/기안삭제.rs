<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>기안삭제</name>
   <tag></tag>
   <elementGuidId>761a3870-04be-4396-bbe5-0b2f65b50eb5</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1OTQxODYyMDMsInVzZXIiOiJ7XCJjbXBJZFwiOjEsXCJlbXBJZFwiOjk5ODYsXCJkZXB0SWRcIjo1LFwiY29uY3VycmVudERlcHRJZExpc3RcIjpbXSxcImRlcHRIZWFkWW5cIjpcIk5cIixcImF1dGhvcml0eVwiOlwiVVNFUlwifSJ9.qAR8a2_wirHRPMYmZQ5-gAfgJMTCWd1obgSOZsdzIS9BJyELMqhEr4x1ZQe0Ib7khjHe3nJYmmkgzaWWCGRByw</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>DELETE</restRequestMethod>
   <restUrl>http://eapproval-kep.dev.daumkakao.io/api/1/${문서번호}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>19e70b03-3a02-466e-8f64-90d7b1d1af37</id>
      <masked>false</masked>
      <name>문서번호</name>
   </variables>
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
