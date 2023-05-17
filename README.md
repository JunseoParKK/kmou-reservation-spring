<h1>💡 today-my-school 💡</h1>

<!--회의실 일러스트-->
<p align="center"><img src="https://github.com/JunseoParKK/today-my-school-final/assets/98972385/c981ee2c-e122-49a3-a8aa-66f58f4351a2" width="750" height="500"></p>

한국해양대학교엔 도서관 내 스터디 룸, 개방형 학습 공간, 학생 쉼터, 이외 체육 공간 등 다양한 편의 시설이 존재합니다. 물론 축구장, 체육관, 스터디 룸 등은 이미 학교 웹사이트를 통해 예약하여 사용할 수 있는 시스템이 마련되어있습니다. 그러나 일부 시설의 경우 온라인으로 예약할 수 있는 시스템이 없어 직접 가서 수기로 명단을 작성해야 합니다. 만약 교내 편의 시설들을 모아 한 번에 예약할 수 있는 앱이 있다면 정말 편할 것 같다는 생각을 했습니다. 이에 해양 과학 기술 대학 2층 스터디존, 아치라운지 회의실 등 수기로 예약했던 시설물을 보다 간편하게 예약, 이용할 수 있는 서비스를 앱으로 만들었습니다. 또한 축구장, 도서관 팀플룸 등 학교의 시설물을 한데 모아 번거로움을 덜어 학생들에게 편의를 제공하는 것이 목표입니다.  

<br><br>

<!--서비스 소개-->

## 🌱 서비스 목록 (Service List)

**1. 학교 이메일로 회원가입 및 로그인** 

**2. 회의실(스터디 룸) , 축구장 , 도서관 시설 예약**  

**3. 현재 예약 정보 조회**  

**4. 이전 예약 기록 조회**  

**5. 예약 수정 , 삭제**  

<!--사용 기술 스택-->

<br>

## 🖋️ 사용 기술 스택
➡️ Front-end
- Dart SDK 2.19.6
- Flutter 3.7.12
- FireBase 

<br>

⬅️ Back-end
- Java11
- SpringBoot 2.7.11
- Gradle 7.6.1
- MySQL 8.0.32
- JPA / QueryDsl
- JUnit5

<br>

🛠️ Tools
- Android Studio
- VSC
- IntelliJ

<br>

🧑‍🤝‍🧑 Cooperation
- github
- Notion
- Figma

<br>

<!--프로젝트 목표-->

## 🪧 프로젝트 목표

**1. 학교 생활에서 `불편함` 을 줄일 수 있도록 고민하고 해결하기 위해 노력합니다.**
- 실제로 우리 주변에서 겪을 수 있는 문제점을 찾고, 자기주도적 학습을 통해 필요한 기술을 익혀 문제를 해결하도록 노력합니다.
- 수기로 관리되던 시설물 예약 시스템을 android, ios 앱으로 만들어 학생들에게 도움이 되도록 노력합니다.

**2. `객체 지향 원리`  를 적용하여 애플리케이션의 유지보수, 확장이 용이하도록 구현합니다.**
- SOLID 원칙과 Controller-Service-Repository 구조를 적용하여 `도메인 주도 설계` 를 하기 위해 노력합니다.
- 객체 지향의 원리를 이해하고, 중복되거나 불필요한 코드를 줄여 `CleanCode` 를 작성하기 위해 노력합니다.

**3. `문서화` 를 통해 협업하는 방법을 배웁니다.**
- Github, Notion 등을 활용하여 프로젝트를 진행하는 과정을 문서화, 팀원들간 공유하며 협업하는 방법을 배웁니다.
- 프로젝트에 필요한 기술을 각자 공부하고 스터디, 회의를 통해 공유하여 팀원간 win-win 이 되도록 노력합니다.

<br>

<!--패키지 구조, 네이밍 컨벤션-->

## 📁 패키지 구조, 네이밍 컨벤션

java > resources >

application.yml

 - java > capston > kmouReserveApp
   - auth
   - domain
     - reservation
       - controller <br>
           &nbsp;&nbsp; XxxController.java
       - dto <br>
           &nbsp;&nbsp; XxxDetails.java <br>
           &nbsp;&nbsp; XxxRequest.java <br>
           &nbsp;&nbsp; XxxDto.java <br>
       - entity <br>
           &nbsp;&nbsp; Xxx.java
       - repository <br>
           &nbsp;&nbsp; XxxRepository.java
       - service <br>
           &nbsp;&nbsp; XxxService.java <br>
           &nbsp;&nbsp; XxxServiceImpl.java
     - user
       - controller <br>
           &nbsp;&nbsp; XxxController.java
       - dto <br>
           &nbsp;&nbsp; XxxRequest.java <br>
           &nbsp;&nbsp; XxxResponse.java <br>
           &nbsp;&nbsp; XxxInfo.java <br>
       - entity <br>
           &nbsp;&nbsp; Xxx.java
       - mapper <br>
           &nbsp;&nbsp; XxxMapper.java
       - repository <br>
           &nbsp;&nbsp; XxxRepository.java
       - service <br>
           &nbsp;&nbsp; XxxService.java <br>
           &nbsp;&nbsp; XxxServiceImpl.java
   - exception
   - utils
