# StartupInvestor-BE
## main page 
### 파라미터
* 공통 검색
  * page : 페이지 위치
  * q (query) : 검색어
  
    ![img_1.png](imgs/img_1.png)


* 스타트업

  ![img.png](imgs/img.png)

```java
@GetMapping()
public ResponseEntity<?> getAllPostsWithParam(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(required = false) String mbt, //main business type
    @RequestParam(required = false) String sbt, //sub business type
    @RequestParam(required = false) String mtt, //main tech type
    @RequestParam(required = false) String stt, //sub tech type
    @RequestParam(required = false) String sc, //series category
    @RequestParam(required = false) Boolean ia, //investment active
    @RequestParam(required = false) String q //query (검색)
)
```
* 스타트업
    * mbt (main business type) : 비즈니스 메인 타입
    * sbt (sub business type) : 비즈니스 서브 타입
      * 메인 타입 값이 없는 상태로 서브 타입 값만 들어오면 예외던짐
    * mtt (main tech type) : 기술 메인 타입
    * stt (sub tech type) : 기술 서브 타입
      * 메인 타입 값이 없는 상태로 서브 타입 값만 들어오면 예외던짐
    * sc (series category) : 스타트업 시리즈 타입, 여기서는 최근 투자 단계를 말함
    * ia (investment active) : 투자 유치 중인지의 여부
---

* 투자기관

  ![img.png](imgs/img_2.png)  


* 투자자 (아직 안함)
  * institutionType : 투자 기관 구분
  * preferredBusinessArea : 선호 비즈니스 분야
  * preferredTechnology : 선호 활용 기술
  * preferredInvestmentStage : 선호 투자 단계

# 이슈 트래킹
[2024-08-12 : erd 수정](https://github.com/Seeds-kr/StartupInvestor-BE/issues/2)
