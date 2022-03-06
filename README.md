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
- 제목, 내용, 첨부파일 추가 (ckeditor5 사용)
- 첨부파일 추가시 서버 특정경로에 파일 업로드
- 사용자 ID, 제목, 작성일을 보여주는 목록 화면
- 제목 클릭시 세부 게시물 내용 첨부파일 포함 
- 파일 업로드 기능 (PC, Smartphone)
- 파일 다운로드 기능 (PC) - Smartphone 및 브라우저별 분기 처리 필요
# 추가 기능 개발 진행 및 개선 필요의 건
- 브라우저 localstorage 잔여 토큰의 Default Layout 호출시 토큰 유효성 체크
- 회원가입 사용자 승인시 사용자의 role을 선택하여 승인할 수 있도록 개선 필요
- 게시판 검색, 페이징 기능
- 사용자별 마이페이지 및 정보 수정 페이지
- SSL적용을 위한 Apache 설치 및 연동
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
- 6) Github commit시 webhook을 이용한 jenkins 서버 빌드 유발 및 자동 배포
- 6) oracle cloud의 인그레스 rule 추가하여 8080포트 접근 가능하도록 설정
# 접속정보
- http://146.56.171.145:8080/
# 로그인 페이지

![스크린샷 2022-02-09 오후 10 53 59](https://user-images.githubusercontent.com/60498178/153215164-f0ab9914-f90f-4a4b-bb32-b7c097192b27.png)
# 회원가입 페이지

![스크린샷 2022-02-09 오후 10 55 41](https://user-images.githubusercontent.com/60498178/153215432-bb0dfe99-e5e8-4bcb-a4f2-f0a80e4ae585.png)
# 사용자 승인 페이지

![image](https://user-images.githubusercontent.com/60498178/156915069-4251ae6b-7cc8-4393-86bc-dfec36cb25dc.png)
# 게시판 조회 페이지

![image](https://user-images.githubusercontent.com/60498178/156914955-27df68a1-b49a-4c84-978a-72ef8eae3b99.png)
# 게시물 등록 페이지

![image](https://user-images.githubusercontent.com/60498178/156915027-ae4b0235-cfb7-49a6-98e4-0f1d7c02b257.png)
# 게시물 상세 조회 페이지

![image](https://user-images.githubusercontent.com/60498178/156915040-58911c41-a4b5-4ad8-a16f-def9601a25aa.png)


