# 영화 리뷰어, 리뷰어의 리뷰를 통해서 다대다의 관계 실습진행하였습니다.

- 하나의 영화에 대한 게시글에 달리는 다수의 리뷰어들의 리뷰가 가능하기 때문에 다대다의 연관관계를 학습하였습니다.
- ajax으로 이미지 파일 및 첨부파일을 업로드하고 영화 엔티티 객체에 처리하는 방법을 학습했습니다.
- JPA를 통하여 다대다 처리를 하는 방법을 학습하였습니다.

사용한 라이브러리 : thumbnailator(섬네일 이미지 생성 처리 관련 라이브러리), JPA, Thymeleaf, servlet 기반의 파일 첨부 라이브러리, starrr(별점처리 라이브러리/starrr.js,starrr.css)
사용한 자바 버전 : Java 11
사용한 스프링 부트 버전 : 2.7.13

실습 도중 직면한 문제
 1. @Param이라고 하는 파라미터는 Java 8버전 이상은 @Param이라는 파라미터를 넣어서 정상 해결된 사례
 <img width="960" alt="스크린샷 2023-07-12 221451" src="https://github.com/john911120/SpringBoot_Study/assets/53751665/a9b22d13-4783-4d2e-8871-4282688983dc">
 <br>
 원인 : 스프링 부트 버전이 업그레이드 됨과 동시에 JPA에서 비즈니스 로직을 담당하는 Repository에 @Query() 어노테이션에 사용하는 메소드 파라미터에 @Param("name")을 지정하지 않아
상기의 에러 메시지가 나옴.
 <br>
 해결 : List<Object[]> getMovieWithAll( @Param("mno") Long mno); 해당 코드를 추가하여 원인을 해결하였습니다.
 <br>
 
