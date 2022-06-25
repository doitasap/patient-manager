# patient-manager
### 에이치디정션 백엔드 개발자 채용 온라인 과제


> ##개발 환경
>```
>JAVA : openJDK 11
>DB : H2 Database
>```

> ##프로젝트 빌드

> ##폴더 구조 

> ##요구사항
> ~~1. 웹 프로젝트 구성
     - [https://start.spring.io](https://start.spring.io) 에서 프로젝트를 생성해주세요.
     - Project, Language, 버전 등을 자유롭게 선택하신 후 Packaging 을 `Jar`로 선택해주세요.
     - `Spring Web`, `Spring Data JPA`, `H2 Databse` 를 Dependencies 에 추가해주세요.
     - GENERATE 를 클릭! 한 후 IntelliJ, Eclipse 등 원하는 IDE 로 해당 Project 를 열고 무사히 실행하면 성공!~~
>
> ~~2. H2 설정하기
    - application.properties(혹은 yml) 에 설정을 추가하여, h2-console을 활성화하고 spring 의 datasource 를 설정해주세요. (이름은 자유롭게!)~~
> 3. Entity 클래스 및 Repository 생성
    - 위 첨부된 기획서와 ERD를 참고하여, javax.persistence.Entity 어노테이션을 이용하여 Entity class 3개 `Hospital` (병원), `Patient` (환자), `Visit` (환자방문) 을 정의해주세요.
        - ERD를 살펴보시고 @OneToMany, @ManyToOne 등의 관계를 적절히 설정해주세요.
        - (옵션) API 구현중 추가로 필요한 컬럼이 있다면 자유롭게 추가해주세요.
        - (옵션) `코드`, `코드그룹`Entity 는 기획서와 ERD를 참고하셔서 적절히 구현해주세요! 코드 관련 테이블은 필요 데이터도 함께 드렸어요.
    - `JpaRepository` 를 상속받아서 `PatientRepository`와 `VisitRepository` 를 생성해주세요.
> 4. 기본 CRUD API 구현
    - `VisitController` 와 `PatientController` 를 생성하여 기본적인 CRUD API를 생성해주세요.
    - endpoint 설계는 RESTful 혹은 혹은 GraphQL로 해주세요.
> 5. 기본 API 구현
    - 아래 API 를 구현하여 주세요. API endpoint 는 직접 정의하여 주세요.
>
>        <aside>
>        💻 환자 등록
>        환자 정보를 등록합니다.
>        환자등록번호는 병원별로 중복되지 않도록 서버에서 생성 해주세요.
>
>        </aside>
>
>        <aside>
>        💻 환자 수정
>        환자 정보를 수정합니다.
>
>        </aside>
>
>        <aside>
>        💻 환자 삭제
>        환자 정보를 삭제합니다.
>
>        </aside>
>
>        <aside>
>        💻 **환자 조회**
>        환자id를 이용해 한 환자의 정보를 조회합니다. 환자 Entity 의 모든 속성과 내원 정보를 목록으로 함께 조회해주세요.
>
>        </aside>
>
>        <aside>
>        💻 **환자 목록 조회**
>        전체 환자 목록을 조회합니다. 조회 항목은, 기획서를 참고해주세요.
>
>        </aside>
>
> 6. 환자 목록 조회 API 확장 - 동적 검색 조건
    - 기획서를 참고하셔서 `환자이름`, `환자등록번호`, `생년월일` 로 환자를 검색하도록 환자 목록 조회 API를 수정해주세요.
    - 동적 검색 조건 구현에는 다양한 방법이 있습니다. 그 중에 아래 library를 활용해주세요.
        - **querydsl**
> 7. 환자 목록 조회 API 확장 - 페이징
    - `pageSize` (한 번에 조회하는 최대 항목 수), `pageNo` (1부터 시작, 페이지 번호)를 요청 인자로 전달받아서 페이징을 구현해주세요.
    - 페이징 구현에는 다양한 방법이 있습니다. 동적 검색 조건의 구현 방식에 따라 또 선택은 달라질 것입니다. 자유롭게 구현해주세요!
> 8. restful api 문서 작업(spring-restdocs)