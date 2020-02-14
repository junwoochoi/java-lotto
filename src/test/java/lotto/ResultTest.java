package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {


    @Test
    void testCreate() {
        final Result result = Result.of(5);

        assertThat(result).isNotNull();
        assertThat(result.getWonMoney()).isEqualTo(Prizes.getPrizes().get(5));
    }

}