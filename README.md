# 원티드 프리온보딩 백엔드 인턴십 선발과제

## 📍Stack

- Language : `Java17`
- Framework : `SpringBoot 3.1.4` , `JUnit5`
- Database : `MySQL`
- ORM : `JPA`

## 📍ERD
<img width="694" alt="스크린샷 2023-10-14 오후 8 55 06" src="https://github.com/ahah525/wanted-pre-onboarding-backend/assets/48237976/95f8cff1-abc8-4181-bf01-90c27a9740dd">

## 📍주요 기능

- [채용공고 등록](https://github.com/ahah525/wanted-pre-onboarding-backend/edit/main/README.md#0-%EA%B3%B5%ED%86%B5-%EA%B3%A0%EB%A0%A4%EC%82%AC%ED%95%AD)
- [채용공고 수정](https://github.com/ahah525/wanted-pre-onboarding-backend/edit/main/README.md#2-%EC%B1%84%EC%9A%A9%EA%B3%B5%EA%B3%A0-%EC%88%98%EC%A0%95--patch-recruitmentrecruitment-id)
- [채용공고 삭제](https://github.com/ahah525/wanted-pre-onboarding-backend/edit/main/README.md#3-%EC%B1%84%EC%9A%A9%EA%B3%B5%EA%B3%A0-%EC%82%AD%EC%A0%9C--delete-recruitmentrecruitment-id)
- [채용공고 목록 조회](https://github.com/ahah525/wanted-pre-onboarding-backend/edit/main/README.md#4-%EC%B1%84%EC%9A%A9%EA%B3%B5%EA%B3%A0-%EB%AA%A9%EB%A1%9D-%EC%A1%B0%ED%9A%8C--get-recruitment)
    - 채용공고 전체 조회
    - 채용공고 검색 조회
- [채용공고 상세 조회](https://github.com/ahah525/wanted-pre-onboarding-backend/edit/main/README.md#5-%EC%B1%84%EC%9A%A9%EA%B3%B5%EA%B3%A0-%EC%83%81%EC%84%B8-%EC%A1%B0%ED%9A%8C--get-recrutimentrecruitment-id)
- [채용공고 지원](https://github.com/ahah525/wanted-pre-onboarding-backend/edit/main/README.md#6-%EC%B1%84%EC%9A%A9%EA%B3%B5%EA%B3%A0-%EC%A7%80%EC%9B%90--post-apply)

## 📍주요 기능 세부 설명

### 0. 공통 고려사항

- 자원과 행위를 분리하여 RESTful API로 설계했습니다.
- 모든 엔티티가 BaseEntity 클래스를 상속해 id, createdAt(생성일시), modifiedAt(수정일시)를 갖도록 설계했습니다.
- 비즈니스 로직에서 발생하는 예외를 처리하기 위해 BusinessException 예외 클래스 및 ErrorCode를 정의하고 GlobalExceptionHandler를 구현한 전역 예외 처리 방식을 사용했습니다.

### 1. 채용공고 등록 : `POST /recruitment`

회사가 채용공고를 등록한다.

- 데이터 추가의 의미로 `POST` 메서드를 사용했습니다.
- 회사 등록 절차는 생략했기 때문에 companyId를 RequestBody에 포함하였습니다.
- RequestBody의 companyId, compensation이 NotNull이고 position, content, stack이 NotBlank일 때만 채용공고 등록에 성공한다.
- 존재하지 않는 회사가 채용공고를 등록 요청하는 경우 `COMPANY_NOT_FOUND` 예외를 던집니다.

> RequestBody
> 
- `RecruitmentCreateReq` 클래스를 만들어 Request DTO로 사용했습니다.

```json
{
    "companyId" : 1,
    "position" : "경력",
    "compensation" : 2000000,
    "content" : "하반기 공채",
    "stack" : "java"
}
```

> ResponseBody
> 
1. 성공(200) → `테스트 수행`
    - 등록 성공시 응답 바디는 필요 없을 것이라고 생각하여 응답 바디에 값을 포함하지 않도록 구현했습니다.
2. 실패(404) : 존재하지 않는 회사(companyId)로 요청한 경우
    
    ```json
    {
        "errorCode": "100",
        "errorMessage": "해당 회사가 존재하지 않습니다."
    }
    ```
    

### 2. 채용공고 수정 : `PATCH /recruitment/{recruitment-id}`

회사가 채용공고를 수정한다.

- 데이터 부분 수정의 의미로 `PATCH` 메서드를 사용했습니다.
- 회사는 수정할 수 없기 때문에 companyId를 제외한 position, compensation, content, stack 값을 담아 채용공고 수정 요청을 할 수 있습니다.
- RequestBody의 compensation이 NotNull이고 position, content, stack이 NotBlank일 때만 채용공고 등록에 성공한다.
- 존재하지 않는 채용공고를 삭제 요청하는 경우 `RECRUITMENT_NOT_FOUND` 예외를 던집니다.

> PathVariable
> 
- `recruitment-id` : 채용공고 id

> Request Body
> 
- `RecruitmentUpdateReq` 클래스를 만들어 Request DTO로 사용했습니다.

```json
{
    "position" : "경력",
    "compensation" : 2000000,
    "content" : "하반기 경력 수시 채용",
    "stack" : "python"
}
```

> ResponseBody
> 
1. 성공(200) → `테스트 수행`
    - 수정 성공시 응답 바디는 필요 없을 것이라고 생각하여 응답 바디에 값을 포함하지 않도록 구현했습니다.
2. 실패(404) : 존재하지 않는 채용공고(recruitment-id)로 요청한 경우
    
    ```json
    {
        "errorCode": "200",
        "errorMessage": "해당 채용공고가 존재하지 않습니다."
    }
    ```
    

### 3. 채용공고 삭제 : `DELETE /recruitment/{recruitment-id}`

회사가 채용공고를 삭제한다.

- 데이터 삭제의 의미로 `DELETE` 메서드를 사용했습니다.
- 존재하지 않는 채용공고를 삭제 요청하는 경우 `RECRUITMENT_NOT_FOUND` 예외를 던집니다.

> PathVariable
> 
- `recruitment-id` : 채용공고 id

> ResponseBody
> 
1. 성공(200) → `테스트 수행`
    - 삭제 성공시 응답 바디는 필요 없을 것이라고 생각하여 응답 바디에 값을 포함하지 않도록 구현했습니다.
2. 실패(404) : 존재하지 않는 채용공고(recruitment-id)로 요청한 경우
    
    ```json
    {
        "errorCode": "200",
        "errorMessage": "해당 채용공고가 존재하지 않습니다."
    }
    ```
    

### 4. 채용공고 목록 조회 : `GET /recruitment`

사용자가 채용공고 목록을 조회한다.

- 데이터 조회의 의미로 `GET` 메서드를 사용했습니다.
- 검색어가 비었거나 공백인 경우 모든 채용공고를 조회하도록 구현했습니다.
- 검색어가 있는 경우 회사명, 포지션, 사용기술에 검색어가 포함된 채용공고를 모두 조회하는 메서드(JPQL로 작성한 메서드)를 호출하도록 구현했습니다.

> RequestParameter
> 
- `search` : 검색어(not required)

> ResponseBody
> 
- `RecruitmentResp` 클래스를 만들어 Response DTO로 사용했습니다.
1. 성공(200) → `테스트 수행`
    
    ```json
    [
        {
            "id": 2,
            "companyName": "삼성",
            "nation": "한국",
            "region": "서울",
            "position": "신입",
            "compensation": 1000000,
            "stack": "java"
        },
        {
            "id": 3,
            "companyName": "삼성",
            "nation": "한국",
            "region": "서울",
            "position": "경력",
            "compensation": 2000000,
            "stack": "python"
        }
    		...
    ]
    ```
    

### 5. 채용공고 상세 조회 : `GET /recrutiment/{recruitment-id}`

사용자가 채용공고의 상세 정보를 조회한다.

- 데이터 조회의 의미로 `GET` 메서드를 사용했습니다.
- 회사명, 국가, 지역, 채용포지션, 채용보상금, 채용내용, 회사가 올린 다른 채용공고 id 목록을 조회한다.
- 회사가 올린 다른 채용공고 목록을 조회하기 위해 JPA 메서드로 회사의 채용공고 중 해당 채용공고를 제외한 채용공고를 조회하고 스트림으로 id를 리스트로 추출했습니다.
- 존재하지 않는 채용공고를 상세조회 요청하는 경우 `RECRUITMENT_NOT_FOUND` 예외를 던집니다.

> PathVariable
> 
- `recruitment-id` : 채용공고 id

> ResponseBody
> 
- `RecruitmentDetailResp` 클래스를 만들어 Response DTO로 사용했습니다.
1. 성공(200) → `테스트 수행`
    
    ```json
    {
        "id": 2,
        "companyName": "삼성",
        "nation": "한국",
        "region": "서울",
        "position": "신입",
        "compensation": 1000000,
        "content": "하반기 신입 공채",
        "stack": "java",
        "ids": [
            3,
            4,
            5
        ]
    }
    ```
    
2. 실패(404) : 존재하지 않는 채용공고(recruitment-id)로 요청한 경우
    
    ```json
    {
        "errorCode": "200",
        "errorMessage": "해당 채용공고가 존재하지 않습니다."
    }
    ```
    

### 6. 채용공고 지원 : `POST /apply`

사용자가 채용공고에 지원한다.

- 데이터 추가의 의미로 `POST` 메서드를 사용했습니다.
- 사용자 등록 절차를 생략했기 때문에 memberId를 RequestBody에 포함하였습니다.
- 사용자는 동일 채용 공고에 1회만 지원가능하도록 (memberId, recruitmentId)가 모두 같은 데이터가 저장되어 있다면 `APPLY_DUPLICATE` 예외를 던집니다. DB에 member_id와 recruitment_id 의 unique 제약조건을 설정했습니다.
- 존재하지 않는 사용자가 지원 요청하는 경우 `MEMBER_NOT_FOUND` 예외를 던집니다.
- 존재하지 않는 채용공고에 지원 요청하는 경우 `RECRUITMENT_NOT_FOUND` 예외를 던집니다.

> RequestBody
> 
- `ApplyCreateReq` 클래스를 만들어 Request DTO로 사용했습니다.

```json
{
    "memberId" : 1,
    "recruitmentId" : 3
}
```

> ResponseBody
> 
1. 성공(200) → `테스트 수행`
    - 지원 성공시 응답 바디는 필요 없을 것이라고 생각하여 응답 바디에 값을 포함하지 않도록 구현했습니다.
2. 실패(409) : 한 회원이 동일 채용 공고(recruitmentId)에 지원 요청한 경우 → `테스트 수행`
    
    ```json
    {
        "errorCode": "400",
        "errorMessage": "중복 지원은 불가합니다."
    }
    ```
    
3. 실패(404) : 존재하지 않는 사용자(memberId)로 요청한 경우
    
    ```
    {
        "errorCode": "300",
        "errorMessage": "해당 사용자가 존재하지 않습니다."
    }
    ```
    
4. 실패(404) : 존재하지 않는 채용공고(recruitmentId)로 요청한 경우
    
    ```json
    {
        "errorCode": "200",
        "errorMessage": "해당 채용공고가 존재하지 않습니다."
    }
    ```
