package lotto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    @DisplayName("로또 번호에 중복값이 들어왔을 떄 Exception을 던진다")
    void testDuplicateNumbers() {
        assertThrows(IllegalArgumentException.class, () -> WinningNumbers.of(Lists.newArrayList(1, 3, 3, 4, 5, 6)));
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 0})
    @DisplayName("로또 번호에 범위가 올바르지 않은 값이 들어왔을 떄 Exception을 던진다")
    void testExceedRangeNumbers(int number) {
        assertThrows(IllegalArgumentException.class, () -> WinningNumbers.of(Lists.newArrayList(number, 3, 3, 4, 5, 6)));
    }


}