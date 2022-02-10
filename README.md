# coreui_react_dashboard
# 기능 개발 현황
1) 회원가입 
- ID 중복 확인
- 비밀번호 확인하여 입력값 검증
- 비밀번호는 암호화하여 DB에 저장 및 Spring Security를 활용하여 입력된 비밀번호 검증
- 회원가입을 통한 사용자의 Role은 "user"
- 관리자 승인 기능 (회원가입 신청한 사용자에 대한 승인)
2) 로그인
- 사용자 Role에 따른 메뉴
- 로그인시 jwt 토큰을 발급받아, 서버에서 특정 시간동안 토큰 유효성 관리
- 관리자 로그인시 현재 회원가입 신청한 인원에 대하여 리스트 확인
3) 로그아웃 기능
- 오른쪽 상단 AppHeaderDropdown 로그아웃 버튼 및 기능 추가
- 사용자별 로그아웃시 다시 로그인 페이지로 Routing
- API호출 에러(jwt토큰 만료)시 로그아웃
4) 게시판 등록 기능
- 제목, 내용, 첨부파일 추가
- 첨부파일 추가시 서버 특정경로에 파일 업로드
- 사용자 ID와 제목만 보여주는 목록 화면
# 추가 기능 개발 진행 및 개선 필요의 건
- 브라우저 localstorage 잔여 토큰의 Default Layout 호출시 토큰 유효성 체크
- 회원가입 사용자 승인시 사용자의 role을 선택하여 승인할 수 있도록 개선 필요
- 게시판 조회 기능 (파일 다운로드 및 게시물 조회)
- 게시판 검색, 페이징 기능
- 사용자별 마이페이지 및 정보 수정 페이지
- SSL적용을 위한 Apache 설치 및 연동
- 자동 빌드 및 배포를 위한 Jenkins, 빌드 서버를 구축하여 배포 파이프라인 구축
- application log와 tomcat log 분리, 날짜별 로그 백업 기능
# 테이블 정보
1) collection
- user: 사용자 정보 (userId, pwd, role)
- userSignTmp: 회원가입 정보 (userId, pwd, role, email)
- board: 게시물 (제목, 내용, 사용자ID)
- file: 파일명, 파일용량, UUID를 포함한 파일명
# 사용한 기술
Front: React, CoreUI 템플릿
Backend: SpringBoot, Spring Security
DB: MongoDB 
- 세부 사용 기술
- 1) react -> spring boot api호출은 axios를 활용하여 http request
- 2) spring boot <-> mongodb jpa를 활용하여 데이터 조회
- 3) 폐쇠망에서의 빌드를 위한 yarn build, yarn build --offline 사용
- 4) maven package build시 yarn build를 하여 하나의 war파일로 static 리소스 생성
- 5) oracle cloud oracle linux image 인스턴스 생성하여 war 배포 (java -jar 패키지명.war > log.out 실행)
- 6) oracle cloud의 인그레스 rule 추가하여 8080포트 접근 가능하도록 설정
# 접속정보
- http://146.56.171.145:8080/index.html 
- 임시 Admin 계정 (admin/admin)
로그인 페이지
![스크린샷 2022-02-09 오후 10 53 59](https://user-images.githubusercontent.com/60498178/153215164-f0ab9914-f90f-4a4b-bb32-b7c097192b27.png)
회원가입 페이지
![스크린샷 2022-02-09 오후 10 55 41](https://user-images.githubusercontent.com/60498178/153215432-bb0dfe99-e5e8-4bcb-a4f2-f0a80e4ae585.png)
사용자 승인 페이지
![스크린샷 2022-02-09 오후 10 56 58](https://user-images.githubusercontent.com/60498178/153215648-224435e2-6d00-4578-aef0-281dd43cf696.png)

