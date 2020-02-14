package lotto;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {

    @Test
    void testCreate() {
        //given
        final ArrayList<Result> resultList = Lists.newArrayList(Result.of(3));
        for (int i = 0; i < 13; i++) {
            resultList.add(Result.of(1));
        }
        final Money inputMoney = Money.of(14000);

        //when
        final Results results = Results.of(resultList, inputMoney);

        //then
        assertThat(results).isNotNull();
        assertThat(results.getYield()).isEqualTo(35);
    }
}