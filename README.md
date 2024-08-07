# MachiTogether

## 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [프로젝트 기간](#프로젝트-기간)
3. [기술 스택](#기술-스택)
4. [배포 URL 및 아키텍처](#배포-url-및-아키텍처)
5. [ERD](#erd)
6. [주요 기능](#주요-기능)
7. [개발 계획](#개발-계획)
8. [이슈 트래킹](#이슈-트래킹)
9. [개발 환경 설정](#개발-환경-설정)

## 프로젝트 개요
MachiTogether는 사용자들이 함께 노래를 맞추고 즐길 수 있는 음악 플랫폼입니다. 실시간으로 다양한 장르의 음악을 듣고 맞추는 게임을 통해 사용자들 간의 상호작용과 음악 지식 공유를 촉진합니다.

## 프로젝트 기간
2023년 8월 1일 ~ 2023년 12월 31일 (예상)

## 기술 스택
### 백엔드
- Java 21
- Spring Boot 3.3.2
- Spring Data JPA
- Spring Security

### 빌드 도구
- Gradle

### 데이터베이스
- H2 Database (개발 및 테스트용)
- MySQL (예정)

### 프론트엔드
- React (예정)

### 실시간 통신
- WebSocket (예정)

### 기타 라이브러리 및 도구
- Lombok: 자바 코드 간소화
- Spring Boot Configuration Processor

### 테스트
- Spring Boot Test
- JUnit 5
- Spring Security Test
## 배포 URL 및 아키텍처
(추후 업데이트 예정)

## ERD
(추후 업데이트 예정)

## 주요 기능
1. 실시간 노래 맞추기 게임
    - 다중 사용자 참여 세션
    - 다양한 음악 장르 지원
2. 점수 시스템 및 리더보드
3. 사용자 프로필 및 통계
4. 음원 관리 시스템
    - 로컬 음원 업로드
    - URL을 통한 외부 음원 연동

## 개발 계획
1. 백엔드 기본 구조 설정 (1주차)
    - Spring Boot 프로젝트 설정
    - 데이터베이스 연결 및 JPA 설정
2. 사용자 관리 시스템 구현 (2-3주차)
    - 회원가입, 로그인, 프로필 관리
3. 음원 관리 시스템 개발 (4-5주차)
    - 로컬 음원 업로드 기능
    - URL을 통한 외부 음원 연동 기능
4. 게임 로직 구현 (6-8주차)
    - 기본 게임 플로우 설계
    - 점수 계산 및 리더보드 시스템
5. 실시간 통신 기능 추가 (9-10주차)
    - WebSocket을 이용한 실시간 게임 세션 구현
6. 프론트엔드 개발 (11-14주차)
    - React를 이용한 사용자 인터페이스 구현
7. 테스트 및 버그 수정 (15-16주차)
8. 배포 및 서버 설정 (17주차)

## 이슈 트래킹
프로젝트 진행 중 발생하는 이슈들을 GitHub Issues를 통해 관리할 예정입니다. 주요 이슈 카테고리:
- 기능 구현 (Feature)
- 버그 수정 (Bug)
- 개선사항 (Enhancement)
- 문서화 (Documentation)

## 개발 환경 설정
1. MySQL 데이터베이스 생성
2. `src/main/resources/application.yml` 파일에서 데이터베이스 연결 정보 수정
3. Gradle로 프로젝트 빌드