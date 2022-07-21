<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>수신전 문서 조회</name>
   <tag></tag>
   <elementGuidId>5068698b-f74d-4f36-a58d-31079c4e287e</elementGuidId>
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
      <value>eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1OTQ0MzQyMDAsInVzZXIiOiJ7XCJjbXBJZFwiOjEsXCJlbXBJZFwiOjk5OTUsXCJkZXB0SWRcIjoyLFwiY29uY3VycmVudERlcHRJZExpc3RcIjpbXSxcImRlcHRIZWFkWW5cIjpcIk5cIixcImF1dGhvcml0eVwiOlwiQURNSU5cIn0ifQ.vYIS8hn73INrfCgwJFpYSKY6e6yx08_maRgN7Vt-iLnZxgPnOMegIlLK-9dTc-aMjxoL8MzJRMHJa86LJi9w0g</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>http://eapproval-kep.dev.daumkakao.io/api/1/box/receive/before?keywordFlag=empId&amp;makeFromDate=2020-01-01&amp;makeToDate=2020-12-01</restUrl>
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
