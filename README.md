## Chookting

포토부스의 사진을 공유하고, 만남을 연결하는 서비스 축팅입니다.

[축팅](https://ka53958d06e25a.user-app.krampoline.com/)에 방문해보세요!

[BE 배포 주소](https://ka53958d06e25a.user-app.krampoline.com/)


## 팀 소개

<table>
  <tr>
    <td align="center"><a href="https://github.com/hwangdaesun"><img src="https://github.com/hwangdaesun.png" width="50"></a></td>
    <td align="center"><a href="https://github.com/GoBeromsu"><img src="https://github.com/GoBeromsu.png" width="50"></a></td>
    <td align="center"><a href="https://github.com/yuseonkim"><img src="https://github.com/yuseonkim.png" width="50"></a></td>
    <td align="center"><a href="https://github.com/SongGaEun16"><img src="https://github.com/SongGaEun16.png" width="50"></a></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/hwangdaesun">hwangdaesun</a></td>
    <td align="center"><a href="https://github.com/GoBeromsu">GoBeromsu</a></td>
    <td align="center"><a href="https://github.com/yuseonkim">yuseonkim</a></td>
    <td align="center"><a href="https://github.com/SongGaEun16">SongGaEun16</a></td>
  </tr>
  <tr>
    <td align="center">조장</td>
    <td align="center">테크리더</td>
    <td align="center">리마인더</td>
    <td align="center">리액셔너</td>
  </tr>
<table>


## 사용기술


- JDK 17
- Springboot 2.7.15
- SpringSecurity
- MySQL : 8.0.33
- Redis 7.2
- java-jwt 4.3.0


## 고민했던 부분

### 외부 API
  - **고민사항**: 카카오 로그인 및 인스타그램 연동을 위한 외부 API 통합 시 프록시 설정의 필요성을 인지하고 기존 OAuth2Client 라이브러리에서 REST API 기반 개발로 전환했습니다.
  - **결과**: 이 변경으로 인해 OAuth2 기반 인증 플로우(인가코드 → 엑세스토큰 → 사용자 정보)를 보다 세밀하게 제어할 수 있게 되었으며, 보안과 효율성 면에서 향상된 결과를 얻었습니다.
    
### 토큰 관리
  - **고민사항**: 리프레시토큰을 사용한 엑세스토큰 재발급 방식에 대한 이해가 부족했습니다. 초기에는 리프레시토큰을 엑세스토큰과 같이 요청헤더에 포함시키려 했으나, 보안상 문제가 있음을 인지했습니다.
  - **결과**: 엑세스토큰은 클라이언트의 로컬스토리지에, 리프레시토큰은 HttpOnly 쿠키에 저장하는 방식으로 변경함으로써 보안성을 강화했습니다. 이는 토큰의 안전한 관리 및 사용을 가능하게 했습니다.
    
### 프록시 설정 개선
  - **고민사항**: 전체 RestTemplate 수정 대신, 멘토의 조언을 받아 프록시 설정 전용 proxyRestTemplate을 별도로 Bean 등록하는 방법을 채택했습니다.
  - **결과**: 이를 통해 기존 restTemplate 전체의 변경 없이도 프록시 설정을 효율적으로 관리할 수 있게 되었으며, 시스템의 유연성과 확장성이 향상되었습니다.
    
### 토큰 재발급 성능 최적화
  - **고민사항**: 리프레시토큰 재발급 시의 성능 향상을 위해 Redis 도입을 고려했습니다.
  - **결과**: Redis를 사용하여 리프레시토큰과 사용자 정보를 Key Value 쌍으로 저장함으로써, 토큰 확인 과정의 속도가 크게 개선되었습니다. 
    

