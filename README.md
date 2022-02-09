# coreui_react_dashboard
# 기능 개발 현황
1) 회원가입 
- ID 중복 확인
- 비밀번호 확인하여 입력값 검증
- 관리자 승인 기능 (회원가입 신청한 사용자에 대한 승인)
2) 로그인
- 사용자 Role에 따른 메뉴
- 로그인시 jwt 토큰을 발급받아, 서버에서 특정 시간동안 토큰 유효성 관리
- 관리자 로그인시 현재 회원가입 신청한 인원에 대하여 리스트 확인
3) 로그아웃 기능
- 사용자별 로그아웃시 다시 로그인 페이지로 Routing
- API호출 에러(jwt토큰 만료)시 로그아웃
# 테이블 정보
1) collection
- user: 사용자 정보
- userSignTmp: 회원가입 정보
- board: 게시물 (제목, 내용)
- file: 파일정보
# 사용한 기술
Front: React, CoreUI 템플릿
Backend: SpringBoot, Spring Security
DB: MongoDB 
# 접속정보 (oracle cloud)
- http://146.56.171.145:8080/index.html
