웹프로그래밍응용 텀프로젝트
---
### 📍 텀프로젝트 1 : [실행 영상](https://drive.google.com/file/d/17qXIXOidqwLhRZi2v-OFKUNuwDk2giXx/view?usp=drive_link)

**1. 프로젝트 요약**
- 쇼핑몰 웹 응용 프로그램 개발
- SPA(Single Page Application)으로 개발
- 판매할 제품의 종류는 본인이 정함 (예: 도서, 옷, 자동차, 핸드폰 등)
- 제품의 속성은 4가지로 할 것 (단, title과 userId 라는 속성은 반드시 가질 것) 
- 데이터베이스에 제품 정보 저장 시 id 값은 자동으로 생성되도록 함

**2. 구조: 레이어드 아키텍처**
- 콘트롤러, 서비스, 퍼시스턴스

**3. 기능**
| 기능                           | 설명                                                          |
|-------------------------------|--------------------------------------------------------------|
| 제품 정보 추가 기능 (POST)      | 클라이언트로부터 제품 한 개의 정보를 받아 데이터베이스에 추가<br>- 현재 존재하는 모든 제품 리스트를 반환 (status code와 함께) |
| 제품 정보 검색 기능 (GET)        | 클라이언트로부터 제품의 title 속성 값을 받아 해당 제품을 데이터베이스에서 검색<br>- 검색된 모든 제품 리스트를 반환 (status code와 함께) |
| 제품 정보 수정 기능 (PUT)        | 클라이언트로부터 전달받은 정보 중 id 속성 값을 이용하여 제품을 찾아 수정<br>- 수정된 제품의 정보를 반환 (status code와 함께) |
| 제품 정보 삭제 기능 (DELETE)     | 클라이언트로부터 전달받은 정보 중 id 속성 값을 이용하여 제품을 찾아 삭제<br>- 삭제 후의 모든 제품 리스트를 반환 (status code와 함께) |


**4. 제한조건**
- 특별한 언급이 없는 경우 교재와 동일하게 구현함
- 클라이언트로의 모든 응답은 ResponseEntity를 이용할 것
- ResponseEntity의 응답 바디에는 ResponseDTO를 전달할 것
- ResponseDTO는 data와 error 속성을 가짐
---
### 📍 텀프로젝트 2 : [실행 영상]([https://drive.google.com/file/d/17qXIXOidqwLhRZi2v-OFKUNuwDk2giXx/view?usp=drive_link](https://drive.google.com/file/d/1JLgnZXBjnoPwShQ9QkmrYXQ_W_betru6/view?usp=drive_link))

**1. 프론트엔드 서비스 구현 및 백엔드 서비스와 연결**
- 현재 제품 리스트를 보여주는 UI 제공
- 제품 정보 추가를 위한 UI 제공
- 제품 검색을 위한 UI 제공
- 제품 수정을 위한 UI 제공
- 제품 삭제를 위한 UI 제공

**2. 제한조건**
- 반드시 SPA(Single Page Application)으로 구현한다.
- React.js 라이브러리를 이용하여 구현한다.
- material ui는 꼭 사용하지 않아도 된다.

