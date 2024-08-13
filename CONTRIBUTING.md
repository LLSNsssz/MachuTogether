# MachiTogether 기여 가이드라인

MachiTogether 프로젝트에 관심을 가져주셔서 감사합니다. 이 문서는 프로젝트에 기여하는 방법을 안내합니다.

## 목차
1. [행동 강령](#행동-강령)
2. [시작하기](#시작하기)
3. [개발 환경 설정](#개발-환경-설정)
4. [기여 프로세스](#기여-프로세스)
5. [코드 스타일 가이드](#코드-스타일-가이드)
6. [커밋 메시지 규칙](#커밋-메시지-규칙)
7. [브랜치 사용 가이드라인](#브랜치-사용-가이드라인)
8. [이슈 보고](#이슈-보고)
9. [풀 리퀘스트 가이드라인](#풀-리퀘스트-가이드라인)

## 행동 강령

본 프로젝트는 [Contributor Covenant](https://www.contributor-covenant.org/version/2/0/code_of_conduct/)를 채택하고 있습니다. 프로젝트에 참여함으로써 이 행동 강령을 준수할 것에 동의하는 것으로 간주됩니다.

## 시작하기

1. 프로젝트를 포크(Fork)합니다.
2. 저장소를 로컬에 클론합니다: `git clone https://github.com/YOUR-USERNAME/MachiTogether.git`
3. 원격 저장소를 추가합니다: `git remote add upstream https://github.com/ORIGINAL-OWNER/MachiTogether.git`

## 개발 환경 설정

1. Java 21을 설치합니다.
2. MySQL을 설치하고 데이터베이스를 생성합니다.
3. `src/main/resources/application.yml` 파일에서 데이터베이스 연결 정보를 수정합니다.
4. Gradle로 프로젝트를 빌드합니다: `./gradlew build`

## 기여 프로세스

1. 메인 브랜치에서 최신 변경사항을 가져옵니다: `git pull upstream main`
2. 새로운 브랜치를 생성합니다: `git checkout -b feature/your-feature-name`
3. 변경사항을 작업합니다.
4. 변경사항을 커밋합니다.
5. 원격 저장소에 푸시합니다: `git push origin feature/your-feature-name`
6. GitHub에서 풀 리퀘스트를 생성합니다.

## 코드 스타일 가이드

- Java 코드는 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)를 따릅니다.
- 들여쓰기는 스페이스 4칸을 사용합니다.
- 메서드와 클래스에는 반드시 JavaDoc 주석을 작성합니다.
- 변수명과 메서드명은 camelCase를 사용합니다.
- 상수는 대문자와 언더스코어를 사용합니다: `MAX_COUNT`

## 커밋 메시지 규칙

커밋 메시지는 다음 형식을 따릅니다:

```
[타입]: [제목]

[본문]

1. [변경사항 1]
2. [변경사항 2]
...

관련 이슈: #[이슈번호]
```

타입:
- Feat: 새로운 기능 추가
- Fix: 버그 수정
- Refactor: 코드 리팩토링
- Docs: 문서 수정
- Style: 코드 포맷팅
- Test: 테스트 코드 추가
- Chore: 빌드 작업, 패키지 매니저 설정 등

## 브랜치 사용 가이드라인

### 브랜치 유형
- `main`: 안정적인 프로덕션 코드
- `develop`: 개발 중인 코드의 통합 브랜치
- `feature/[기능명]`: 새로운 기능 개발
- `bugfix/[버그명]`: 버그 수정
- `hotfix/[긴급수정명]`: 프로덕션 환경의 긴급 수정
- `release/[버전]`: 릴리즈 준비
- `documentation/[문서명]`: 문서 작성 또는 수정
- `chore/[작업명]`: 빌드 프로세스, 도구 변경, 의존성 관리 등

### 일반 가이드라인
1. 모든 변경사항은 풀 리퀘스트를 통해 리뷰 후 병합됩니다.
2. 브랜치 이름은 해당 작업을 명확히 설명해야 합니다.

### 브랜치별 상세 가이드라인

#### `feature`, `bugfix`, `documentation`, `chore` 브랜치
- `develop` 브랜치에서 분기합니다.
- 작업 완료 후 `develop`에 병합됩니다.
- 명명 규칙: `feature/user-authentication`, `bugfix/login-error`, `documentation/api-guide`, `chore/update-dependencies`

#### `hotfix` 브랜치
- `main` 브랜치에서 분기합니다.
- 긴급한 프로덕션 이슈를 해결합니다.
- 수정 완료 후 `main`과 `develop` 양쪽에 병합됩니다.
- 명명 규칙: `hotfix/critical-security-patch`

#### `release` 브랜치
- `develop`에서 분기합니다.
- 최종 테스트를 거친 후 `main`과 `develop` 양쪽에 병합됩니다.
- 명명 규칙: `release/v1.2.0`

### 특별 고려사항

#### 문서화 작업
- `documentation/[문서명]` 브랜치를 사용하여 README, API 문서, 사용자 가이드 등을 작성하거나 수정합니다.
- 문서 변경사항도 코드 변경과 마찬가지로 리뷰 프로세스를 거칩니다.
- 문서화 작업이 코드 변경과 관련된 경우, 해당 feature 또는 bugfix 브랜치 내에서 직접 문서를 수정할 수 있습니다.

#### 유지보수 작업 (`chore`)
- 프로젝트의 기능이나 버그와 직접적으로 관련되지 않은 유지보수 작업에 사용합니다.
- 예: 의존성 업데이트, 빌드 스크립트 개선, 코드 포맷팅 규칙 변경 등
- `develop` 브랜치에서 분기하여 작업하고, 완료 후 `develop`에 병합합니다.

이 브랜치 전략을 따름으로써 개발, 테스트, 문서화, 유지보수, 그리고 릴리즈 프로세스를 체계적으로 관리할 수 있습니다.

## 이슈 보고

버그를 발견하거나 새로운 기능을 제안하고 싶다면, GitHub Issues를 사용해 주세요.
이슈 템플릿을 사용하여 필요한 정보를 모두 제공해 주시기 바랍니다.

## 풀 리퀘스트 가이드라인

1. 풀 리퀘스트를 생성하기 전에 관련 이슈가 있는지 확인하고, 없다면 먼저 이슈를 생성해주세요.
2. 풀 리퀘스트 생성 시 제공되는 템플릿을 사용해 주세요. 템플릿은 필요한 정보를 구조화된 방식으로 제공하도록 설계되어 있습니다.
3. 풀 리퀘스트 제목은 명확하고 간결하게 작성합니다.
4. 설명에는 변경사항과 그 이유를 자세히 기술합니다.
5. 관련된 이슈가 있다면 반드시 언급합니다. (예: "Closes #123" 또는 "Related to #456")
6. 코드 리뷰 과정에서 받은 피드백은 성실히 반영합니다.
7. 모든 테스트가 통과되었는지 확인합니다.

풀 리퀘스트 템플릿에 대한 자세한 내용은 `.github/PULL_REQUEST_TEMPLATE.md` 파일을 참조해 주세요.
