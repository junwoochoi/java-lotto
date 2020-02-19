package lotto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또 객체 테스트")
class LotteryTest {

    @Test
    @DisplayName("로또 정상 생성 테스트")
    void create() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 3, 6, 30, 45, 20);

        //when
        final Lottery lottery = Lottery.of(numbers);

        //then
        assertThat(lottery).isNotNull();
    }

    @Test
    @DisplayName("로또 중복값 생성 테스트")
    void duplicateNumber() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 1, 6, 30, 45, 20);


        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Lottery.of(numbers))
                .withMessage("로또의 갯수가 잘못되었거나 중복된 숫자가 있습니다.");
    }

    @Test
    @DisplayName("로또 모자란 숫자 갯수 생성 테스트")
    void checkNumber() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 1, 6, 30, 45, 20);


        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Lottery.of(numbers))
                .withMessage("로또의 갯수가 잘못되었거나 중복된 숫자가 있습니다.");
    }

    @ParameterizedTest
    @MethodSource("lottoCheckTestProvider")
    @DisplayName("로또 당첨 확인")
    void checkIfWin(Lottery lottery, WinningNumbers winningNumbers, int expected) {

        //when
        final Result result = lottery.checkResult(winningNumbers);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getCorrectCount()).isEqualTo(expected);
    }

    static Stream<Arguments> lottoCheckTestProvider() {
        final WinningNumbers winningNumbers = WinningNumbers.of(Lists.newArrayList(1, 3, 6, 7, 12, 45));
        return Stream.of(
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 12, 45)), winningNumbers, 6),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 12, 44)), winningNumbers, 5),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 11, 44)), winningNumbers, 4),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 5, 11, 44)), winningNumbers, 3),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 2, 5, 11, 44)), winningNumbers, 2),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 21, 2, 5, 11, 44)), winningNumbers, 1),
                Arguments.of(Lottery.of(Lists.newArrayList(27, 22, 2, 5, 11, 44)), winningNumbers, 0),
                Arguments.of(Lottery.of(Lists.newArrayList(26, 23, 2, 5, 11, 44)), winningNumbers, 0)
        );

    }
}