import re
from custom_logger import *

logger = make_logger("text_parsing.py")

def text_parser(data):
    try:
        pattern_1 = re.compile(r"테스트 과제명\n - ")
        pars_1 = re.search(pattern_1, data)
        pars_1_end = pars_1.end()

        pattern_2 = re.compile(r"\n\n2\. 테스트 목적")
        pars_2 = re.search(pattern_2, data)
        pars_2_start = pars_2.start()

        result = dict()
        result['test_title'] = data[pars_1_end:pars_2_start]        # 테스트 과제명 추출
        logger.info(result)

        return result
    except Exception as e:
        logger.debug(str(e))

sample = {'text' : "```n1. 테스트 과제명\n - Kakao i Cloud Console : Object Storage\n\n2. 테스트 목적\n - 3월 정식 출시 버전 테스트\n\n3. 오픈 희망 일정\n - 21년 3월 31일\n\n4. 개발 완료 예상 일정\n - 3월 12일 (금)\n\n5. 개발 범위 및 제외 범위\n - 기획서 상의 전체 기능\n\n6. 서비스 담당자\n - BE : peter.bro / jay.brown\n - FE : sherlock.code\n - 기획 : uno.k\n - 디자인 : erica.kk\n\n7. 참고 URL \n - 기획 제플린 : https://zpl.io/2EnyvEv\n - 디자인 제플린 : https://scene.zeplin.io/project/5fffe00c09b8bc1fdfd3c846 (DKT 문서)\n - 콘솔 : console.sandbox.kakaoi.io/objectstorage\n\n8. 참고사항\n - 감사합니다!\n```"}

# pattern = r"테스트 목적"
# pattern_1 = re.compile(r"n1\. 테스트 과제명\n - ")
# pars_1 = re.search(pattern_1, sample['text'])
#
# pattern_2 = re.compile(r"\n\n2\. 테스트 목적")
# pars_2 = re.search(pattern_2, sample['text'])

# print(pars_1.group())
# print(pars_1.start())
# print(pars_1.end())
# print(sample['text'][pars_1.start():pars_1.end()])
#
# print('---')
# print(pars_2.group())
# print(pars_2.start())
# print(pars_2.end())
# print(sample['text'][pars_2.start():pars_2.end()])
#
# print('---')
# print(sample['text'][pars_1.end():pars_2.start()])

s = text_parser(sample['text'])
print(s['test_title'])
# print(sample['text'][3:-3])