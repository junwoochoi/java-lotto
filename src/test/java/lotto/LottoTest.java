package lotto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또 객체 테스트")
class LottoTest {

    @Test
    @DisplayName("로또 정상 생성 테스트")
    void create() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 3, 6, 30, 45, 20);

        //when
        final Lotto lotto = Lotto.of(numbers);

        //then
        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("로또 중복값 생성 테스트")
    void duplicateNumber() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 1, 6, 30, 45, 20);


        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Lotto.of(numbers))
                .withMessage("로또에 중복된 숫자가 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("로또 모자란 숫자 갯수 생성 테스트")
    void checkNumber() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 1, 6, 30, 45, 20);


        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Lotto.of(numbers))
                .withMessage("로또에 중복된 숫자가 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("lottoCheckTestProvider")
    @DisplayName("로또 당첨 확인")
    void checkIfWin(Lotto lotto, List<Integer> winningNumbers, int expected) {

        //when
        int winningCount = lotto.correctCount(winningNumbers);

        //then
        assertThat(winningCount).isEqualTo(expected);
    }

    static Stream<Arguments> lottoCheckTestProvider() {
        final ArrayList<Integer> winningNumbers = Lists.newArrayList(1, 3, 6, 7, 12, 45);
        return Stream.of(
                Arguments.of(Lotto.of(Lists.newArrayList(1, 3, 6, 7, 12, 45)), winningNumbers, 6),
                Arguments.of(Lotto.of(Lists.newArrayList(1, 3, 6, 7, 12, 44)), winningNumbers, 5),
                Arguments.of(Lotto.of(Lists.newArrayList(1, 3, 6, 7, 11, 44)), winningNumbers, 4),
                Arguments.of(Lotto.of(Lists.newArrayList(1, 3, 6, 5, 11, 44)), winningNumbers, 3),
                Arguments.of(Lotto.of(Lists.newArrayList(1, 3, 2, 5, 11, 44)), winningNumbers, 2),
                Arguments.of(Lotto.of(Lists.newArrayList(1, 21, 2, 5, 11, 44)), winningNumbers, 1),
                Arguments.of(Lotto.of(Lists.newArrayList(27, 22, 2, 5, 11, 44)), winningNumbers, 0),
                Arguments.of(Lotto.of(Lists.newArrayList(26, 23, 2, 5, 11, 44)), winningNumbers, 0)
        )

    }
}