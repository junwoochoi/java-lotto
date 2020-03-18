package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LottoNo 테스")
class LottoNoTest {

    @ParameterizedTest
    @DisplayName("허용되지 않는 숫자가 들어올 시 Exception을 발생시킨다.")
    @ValueSource(ints = {-1, 0, 50})
    void testValidate(int number) {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNo.of(number));
    }
}