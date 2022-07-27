import requests
import json
from base64 import b64encode
from ast import literal_eval
from custom_logger import *

logger = make_logger("create_jira_ticket_func.py")

# 지라 티켓 생성용 계정
ID = "peter.bo"
PW = "password"

userAndPass = b64encode(b"peter.bo:password").decode("ascii")
# print(userAndPass)
# "ZGFuaWVsLmpzOmRsd2xzdGpyMkA="

BASE_URL = "https://jira.daumkakao.com/rest/api/2"
#===============================================================
#프로젝트 관련 기능
action_1 = "/project"

#이슈 티켓 관련 기능
action_2 = "/issue"

#이슈 정보 조회
action_3 = "/issue/{ticket_number}"

#Jira - project에 속해있는 이슈 모두 조회(jql형식)
action_4 = "/search?jql=project={project_name}"

#프로젝트에 속해있는 문제 유형 검색
# 1:Bug,  11300:initiative,  13:sub-task,  14:story,  28:epic,  3:Task
# /issue/createmeta/{project_id=project uid or ticket code}/issuetypes
action_5 = "/issue/createmeta/EQM/issuetypes"

# 해당 유형(ex. Task) 에서 사용가능한 필드 검색
action_6 = "/issue/createmeta/EQM/issuetypes/3"

#===============================================================
# Basic Auth 인증 헤더
HEADER = {
    "Content-Type": "application/json",
    'Authorization' : 'Basic {}'.format(userAndPass)
}
# print(HEADER)
#===============================================================
# 티켓 생성 Func
def create_ticket(**params):
    try:
        total_url = BASE_URL + action_2
        # 기한 = "duedate" : "2015-11-18"
        # 컴포넌트 = "components" : [ { "name": "Active Directory"} , { "name": "Network Switch" } ]
        # 레이블 = "labels" : ["examplelabelnumber1", "examplelabelnumber2"]
        # 티켓 생성 시 필요한 최소 데이터
        DATA = {
            "fields": {
                "project":
                    {
                        "key": params['key']
                    },
                "summary": params['summary'],
                "description": params['description'],
                "issuetype": {
                    "name": params['issuetype']
                }
            }
        }
        resp = requests.post(url=total_url, data=json.dumps(DATA), headers=HEADER)
        create_agit_comment(str(resp.status_code), json.loads(resp.text), params['parent_id'])
        #
        logger.debug("success to create jira ticket")
        return "201"
    except Exception as e:
        logger.debug("err => " + str(e))
        return "405"


def create_agit_comment(*param):
    try:
        logger.debug("create_agit_comment() => " + str(param))
        #
        WEBHOOK_URL = "https://agit.in/webhook/0f2d80c8-6566-403a-9484-42d4abb3ac8a"
        HEADER_2 = {"Content-Type": "application/json"}
        payload = {
            "parent_id": param[2],
            "text": "https://jira.daumkakao.com/browse/" + param[1]['key']
        }
        result = requests.post(url=WEBHOOK_URL, headers=HEADER_2, data=json.dumps(payload))
        #
        logger.debug("agit comment result => " + result.text)
        return "202"
    except:
        return "406"
