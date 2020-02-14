package lotto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningNumbersTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("로또 번호 갯수가 NULL 또는 빈값이 들어왔을 떄 Exception을 던진다")
    void testValidate(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> WinningNumbers.of(numbers));
    }

    @Test
    @DisplayName("로또 번호 갯수와 다른 값이 들어왔을 떄 Exception을 던진다")
    void testValidate() {
        assertThrows(IllegalArgumentException.class, () -> WinningNumbers.of(Lists.newArrayList(4, 5, 6)));
    }


}