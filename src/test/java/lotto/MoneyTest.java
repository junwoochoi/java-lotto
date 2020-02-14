package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Money 테스트")
class MoneyTest {

    @ParameterizedTest
    @ValueSource(longs = {1000, 2000})
    @DisplayName("금액 생성 테스트")
    void testCreateMoney(long amount) {
        //when
        final Money money = Money.of(amount);

        //then
        assertThat(money).isNotNull();
    }


    @ParameterizedTest
    @ValueSource(longs = {-1000, -15, -1})
    @DisplayName("금액 생성 테스트")
    void failCreateMoney(long amount) {
        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> {
                    Money.of(amount);
                });
    }


    @ParameterizedTest
    @CsvSource({"1000,1", "2000, 2", "4500,4"})
    @DisplayName("로또 구입 가능 갯수 테스트")
    void testAvailableCount(long moneyAmount, int expected) {
        //given
        Money money = Money.of(moneyAmount);

        //when
        final int availableLottoCount = money.availableLottoCount();

        //then
        assertThat(availableLottoCount).isEqualTo(expected);
    }

    @Test
    @DisplayName("한국 원 String으로 변환")
    void testWonString() {
        //given
        Money money = Money.of(1000);

        //when
        final String wonString = money.wonString();

        //then
        assertThat(wonString).isEqualTo("1000원");
    }

    @Test
    void testSum() {
        //given
        Money money1 = Money.of(1000);
        Money money2 = Money.of(3000);

        //when
        final Money sum = Money.sum(money1, money2);

        //then
        assertThat(sum).isNotNull();
        assertThat(sum.wonString()).isEqualTo("4000원");
        assertThat(sum.availableLottoCount()).isEqualTo(4);
    }

    @Test
    void testMultiply() {
        //given
        Money money = Money.of(1000);

        //when
        final Money sum = Money.multiply(money, 5);

        //then
        assertThat(sum).isNotNull();
        assertThat(sum.wonString()).isEqualTo("5000원");
        assertThat(sum.availableLottoCount()).isEqualTo(5);
    }

    @Test
    void testCalculateYield() {
        //given
        Money money1 = Money.of(3000);
        Money money2 = Money.of(1000);

        //when
        final int yield = Money.calculateYield(money1, money2);

        //then
        assertThat(yield).isEqualTo(300);
    }
}