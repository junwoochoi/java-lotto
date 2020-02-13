package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
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
    @ValueSource(longs = {-1000, 0, 990})
    @DisplayName("금액 생성 테스트")
    void failCreateMoney(long amount) {
        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Money.of(amount));
    }


}