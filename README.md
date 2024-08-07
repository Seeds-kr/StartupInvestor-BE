# StartupInvestor-BE
## main page 
### 페이지 파라미터
```java
@GetMapping()
    public ResponseEntity<Page<RespMainPost>> getAllPosts(
        @RequestParam(defaultValue = "0") int page)
```
* 페이지네이션을 위한 파라미터
  * 파라미터값이 없을 시, 디폴트로 첫 페이지 반환
  * 값이 0부터 시작

### 검색 파라미터
* 공통 검색

![img_1.png](imgs/img_1.png)
* 스타트업

![img.png](imgs/img.png)
* 투자기관

![img.png](imgs/img_2.png)
```java
@GetMapping("/")
    public ResponseEntity<?> getAllPostsWithParam(
        @RequestParam(required = false) String institutionType,
        @RequestParam(required = false) String preferredBusinessArea,
        @RequestParam(required = false) String preferredTechnology,
        @RequestParam(required = false) String preferredInvestmentStage,
        @RequestParam(required = false) String businessArea,
        @RequestParam(required = false) String technology,
        @RequestParam(required = false) String investmentStage,
        @RequestParam(required = false) String region,
        @RequestParam(required = false) Boolean investmentActive,
        @RequestParam(required = false) String query){

    ....
    }
```
* tag : 스타트업, 투자기관, 엔젤투자자(미정)
* query : 이름(기업명, 투자자명) 검색


* 스타트업
  * technology : 활용기술
  * businessArea : 비즈니스 분야
  * investmentStage : 최근 투자 단계
  * region : 지역
  * investmentActive : 투자 유치 중 (yes or not)
* 투자자
  * institutionType : 투자 기관 구분
  * preferredBusinessArea : 선호 비즈니스 분야
  * preferredTechnology : 선호 활용 기술
  * preferredInvestmentStage : 선호 투자 단계

