package lotto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void checkIfWin(Lottery lottery, WinningNumbers winningNumbers, Money expected) {

        //when
        final Result result = lottery.checkResult(winningNumbers);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getPrize().getRewardMoney()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 0})
    @DisplayName("로또 번호에 범위가 올바르지 않은 값이 들어왔을 떄 Exception을 던진다")
    void testExceedRangeNumbers(int number) {
        assertThrows(IllegalArgumentException.class, () -> Lottery.of(Lists.newArrayList(number, 3, 3, 4, 5, 6)));
    }

    static Stream<Arguments> lottoCheckTestProvider() {
        final WinningNumbers winningNumbers = WinningNumbers.of(Lists.newArrayList(1, 3, 6, 7, 12, 45), 30);
        return Stream.of(
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 12, 45)), winningNumbers, Prize.FIRST_PRIZE.getRewardMoney()),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 12, 30)), winningNumbers, Prize.SECOND_PRIZE.getRewardMoney()),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 12, 44)), winningNumbers, Prize.THIRD_PRIZE.getRewardMoney()),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 7, 11, 44)), winningNumbers, Prize.FOURTH_PRIZE.getRewardMoney()),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 6, 5, 11, 44)), winningNumbers, Prize.FIFTH_PRIZE.getRewardMoney()),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 3, 2, 5, 11, 44)), winningNumbers, Money.ZERO),
                Arguments.of(Lottery.of(Lists.newArrayList(1, 21, 2, 5, 11, 44)), winningNumbers, Money.ZERO),
                Arguments.of(Lottery.of(Lists.newArrayList(27, 22, 2, 5, 11, 44)), winningNumbers, Money.ZERO),
                Arguments.of(Lottery.of(Lists.newArrayList(26, 23, 2, 5, 11, 44)), winningNumbers, Money.ZERO)
        );

    }
}