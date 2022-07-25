from logging import handlers
import logging
from datetime import datetime
import os

def make_logger(name=None):
    #1. 로거 생성
    logger = logging.getLogger(name)

    #2. 로거에 레벨 부여
    logger.setLevel(logging.DEBUG)

    # 날짜 확인
    today_date = str(datetime.today().date())

    # 디렉토리 생성 체크
    current_dir = os.path.dirname(os.path.realpath(__file__))
    log_dir = '{}/logs'.format(current_dir)
    # current_file = os.path.basename(__file__)
    try:
        if not (os.path.isdir(log_dir)):
            os.makedirs(os.path.join(log_dir))
    except OSError as e:
        if e.errno != errno.EEXIST:
            print("Failed to create directory!!!!!")
            raise

    # formatter 객체 생성
    formatter = logging.Formatter(fmt="[%(asctime)s][Line No: %(lineno)d] - %(name)s - %(levelname)s - %(message)s")

    #3-1. handler 객체 생성
    stream_handler = logging.StreamHandler()
    file_Handler = handlers.TimedRotatingFileHandler(filename="./logs/server2.log", when='midnight', interval=1, encoding='utf-8')

    #3-2. handler에 level 설정
    stream_handler.setLevel(logging.DEBUG)   # 콘솔에 표시
    file_Handler.setLevel(logging.DEBUG)

    #3-3. handler에 format 설정
    stream_handler.setFormatter(formatter)
    file_Handler.setFormatter(formatter)
    file_Handler.suffix = "%Y%m%d"
    #===

    #4. logger에 handler 추가
    logger.addHandler(stream_handler)
    logger.addHandler(file_Handler)
    return logger

# gg = make_logger()
# gg.debug("g")