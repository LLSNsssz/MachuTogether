package com.guessthesong.machutogether.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

/**
 * UserRegistrationDto 클래스의 유효성 검사 로직을 테스트합니다.
 * 이 테스트 클래스는 Bean Validation API를 사용하여 DTO의 필드 제약 조건을 검증합니다.
 * <p>
 * Bean Validation API는 Java EE와 Java SE의 일부로, 객체의 제약 조건을 쉽게 정의하고 검증할 수 있게 해줍니다.
 * 이 API를 사용하면 어노테이션을 통해 객체의 필드나 메서드에 제약 조건을 지정할 수 있으며,
 * 런타임에 이러한 제약 조건의 준수 여부를 확인할 수 있습니다.
 * </p>
 * <p>
 * 이 테스트 클래스에서는 {@link Validator} 인터페이스를 사용하여 UserRegistrationDto 객체의 유효성을 검사합니다.
 * Validator는 객체의 모든 제약 조건을 검사하고, 위반 사항이 있으면 ConstraintViolation 객체의 집합을 반환합니다.
 * </p>
 */
class UserRegistrationDtoTest {

    /**
     * Bean Validation API의 Validator를 생성하기 위한 팩토리입니다.
     * ValidatorFactory는 Validator 인스턴스를 생성하는 역할을 합니다.
     * <p>
     * Validation.buildDefaultValidatorFactory() 메서드는 클래스패스에서 사용 가능한
     * Bean Validation 구현체(예: Hibernate Validator)를 찾아 ValidatorFactory를 생성합니다.
     * </p>
     */
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * DTO 객체의 유효성을 검사하는 Validator 인스턴스입니다.
     * <p>
     * Validator는 Bean Validation API의 핵심 인터페이스로, 객체의 제약 조건을 검증하는 메서드를 제공합니다.
     * 주로 사용되는 메서드는 validate()로, 이 메서드는 주어진 객체의 모든 제약 조건을 검사하고
     * 위반 사항이 있으면 ConstraintViolation 객체의 집합을 반환합니다.
     * </p>
     * <p>
     * 이 테스트 클래스에서는 Validator를 사용하여 UserRegistrationDto 객체의 각 필드가
     * 정의된 제약 조건(@NotBlank, @Size, @Email 등)을 준수하는지 확인합니다.
     * </p>
     */
    private final Validator validator = validatorFactory.getValidator();

    /**
     * 모든 필드가 유효한 UserRegistrationDto 객체를 검증합니다.
     * 이 테스트는 어떠한 제약 조건 위반도 없어야 합니다.
     *
     * <p>테스트 과정:
     * 1. 모든 필드가 유효한 값으로 설정된 UserRegistrationDto 객체를 생성합니다.
     * 2. validator.validate() 메서드를 사용하여 객체의 유효성을 검사합니다.
     * 3. 반환된 위반 사항 집합이 비어 있는지 확인합니다.</p>
     *
     * <p>예상 결과: 위반 사항이 없어야 합니다.</p>
     */
    @Test
    void testValidUserRegistrationDto() {
        UserRegistrationDto dto = new UserRegistrationDto(
            "testuser",
            "Test User",
            "test@example.com",
            "Password123!",
            "010-1234-5678"
        );

        var violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    /**
     * 유효하지 않은 사용자 이름(6자 미만)을 가진 UserRegistrationDto 객체를 검증합니다.
     * 이 테스트는 사용자 이름 길이 제약 조건 위반을 확인해야 합니다.
     *
     * <p>테스트 과정:
     * 1. 사용자 이름이 6자 미만인 UserRegistrationDto 객체를 생성합니다.
     * 2. validator.validate() 메서드를 사용하여 객체의 유효성을 검사합니다.
     * 3. 정확히 하나의 제약 조건 위반이 있는지 확인합니다.
     * 4. 위반 메시지가 사용자 이름 길이 관련 내용을 포함하는지 확인합니다.</p>
     *
     * <p>예상 결과: 하나의 위반 사항이 있어야 하며, 해당 위반 사항은 사용자 이름 길이 관련 메시지를 포함해야 합니다.</p>
     */
    @Test
    void testInvalidUsername() {
        UserRegistrationDto dto = new UserRegistrationDto(
            "test", // 6자 미만으로 설정
            "Test User",
            "test@example.com",
            "Password123!",
            "010-1234-5678"
        );

        var violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).contains("사용자 이름은 6자에서 20자 사이여야 합니다.");
    }

    /**
     * 유효하지 않은 이메일 형식을 가진 UserRegistrationDto 객체를 검증합니다.
     * 이 테스트는 이메일 형식 제약 조건 위반을 확인해야 합니다.
     *
     * <p>테스트 과정:
     * 1. 유효하지 않은 이메일 형식을 가진 UserRegistrationDto 객체를 생성합니다.
     * 2. validator.validate() 메서드를 사용하여 객체의 유효성을 검사합니다.
     * 3. 정확히 하나의 제약 조건 위반이 있는지 확인합니다.
     * 4. 위반 메시지가 이메일 형식 관련 내용을 포함하는지 확인합니다.</p>
     *
     * <p>예상 결과: 하나의 위반 사항이 있어야 하며, 해당 위반 사항은 이메일 형식 관련 메시지를 포함해야 합니다.</p>
     */
    @Test
    void testInvalidEmail() {
        UserRegistrationDto dto = new UserRegistrationDto(
            "testuser",
            "Test User",
            "invalid-email", // 올바르지 않은 이메일 형식
            "Password123!",
            "010-1234-5678"
        );

        var violations = validator.validate(dto);
        assertThat(violations).hasSize(2);
        assertThat(violations.iterator().next().getMessage()).contains("올바른 이메일 주소를 입력해주세요.");
    }


    /**
     * 유효하지 않은 비밀번호 형식을 가진 UserRegistrationDto 객체를 검증합니다.
     * 이 테스트는 비밀번호 형식 제약 조건 위반을 확인해야 합니다.
     *
     * <p>테스트 과정:
     * 1. 유효하지 않은 비밀번호 형식(문자와 숫자 또는 특수문자 조합 미충족)을 가진 UserRegistrationDto 객체를 생성합니다.
     * 2. validator.validate() 메서드를 사용하여 객체의 유효성을 검사합니다.
     * 3. 정확히 하나의 제약 조건 위반이 있는지 확인합니다.
     * 4. 위반 메시지가 비밀번호 형식 관련 내용을 포함하는지 확인합니다.</p>
     *
     * <p>예상 결과: 하나의 위반 사항이 있어야 하며, 해당 위반 사항은 비밀번호 형식 관련 메시지를 포함해야 합니다.</p>
     */
    @Test
    void testInvalidPassword() {
        UserRegistrationDto dto = new UserRegistrationDto(
            "testuser",
            "Test User",
            "test@example.com",
            "password", // 숫자나 특수문자가 없는 비밀번호
            "010-1234-5678"
        );

        var violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).contains("비밀번호는 문자, 숫자, 특수문자 중 2종류 이상을 조합해야 합니다.");
    }

    /**
     * 유효하지 않은 전화번호 형식을 가진 UserRegistrationDto 객체를 검증합니다.
     * 이 테스트는 전화번호 형식 제약 조건 위반을 확인해야 합니다.
     *
     * <p>테스트 과정:
     * 1. 유효하지 않은 전화번호 형식을 가진 UserRegistrationDto 객체를 생성합니다.
     * 2. validator.validate() 메서드를 사용하여 객체의 유효성을 검사합니다.
     * 3. 정확히 하나의 제약 조건 위반이 있는지 확인합니다.
     * 4. 위반 메시지가 전화번호 형식 관련 내용을 포함하는지 확인합니다.</p>
     *
     * <p>예상 결과: 하나의 위반 사항이 있어야 하며, 해당 위반 사항은 전화번호 형식 관련 메시지를 포함해야 합니다.</p>
     */
    @Test
    void testInvalidPhoneNumber() {
        UserRegistrationDto dto = new UserRegistrationDto(
            "testuser",
            "Test User",
            "test@example.com",
            "Password123!",
            "1234567890" // 올바르지 않은 전화번호 형식
        );

        var violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).contains("올바른 전화번호 형식이 아닙니다.");
    }

}