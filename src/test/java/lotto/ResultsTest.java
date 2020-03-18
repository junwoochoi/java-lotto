package lotto;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {

    @Test
    void testCreate() {
        //given
        final ArrayList<Integer> lottoNumbers1 = Lists.newArrayList(1, 3, 5, 6, 8, 13);
        final ArrayList<Integer> lottoNumbers2 = Lists.newArrayList(2, 5, 10, 23, 35, 40);
        final ArrayList<Lottery> lotteries = Lists.newArrayList(Lottery.of(lottoNumbers1), Lottery.of(lottoNumbers2));

        WinningNumbers winningNumbers = WinningNumbers.of(Lists.newArrayList(1, 3, 5, 6, 10, 15), 45);

        final Money inputMoney = Money.of(14000);

        //when
        final Results results = ResultsFactory.create(lotteries, winningNumbers, inputMoney);

        //then
        assertThat(results).isNotNull();
        assertThat(results.getYield()).isEqualTo(357);
    }
}