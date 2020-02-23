package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 가게 테스트")
class LotteryShopTest {

    @Test
    void testBuy() {
        //given
        final Money money = Money.of(2000);
        final LottoShop lottoShop = new LottoShop();

        //when
        final List<Lottery> buy = lottoShop.sell(money);

        //then
        assertThat(buy).hasSize(2);
    }

}