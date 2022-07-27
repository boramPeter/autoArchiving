from flask import Flask, request, make_response, Response
import requests
import json
from ast import literal_eval
from create_jira_ticket_func import *
from datetime import datetime
from custom_logger import *
from text_parsing import *

#==========================
logger = make_logger("peter.bo-flask")
#==========================
app = Flask(__name__)

HEADER = {
    "Content-type": "application/json"
}
#==========================
@app.route("/echo", methods=["GET"])
def echo_check():
    logger.debug("server alive")
    return make_response("server alive", 201)

@app.route("/event", methods=["GET", "POST"])
def cred_check():
    try:
        agit_event = request.data  # 요청 데이터 수신
        agit_event = str(agit_event, 'utf-8')  # agit_event의 값이 겉으로는 json이나 바이트 형식의 문자 > unicode로 변환
        #
        logger.debug("agit event: " + agit_event)
        #
        event_dict = json.loads(agit_event)  # 유니코드로 변환된 문자열을 딕셔너리로 변환

        if "challenge" in agit_event:
            resp = {"challenge": event_dict['challenge']}  # 응답 데이터 구성
            #
            logger.debug("challenge resp" + resp)
            #
            return make_response(resp, 200, HEADER)

        # agit event에서 게시글만 구분 & 5초 이내로 들어온 수정 건은 티켓이 새로 등록 됨(오차범위 수정 가능)
        elif event_dict['type'] == "event_callback" and event_dict['event']['is_root'] == True and event_dict['event']['group_id'] == 300049268:
            # event time stamp와 현재 time stamp를 비교
            time = int(datetime.now().timestamp() - event_dict['event']['created_time'])
            #
            logger.debug("time check: " + time)
            #
            title = text_parser(event_dict['event']['text'])['test_title']
            logger.info("title:"+title)
            if time < 5:
                # 지라 티켓 생성
                return create_ticket(key='EQM', summary=title, description=event_dict['event']['text'][3:-3],
                                     issuetype='Task', parent_id=event_dict['event']['root_post_id'])
            else:
                resp = "please check to edit time stamp"
                logger.debug(resp)
                return make_response(resp, 405)
        else:
            return make_response("please check data", 201)
    except Exception as e:
        logger.debug("Exception => " + str(e))
        return make_response("agit event error", 404)
#==========================
if __name__ == '__main__':
    # logger = make_logger("daniel.js-flask")
    HOST = '127.0.0.1'
    PORT = 7070
    app.run(host=HOST, port=PORT, debug=True)

"""
## To-do List ## 
1. 아지트 부모글에 댓글 달기 -> agit webhook 활용 -> [구현완료]
2. 본문 수정 시, 예외처리 -> 현재시각과 created_time을 비교 -> [구현완료]
 # 댓글에 대한 이벤트 콜백이 계속 들어오니까 무한루프가 돌았던 것! -> agit setting에서 변경 및 소스에서 필터링 완료
3. 요청내용 파싱 -> 지라 티켓 파라미터별 내용 구분 필요 -> 파싱 모듈 [구현완료]
4. 로깅 데코레이터 구성 -> nohup에 print문을 찍을 수 있는지 확인 중 -> nohup python3 실행 시 -u 옵션을 주어도 표현되지 않음..
 -> nohup 출력 파일은 flask 콘솔 로그 확인 용도로만 & logging 라이브러리 사용하여 필요한 요소들을 기록으로 남기는 형태
 -> 데코레이터는 효용성 현재는 없어보임
 -> 실행 옵션에 따라 로깅 레벨을 info 로만 남길지, debug로만 남길지 (추후 고려) 
 ==> [구현 완료]
5. 서버 백그라운드 실행 -> [확인완료] 
6. IDE ssh 인터프리터 실행 -> PyCharm Pro 라이센스 신청 -> 검토 중 -> 리뷰 후 도입 검토[반려]
7. 지라 티켓 생성용 봇 계정 필요 -> 절차 확인 필요
8. git 활용해서 소스 관리하기 -> repo [생성완료] 
"""
