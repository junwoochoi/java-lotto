package lotto;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PrizesTest {

    @Test
    void testPrize() {
        final Map<Integer, Money> prizes = Prizes.getPrizes();

        assertThat(prizes).isNotNull();
        assertThat(prizes)
                .containsOnlyKeys(3, 4, 5, 6)
    }
}